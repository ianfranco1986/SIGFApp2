package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.RegistroBoleto;
import java.util.List;
import com.areatecnica.sigf.facade.GuiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "guiaController")
@ViewScoped
public class GuiaController extends AbstractController<Guia> {

    @Inject
    private BusController guiaIdBusController;
    @Inject
    private TrabajadorController guiaIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionGuiaListEmpty;
    private boolean isRegistroBoletoListEmpty;

    public GuiaController() {
        // Inform the Abstract parent controller of the concrete Guia Entity
        super(Guia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        guiaIdBusController.setSelected(null);
        guiaIdTrabajadorController.setSelected(null);
    }

//    /**
//     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
//     */
//    @Override
//    protected void setChildrenEmptyFlags() {
//        this.setIsRecaudacionGuiaListEmpty();
//        this.setIsRegistroBoletoListEmpty();
//    }

    public boolean getIsRecaudacionGuiaListEmpty() {
        return this.isRecaudacionGuiaListEmpty;
    }

    private void setIsRecaudacionGuiaListEmpty() {
        Guia selected = this.getSelected();
        if (selected != null) {
            GuiaFacade ejbFacade = (GuiaFacade) this.getFacade();
            this.isRecaudacionGuiaListEmpty = ejbFacade.isRecaudacionGuiaListEmpty(selected);
        } else {
            this.isRecaudacionGuiaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionGuia entities
     * that are retrieved from Guia and returns the navigation outcome.
     *
     * @return navigation outcome for RecaudacionGuia page
     */
    public String navigateRecaudacionGuiaList() {
        Guia selected = this.getSelected();
        if (selected != null) {
            GuiaFacade ejbFacade = (GuiaFacade) this.getFacade();
            List<RecaudacionGuia> selectedRecaudacionGuiaList = ejbFacade.findRecaudacionGuiaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionGuia_items", selectedRecaudacionGuiaList);
        }
        return "/app/recaudacionGuia/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdBus(ActionEvent event) {
        Guia selected = this.getSelected();
        if (selected != null && guiaIdBusController.getSelected() == null) {
            guiaIdBusController.setSelected(selected.getGuiaIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGuiaIdTrabajador(ActionEvent event) {
        Guia selected = this.getSelected();
        if (selected != null && guiaIdTrabajadorController.getSelected() == null) {
            guiaIdTrabajadorController.setSelected(selected.getGuiaIdTrabajador());
        }
    }

    public boolean getIsRegistroBoletoListEmpty() {
        return this.isRegistroBoletoListEmpty;
    }

//    private void setIsRegistroBoletoListEmpty() {
//        Guia selected = this.getSelected();
//        if (selected != null) {
//            GuiaFacade ejbFacade = (GuiaFacade) this.getFacade();
//            this.isRegistroBoletoListEmpty = ejbFacade.isRegistroBoletoListEmpty(selected);
//        } else {
//            this.isRegistroBoletoListEmpty = true;
//        }
//    }

    /**
     * Sets the "items" attribute with a collection of RegistroBoleto entities
     * that are retrieved from Guia and returns the navigation outcome.
     *
     * @return navigation outcome for RegistroBoleto page
     */
//    public String navigateRegistroBoletoList() {
//        Guia selected = this.getSelected();
//        if (selected != null) {
//            GuiaFacade ejbFacade = (GuiaFacade) this.getFacade();
//            List<RegistroBoleto> selectedRegistroBoletoList = ejbFacade.findRegistroBoletoList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RegistroBoleto_items", selectedRegistroBoletoList);
//        }
//        return "/app/registroBoleto/index";
//    }

}
