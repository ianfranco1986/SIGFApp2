package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.InstitucionSalud;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.facade.InstitucionSaludFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "institucionSaludController")
@ViewScoped
public class InstitucionSaludController extends AbstractController<InstitucionSalud> {

    @Inject
    private CuentaController institucionSaludIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public InstitucionSaludController() {
        // Inform the Abstract parent controller of the concrete InstitucionSalud Entity
        super(InstitucionSalud.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        institucionSaludIdCuentaController.setSelected(null);
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
    public void prepareInstitucionSaludIdCuenta(ActionEvent event) {
        InstitucionSalud selected = this.getSelected();
        if (selected != null && institucionSaludIdCuentaController.getSelected() == null) {
            institucionSaludIdCuentaController.setSelected(selected.getInstitucionSaludIdCuenta());
        }
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        InstitucionSalud selected = this.getSelected();
        if (selected != null) {
            InstitucionSaludFacade ejbFacade = (InstitucionSaludFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionSalud and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        InstitucionSalud selected = this.getSelected();
        if (selected != null) {
            InstitucionSaludFacade ejbFacade = (InstitucionSaludFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
