package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ParentescoCarga;
import com.areatecnica.sigf.entities.CargaTrabajador;
import java.util.List;
import com.areatecnica.sigf.facade.ParentescoCargaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "parentescoCargaController")
@ViewScoped
public class ParentescoCargaController extends AbstractController<ParentescoCarga> {

    // Flags to indicate if child collections are empty
    private boolean isCargaTrabajadorListEmpty;

    public ParentescoCargaController() {
        // Inform the Abstract parent controller of the concrete ParentescoCarga Entity
        super(ParentescoCarga.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCargaTrabajadorListEmpty();
    }

    public boolean getIsCargaTrabajadorListEmpty() {
        return this.isCargaTrabajadorListEmpty;
    }

    private void setIsCargaTrabajadorListEmpty() {
        ParentescoCarga selected = this.getSelected();
        if (selected != null) {
            ParentescoCargaFacade ejbFacade = (ParentescoCargaFacade) this.getFacade();
            this.isCargaTrabajadorListEmpty = ejbFacade.isCargaTrabajadorListEmpty(selected);
        } else {
            this.isCargaTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from ParentescoCarga and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        ParentescoCarga selected = this.getSelected();
        if (selected != null) {
            ParentescoCargaFacade ejbFacade = (ParentescoCargaFacade) this.getFacade();
            List<CargaTrabajador> selectedCargaTrabajadorList = ejbFacade.findCargaTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", selectedCargaTrabajadorList);
        }
        return "/app/cargaTrabajador/index";
    }

}
