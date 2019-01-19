package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FormaPago;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import com.areatecnica.sigf.facade.FormaPagoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "formaPagoController")
@ViewScoped
public class FormaPagoController extends AbstractController<FormaPago> {

    // Flags to indicate if child collections are empty
    private boolean isTrabajadorListEmpty;

    public FormaPagoController() {
        // Inform the Abstract parent controller of the concrete FormaPago Entity
        super(FormaPago.class);
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
        FormaPago selected = this.getSelected();
        if (selected != null) {
            FormaPagoFacade ejbFacade = (FormaPagoFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from FormaPago and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        FormaPago selected = this.getSelected();
        if (selected != null) {
            FormaPagoFacade ejbFacade = (FormaPagoFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

}
