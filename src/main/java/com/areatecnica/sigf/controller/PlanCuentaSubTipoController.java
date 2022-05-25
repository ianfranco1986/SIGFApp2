package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.facade.PlanCuentaSubTipoFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "planCuentaSubTipoController")
@ViewScoped
public class PlanCuentaSubTipoController extends AbstractController<PlanCuentaSubTipo> {

    // Flags to indicate if child collections are empty
    private boolean isPlanCuentaListEmpty;
    private boolean isCuentaMayorListEmpty;

    public PlanCuentaSubTipoController() {
        // Inform the Abstract parent controller of the concrete PlanCuentaSubTipo Entity
        super(PlanCuentaSubTipo.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPlanCuentaListEmpty();
        this.setIsCuentaMayorListEmpty();
    }

    public boolean getIsPlanCuentaListEmpty() {
        return this.isPlanCuentaListEmpty;
    }

    private void setIsPlanCuentaListEmpty() {
        PlanCuentaSubTipo selected = this.getSelected();
        if (selected != null) {
            PlanCuentaSubTipoFacade ejbFacade = (PlanCuentaSubTipoFacade) this.getFacade();
            this.isPlanCuentaListEmpty = ejbFacade.isPlanCuentaListEmpty(selected);
        } else {
            this.isPlanCuentaListEmpty = true;
        }
    }


    public boolean getIsCuentaMayorListEmpty() {
        return this.isCuentaMayorListEmpty;
    }

    private void setIsCuentaMayorListEmpty() {
        PlanCuentaSubTipo selected = this.getSelected();
        if (selected != null) {
            PlanCuentaSubTipoFacade ejbFacade = (PlanCuentaSubTipoFacade) this.getFacade();
            this.isCuentaMayorListEmpty = ejbFacade.isCuentaMayorListEmpty(selected);
        } else {
            this.isCuentaMayorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CuentaMayor entities that
     * are retrieved from PlanCuentaSubTipo and returns the navigation outcome.
     *
     * @return navigation outcome for CuentaMayor page
     */
    public String navigateCuentaMayorList() {
        PlanCuentaSubTipo selected = this.getSelected();
        if (selected != null) {
            PlanCuentaSubTipoFacade ejbFacade = (PlanCuentaSubTipoFacade) this.getFacade();
            List<CuentaMayor> selectedCuentaMayorList = ejbFacade.findCuentaMayorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaMayor_items", selectedCuentaMayorList);
        }
        return "/app/cuentaMayor/index";
    }

}
