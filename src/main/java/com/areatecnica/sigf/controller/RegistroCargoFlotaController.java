/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICargoBusDao;
import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.ITipoCargoDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.CargoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.util.CurrentDate;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "registroCargoFlotaController")
@ViewScoped
public class RegistroCargoFlotaController implements Serializable {

    private IFlotaDao flotaDaoImpl;
    private IUnidadNegocioDao unidadNegocioDaoImpl;
    private ITipoCargoDao tipoCargoDao;
    private ICargoBusDao cargoBusDaoImpl;
    private IBusDaoImpl busDaoImpl;
    private IGuiaDao guiaDao;

    private CargoBus selected;
    private CargoBus rowSelected;
    private TipoCargo tipoCargo;
    private List<TipoCargo> tipoCargoItems;
    private List<CargoBus> items;
    private List<Bus> busItems;
    private List<Flota> flotaItems;
    private List<UnidadNegocio> unidadNegocioItems;
    private List<CargoBus> registroCargoItems;
    private List<Guia> guiasItems;

    private Bus bus;
    private UnidadNegocio unidadNegocio;
    private Flota flota;
    private int monto;
    private int numeroCuotas;
    private Date fecha;
    private CurrentDate currentDate;
    private int idFina;
    private int mes;
    private int anio;
    private Date fechaAux;
    private Boolean cuotas;

    /**
     * Creates a new instance of InstitucionPrevisionController
     */
    public RegistroCargoFlotaController() {
        this.fecha = new Date();
        this.selected = prepareCreate();
        this.getSelected().setCargoBusCuotaActual(0);
        this.getSelected().setCargoBusTotalCuotas(0);
        this.selected.setCargoBusFechaInicio(new Date());
        this.guiaDao = new IGuiaDaoImpl();
        this.cargoBusDaoImpl = new CargoBusDaoImpl();

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        String aux = "01-" + this.mes + "-" + this.anio;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechaAux = format.parse(aux);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroCargoFlotaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public CargoBus prepareCreate() {
        CargoBus newCargoBus;
        newCargoBus = new CargoBus();
        newCargoBus.setCargoBusMontoFijo(0);
        newCargoBus.setCargoBusTotalCuotas(0);
        return newCargoBus;
    }

    public void saveNew() {
        if (!this.registroCargoItems.isEmpty()) {

            for (CargoBus c : this.registroCargoItems) {
                if (c.getCargoBusMontoFijo() > 0) {
                    c.setCargoBusActivo(Boolean.TRUE);
                    c.setCargoBusFechaInicio(fecha);

                    if (c.getCargoBusTotalCuotas() == 0) {
                        c.setCargoBusFechaTermino(fecha);
                    } else {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha);

                        int cuotasRestantes = c.getCargoBusTotalCuotas() - c.getCargoBusCuotaActual();

                        if (cuotasRestantes > 0) {
                            calendar.add(calendar.get(Calendar.MONTH), cuotasRestantes);
                            c.setCargoBusFechaTermino(calendar.getTime());
                        } else {
                            c.setCargoBusCuotaActual(0);
                            c.setCargoBusTotalCuotas(0);
                            c.setCargoBusFechaTermino(fecha);
                        }
                    }
                    this.cargoBusDaoImpl.create(c);
                } else {
                }
            }

            JsfUtil.addSuccessMessage("Se han registrado " + this.registroCargoItems.size() + " cargos");
        } else {
            JsfUtil.addErrorMessage("Error al registrar los cambios");
        }

        this.registroCargoItems = new ArrayList<>();
        this.selected = prepareCreate();
    }

    public void save() {
        if (this.rowSelected != null) {

        } else {

        }
    }

    public void resetParents() {

    }

    public void delete() {
        if (this.selected != null) {

        } else {

        }
    }

    public void deleteRow() {
        if (this.rowSelected != null) {

        } else {

        }
    }

    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    public List<CargoBus> getItems() {
        return items;
    }

    public void setItems(List<CargoBus> items) {
        this.items = items;
    }

    public void setCuotas(Boolean cuotas) {
        this.cuotas = cuotas;
    }

    public Boolean getCuotas() {
        return cuotas;
    }

    public CargoBus getSelected() {
        return selected;
    }

    public void setSelected(CargoBus selected) {
        this.selected = selected;
    }

    public void setBusItems(List<Bus> trabajadorItems) {
        this.busItems = trabajadorItems;
    }

