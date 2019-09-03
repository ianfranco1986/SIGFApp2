/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IAbonoBusDao;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.ICargoBusDao;
import com.areatecnica.sigf.dao.impl.AbonoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.CargoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionMinutoDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoCargoDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.AbonoBusDataModel;
import com.areatecnica.sigf.models.CargoBusDataModel;
import com.areatecnica.sigf.models.RecaudacionLiquidacionDataModel;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.joda.time.DateTime;

/**
 *
 * @author ianfr
 */
@Named(value = "registroLiquidacionBusController")
@ViewScoped
public class RegistroLiquidacionBusController implements Serializable {

    private static final long serialVersionUID = 1L;

    private UnidadNegocio unidadNegocio;
    private Bus bus;
    private Recaudacion recaudacion;
    private List<CargoBus> cargoBusItems;
    private List<AbonoBus> abonoBusItems;
    private List<RecaudacionGuia> recaudacionItems;
    private List<UnidadNegocio> unidadItems;
    private List<Bus> busItems;
    private List<TipoCargo> tipoCargoItems;
    private List<TipoAbono> tipoAbonoItems;
    private RecaudacionLiquidacionDataModel model;
    private List<RecaudacionMinuto> minutosItems;

    private List<Recaudacion> items;
    private List<RecaudacionGuiaHelper> itemsRecaudacion;

    private CargoBus cargoBus;
    private AbonoBus abonoBus;
    private CargoBusDataModel cargoDataModel;
    private AbonoBusDataModel abonoDataModel;
    private ICargoBusDao cargoBusDao;
    private IAbonoBusDao abonoBusDao;
    private IBusDao busDao;

    private CurrentDate currentDate;
    private Date fecha;
    private int mes;
    private int anio;

    private Boolean cuotasCargo;
    private Boolean cuotasAbono;
    private DateTime dateTime;

    private String informe;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    private int totalAdministracion = 0;
    private int totalCuotaExtra = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalRecaudacion = 0;
    private int totalMinutos = 0;
    private int totalAbonos = 0;
    private int totalCargos = 0;
    private int saldo = 0;
    
    private Date desde; 
    private Date hasta; 

    /**
     * Creates a new instance of RegistroLiquidacionBusController
     */
    public RegistroLiquidacionBusController() {
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.currentDate = new CurrentDate();

        Calendar calendar = Calendar.getInstance();

        this.anio = calendar.get(Calendar.YEAR);
        this.mes = calendar.get(Calendar.MONTH) + 1;

        this.informe = "inf-detalle_ingresos_bus_empty";

        this.unidadItems = new IUnidadNegocioDaoImpl().findNandu();
        System.err.println("primera fecha: " + this.fecha);
        setFecha();
        this.desde = this.fecha;
        this.hasta = this.fecha;
    }
    
