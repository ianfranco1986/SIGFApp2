package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AsignacionFamiliar;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.AsignacionFamiliarFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "asignacionFamiliarController")
@ViewScoped
public class AsignacionFamiliarController extends AbstractController<AsignacionFamiliar> {

    @Inject
    private CuentaController asignacionFamiliarIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public AsignacionFamiliarController() {
        // Inform the Abstract parent controller of the concrete AsignacionFamiliar Entity
        super(AsignacionFamiliar.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        asignacionFamiliarIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsTrabajadorListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAsignacionFamiliarIdCuenta(ActionEvent event) {
        AsignacionFamiliar selected = this.getSelected();
        if (selected != null && asignacionFamiliarIdCuentaController.getSelected() == null) {
            asignacionFamiliarIdCuentaController.setSelected(selected.getAsignacionFamiliarIdCuenta());
        }
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        AsignacionFamiliar selected = this.getSelected();
        if (selected != null) {
            AsignacionFamiliarFacade ejbFacade = (AsignacionFamiliarFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from AsignacionFamiliar and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        AsignacionFamiliar selected = this.getSelected();
        if (selected != null) {
            AsignacionFamiliarFacade ejbFacade = (AsignacionFamiliarFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
