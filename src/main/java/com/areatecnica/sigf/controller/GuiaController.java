package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.BusDaoImpl;
import com.areatecnica.sigf.dao.impl.GuiaDaoImpl;
import com.areatecnica.sigf.dao.impl.ProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.models.GuiaDataModel;
import com.areatecnica.sigf.util.LocalDateConverter;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named(value = "guiaController")
@ViewScoped
public class GuiaController extends AbstractController<Guia> {

    private LocalDate date;
    private LocalDateConverter dc;
    private List<Trabajador> trabajadorItems;
    private List<Guia> items;
    private List<Bus> itemsBus;
    private Guia selectedGuia;
    private Bus bus;
 
    private GuiaDataModel model;

    public GuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    public void setTrabajadorItems(List<Trabajador> trabajadorItems) {
        this.trabajadorItems = trabajadorItems;
    }

    public List<Trabajador> getTrabajadorItems() {
        return trabajadorItems;
    }

    public void setItemsBus(List<Bus> itemsBus) {
        this.itemsBus = itemsBus;
    }

    public List<Bus> getItemsBus() {
        return itemsBus;
    }

    public List<Guia> getItems() {
        return items;
    }

    public void setItems(List<Guia> items) {
        this.items = items;
    }

    public Guia getSelectedGuia() {
        return this.selectedGuia; //To change body of generated methods, choose Tools | Templates.
    }

    public void setSelectedGuia(Guia selectedGuia) {
        this.selectedGuia = selectedGuia;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public GuiaDataModel getModel() {
        return model;
    }

    public void setModel(GuiaDataModel model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        this.date = LocalDate.now();
        this.dc = new LocalDateConverter(this.date);

        this.itemsBus = new BusDaoImpl().findByProceso(new ProcesoRecaudacionDaoImpl().findById(2));
        this.trabajadorItems = new TrabajadorDaoImpl().findNanduOrderByCode();
    }

    public void load() {
        if (this.date != null && this.bus != null) {
            this.items = new GuiaDaoImpl().findByBusBetweenFecha(bus, this.dc.getFirstDateOfMonth(), this.dc.getLastDayOfMonth());
            System.err.println("FECHAs:"+this.dc.getDate()+"/"+this.dc.getLastDayOfMonth());
            if (!this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " guías del bus n° " + this.bus.getBusNumero() + " para el periodo " + this.dc.getMonthYearString());

                this.model = new GuiaDataModel(this.items);

            } else {
                JsfUtil.addErrorMessage("No se han encontrado guías para el mes " + this.dc.getMonthYearString());
                this.model = new GuiaDataModel();
            }

        } else {
            JsfUtil.addErrorMessage("Ocurrió un error al cargar los datos");
        }
    }

    public void save() {

    }

    public void onRowEdit(RowEditEvent event) {

        Guia aux = null;

        try {
            aux = (Guia) event.getObject();
            new GuiaDaoImpl().update(aux);
            JsfUtil.addSuccessMessage("Se ha actualizado la guía ");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al guardar la guía");
        }

    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }
    
    
}
