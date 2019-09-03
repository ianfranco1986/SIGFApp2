package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoPlanCuenta;
import com.areatecnica.sigf.entities.PlanCuenta;
import java.util.List;
import com.areatecnica.sigf.facade.TipoPlanCuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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

    /**
     * Sets the "items" attribute with a collection of PlanCuenta entities that
     * are retrieved from TipoPlanCuenta and returns the navigation outcome.
     *
     * @return navigation outcome for PlanCuenta page
     */
    public String navigatePlanCuentaList() {
        TipoPlanCuenta selected = this.getSelected();
        if (selected != null) {
            TipoPlanCuentaFacade ejbFacade = (TipoPlanCuentaFacade) this.getFacade();
            List<PlanCuenta> selectedPlanCuentaList = ejbFacade.findPlanCuentaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PlanCuenta_items", selectedPlanCuentaList);
        }
        return "/app/planCuenta/index";
    }

}
