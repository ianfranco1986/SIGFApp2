/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.dao.impl.GuiaDaoImpl;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.EstructuraRegistroBoletoÑandu;
import com.areatecnica.sigf.models.RegistroBoletoTableModel;
import com.areatecnica.sigf.util.CurrentDate;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informeRegistroBoletoController")
@ViewScoped
public class InformeRegistroBoletoController implements Serializable {

    private final Usuario currentUser;
    private List<Bus> items;
    private Bus selected;
    private List<Guia> guiaItems;
    private Guia guia;
    private IGuiaDao guiaDao;
    private RegistroBoletoTableModel model;
    private Date fecha;
    private int mes;
    private int anio;
    private CurrentDate currentDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates a new instance of InformeRegistroBoletoController
     */
    public InformeRegistroBoletoController() {
        this.items = new ArrayList<>();
        this.currentUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");

        Administrador admin = this.currentUser.getAdministradorList().get(0);

        if (!admin.getAdministradorBusList().isEmpty()) {
            for (AdministradorBus b : admin.getAdministradorBusList()) {
                this.items.add(b.getAdministradorBusIdBus());
            }
        } else if (!admin.getAdministradorFlotaList().isEmpty()) {
            for (AdministradorFlota f : admin.getAdministradorFlotaList()) {
                this.items.addAll(f.getAdministradorFlotaIdFlota().getBusList());
            }
        }

        this.model = new RegistroBoletoTableModel();

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);
        } catch (ParseException ex) {
            Logger.getLogger(InformeRegistroBoletoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleBusChange() {
        if (this.selected != null) {
            this.guiaDao = new GuiaDaoImpl();
            this.guiaItems = this.guiaDao.findByBusBetweenFecha(selected, this.fecha, this.currentDate.getMaxDate());
            this.model = new RegistroBoletoTableModel();
        }
    }

    public void load() {
        if (this.guia != null) {
            this.model = new RegistroBoletoTableModel(guia);
        }
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public Bus getSelected() {
        return selected;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

    public RegistroBoletoTableModel getModel() {
        return model;
    }

    public void setModel(RegistroBoletoTableModel model) {
        this.model = model;
    }

    public List<EstructuraRegistroBoletoÑandu> getBoletosItems() {
        return this.model.getList();
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMes() {
        return mes;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public List<Guia> getGuiaItems() {
        return guiaItems;
    }

    public void setGuiaItems(List<Guia> guiaItems) {
        this.guiaItems = guiaItems;
    }

    public void setFecha() {
        try {
            
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);

            handleBusChange();
        } catch (ParseException ex) {
            Logger.getLogger(InformeRegistroBoletoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

}
