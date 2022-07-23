/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.LiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCargoDaoImpl;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;

import com.areatecnica.sigf.models.RecaudacionLiquidacionEmpresaDataModel;
import com.areatecnica.sigf.util.LocalDateConverter;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author ianfr
 */
@Named(value = "registroLiquidacionEmpresaController")
@ViewScoped
public class RegistroLiquidacionEmpresa implements Serializable {

    private Empresa empresa;
    private CargoLiquidacion cargoLiquidacion;
    private AbonoLiquidacion abonoLiquidacion;
    private LiquidacionEmpresa liquidacionEmpresa;

    private RecaudacionLiquidacionEmpresaDataModel model;

    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalRecaudacion = 0;
    private int totalMinutos = 0;
    private int totalAbonos = 0;
    private int totalCargos = 0;
    private int saldo = 0;
    private int totalIngresos = 0;
    private int totalEgresos = 0;

    private String descripcionAbono;
    private int montoAbono;
    private String descripcionCargo;
    private int montoCargo;

    private LocalDate date;
    private LocalDateConverter dc;
    private static final String informe = "inf-liquidacion_empresa_general";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private NumberFormat nf = NumberFormat.getInstance();

//    private List<CargoLiquidacion> cargoLiquidacionItems;
//    private List<AbonoLiquidacion> abonoLiquidacionItems;
    private List<Empresa> empresaItems; 
    
    private List<TipoCargo> tipoCargoItems;
    private List<TipoAbono> tipoAbonoItems;
    private TipoCargo tipoCargo;
    private TipoAbono tipoAbono;

    private AbonoLiquidacion selectedAbono;
    private CargoLiquidacion selectedCargo;

    private boolean caratula = Boolean.TRUE;
    private boolean ingresosbus = Boolean.TRUE;
    private boolean minutos = Boolean.TRUE;
    private boolean boletos = Boolean.TRUE;

    /**
     * Creates a new instance of RegistroLiquidacionEmpresa
     */
    public RegistroLiquidacionEmpresa() {
    }

    @PostConstruct
    public void init() {
        this.empresaItems = new EmpresaDaoImpl().findByNandu();
        this.setDate(LocalDate.now());
    }

