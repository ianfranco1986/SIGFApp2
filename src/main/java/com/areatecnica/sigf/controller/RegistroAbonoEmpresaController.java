/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IAbonoBusDao;
import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.ITipoAbonoDao;
import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.dao.impl.AbonoBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.IUnidadNegocioDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.TipoAbono;
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
@Named(value = "registroAbonoEmpresaController")
@ViewScoped
public class RegistroAbonoEmpresaController implements Serializable {

    private IEmpresaDao empresaDaoImpl;
    private IUnidadNegocioDao unidadNegocioDaoImpl;
    private ITipoAbonoDao tipoAbonoDao;
    private IAbonoBusDao abonoBusDaoImpl;
    private IBusDaoImpl busDaoImpl;
    private IGuiaDao guiaDao;

    private AbonoBus selected;
    private AbonoBus rowSelected;
    private TipoAbono tipoAbono;
    private List<TipoAbono> tipoAbonoItems;
    private List<AbonoBus> items;
    private List<Bus> busItems;
    private List<Empresa> empresaItems;
    private List<UnidadNegocio> unidadNegocioItems;
    private List<AbonoBus> registroAbonoItems;
    private List<Guia> guiasItems;

    private Bus bus;
    private UnidadNegocio unidadNegocio;
    private Empresa empresa;
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
    public RegistroAbonoEmpresaController() {
        this.fecha = new Date();
        this.selected = prepareCreate();
        this.getSelected().setAbonoBusCuotaActual(0);
        this.getSelected().setAbonoBusTotalCuotas(0);
        this.selected.setAbonoBusFechaInicio(new Date());
        this.guiaDao = new IGuiaDaoImpl();
        this.abonoBusDaoImpl = new AbonoBusDaoImpl();

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        String aux = "01-" + this.mes + "-" + this.anio;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fechaAux = format.parse(aux);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroAbonoEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.unidadNegocioItems = new IUnidadNegocioDaoImpl().findNandu();

    }

    public AbonoBus prepareCreate() {
        AbonoBus newAbonoBus;
        newAbonoBus = new AbonoBus();
        newAbonoBus.setAbonoBusMontoFijo(0);
        newAbonoBus.setAbonoBusTotalCuotas(0);
        return newAbonoBus;
    }

    public void saveNew() {
        if (!this.registroAbonoItems.isEmpty()) {

            for (AbonoBus c : this.registroAbonoItems) {
                c.setAbonoBusActivo(Boolean.TRUE);
                c.setAbonoBusFechaInicio(fecha);

                if (c.getAbonoBusTotalCuotas() == 0) {
                    c.setAbonoBusFechaTermino(fecha);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fecha);

                    int cuotasRestantes = c.getAbonoBusTotalCuotas() - c.getAbonoBusCuotaActual();

                    if (cuotasRestantes > 0) {
                        calendar.add(calendar.get(Calendar.MONTH), cuotasRestantes);
                        c.setAbonoBusFechaTermino(calendar.getTime());
                    } else {
                        c.setAbonoBusCuotaActual(0);
                        c.setAbonoBusTotalCuotas(0);
                        c.setAbonoBusFechaTermino(fecha);
                    }
                }
                this.abonoBusDaoImpl.create(c);
            }

            JsfUtil.addSuccessMessage("Se han registrado " + this.registroAbonoItems.size() + " abonos");
        } else {
            JsfUtil.addErrorMessage("Error al registrar los cambios");
        }

        this.registroAbonoItems = new ArrayList<>();
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

    public List<AbonoBus> getItems() {
        return items;
    }

    public void setItems(List<AbonoBus> items) {
        this.items = items;
    }

    public void setCuotas(Boolean cuotas) {
        this.cuotas = cuotas;
    }

    public Boolean getCuotas() {
        return cuotas;
    }

    public AbonoBus getSelected() {
        return selected;
    }

