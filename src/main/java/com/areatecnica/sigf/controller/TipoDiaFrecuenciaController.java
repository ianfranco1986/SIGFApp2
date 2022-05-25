package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.entities.TipoDiaFrecuencia;
import com.areatecnica.sigf.facade.TipoDiaFrecuenciaFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoDiaFrecuenciaController")
@ViewScoped
public class TipoDiaFrecuenciaController extends AbstractController<TipoDiaFrecuencia> {

    // Flags to indicate if child collections are empty
    private boolean isFrecuenciaServicioListEmpty;

    public TipoDiaFrecuenciaController() {
        // Inform the Abstract parent controller of the concrete TipoDiaFrecuencia Entity
        super(TipoDiaFrecuencia.class);
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
        TipoDiaFrecuencia selected = this.getSelected();
        if (selected != null) {
            TipoDiaFrecuenciaFacade ejbFacade = (TipoDiaFrecuenciaFacade) this.getFacade();
            this.isFrecuenciaServicioListEmpty = ejbFacade.isFrecuenciaServicioListEmpty(selected);
        } else {
            this.isFrecuenciaServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FrecuenciaServicio
     * entities that are retrieved from TipoDiaFrecuencia and returns the
     * navigation outcome.
     *
     * @return navigation outcome for FrecuenciaServicio page
     */
    public String navigateFrecuenciaServicioList() {
        TipoDiaFrecuencia selected = this.getSelected();
        if (selected != null) {
            TipoDiaFrecuenciaFacade ejbFacade = (TipoDiaFrecuenciaFacade) this.getFacade();
            List<FrecuenciaServicio> selectedFrecuenciaServicioList = ejbFacade.findFrecuenciaServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FrecuenciaServicio_items", selectedFrecuenciaServicioList);
        }
        return "/app/frecuenciaServicio/index";
    }

}
