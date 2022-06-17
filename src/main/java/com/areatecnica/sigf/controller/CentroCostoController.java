package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CentroCosto;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.facade.CentroCostoFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "centroCostoController")
@ViewScoped
public class CentroCostoController extends AbstractController<CentroCosto> {

    @Inject
    private CuentaController centroCostoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public CentroCostoController() {
        // Inform the Abstract parent controller of the concrete CentroCosto Entity
        super(CentroCosto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        centroCostoIdCuentaController.setSelected(null);
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
        CentroCosto selected = this.getSelected();
        if (selected != null) {
            CentroCostoFacade ejbFacade = (CentroCostoFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

}