    public void setSelected(AbonoBus selected) {
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

    public List<TipoAbono> getTipoAbonoItems() {
        return tipoAbonoItems;
    }

    public void setTipoAbonoItems(List<TipoAbono> tipoAbonoItems) {
        this.tipoAbonoItems = tipoAbonoItems;
    }

    public List<Empresa> getEmpresaItems() {
        return empresaItems;
    }

    public void setEmpresaItems(List<Empresa> empresaItems) {
        this.empresaItems = empresaItems;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<AbonoBus> getRegistroAbonoItems() {
        return registroAbonoItems;
    }

    public void setRegistroAbonoItems(List<AbonoBus> registroAbonoItems) {
        this.registroAbonoItems = registroAbonoItems;
    }

    public void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = "01/" + mes + "/" + anio;
        try {
            this.fecha = format.parse(from);
        } catch (ParseException p) {

        }
    }

    public void handleEmpresa() {

        if (this.selected.getAbonoBusIdTipoAbono()!= null) {
            setDate();
            this.currentDate = new CurrentDate();
            this.currentDate.setDate(fecha);

            boolean flag = false;
            if (this.empresa != null) {

                this.busDaoImpl = new IBusDaoImpl();
                this.busItems = this.busDaoImpl.findByEmpresa(empresa);

                registroAbonoItems = new ArrayList<>();

                int i = 0;
                for (Bus b : this.busItems) {
                    int dias = 0;
                    this.guiasItems = this.guiaDao.findByBusBetweenFecha(b, fecha, this.currentDate.getMaxDate());

                    dias = this.guiasItems.size();

                    //************************
                    this.items = this.abonoBusDaoImpl.findByBusTipoAbonoBetweenDates(b, this.selected.getAbonoBusIdTipoAbono(), this.selected.getAbonoBusFechaInicio(), this.currentDate.getMaxDate());

                    if (!this.items.isEmpty()) {
                        for (AbonoBus c : this.items) {
                            //c.setIdAbono(dias);
                            registroAbonoItems.add(c);
                        }

                        flag = true;
                    } else {
                        AbonoBus abono = new AbonoBus();

                        abono.setAbonoBusFechaInicio(new Date());
                        abono.setAbonoBusFechaInicio(this.selected.getAbonoBusFechaInicio());
                        abono.setAbonoBusMontoFijo(0);
                        abono.setAbonoBusDescripcion(this.selected.getAbonoBusDescripcion());
                        abono.setAbonoBusIdBus(b);
                        abono.setAbonoBusCuotaActual(0);
                        abono.setAbonoBusTotalCuotas(0);
                        abono.setAbonoBusIdTipoAbono(this.selected.getAbonoBusIdTipoAbono());
                        //abono.setIdAbono(dias);
                        registroAbonoItems.add(abono);
                    }

                    i++;

                }

            }
            if (flag) {
                JsfUtil.addSuccessMessage("Existen abonos del tipo: " + this.selected.getAbonoBusIdTipoAbono().getTipoAbonoNombre() + " ingresados a la empresa:" + this.empresa.getEmpresaNombre() + ". Los cambios que realice afectarán a todos los abonos registrados");
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el tipo de Abono");
        }
    }

    public void loadUnidad() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = "01/" + mes + "/" + anio;
        try {
            this.fecha = format.parse(from);
        } catch (ParseException p) {

        }

        if (this.selected.getAbonoBusIdTipoAbono() != null) {
            boolean flag = false;
            if (this.unidadNegocio != null) {

                this.busDaoImpl = new IBusDaoImpl();
                this.busItems = this.busDaoImpl.findByEmpresaUnidad(this.empresa, this.unidadNegocio);

                registroAbonoItems = new ArrayList<>();

                int i = 0;
                for (Bus b : this.busItems) {

                    this.items = this.abonoBusDaoImpl.findByBusTipoAbonoBetweenDates(b, this.selected.getAbonoBusIdTipoAbono(), this.selected.getAbonoBusFechaInicio(), this.currentDate.getMaxDate());

                    if (!this.items.isEmpty()) {
                        for (AbonoBus c : this.items) {
                            registroAbonoItems.add(c);
                            flag = true;
                        }
                    } else {
                        AbonoBus abono = new AbonoBus();

                        //abono.setAbonoBusFechaInicio(new Date());
                        abono.setAbonoBusFechaInicio(this.selected.getAbonoBusFechaInicio());
                        abono.setAbonoBusMontoFijo(0);
                        abono.setAbonoBusDescripcion(this.selected.getAbonoBusDescripcion());
                        abono.setAbonoBusIdBus(b);
                        abono.setAbonoBusCuotaActual(0);
                        abono.setAbonoBusTotalCuotas(0);
                        abono.setAbonoBusIdTipoAbono(this.selected.getAbonoBusIdTipoAbono());
                        registroAbonoItems.add(abono);
                    }

                    i++;
                }

            } else {
                handleEmpresa();
            }
            if (flag) {
                JsfUtil.addSuccessMessage("Existen abonos del tipo: " + this.selected.getAbonoBusIdTipoAbono().getTipoAbonoNombre() + " ingresados a la empresa:" + this.empresa.getEmpresaNombre() + ". Los cambios que realice afectarán a todos los abonos registrados");
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar el tipo de Abono");
        }
    }

    public void loadDescripcion() {
        if (this.registroAbonoItems != null && !this.registroAbonoItems.isEmpty()) {
            for (AbonoBus r : this.registroAbonoItems) {
                if (!"".equals(this.selected.getAbonoBusDescripcion())) {
                    r.setAbonoBusDescripcion(this.selected.getAbonoBusDescripcion());
                }
            }
        }
    }

    public void setMontoXDefecto() {
        if (this.selected != null) {
            this.selected.setAbonoBusMontoFijo(monto);
            System.err.println("monto:" + monto);
            if (this.empresa != null) {
                for (AbonoBus r : registroAbonoItems) {
                    r.setAbonoBusMontoFijo(monto);
                }
            }
        }
    }

    public void setMontoXDefectoTipoAbono() {
        if (this.selected != null) {
            this.monto = this.selected.getAbonoBusIdTipoAbono().getTipoAbonoMontoDefecto();
            if (this.empresa != null) {
                this.empresa = null;
                this.registroAbonoItems = null;
            }
        }
    }

    public void setCuotaActual() {
        if (this.selected != null) {
            try {
                int cuotaActual = this.selected.getAbonoBusCuotaActual();

                for (AbonoBus r : registroAbonoItems) {
                    r.setAbonoBusCuotaActual(cuotaActual);
                }

            } catch (NullPointerException e) {
                for (AbonoBus r : registroAbonoItems) {
                    r.setAbonoBusCuotaActual(0);
                }
            }

        }
    }

    public void setTotalCuota() {
        if (this.selected != null) {
            try {
                int totalCuotas = this.selected.getAbonoBusTotalCuotas();

                for (AbonoBus r : registroAbonoItems) {
                    r.setAbonoBusTotalCuotas(totalCuotas);
                }

            } catch (NullPointerException e) {
                for (AbonoBus r : registroAbonoItems) {
                    r.setAbonoBusTotalCuotas(0);
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

        if (this.empresa != null) {

            this.busDaoImpl = new IBusDaoImpl();
            this.busItems = this.busDaoImpl.findByEmpresa(empresa);

            registroAbonoItems = new ArrayList<>();

            int i = 0;
            for (Bus b : this.busItems) {

                this.items = this.abonoBusDaoImpl.findByBusTipoAbonoBetweenDates(b, this.tipoAbono, this.fecha, this.currentDate.getMaxDate());

                if (!this.items.isEmpty()) {
                    for (AbonoBus c : this.items) {
                        registroAbonoItems.add(c);
                    }
                } else {/*
                    AbonoBus abono = new AbonoBus();
                    abono.setFechaIngresoAbonoBus(new Date());
                    abono.setFechaInicioAbonoBus(this.selected.getFechaInicioAbonoBus());
                    abono.setMontoAbonoBus(this.monto);
                    abono.setActivoAbonoBus(Boolean.TRUE);
                    abono.setDescripcion(this.selected.getDescripcion());
                    abono.setBus(b);
                    abono.setTipoAbono(this.selected.getTipoAbono());
                    RegistroAbonoEmpresa c = new RegistroAbonoEmpresa(b, abono);
                    c.setKey(i);
                    registroAbonoItems.add(c);*/
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

    public TipoAbono getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoAbono(TipoAbono tipoAbono) {
        this.tipoAbono = tipoAbono;
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

    public AbonoBus getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(AbonoBus rowSelected) {
        this.rowSelected = rowSelected;
    }

}