    public void setFecha() {
        try {

            this.fecha = this.sdf.parse(this.anio + "/" + this.mes + "/01");
        } catch (ParseException ex) {
        }
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        return informe;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        System.err.println("DESDE:"+desde);
        System.err.println("HATAS:"+hasta);
        map.put("desde", desde);
        map.put("hasta", hasta);
        map.put("bus", this.bus.getBusId());

        return map;
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

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public int getTotalMinutos() {
        return totalMinutos;
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

    public void setModel(RecaudacionLiquidacionDataModel model) {
        this.model = model;
    }

    public RecaudacionLiquidacionDataModel getModel() {
        return model;
    }

    public void setUnidadItems(List<UnidadNegocio> unidadItems) {
        this.unidadItems = unidadItems;
    }

    public List<UnidadNegocio> getUnidadItems() {
        return unidadItems;
    }

    public Recaudacion getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<CargoBus> getCargoBusItems() {
        return cargoBusItems;
    }

    public void setCargoBusItems(List<CargoBus> cargoBusItems) {
        this.cargoBusItems = cargoBusItems;
    }

    public List<AbonoBus> getAbonoBusItems() {
        return abonoBusItems;
    }

    public void setAbonoBusItems(List<AbonoBus> abonoBusItems) {
        this.abonoBusItems = abonoBusItems;
    }

    public List<RecaudacionGuia> getRecaudacionItems() {
        return recaudacionItems;
    }

    public void setRecaudacionItems(List<RecaudacionGuia> recaudacionItems) {
        this.recaudacionItems = recaudacionItems;
    }

    public CargoBus getCargoBus() {
        return cargoBus;
    }

    public void setCargoBus(CargoBus cargoBus) {
        this.cargoBus = cargoBus;
    }

    public AbonoBus getAbonoBus() {
        return abonoBus;
    }

    public void setAbonoBus(AbonoBus abonoBus) {
        this.abonoBus = abonoBus;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setTotalAbonos(int totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalCargos(int totalCargos) {
        this.totalCargos = totalCargos;
    }

    public int getTotalCargos() {
        return totalCargos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public CargoBusDataModel getCargoDataModel() {
        return cargoDataModel;
    }

    public void setCargoDataModel(CargoBusDataModel cargoDataModel) {
        this.cargoDataModel = cargoDataModel;
    }

    public AbonoBusDataModel getAbonoDataModel() {
        return abonoDataModel;
    }

    public void setAbonoDataModel(AbonoBusDataModel abonoDataModel) {
        this.abonoDataModel = abonoDataModel;
    }

    public void setDate() {
        String _auxFecha = "01/" + this.mes + "/" + this.anio;
        try {
            this.fecha = sdf.parse(_auxFecha);
            this.currentDate = new CurrentDate();
            this.currentDate.setDate(fecha);
        } catch (ParseException exception) {
            JsfUtil.addErrorMessage("Error al crear la fecha");
        }
    }

    public void prepareCreateAbono() {
        if (this.bus != null) {

            this.tipoAbonoItems = new ITipoAbonoDaoImpl().findAll();

            this.abonoBus = new AbonoBus();
            this.abonoBus.setAbonoBusFechaInicio(fecha);
            this.abonoBus.setAbonoBusIdBus(bus);
            this.abonoBus.setAbonoBusCuotaActual(0);
            this.abonoBus.setAbonoBusTotalCuotas(0);
            this.abonoBus.setAbonoBusMontoFijo(0);
            this.abonoBus.setAbonoBusActivo(true);
        }
    }

    public void prepareCreateCargo() {
        if (this.bus != null) {
            this.tipoCargoItems = new ITipoCargoDaoImpl().findAll();

            this.cargoBus = new CargoBus();
            this.cargoBus.setCargoBusIdBus(bus);
            this.cargoBus.setCargoBusFechaInicio(fecha);
            this.cargoBus.setCargoBusCuotaActual(0);
            this.cargoBus.setCargoBusTotalCuotas(0);
            this.cargoBus.setCargoBusMontoFijo(0);
            this.cargoBus.setCargoBusActivo(true);

        }
    }

    public void addAbono() {
        if (this.abonoBus != null) {
            this.abonoBusItems.add(abonoBus);
            JsfUtil.addSuccessMessage("Se ha agregado un abono al Bus");
        }
    }

    public void saveAbono() {
        if (!this.abonoBusItems.isEmpty()) {
            for (AbonoBus a : this.abonoBusItems) {
                abonoBusDao.update(a);
            }
        }
        JsfUtil.addSuccessMessage("Se han actualizado los abonos");
    }

    public void addCargo() {
        if (this.cargoBus != null) {
            this.cargoBusItems.add(cargoBus);
            JsfUtil.addSuccessMessage("Se ha agregado un cargo al Bus");
        }
    }

    public void saveCargo() {
        if (!this.cargoBusItems.isEmpty()) {
            for (CargoBus a : this.cargoBusItems) {
                cargoBusDao.update(a);
            }
        }
        JsfUtil.addSuccessMessage("Se han actualizado los cargos");
    }

    public void deleteAbono() {
        if (this.getAbonoBus() != null) {
            this.abonoBusDao.delete(this.getAbonoBus());
            this.abonoBusItems.remove(this.cargoBus);

            this.setAbonoBus(null);
            JsfUtil.addSuccessMessage("Se ha eliminado el cargo");
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el cargo");
        }
    }

    public void deleteCargo() {
        if (this.getCargoBus() != null) {
            this.cargoBusDao.delete(this.getCargoBus());
            this.cargoBusItems.remove(this.cargoBus);

            this.setCargoBus(null);
            JsfUtil.addSuccessMessage("Se ha eliminado el cargo");
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el cargo");
        }
    }

    public void handleUnidadChange() {
        if (this.unidadNegocio != null) {
            this.busDao = new IBusDaoImpl();

            this.busItems = this.busDao.findByUnidad(unidadNegocio);
        }
    }

    public void load() {
        if (this.bus != null && this.fecha != null) {
            setDate();
            
            this.totalAdministracion = 0; 
            this.totalMinutos = 0; 
            this.totalAbonos = 0; 
            this.totalCargos = 0; 
            this.totalCuotaExtra = 0; 
            this.totalBoletos = 0; 
            this.totalImposiciones = 0; 
            this.totalRecaudacion = 0; 
            

            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();

            this.cargoBusDao = new CargoBusDaoImpl();
            this.abonoBusDao = new AbonoBusDaoImpl();

            this.cargoBusItems = this.cargoBusDao.findByBusBetweenDates(bus, fecha, _maxDate.toDate());

            for (CargoBus c : this.cargoBusItems) {
                this.totalCargos = this.totalCargos + c.getCargoBusMontoFijo();
            }

            this.abonoBusItems = this.abonoBusDao.findByBusBetweenDates(bus, fecha, _maxDate.toDate());

            for (AbonoBus c : this.abonoBusItems) {
                this.totalAbonos = this.totalAbonos + c.getAbonoBusMontoFijo();
            }

            this.cargoDataModel = new CargoBusDataModel(cargoBusItems);
            this.abonoDataModel = new AbonoBusDataModel(abonoBusItems);

            this.recaudacionItems = new IRecaudacionGuiaDaoImpl().findByBusBetweenFechaRecaudacion(bus, fecha, _maxDate.toDate());

            this.items = new ArrayList<Recaudacion>();

            for (RecaudacionGuia r : this.recaudacionItems) {
                this.items.add(r.getRecaudacionGuiaIdRecaudacion());
            }

            this.itemsRecaudacion = new ArrayList<RecaudacionGuiaHelper>();
            if (!this.recaudacionItems.isEmpty()) {
                this.totalRecaudacion = 0;
                for (Recaudacion g : this.items) {

                    RecaudacionGuiaHelper h = new RecaudacionGuiaHelper(g);
                    this.totalRecaudacion = this.totalRecaudacion + h.total;
                    this.itemsRecaudacion.add(h);

                    this.totalAdministracion = this.totalAdministracion + h.administracion;
                    this.totalBoletos = this.totalBoletos + h.boletos;
                    this.totalCuotaExtra = this.totalCuotaExtra + h.cuotaExtra;
                    this.totalImposiciones = this.totalImposiciones + h.imposiciones;

                }

                this.model = new RecaudacionLiquidacionDataModel(this.itemsRecaudacion);

                JsfUtil.addSuccessMessage("Liquidación Bus N°: " + this.bus.getBusNumero() + " ");
            } else {
                JsfUtil.addErrorMessage("Error en validación");
            }

            this.minutosItems = new IRecaudacionMinutoDaoImpl().findRecibidosBusAndDate(bus, fecha, _maxDate.toDate());

            for (RecaudacionMinuto rrm : this.minutosItems) {
                this.totalMinutos = this.totalMinutos + rrm.getRecaudacionMinutoMonto();
            }

            this.saldo = (this.totalRecaudacion + this.totalAbonos + this.totalMinutos) - this.totalCargos;
        }
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void resetParents() {

    }

    public void setCuotasCargo(Boolean cuotasCargo) {
        this.cuotasCargo = cuotasCargo;
    }

    public Boolean getCuotasCargo() {
        return cuotasCargo;
    }

    public void setCuotasAbono(Boolean cuotasAbono) {
        this.cuotasAbono = cuotasAbono;
    }

    public Boolean getCuotasAbono() {
        return cuotasAbono;
    }

    public List<TipoCargo> getTipoCargoItems() {
        return tipoCargoItems;
    }

    public void setTipoCargoItems(List<TipoCargo> tipoCargoItems) {
        this.tipoCargoItems = tipoCargoItems;
    }

    public List<TipoAbono> getTipoAbonoItems() {
        return tipoAbonoItems;
    }

    public void setTipoAbonoItems(List<TipoAbono> tipoAbonoItems) {
        this.tipoAbonoItems = tipoAbonoItems;
    }

    public class RecaudacionGuiaHelper {

        private Integer id;

        private int administracion;
        private int cuotaExtra;
        private int boletos;
        private int imposiciones;
        private int total;

        private Recaudacion recaudacion;

        private Guia guia;

        public RecaudacionGuiaHelper() {
        }

        public RecaudacionGuiaHelper(Recaudacion recaudacion) {
            this.id = recaudacion.getRecaudacionId();
            this.recaudacion = recaudacion;
            for (RecaudacionGuia g : recaudacion.getRecaudacionGuiaList()) {

                if (guia != g.getRecaudacionGuiaIdGuia()) {
                    guia = g.getRecaudacionGuiaIdGuia();
                }

                switch (g.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                    case 1:
                        this.administracion = g.getRecaudacionGuiaMonto();
                        break;
                    case 2:
                        this.cuotaExtra = g.getRecaudacionGuiaMonto();
                        break;
                    case 3:
                        this.imposiciones = g.getRecaudacionGuiaMonto();
                        break;
                    case 4:
                        this.boletos = g.getRecaudacionGuiaMonto();

                        int cantidad = this.boletos / 5000;
                        if (cantidad > 0) {
                            this.boletos = this.boletos - (cantidad * 500);
                        }

                        break;
                }

            }
            this.total = this.administracion + this.cuotaExtra + this.imposiciones + this.boletos;
        }

        public Guia getGuia() {
            return guia;
        }

        public void setGuia(Guia guia) {
            this.guia = guia;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public Integer getAdministracion() {
            return administracion;
        }

        public void setAdministracion(int administracion) {
            this.administracion = administracion;
        }

        public int getCuotaExtra() {
            return cuotaExtra;
        }

        public void setCuotaExtra(int cuotaExtra) {
            this.cuotaExtra = cuotaExtra;
        }

        public int getBoletos() {
            return boletos;
        }

        public void setBoletos(int boletos) {
            this.boletos = boletos;
        }

        public int getImposiciones() {
            return imposiciones;
        }

        public void setImposiciones(int imposiciones) {
            this.imposiciones = imposiciones;
        }

        public Recaudacion getRecaudacion() {
            return recaudacion;
        }

        public void setRecaudacion(Recaudacion Recaudacion) {
            this.recaudacion = Recaudacion;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

    }

}
