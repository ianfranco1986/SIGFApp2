package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.TipoTrabajador;
import com.areatecnica.sigf.facade.TipoTrabajadorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoTrabajadorController")
@ViewScoped
public class TipoTrabajadorController extends AbstractController<TipoTrabajador> {

    @Inject
    private CuentaController tipoTrabajadorIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isRelacionLaboralListEmpty;

    public TipoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoTrabajador Entity
        super(TipoTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoTrabajadorIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRelacionLaboralListEmpty();
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        TipoTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoTrabajadorFacade ejbFacade = (TipoTrabajadorFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from TipoTrabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        TipoTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoTrabajadorFacade ejbFacade = (TipoTrabajadorFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoTrabajadorIdCuenta(ActionEvent event) {
        TipoTrabajador selected = this.getSelected();
        if (selected != null && tipoTrabajadorIdCuentaController.getSelected() == null) {
            tipoTrabajadorIdCuentaController.setSelected(selected.getTipoTrabajadorIdCuenta());
        }
    }

}
