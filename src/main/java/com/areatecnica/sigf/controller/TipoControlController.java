package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.TipoControl;
import com.areatecnica.sigf.facade.TipoControlFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoControlController")
@ViewScoped
public class TipoControlController extends AbstractController<TipoControl> {

    @Inject
    private CuentaController tipoControlIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isControlListEmpty;

    public TipoControlController() {
        // Inform the Abstract parent controller of the concrete TipoControl Entity
        super(TipoControl.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoControlIdCuentaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsControlListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoControlIdCuenta(ActionEvent event) {
        TipoControl selected = this.getSelected();
        if (selected != null && tipoControlIdCuentaController.getSelected() == null) {
            tipoControlIdCuentaController.setSelected(selected.getTipoControlIdCuenta());
        }
    }

    public boolean getIsControlListEmpty() {
        return this.isControlListEmpty;
    }

    private void setIsControlListEmpty() {
        TipoControl selected = this.getSelected();
        if (selected != null) {
            TipoControlFacade ejbFacade = (TipoControlFacade) this.getFacade();
            this.isControlListEmpty = ejbFacade.isControlListEmpty(selected);
        } else {
            this.isControlListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Control entities that are
     * retrieved from TipoControl and returns the navigation outcome.
     *
     * @return navigation outcome for Control page
     */
    public String navigateControlList() {
        TipoControl selected = this.getSelected();
        if (selected != null) {
            TipoControlFacade ejbFacade = (TipoControlFacade) this.getFacade();
            List<Control> selectedControlList = ejbFacade.findControlList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Control_items", selectedControlList);
        }
        return "/app/control/index";
    }

}
