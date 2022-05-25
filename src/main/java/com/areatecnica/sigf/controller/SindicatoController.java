package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Sindicato;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.facade.SindicatoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "sindicatoController")
@ViewScoped
public class SindicatoController extends AbstractController<Sindicato> {

    @Inject
    private CuentaController sindicatoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public SindicatoController() {
        // Inform the Abstract parent controller of the concrete Sindicato Entity
        super(Sindicato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        sindicatoIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsTrabajadorListEmpty();
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        Sindicato selected = this.getSelected();
        if (selected != null) {
            SindicatoFacade ejbFacade = (SindicatoFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Sindicato and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        Sindicato selected = this.getSelected();
        if (selected != null) {
            SindicatoFacade ejbFacade = (SindicatoFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSindicatoIdCuenta(ActionEvent event) {
        Sindicato selected = this.getSelected();
        if (selected != null && sindicatoIdCuentaController.getSelected() == null) {
            sindicatoIdCuentaController.setSelected(selected.getSindicatoIdCuenta());
        }
    }

}
