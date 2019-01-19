package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoCombustible;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.CompraCombustible;
import java.util.List;
import com.areatecnica.sigf.facade.TipoCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoCombustibleController")
@ViewScoped
public class TipoCombustibleController extends AbstractController<TipoCombustible> {

    // Flags to indicate if child collections are empty
    private boolean isPrecioCombustibleListEmpty;
    private boolean isCompraCombustibleListEmpty;

    public TipoCombustibleController() {
        // Inform the Abstract parent controller of the concrete TipoCombustible Entity
        super(TipoCombustible.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPrecioCombustibleListEmpty();
        this.setIsCompraCombustibleListEmpty();
    }

    public boolean getIsPrecioCombustibleListEmpty() {
        return this.isPrecioCombustibleListEmpty;
    }

    private void setIsPrecioCombustibleListEmpty() {
        TipoCombustible selected = this.getSelected();
        if (selected != null) {
            TipoCombustibleFacade ejbFacade = (TipoCombustibleFacade) this.getFacade();
            this.isPrecioCombustibleListEmpty = ejbFacade.isPrecioCombustibleListEmpty(selected);
        } else {
            this.isPrecioCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PrecioCombustible
     * entities that are retrieved from TipoCombustible and returns the
     * navigation outcome.
     *
     * @return navigation outcome for PrecioCombustible page
     */
    public String navigatePrecioCombustibleList() {
        TipoCombustible selected = this.getSelected();
        if (selected != null) {
            TipoCombustibleFacade ejbFacade = (TipoCombustibleFacade) this.getFacade();
            List<PrecioCombustible> selectedPrecioCombustibleList = ejbFacade.findPrecioCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PrecioCombustible_items", selectedPrecioCombustibleList);
        }
        return "/app/precioCombustible/index";
    }

    public boolean getIsCompraCombustibleListEmpty() {
        return this.isCompraCombustibleListEmpty;
    }

    private void setIsCompraCombustibleListEmpty() {
        TipoCombustible selected = this.getSelected();
        if (selected != null) {
            TipoCombustibleFacade ejbFacade = (TipoCombustibleFacade) this.getFacade();
            this.isCompraCombustibleListEmpty = ejbFacade.isCompraCombustibleListEmpty(selected);
        } else {
            this.isCompraCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CompraCombustible
     * entities that are retrieved from TipoCombustible and returns the
     * navigation outcome.
     *
     * @return navigation outcome for CompraCombustible page
     */
    public String navigateCompraCombustibleList() {
        TipoCombustible selected = this.getSelected();
        if (selected != null) {
            TipoCombustibleFacade ejbFacade = (TipoCombustibleFacade) this.getFacade();
            List<CompraCombustible> selectedCompraCombustibleList = ejbFacade.findCompraCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CompraCombustible_items", selectedCompraCombustibleList);
        }
        return "/app/compraCombustible/index";
    }

}