    public void load() {
        if (this.empresa != null) {
            this.liquidacionEmpresa = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(empresa, dc.getFirstDateOfMonth());

            if (this.liquidacionEmpresa == null) {
                this.liquidacionEmpresa = new LiquidacionEmpresa();
                this.liquidacionEmpresa.setLiquidacionEmpresaIdEmpresa(empresa);
                this.liquidacionEmpresa.setLiquidacionEmpresaFechaLiquidacion(dc.getFirstDateOfMonth());
                this.liquidacionEmpresa.setLiquidacionEmpresaFechaPago(this.dc.getLastDayOfMonth());
                this.liquidacionEmpresa.setAbonoLiquidacionList(new ArrayList<>());
                this.liquidacionEmpresa.setCargoLiquidacionList(new ArrayList<>());
                JsfUtil.addWarningMessage("Se inicia la Liquidación para la empresa " + this.liquidacionEmpresa.getLiquidacionEmpresaIdEmpresa().getEmpresaNombre());
            } else {
                JsfUtil.addSuccessMessage("Existe Liquidación para la empresa " + this.liquidacionEmpresa.getLiquidacionEmpresaIdEmpresa().getEmpresaNombre());
            }

            this.abonoLiquidacion = new AbonoLiquidacion(liquidacionEmpresa);
            this.cargoLiquidacion = new CargoLiquidacion(liquidacionEmpresa);

            this.tipoAbono = new TipoAbono();
            this.tipoCargo = new TipoCargo();

            this.tipoAbonoItems = new TipoAbonoDaoImpl().findAll();
            this.tipoCargoItems = new TipoCargoDaoImpl().findAll();

            this.totalAdministracion = new RecaudacionGuiaDaoImpl().findByEgreso(empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 1);
            this.totalMinutos = 0;
            this.totalAbonos = 0;
            this.totalCargos = 0;
            this.totalCuotaExtra = new RecaudacionGuiaDaoImpl().findByEgreso(empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 2);
            this.totalBoletos = new RecaudacionGuiaDaoImpl().findByEgreso(empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 4);
            this.totalImposiciones = new RecaudacionGuiaDaoImpl().findByEgreso(empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth(), 3);

            //Cálculo ahorro boletos
            if (this.totalBoletos > 0) {
                int cantidad = totalBoletos / 5000;
                int costoBoleto = cantidad * 800;
                this.totalBoletos = this.totalBoletos - costoBoleto;
            }

//            this.cargoLiquidacionItems = new CargoLiquidacionDaoImpl().findByEmpresaBetweenDates(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());
            for (CargoLiquidacion c : this.liquidacionEmpresa.getCargoLiquidacionList()) {
                this.totalCargos = this.totalCargos + c.getCargoLiquidacionMonto();

                if (this.tipoCargoItems.contains(c.getCargoLiquidacionCargoId())) {
                    this.tipoCargoItems.remove(c.getCargoLiquidacionCargoId());
                }
            }            

//            this.abonoLiquidacionItems = new AbonoLiquidacionDaoImpl().findByEmpresaBetweenDates(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());
            for (AbonoLiquidacion c : this.liquidacionEmpresa.getAbonoLiquidacionList()) {
                this.totalAbonos = this.totalAbonos + c.getAbonoLiquidacionMonto();

                if (this.tipoAbonoItems.contains(c.getAbonoLiquidacionTipoId())) {
                    this.tipoAbonoItems.remove(c.getAbonoLiquidacionTipoId());
                }
            }

            //Minutos
            this.totalMinutos = new RecaudacionMinutoDaoImpl().findMinutosRecibidos(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

            this.totalRecaudacion = this.totalAdministracion + this.totalCuotaExtra + this.totalImposiciones + this.totalBoletos + this.totalMinutos;

            this.totalIngresos = this.totalRecaudacion + this.totalAbonos;
            this.totalEgresos = this.totalCargos;

            setSaldo();

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar una empresa");
        }
    }

    public Map<String, Object> getSingleMap() {
        Map<String, Object> map = new HashMap();

        if (this.empresa != null) {

            map.put("fechaCompleta", this.dc.getMonthYearString());

            map.put("desde", this.dc.getFirstDateOfMonth());//
            map.put("hasta", this.dc.getLastDayOfMonth());//
            map.put("empresa", this.empresa.getEmpresaNombre().toUpperCase());
            map.put("codigo", this.empresa.getEmpresaId());

            map.put("caratula", caratula);
            map.put("boletos", boletos);
            map.put("minutos", minutos);
            map.put("ingresosbus", ingresosbus);

            return map;
        }

        return null;
    }

    public void onCellEditAbono(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            this.selectedAbono = this.liquidacionEmpresa.getAbonoLiquidacionList().get(event.getRowIndex());

            AbonoLiquidacion c = new AbonoLiquidacionDaoImpl().update(this.selectedAbono);

            if (c != null) {
                JsfUtil.addSuccessMessage("Se editó el valor del abono #" + c.getAbonoLiquidacionId());
                this.totalAbonos = this.totalAbonos - (Integer) oldValue;
                this.totalAbonos += c.getAbonoLiquidacionMonto();
                setSaldo();
            }
        }
    }

