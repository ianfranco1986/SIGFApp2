package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.EstadoBus;
import com.areatecnica.sigf.entities.Bus;
import java.util.List;
import com.areatecnica.sigf.facade.EstadoBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "estadoBusController")
@ViewScoped
public class EstadoBusController extends AbstractController<EstadoBus> {

    // Flags to indicate if child collections are empty
    private boolean isBusListEmpty;

    public EstadoBusController() {
        // Inform the Abstract parent controller of the concrete EstadoBus Entity
        super(EstadoBus.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBusListEmpty();
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        EstadoBus selected = this.getSelected();
        if (selected != null) {
            EstadoBusFacade ejbFacade = (EstadoBusFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from EstadoBus and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        EstadoBus selected = this.getSelected();
        if (selected != null) {
            EstadoBusFacade ejbFacade = (EstadoBusFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

}
