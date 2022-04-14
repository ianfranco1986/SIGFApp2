package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoCotizacionTrabajador;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.TipoCotizacionTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoCotizacionTrabajadorController")
@ViewScoped
public class TipoCotizacionTrabajadorController extends AbstractController<TipoCotizacionTrabajador> {

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public TipoCotizacionTrabajadorController() {
        // Inform the Abstract parent controller of the concrete TipoCotizacionTrabajador Entity
        super(TipoCotizacionTrabajador.class);
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
        TipoCotizacionTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoCotizacionTrabajadorFacade ejbFacade = (TipoCotizacionTrabajadorFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from TipoCotizacionTrabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        TipoCotizacionTrabajador selected = this.getSelected();
        if (selected != null) {
            TipoCotizacionTrabajadorFacade ejbFacade = (TipoCotizacionTrabajadorFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