    public void onCellEditCargo(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            this.selectedCargo = this.liquidacionEmpresa.getCargoLiquidacionList().get(event.getRowIndex());

            CargoLiquidacion c = new CargoLiquidacionDaoImpl().update(this.selectedCargo);

            if (c != null) {
                JsfUtil.addSuccessMessage("Se editó el valor del cargo #" + c.getCargoLiquidacionId());
                this.totalCargos = this.totalCargos - (Integer) oldValue;
                this.totalCargos += c.getCargoLiquidacionMonto();
                setSaldo();
            }
        }
    }

    public void deleteAbono() {
        if (this.selectedAbono != null) {

        }
    }

    public void saveNewAbono() {
        if (this.abonoLiquidacion != null) {
            if (this.montoAbono > 0) {
                if (this.tipoAbono != null) {
                    this.abonoLiquidacion.setAbonoLiquidacionMonto(this.montoAbono);
                    this.abonoLiquidacion.setAbonoLiquidacionDescripcion(this.descripcionAbono);
                    this.abonoLiquidacion.setAbonoLiquidacionTipoId(this.tipoAbono);

                    this.liquidacionEmpresa.getAbonoLiquidacionList().add(abonoLiquidacion);

                    LiquidacionEmpresa a = new LiquidacionEmpresaDaoImpl().update(liquidacionEmpresa);
                    if (a != null) {
                        JsfUtil.addSuccessMessage("Se ha registrado el abono");
                        this.totalAbonos += this.abonoLiquidacion.getAbonoLiquidacionMonto();
                        
                        setSaldo();

                        this.montoAbono = 0;
                        this.descripcionAbono = "";
                        this.tipoAbonoItems.remove(this.tipoAbono);
                        this.tipoAbono = null;

                        this.liquidacionEmpresa = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(empresa, dc.getFirstDateOfMonth());

                        if (this.liquidacionEmpresa != null) {
                            JsfUtil.addSuccessMessage("Liquidación actualizada");
                            this.cargoLiquidacion = new CargoLiquidacion(this.liquidacionEmpresa);
                            this.abonoLiquidacion = new AbonoLiquidacion(this.liquidacionEmpresa);
                        }

                    } else {
                        JsfUtil.addErrorMessage("Error al guardar el abono");
                    }
                } else {
                    JsfUtil.addErrorMessage("Debe seleccionar un tipo de abono");
                }
            } else {
                JsfUtil.addErrorMessage("El monto del abono debe ser mayor que 0");
            }
        }
    }

    public void saveNewCargo() {
        if (this.cargoLiquidacion != null) {
            if (this.montoCargo > 0) {
                if (this.tipoCargo != null) {
                    this.cargoLiquidacion.setCargoLiquidacionCargoId(this.tipoCargo);
                    this.cargoLiquidacion.setCargoLiquidacionDescripcion(this.descripcionCargo);
                    this.cargoLiquidacion.setCargoLiquidacionMonto(this.montoCargo);

                    this.liquidacionEmpresa.getCargoLiquidacionList().add(cargoLiquidacion);

                    LiquidacionEmpresa a = new LiquidacionEmpresaDaoImpl().update(liquidacionEmpresa);

                    if (a != null) {
                        JsfUtil.addSuccessMessage("Se ha registrado el cargo");
                        this.totalCargos += this.cargoLiquidacion.getCargoLiquidacionMonto();

                        setSaldo();

                        this.montoCargo = 0;
                        this.descripcionCargo = "";
                        this.tipoCargoItems.remove(this.tipoCargo);
                        this.tipoCargo = null;

                        this.liquidacionEmpresa = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(empresa, dc.getFirstDateOfMonth());

                        if (this.liquidacionEmpresa != null) {
                            JsfUtil.addSuccessMessage("Liquidación actualizada");
                            this.cargoLiquidacion = new CargoLiquidacion(this.liquidacionEmpresa);
                            this.abonoLiquidacion = new AbonoLiquidacion(this.liquidacionEmpresa);
                        }

                    } else {
                        JsfUtil.addErrorMessage("Error al guardar el cargo");
                    }
                } else {
                    JsfUtil.addErrorMessage("Debe seleccionar un tipo de cargo");
                }
            } else {
                JsfUtil.addErrorMessage("El monto del cargo debe ser mayor que 0");
            }
        }
    }

    public void setSaldo() {
        this.totalIngresos = this.totalRecaudacion + this.totalAbonos;
        this.totalEgresos = this.totalCargos;
        this.saldo = this.totalIngresos - this.totalEgresos;
    }

    public void handleTipoAbonoChange() {
        if (this.tipoAbono != null) {
            this.montoAbono = (this.tipoAbono.getTipoAbonoMontoDefecto());
        } else {
            JsfUtil.addErrorMessage("El abono es nulo");
        }
    }

    public void handleTipoCargoChange() {
        if (this.tipoCargo != null) {
            this.montoCargo = (this.tipoCargo.getTipoCargoMontoDefecto());
            JsfUtil.addSuccessMessage("Cargo:" + this.tipoCargo.getTipoCargoMontoDefecto());
        } else {
            JsfUtil.addErrorMessage("El cargo es nulo");
        }
    }

    public void setTipoCargoItems(List<TipoCargo> tipoCargoItems) {
        this.tipoCargoItems = tipoCargoItems;
    }

    public List<TipoCargo> getTipoCargoItems() {
        return tipoCargoItems;
    }

    public void setTipoAbonoItems(List<TipoAbono> tipoAbonoItems) {
        this.tipoAbonoItems = tipoAbonoItems;
    }

    public List<TipoAbono> getTipoAbonoItems() {
        return tipoAbonoItems;
    }

    public RecaudacionLiquidacionEmpresaDataModel getModel() {
        return model;
    }

    public void setModel(RecaudacionLiquidacionEmpresaDataModel model) {
        this.model = model;
    }

    public void setTotalEgresos(int totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public int getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalIngresos(int totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public int getTotalIngresos() {
        return totalIngresos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setTipoAbono(TipoAbono tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    public TipoAbono getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoCargo(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }

    public CargoLiquidacion getCargoLiquidacion() {
        return cargoLiquidacion;
    }

    public void setCargoLiquidacion(CargoLiquidacion cargoLiquidacion) {
        this.cargoLiquidacion = cargoLiquidacion;
    }

    public AbonoLiquidacion getAbonoLiquidacion() {
        return abonoLiquidacion;
    }

    public void setAbonoLiquidacion(AbonoLiquidacion abonoLiquidacion) {
        this.abonoLiquidacion = abonoLiquidacion;
    }

    public LiquidacionEmpresa getLiquidacionEmpresa() {
        return liquidacionEmpresa;
    }

    public void setLiquidacionEmpresa(LiquidacionEmpresa liquidacionEmpresa) {
        this.liquidacionEmpresa = liquidacionEmpresa;
    }

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public void setTotalAdministracion(int totalAdministracion) {
        this.totalAdministracion = totalAdministracion;
    }

    public int getTotalCuotaExtra() {
        return totalCuotaExtra;
    }

    public void setTotalCuotaExtra(int totalCuotaExtra) {
        this.totalCuotaExtra = totalCuotaExtra;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public void setTotalBoletos(int totalBoletos) {
        this.totalBoletos = totalBoletos;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public void setTotalImposiciones(int totalImposiciones) {
        this.totalImposiciones = totalImposiciones;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(int totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public int getTotalCargos() {
        return totalCargos;
    }

    public void setTotalCargos(int totalCargos) {
        this.totalCargos = totalCargos;
    }

    public void setSelectedCargo(CargoLiquidacion selectedCargo) {
        this.selectedCargo = selectedCargo;
    }

    public void setSelectedAbono(AbonoLiquidacion selectedAbono) {
        this.selectedAbono = selectedAbono;
    }

    public CargoLiquidacion getSelectedCargo() {
        return selectedCargo;
    }

    public AbonoLiquidacion getSelectedAbono() {
        return selectedAbono;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getInforme() {
        return informe;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public String getDescripcionAbono() {
        return descripcionAbono;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public void setDescripcionAbono(String descripcionAbono) {
        this.descripcionAbono = descripcionAbono;
    }

    public void setMontoCargo(int montoCargo) {
        this.montoCargo = montoCargo;
    }

    public void setMontoAbono(int montoAbono) {
        this.montoAbono = montoAbono;
    }

    public int getMontoCargo() {
        return montoCargo;
    }

    public int getMontoAbono() {
        return montoAbono;
    }

    public boolean getIngresosbus() {
        return ingresosbus;
    }

    public void setIngresosbus(boolean ingresosbus) {
        this.ingresosbus = ingresosbus;
    }

    public boolean getMinutos() {
        return minutos;
    }

    public void setMinutos(boolean minutos) {
        this.minutos = minutos;
    }

    public boolean getBoletos() {
        return boletos;
    }

    public void setBoletos(boolean boletos) {
        this.boletos = boletos;
    }

    public boolean getCaratula() {
        return caratula;
    }

    public void setCaratula(boolean caratula) {
        this.caratula = caratula;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

}
