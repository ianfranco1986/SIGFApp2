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
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoAbonoDaoImpl;
import com.areatecnica.sigf.dao.impl.ITipoCargoDaoImpl;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.models.AbonoBusDataModel;
import com.areatecnica.sigf.models.CargoBusDataModel;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    private List<Recaudacion> recaudacionItems;
    private List<Bus> busItems;
    private List<TipoCargo> tipoCargoItems;
    private List<TipoAbono> tipoAbonoItems; 
    
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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

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

    public List<Recaudacion> getRecaudacionItems() {
        return recaudacionItems;
    }

    public void setRecaudacionItems(List<Recaudacion> recaudacionItems) {
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

            this.dateTime = new DateTime(fecha);
            DateTime _maxDate = this.dateTime.dayOfMonth().withMaximumValue();

            this.cargoBusDao = new CargoBusDaoImpl();
            this.abonoBusDao = new AbonoBusDaoImpl();

            this.cargoBusItems = this.cargoBusDao.findByBusBetweenDates(bus, fecha, _maxDate.toDate());

            this.abonoBusItems = this.abonoBusDao.findByBusBetweenDates(bus, fecha, _maxDate.toDate());

            this.cargoDataModel = new CargoBusDataModel(cargoBusItems);
            this.abonoDataModel = new AbonoBusDataModel(abonoBusItems);

            this.recaudacionItems = new IRecaudacionDaoImpl().findByBusBetweenFechaRecaudacion(bus, fecha, _maxDate.toDate());
            
            
            
            JsfUtil.addSuccessMessage("Liquidación Bus N°: " + this.bus.getBusNumero() + " ");
        } else {
            JsfUtil.addErrorMessage("Error en validación");
        }
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

    
    
}
