package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.entities.TipoCargaFamiliar;
import com.areatecnica.sigf.facade.TipoCargaFamiliarFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoCargaFamiliarController")
@ViewScoped
public class TipoCargaFamiliarController extends AbstractController<TipoCargaFamiliar> {

    // Flags to indicate if child collections are empty
    private boolean isCargaTrabajadorListEmpty;

    public TipoCargaFamiliarController() {
        // Inform the Abstract parent controller of the concrete TipoCargaFamiliar Entity
        super(TipoCargaFamiliar.class);
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
        TipoCargaFamiliar selected = this.getSelected();
        if (selected != null) {
            TipoCargaFamiliarFacade ejbFacade = (TipoCargaFamiliarFacade) this.getFacade();
            this.isCargaTrabajadorListEmpty = ejbFacade.isCargaTrabajadorListEmpty(selected);
        } else {
            this.isCargaTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from TipoCargaFamiliar and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        TipoCargaFamiliar selected = this.getSelected();
        if (selected != null) {
            TipoCargaFamiliarFacade ejbFacade = (TipoCargaFamiliarFacade) this.getFacade();
            List<CargaTrabajador> selectedCargaTrabajadorList = ejbFacade.findCargaTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", selectedCargaTrabajadorList);
        }
        return "/app/cargaTrabajador/index";
    }

}
