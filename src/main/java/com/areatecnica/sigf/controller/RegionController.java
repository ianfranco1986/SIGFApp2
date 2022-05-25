package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Ciudad;
import com.areatecnica.sigf.entities.Region;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.facade.RegionFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "regionController")
@ViewScoped
public class RegionController extends AbstractController<Region> {

    // Flags to indicate if child collections are empty
    private boolean isCiudadListEmpty;
    private boolean isUnidadNegocioListEmpty;

    public RegionController() {
        // Inform the Abstract parent controller of the concrete Region Entity
        super(Region.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCiudadListEmpty();
        this.setIsUnidadNegocioListEmpty();
    }

    public boolean getIsCiudadListEmpty() {
        return this.isCiudadListEmpty;
    }

    private void setIsCiudadListEmpty() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            this.isCiudadListEmpty = ejbFacade.isCiudadListEmpty(selected);
        } else {
            this.isCiudadListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ciudad entities that are
     * retrieved from Region and returns the navigation outcome.
     *
     * @return navigation outcome for Ciudad page
     */
    public String navigateCiudadList() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            List<Ciudad> selectedCiudadList = ejbFacade.findCiudadList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ciudad_items", selectedCiudadList);
        }
        return "/app/ciudad/index";
    }

    public boolean getIsUnidadNegocioListEmpty() {
        return this.isUnidadNegocioListEmpty;
    }

    private void setIsUnidadNegocioListEmpty() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            this.isUnidadNegocioListEmpty = ejbFacade.isUnidadNegocioListEmpty(selected);
        } else {
            this.isUnidadNegocioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from Region and returns the navigation outcome.
     *
     * @return navigation outcome for UnidadNegocio page
     */
    public String navigateUnidadNegocioList() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            List<UnidadNegocio> selectedUnidadNegocioList = ejbFacade.findUnidadNegocioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UnidadNegocio_items", selectedUnidadNegocioList);
        }
        return "/app/unidadNegocio/index";
    }

}
