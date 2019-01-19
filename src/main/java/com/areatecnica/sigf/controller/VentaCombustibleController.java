package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.reports.InformeConsumoCombustibleControllerFlota;
import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import java.util.List;
import com.areatecnica.sigf.facade.VentaCombustibleFacade;
import com.areatecnica.sigf.util.CurrentDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ventaCombustibleController")
@ViewScoped
public class VentaCombustibleController extends AbstractController<VentaCombustible> {

    @Inject
    private BusController ventaCombustibleIdBusController;
    @Inject
    private SurtidorController ventaCombustibleIdSurtidorController;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionCombustibleListEmpty;

    private Date fecha;
    private int mes;
    private int anio;
    private CurrentDate currentDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    private List<VentaCombustible> items;
    private IVentaCombustibleDao dao;

    public VentaCombustibleController() {
        // Inform the Abstract parent controller of the concrete VentaCombustible Entity
        super(VentaCombustible.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        //super.initParams(); //To change body of generated methods, choose Tools | Templates.

        Calendar calendar = GregorianCalendar.getInstance();
        this.mes = calendar.get(Calendar.MONTH) + 1;
        this.anio = calendar.get(Calendar.YEAR);

        try {
            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.currentDate = new CurrentDate(1, mes, anio);
        } catch (ParseException ex) {
            Logger.getLogger(InformeConsumoCombustibleControllerFlota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaCombustibleIdBusController.setSelected(null);
        ventaCombustibleIdSurtidorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRecaudacionCombustibleListEmpty();
    }

    public boolean getIsRecaudacionCombustibleListEmpty() {
        return this.isRecaudacionCombustibleListEmpty;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public Collection<VentaCombustible> getItems() {
        return items; //To change body of generated methods, choose Tools | Templates.
    }

    public void setItems(List<VentaCombustible> items) {
        this.items = items;
    }

    private void setIsRecaudacionCombustibleListEmpty() {
        VentaCombustible selected = this.getSelected();
        if (selected != null) {
            VentaCombustibleFacade ejbFacade = (VentaCombustibleFacade) this.getFacade();
            this.isRecaudacionCombustibleListEmpty = ejbFacade.isRecaudacionCombustibleListEmpty(selected);
        } else {
            this.isRecaudacionCombustibleListEmpty = true;
        }
    }

    public void load() {
        if (this.fecha != null) {
            this.currentDate = new CurrentDate();
            this.currentDate.setDate(fecha);

            this.dao = new IVentaCombustibleDaoImpl();

            this.items = this.dao.findByDate(fecha);

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la fecha");
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionCombustible
     * entities that are retrieved from VentaCombustible and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RecaudacionCombustible page
     */
    public String navigateRecaudacionCombustibleList() {
        VentaCombustible selected = this.getSelected();
        if (selected != null) {
            VentaCombustibleFacade ejbFacade = (VentaCombustibleFacade) this.getFacade();
            List<RecaudacionCombustible> selectedRecaudacionCombustibleList = ejbFacade.findRecaudacionCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionCombustible_items", selectedRecaudacionCombustibleList);
        }
        return "/app/recaudacionCombustible/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaCombustibleIdBus(ActionEvent event) {
        VentaCombustible selected = this.getSelected();
        if (selected != null && ventaCombustibleIdBusController.getSelected() == null) {
            ventaCombustibleIdBusController.setSelected(selected.getVentaCombustibleIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Surtidor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaCombustibleIdSurtidor(ActionEvent event) {
        VentaCombustible selected = this.getSelected();
        if (selected != null && ventaCombustibleIdSurtidorController.getSelected() == null) {
            ventaCombustibleIdSurtidorController.setSelected(selected.getVentaCombustibleIdSurtidor());
        }
    }

//    public void setFecha() {
//        try {
//            this.fecha = this.sdf.parse("01/" + this.mes + "/" + this.anio);
//            this.currentDate = new CurrentDate(1, mes, anio);
//        } catch (ParseException ex) {
//            Logger.getLogger(InformeConsumoCombustibleControllerFlota.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