    public List<Bus> getBusItems() {
        return this.busItems;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<TipoCargo> getTipoCargoItems() {
        return tipoCargoItems;
    }

    public void setTipoCargoItems(List<TipoCargo> tipoCargoItems) {
        this.tipoCargoItems = tipoCargoItems;
    }

    public List<Flota> getFlotaItems() {
        return flotaItems;
    }

    public void setFlotaItems(List<Flota> flotaItems) {
        this.flotaItems = flotaItems;
    }

    public List<UnidadNegocio> getUnidadNegocioItems() {
        return unidadNegocioItems;
    }

    public void setUnidadNegocioItems(List<UnidadNegocio> unidadNegocioItems) {
        this.unidadNegocioItems = unidadNegocioItems;
    }

    public UnidadNegocio getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Flota getFlota() {
        return flota;
    }

    public void setFlota(Flota flota) {
        this.flota = flota;
    }

    public List<CargoBus> getRegistroCargoItems() {
        return registroCargoItems;
    }

    public void setRegistroCargoItems(List<CargoBus> registroCargoItems) {
        this.registroCargoItems = registroCargoItems;
    }

    public void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = "01/" + mes + "/" + anio;
        try {
            this.fecha = format.parse(from);
        } catch (ParseException p) {

        }
    }

    public void handleFlota() {

        if (this.selected.getCargoBusIdTipo() != null) {
            setDate();
            this.currentDate = new CurrentDate();
            this.currentDate.setDate(fecha);

            boolean flag = false;
            if (this.flota != null) {

                this.busDaoImpl = new IBusDaoImpl();
                this.busItems = this.busDaoImpl.findAllByFlota(flota);

                registroCargoItems = new ArrayList<>();

                int i = 0;
                for (Bus b : this.busItems) {
                    int dias = 0;
                    this.guiasItems = this.guiaDao.findByBusBetweenFecha(b, fecha, this.currentDate.getMaxDate());

                    dias = this.guiasItems.size();

                    //************************
                    this.items = this.cargoBusDaoImpl.findByBusTipoCargoBetweenDates(b, this.selected.getCargoBusIdTipo(), this.selected.getCargoBusFechaInicio(), this.currentDate.getMaxDate());

                    if (!this.items.isEmpty()) {
                        for (CargoBus c : this.items) {
                            //c.setIdCargo(dias);
                            registroCargoItems.add(c);
                        }

                        flag = true;
                    } else {
                        CargoBus cargo = new CargoBus();

                        cargo.setCargoBusFechaInicio(new Date());
                        cargo.setCargoBusFechaInicio(this.selected.getCargoBusFechaInicio());
                        cargo.setCargoBusMontoFijo(0);
                        cargo.setCargoBusDescripcion(this.selected.getCargoBusDescripcion());
                        cargo.setCargoBusIdBus(b);
                        cargo.setCargoBusCuotaActual(0);
                        cargo.setCargoBusTotalCuotas(0);
                        cargo.setCargoBusIdTipo(this.selected.getCargoBusIdTipo());
                        //cargo.setIdCargo(dias);
                        registroCargoItems.add(cargo);
                    }

                    i++;

                }

            }
            if (flag) {
                JsfUtil.addSuccessMessage("Existen cargos del tipo: " + this.selected.getCargoBusIdTipo().getTipoCargoNombre() + " ingresados a la flota:" + this.flota.getFlotaNombre() + ". Los cambios que realice afectarán a todos los cargos registrados");
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el tipo de Cargo");
        }
    }

    public void loadUnidad() {
        setDate();
        this.currentDate = new CurrentDate();
        this.currentDate.setDate(fecha);

        if (this.selected.getCargoBusIdTipo() != null) {
            boolean flag = false;
            if (this.unidadNegocio != null) {

                this.busDaoImpl = new IBusDaoImpl();
                this.busItems = this.busDaoImpl.findAllByFlotaUnidad(this.flota, this.unidadNegocio);

                registroCargoItems = new ArrayList<>();

                int i = 0;
                for (Bus b : this.busItems) {

                    this.items = this.cargoBusDaoImpl.findByBusTipoCargoBetweenDates(b, this.selected.getCargoBusIdTipo(), this.selected.getCargoBusFechaInicio(), this.currentDate.getMaxDate());

                    if (!this.items.isEmpty()) {
                        for (CargoBus c : this.items) {
                            registroCargoItems.add(c);
                            flag = true;
                        }
                    } else {
                        CargoBus cargo = new CargoBus();

                        //cargo.setCargoBusFechaInicio(new Date());
                        cargo.setCargoBusFechaInicio(this.selected.getCargoBusFechaInicio());
                        cargo.setCargoBusMontoFijo(0);
                        cargo.setCargoBusDescripcion(this.selected.getCargoBusDescripcion());
                        cargo.setCargoBusIdBus(b);
                        cargo.setCargoBusCuotaActual(0);
                        cargo.setCargoBusTotalCuotas(0);
                        cargo.setCargoBusIdTipo(this.selected.getCargoBusIdTipo());
                        registroCargoItems.add(cargo);
                    }

                    i++;
                }

            } else {
                handleFlota();
            }
            if (flag) {
                JsfUtil.addSuccessMessage("Existen cargos del tipo: " + this.selected.getCargoBusIdTipo().getTipoCargoNombre() + " ingresados a la flota:" + this.flota.getFlotaNombre() + ". Los cambios que realice afectarán a todos los cargos registrados");
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el tipo de Cargo");
        }
    }

    public void loadDescripcion() {
        if (this.registroCargoItems != null && !this.registroCargoItems.isEmpty()) {
            for (CargoBus r : this.registroCargoItems) {
                if (!"".equals(this.selected.getCargoBusDescripcion())) {
                    r.setCargoBusDescripcion(this.selected.getCargoBusDescripcion());
                }
            }
        }
    }

    public void setMontoXDefecto() {
        if (this.selected != null) {
            this.selected.setCargoBusMontoFijo(monto);
            System.err.println("monto:" + monto);
            if (this.flota != null) {
                for (CargoBus r : registroCargoItems) {
                    r.setCargoBusMontoFijo(monto);
                }
            }
        }
    }

    public void setMontoXDefectoTipoCargo() {
        if (this.selected != null) {
            this.monto = this.selected.getCargoBusIdTipo().getTipoCargoMontoDefecto();
            if (this.flota != null) {
                this.flota = null;
                this.registroCargoItems = null;
            }
        }
    }

    public void setCuotaActual() {
        if (this.selected != null) {
            try {
                int cuotaActual = this.selected.getCargoBusCuotaActual();

                for (CargoBus r : registroCargoItems) {
                    r.setCargoBusCuotaActual(cuotaActual);
                }

            } catch (NullPointerException e) {
                for (CargoBus r : registroCargoItems) {
                    r.setCargoBusCuotaActual(0);
                }
            }

        }
    }

    public void setTotalCuota() {
        if (this.selected != null) {
            try {
                int totalCuotas = this.selected.getCargoBusTotalCuotas();

                for (CargoBus r : registroCargoItems) {
                    r.setCargoBusTotalCuotas(totalCuotas);
                }

            } catch (NullPointerException e) {
                for (CargoBus r : registroCargoItems) {
                    r.setCargoBusTotalCuotas(0);
                }
            }

        }
    }

    public void init() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = "01/" + mes + "/" + anio;
        try {
            this.fecha = format.parse(from);
        } catch (ParseException p) {

        }

        if (this.flota != null) {

            this.busDaoImpl = new IBusDaoImpl();
            this.busItems = this.busDaoImpl.findAllByFlota(flota);

            registroCargoItems = new ArrayList<>();

            int i = 0;
            for (Bus b : this.busItems) {

                this.items = this.cargoBusDaoImpl.findByBusTipoCargoBetweenDates(b, this.tipoCargo, this.fecha, this.currentDate.getMaxDate());

                if (!this.items.isEmpty()) {
                    for (CargoBus c : this.items) {
                        registroCargoItems.add(c);
                    }
                } else {/*
                    CargoBus cargo = new CargoBus();
                    cargo.setFechaIngresoCargoBus(new Date());
                    cargo.setFechaInicioCargoBus(this.selected.getFechaInicioCargoBus());
                    cargo.setMontoCargoBus(this.monto);
                    cargo.setActivoCargoBus(Boolean.TRUE);
                    cargo.setDescripcion(this.selected.getDescripcion());
                    cargo.setBus(b);
                    cargo.setTipoCargo(this.selected.getTipoCargo());
                    RegistroCargoFlota c = new RegistroCargoFlota(b, cargo);
                    c.setKey(i);
                    registroCargoItems.add(c);*/
                }

                i++;
            }

        }
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public int getIdFina() {
        return idFina;
    }

    public void setIdFina(int idFina) {
        this.idFina = idFina;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
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

    public void showMessage(String value) {
        //JsfUtil.addSuccessMessage("modificado:"+value);
    }

    public CargoBus getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(CargoBus rowSelected) {
        this.rowSelected = rowSelected;
    }

}
