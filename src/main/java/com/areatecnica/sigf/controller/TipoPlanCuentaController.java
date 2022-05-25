package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoPlanCuenta;
import com.areatecnica.sigf.facade.TipoPlanCuentaFacade;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "tipoPlanCuentaController")
@ViewScoped
public class TipoPlanCuentaController extends AbstractController<TipoPlanCuenta> {

    // Flags to indicate if child collections are empty
    private boolean isPlanCuentaListEmpty;

    public TipoPlanCuentaController() {
        // Inform the Abstract parent controller of the concrete TipoPlanCuenta Entity
        super(TipoPlanCuenta.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPlanCuentaListEmpty();
    }

    public boolean getIsPlanCuentaListEmpty() {
        return this.isPlanCuentaListEmpty;
    }

    private void setIsPlanCuentaListEmpty() {
        TipoPlanCuenta selected = this.getSelected();
        if (selected != null) {
            TipoPlanCuentaFacade ejbFacade = (TipoPlanCuentaFacade) this.getFacade();
            this.isPlanCuentaListEmpty = ejbFacade.isPlanCuentaListEmpty(selected);
        } else {
            this.isPlanCuentaListEmpty = true;
        }
    }


}
