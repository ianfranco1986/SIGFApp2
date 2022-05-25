package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.EstadoCivil;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.facade.EstadoCivilFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "estadoCivilController")
@ViewScoped
public class EstadoCivilController extends AbstractController<EstadoCivil> {

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public EstadoCivilController() {
        // Inform the Abstract parent controller of the concrete EstadoCivil Entity
        super(EstadoCivil.class);
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
        EstadoCivil selected = this.getSelected();
        if (selected != null) {
            EstadoCivilFacade ejbFacade = (EstadoCivilFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from EstadoCivil and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        EstadoCivil selected = this.getSelected();
        if (selected != null) {
            EstadoCivilFacade ejbFacade = (EstadoCivilFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
