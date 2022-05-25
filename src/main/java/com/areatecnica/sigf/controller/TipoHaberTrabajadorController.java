package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.TipoHaberTrabajador;
import com.areatecnica.sigf.facade.TipoHaberTrabajadorFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoHaberTrabajadorController")
@ViewScoped
public class TipoHaberTrabajadorController extends AbstractController<TipoHaberTrabajador> {

    @Inject
    private CuentaController tipoHaberTrabajadorIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isHaberTrabajadorListEmpty;

    public TipoHaberTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoHaberTrabajador Entity
        super(TipoHaberTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoHaberTrabajadorIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHaberTrabajadorListEmpty();
    }

    public boolean getIsHaberTrabajadorListEmpty() {
        return this.isHaberTrabajadorListEmpty;
    }

    private void setIsHaberTrabajadorListEmpty() {
        TipoHaberTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoHaberTrabajadorFacade ejbFacade = (TipoHaberTrabajadorFacade) this.getFacade();
            this.isHaberTrabajadorListEmpty = ejbFacade.isHaberTrabajadorListEmpty(selected);
        } else {
            this.isHaberTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HaberTrabajador entities
     * that are retrieved from TipoHaberTrabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HaberTrabajador page
     */
    public String navigateHaberTrabajadorList() {
        TipoHaberTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoHaberTrabajadorFacade ejbFacade = (TipoHaberTrabajadorFacade) this.getFacade();
            List<HaberTrabajador> selectedHaberTrabajadorList = ejbFacade.findHaberTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberTrabajador_items", selectedHaberTrabajadorList);
        }
        return "/app/haberTrabajador/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoHaberTrabajadorIdCuenta(ActionEvent event) {
        TipoHaberTrabajador selected = this.getSelected();
        if (selected != null && tipoHaberTrabajadorIdCuentaController.getSelected() == null) {
            tipoHaberTrabajadorIdCuentaController.setSelected(selected.getTipoHaberTrabajadorIdCuenta());
        }
    }

}
