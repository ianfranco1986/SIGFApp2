package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.RecaudacionBoleto;
import java.util.List;
import com.areatecnica.sigf.facade.VentaBoletoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ventaBoletoController")
@ViewScoped
public class VentaBoletoController extends AbstractController<VentaBoleto> {

    @Inject
    private BusController ventaBoletoIdBusController;
    @Inject
    private InventarioCajaController ventaBoletoIdInventarioCajaController;
    @Inject
    private TrabajadorController ventaBoletoIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionBoletoListEmpty;

    public VentaBoletoController() {
        // Inform the Abstract parent controller of the concrete VentaBoleto Entity
        super(VentaBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ventaBoletoIdBusController.setSelected(null);
        ventaBoletoIdInventarioCajaController.setSelected(null);
        ventaBoletoIdTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRecaudacionBoletoListEmpty();
    }

    public boolean getIsRecaudacionBoletoListEmpty() {
        return this.isRecaudacionBoletoListEmpty;
    }

    private void setIsRecaudacionBoletoListEmpty() {
        VentaBoleto selected = this.getSelected();
        if (selected != null) {
            VentaBoletoFacade ejbFacade = (VentaBoletoFacade) this.getFacade();
            this.isRecaudacionBoletoListEmpty = ejbFacade.isRecaudacionBoletoListEmpty(selected);
        } else {
            this.isRecaudacionBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionBoleto
     * entities that are retrieved from VentaBoleto and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RecaudacionBoleto page
     */
    public String navigateRecaudacionBoletoList() {
        VentaBoleto selected = this.getSelected();
        if (selected != null) {
            VentaBoletoFacade ejbFacade = (VentaBoletoFacade) this.getFacade();
            List<RecaudacionBoleto> selectedRecaudacionBoletoList = ejbFacade.findRecaudacionBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionBoleto_items", selectedRecaudacionBoletoList);
        }
        return "/app/recaudacionBoleto/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdBus(ActionEvent event) {
        VentaBoleto selected = this.getSelected();
        if (selected != null && ventaBoletoIdBusController.getSelected() == null) {
            ventaBoletoIdBusController.setSelected(selected.getVentaBoletoIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the InventarioCaja controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdInventarioCaja(ActionEvent event) {
        VentaBoleto selected = this.getSelected();
        if (selected != null && ventaBoletoIdInventarioCajaController.getSelected() == null) {
            ventaBoletoIdInventarioCajaController.setSelected(selected.getVentaBoletoIdInventarioCaja());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVentaBoletoIdTrabajador(ActionEvent event) {
        VentaBoleto selected = this.getSelected();
        if (selected != null && ventaBoletoIdTrabajadorController.getSelected() == null) {
            ventaBoletoIdTrabajadorController.setSelected(selected.getVentaBoletoIdTrabajador());
        }
    }

}
