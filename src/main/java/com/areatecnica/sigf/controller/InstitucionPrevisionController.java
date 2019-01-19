package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.InstitucionPrevision;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.InstitucionPrevisionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "institucionPrevisionController")
@ViewScoped
public class InstitucionPrevisionController extends AbstractController<InstitucionPrevision> {

    @Inject
    private CuentaController institucionPrevisionIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public InstitucionPrevisionController() {
        // Inform the Abstract parent controller of the concrete InstitucionPrevision Entity
        super(InstitucionPrevision.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        institucionPrevisionIdCuentaController.setSelected(null);
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
        InstitucionPrevision selected = this.getSelected();
        if (selected != null) {
            InstitucionPrevisionFacade ejbFacade = (InstitucionPrevisionFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionPrevision and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        InstitucionPrevision selected = this.getSelected();
        if (selected != null) {
            InstitucionPrevisionFacade ejbFacade = (InstitucionPrevisionFacade) this.getFacade();
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
    public void prepareInstitucionPrevisionIdCuenta(ActionEvent event) {
        InstitucionPrevision selected = this.getSelected();
        if (selected != null && institucionPrevisionIdCuentaController.getSelected() == null) {
            institucionPrevisionIdCuentaController.setSelected(selected.getInstitucionPrevisionIdCuenta());
        }
    }

}
