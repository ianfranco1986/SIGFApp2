package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoCuentaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import java.util.List;
import com.areatecnica.sigf.facade.TipoCuentaBancoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoCuentaBancoController")
@ViewScoped
public class TipoCuentaBancoController extends AbstractController<TipoCuentaBanco> {

    // Flags to indicate if child collections are empty
    private boolean isCuentaBancariaListEmpty;

    public TipoCuentaBancoController() {
        // Inform the Abstract parent controller of the concrete TipoCuentaBanco Entity
        super(TipoCuentaBanco.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCuentaBancariaListEmpty();
    }

    public boolean getIsCuentaBancariaListEmpty() {
        return this.isCuentaBancariaListEmpty;
    }

    private void setIsCuentaBancariaListEmpty() {
        TipoCuentaBanco selected = this.getSelected();
        if (selected != null) {
            TipoCuentaBancoFacade ejbFacade = (TipoCuentaBancoFacade) this.getFacade();
            this.isCuentaBancariaListEmpty = ejbFacade.isCuentaBancariaListEmpty(selected);
        } else {
            this.isCuentaBancariaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancaria entities
     * that are retrieved from TipoCuentaBanco and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CuentaBancaria page
     */
    public String navigateCuentaBancariaList() {
        TipoCuentaBanco selected = this.getSelected();
        if (selected != null) {
            TipoCuentaBancoFacade ejbFacade = (TipoCuentaBancoFacade) this.getFacade();
            List<CuentaBancaria> selectedCuentaBancariaList = ejbFacade.findCuentaBancariaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancaria_items", selectedCuentaBancariaList);
        }
        return "/app/cuentaBancaria/index";
    }

}
