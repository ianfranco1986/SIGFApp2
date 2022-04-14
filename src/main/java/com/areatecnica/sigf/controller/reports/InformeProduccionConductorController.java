/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IRecaudacionGuiaDao;
import com.areatecnica.sigf.dao.impl.IRecaudacionGuiaDaoImpl;
import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.AdministradorBus;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.models.TableModelConductor;
import com.areatecnica.sigf.util.CurrentDate;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informeProduccionConductorController")
@ViewScoped
public class InformeProduccionConductorController implements Serializable {

    private Usuario currentUser;
    private List<Bus> items;
    private List<Trabajador> conductoresItems;
    private List<RecaudacionGuia> recaudacion;
    private List<TableModelConductor> data;
    private IRecaudacionGuiaDao dao;
    private Date fecha;
    private int mes;
    private int anio;
    private CurrentDate currentDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Map sumas;
    private Map hashConductor;

    /**
     * Creates a new instance of InformeRegistroBoletoController
     */
    public InformeProduccionConductorController() {
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

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);
        } catch (ParseException ex) {
            Logger.getLogger(InformeProduccionConductorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.hashConductor = new HashMap();
        this.recaudacion = new ArrayList<>();
        this.dao = new IRecaudacionGuiaDaoImpl();
        this.data = new ArrayList<>();

        load();
    }

    public void load() {
        System.err.println("BUSCANDO");
        System.err.println("TAMAÑO DE LISTA DE BUSES: "+this.items);
        
        
        for (Bus b : this.items) {
            List<RecaudacionGuia> aux = this.dao.findByBusBetweenFechaRecaudacion(b, fecha, this.currentDate.getMaxDate());
            System.err.println("TAMAÑO DE LISTA DE GUIÍAS DEL BUS: "+b.getBusNumero()+" "+aux.size());
            if (!aux.isEmpty()) {
                this.recaudacion.addAll(aux);
            }
        }

        for (RecaudacionGuia r : this.recaudacion) {
            TableModelConductor tableModelConductor = new TableModelConductor(r.getRecaudacionGuiaIdRecaudacion());
            data.add(tableModelConductor);
        }
    }

    public List<TableModelConductor> getData() {
        return data;
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
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

    public Map getSumas() {
        return sumas;
    }

    public void setFecha() {
        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            System.err.println("FECHA:"+this.fecha);
            this.currentDate = new CurrentDate(1, mes, anio);
            load();
        } catch (ParseException ex) {
            Logger.getLogger(InformeProduccionConductorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

}
