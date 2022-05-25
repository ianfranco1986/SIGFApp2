package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.entities.TipoDemanda;
import com.areatecnica.sigf.facade.TipoDemandaFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoDemandaController")
@ViewScoped
public class TipoDemandaController extends AbstractController<TipoDemanda> {

    // Flags to indicate if child collections are empty
    private boolean isFrecuenciaServicioListEmpty;

    public TipoDemandaController() {
        // Inform the Abstract parent controller of the concrete TipoDemanda Entity
        super(TipoDemanda.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsFrecuenciaServicioListEmpty();
    }

    public boolean getIsFrecuenciaServicioListEmpty() {
        return this.isFrecuenciaServicioListEmpty;
    }

    private void setIsFrecuenciaServicioListEmpty() {
        TipoDemanda selected = this.getSelected();
        if (selected != null) {
            TipoDemandaFacade ejbFacade = (TipoDemandaFacade) this.getFacade();
            this.isFrecuenciaServicioListEmpty = ejbFacade.isFrecuenciaServicioListEmpty(selected);
        } else {
            this.isFrecuenciaServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from TipoDemanda and returns the navigation
     * outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        TipoDemanda selected = this.getSelected();
        if (selected != null) {
            TipoDemandaFacade ejbFacade = (TipoDemandaFacade) this.getFacade();
            List<FrecuenciaServicio> selectedFrecuenciaServicioList = ejbFacade.findFrecuenciaServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", selectedFrecuenciaServicioList);
        }
        return "/app/frecuenciaServicio/index";
    }

}
