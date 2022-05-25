package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CausalFiniquito;
import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import com.areatecnica.sigf.facade.CausalFiniquitoFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "causalFiniquitoController")
@ViewScoped
public class CausalFiniquitoController extends AbstractController<CausalFiniquito> {

    // Flags to indicate if child collections are empty
    private boolean isFiniquitoRelacionLaboralListEmpty;

    public CausalFiniquitoController() {
        // Inform the Abstract parent controller of the concrete CausalFiniquito Entity
        super(CausalFiniquito.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsFiniquitoRelacionLaboralListEmpty();
    }

    public boolean getIsFiniquitoRelacionLaboralListEmpty() {
        return this.isFiniquitoRelacionLaboralListEmpty;
    }

    private void setIsFiniquitoRelacionLaboralListEmpty() {
        CausalFiniquito selected = this.getSelected();
        if (selected != null) {
            CausalFiniquitoFacade ejbFacade = (CausalFiniquitoFacade) this.getFacade();
            this.isFiniquitoRelacionLaboralListEmpty = ejbFacade.isFiniquitoRelacionLaboralListEmpty(selected);
        } else {
            this.isFiniquitoRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FiniquitoRelacionLaboral
     * entities that are retrieved from CausalFiniquito and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FiniquitoRelacionLaboral page
     */
    public String navigateFiniquitoRelacionLaboralList() {
        CausalFiniquito selected = this.getSelected();
        if (selected != null) {
            CausalFiniquitoFacade ejbFacade = (CausalFiniquitoFacade) this.getFacade();
            List<FiniquitoRelacionLaboral> selectedFiniquitoRelacionLaboralList = ejbFacade.findFiniquitoRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FiniquitoRelacionLaboral_items", selectedFiniquitoRelacionLaboralList);
        }
        return "/app/finiquitoRelacionLaboral/index";
    }

}
