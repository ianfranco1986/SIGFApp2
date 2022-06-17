/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.AbonoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoLiquidacionDaoImpl;
import com.areatecnica.sigf.dao.impl.LiquidacionEmpresaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.RecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.TipoCargoDaoImpl;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.models.AbonoLiquidacionDataModel;
import com.areatecnica.sigf.models.CargoLiquidacionDataModel;

import com.areatecnica.sigf.models.RecaudacionLiquidacionEmpresaDataModel;
import com.areatecnica.sigf.util.LocalDateConverter;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private String informe;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private NumberFormat nf = NumberFormat.getInstance();

    private List<CargoLiquidacion> cargoLiquidacionItems;
    private List<AbonoLiquidacion> abonoLiquidacionItems;
    private List<RecaudacionMinuto> minutosItems;
    private List<CargoLiquidacion> cargoBusItems;
    private CargoLiquidacionDataModel cargoDataModel;
    private AbonoLiquidacionDataModel abonoDataModel;

    private List<TipoCargo> tipoCargoItems;
    private List<TipoAbono> tipoAbonoItems;
    private TipoCargo tipoCargo;
    private TipoAbono tipoAbono;

    /**
     * Creates a new instance of RegistroLiquidacionEmpresa
     */
    public RegistroLiquidacionEmpresa() {
    }

    @PostConstruct
    public void init() {

        Calendar calendar = Calendar.getInstance();

        this.informe = "inf-detalle_ingresos_bus_empty";

        this.setDate(LocalDate.now());

    }

    public void load() {
        if (this.empresa != null) {
            this.liquidacionEmpresa = new LiquidacionEmpresaDaoImpl().findByEmpresaFechaLiquidacion(empresa, dc.getDate());

            if (this.liquidacionEmpresa == null) {
                this.liquidacionEmpresa = new LiquidacionEmpresa();
                this.liquidacionEmpresa.setLiquidacionEmpresaIdEmpresa(empresa);
                this.liquidacionEmpresa.setLiquidacionEmpresaFechaLiquidacion(dc.getFirstDateOfMonth());
                this.liquidacionEmpresa.setLiquidacionEmpresaFechaPago(this.dc.getLastDayOfMonth());
            } else {
                JsfUtil.addSuccessMessage("Empresa: " + this.liquidacionEmpresa.getLiquidacionEmpresaIdEmpresa().getEmpresaNombre());
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

            this.cargoLiquidacionItems = new CargoLiquidacionDaoImpl().findByEmpresaBetweenDates(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

            for (CargoLiquidacion c : this.cargoLiquidacionItems) {
                this.totalCargos = this.totalCargos + c.getCargoLiquidacionMonto();

                if (this.tipoCargoItems.contains(c.getCargoLiquidacionCargoId())) {
                    this.tipoCargoItems.remove(c.getCargoLiquidacionCargoId());
                }
            }

            this.abonoLiquidacionItems = new AbonoLiquidacionDaoImpl().findByEmpresaBetweenDates(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

            for (AbonoLiquidacion c : this.abonoLiquidacionItems) {
                this.totalAbonos = this.totalAbonos + c.getAbonoLiquidacionMonto();

                if (this.tipoAbonoItems.contains(c.getAbonoLiquidacionTipoId())) {
                    this.tipoAbonoItems.remove(c.getAbonoLiquidacionTipoId());
                }
            }

            this.cargoDataModel = new CargoLiquidacionDataModel(cargoBusItems);
            this.abonoDataModel = new AbonoLiquidacionDataModel(abonoLiquidacionItems);

            //Minutos
            this.totalMinutos = new RecaudacionMinutoDaoImpl().findMinutosRecibidos(this.empresa, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());

            this.totalRecaudacion = this.totalAdministracion + this.totalCuotaExtra + this.totalImposiciones + this.totalBoletos + this.totalMinutos;

            this.totalIngresos = this.totalRecaudacion + this.totalAbonos;
            this.totalEgresos = this.totalCargos;

            this.saldo = (this.totalRecaudacion + this.totalAbonos) - this.totalCargos;

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar una empresa");
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void saveNewAbono() {
        if (this.abonoLiquidacion != null) {
            if (this.montoAbono > 0) {
                if (this.tipoAbono != null) {
                    this.abonoLiquidacion.setAbonoLiquidacionMonto(this.montoAbono);
                    this.abonoLiquidacion.setAbonoLiquidacionDescripcion(this.descripcionAbono);
                    this.abonoLiquidacion.setAbonoLiquidacionTipoId(this.tipoAbono);

                    AbonoLiquidacion a = new AbonoLiquidacionDaoImpl().create(this.abonoLiquidacion);
                    if (a != null) {
                        JsfUtil.addSuccessMessage("Se ha registrado el abono");
                        this.abonoLiquidacionItems.add(a);

                        this.abonoLiquidacion = new AbonoLiquidacion(this.liquidacionEmpresa);
                        this.totalAbonos += a.getAbonoLiquidacionMonto();
                        setSaldo();
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

                    CargoLiquidacion a = new CargoLiquidacionDaoImpl().create(this.cargoLiquidacion);
                    if (a != null) {
                        JsfUtil.addSuccessMessage("Se ha registrado el cargo");

                        this.cargoLiquidacionItems.add(a);

                        this.cargoLiquidacion = new CargoLiquidacion(this.liquidacionEmpresa);
                        this.totalCargos += a.getCargoLiquidacionMonto();
                        setSaldo();
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
        this.saldo = (this.totalAbonos - this.totalCargos);
    }

    public void handleTipoAbonoChange() {
        if (this.tipoAbono != null) {
            this.abonoLiquidacion.setAbonoLiquidacionMonto(this.tipoAbono.getTipoAbonoMontoDefecto());
        } else {
            JsfUtil.addErrorMessage("El abono es nulo");
        }
    }

    public void handleTipoCargoChange() {
        if (this.tipoCargo != null) {
            this.cargoLiquidacion.setCargoLiquidacionMonto(this.tipoCargo.getTipoCargoMontoDefecto());
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

    public List<AbonoLiquidacion> getAbonoLiquidacionItems() {
        return abonoLiquidacionItems;
    }

    public void setAbonoLiquidacionItems(List<AbonoLiquidacion> abonoLiquidacionItems) {
        this.abonoLiquidacionItems = abonoLiquidacionItems;
    }

    public List<CargoLiquidacion> getCargoLiquidacionItems() {
        return cargoLiquidacionItems;
    }

    public void setCargoLiquidacionItems(List<CargoLiquidacion> cargoLiquidacionItems) {
        this.cargoLiquidacionItems = cargoLiquidacionItems;
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        RegistroLiquidacionEmpresa.sdf = sdf;
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

}
