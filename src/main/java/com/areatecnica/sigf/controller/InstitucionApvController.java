package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.InstitucionApvFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "institucionApvController")
@ViewScoped
public class InstitucionApvController extends AbstractController<InstitucionApv> {

    @Inject
    private CuentaController institucionApvIdCuentaController;
    @Inject
    private TipoInstitucionApvController institucionApvIdTipoController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public InstitucionApvController() {
        // Inform the Abstract parent controller of the concrete InstitucionApv Entity
        super(InstitucionApv.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        institucionApvIdCuentaController.setSelected(null);
        institucionApvIdTipoController.setSelected(null);
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
        InstitucionApv selected = this.getSelected();
        if (selected != null) {
            InstitucionApvFacade ejbFacade = (InstitucionApvFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from InstitucionApv and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        InstitucionApv selected = this.getSelected();
        if (selected != null) {
            InstitucionApvFacade ejbFacade = (InstitucionApvFacade) this.getFacade();
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
    public void prepareInstitucionApvIdCuenta(ActionEvent event) {
        InstitucionApv selected = this.getSelected();
        if (selected != null && institucionApvIdCuentaController.getSelected() == null) {
            institucionApvIdCuentaController.setSelected(selected.getInstitucionApvIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoInstitucionApv controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInstitucionApvIdTipo(ActionEvent event) {
        InstitucionApv selected = this.getSelected();
        if (selected != null && institucionApvIdTipoController.getSelected() == null) {
            institucionApvIdTipoController.setSelected(selected.getInstitucionApvIdTipo());
        }
    }

}
