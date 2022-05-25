package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Ciudad;
import com.areatecnica.sigf.entities.Comuna;
import com.areatecnica.sigf.facade.CiudadFacade;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "ciudadController")
@ViewScoped
public class CiudadController extends AbstractController<Ciudad> {

    @Inject
    private RegionController ciudadIdRegionController;

    // Flags to indicate if child collections are empty
    private boolean isComunaListEmpty;

    public CiudadController() {
        // Inform the Abstract parent controller of the concrete Ciudad Entity
        super(Ciudad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ciudadIdRegionController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsComunaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Region controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCiudadIdRegion(ActionEvent event) {
        Ciudad selected = this.getSelected();
        if (selected != null && ciudadIdRegionController.getSelected() == null) {
            ciudadIdRegionController.setSelected(selected.getCiudadIdRegion());
        }
    }

    public boolean getIsComunaListEmpty() {
        return this.isComunaListEmpty;
    }

    private void setIsComunaListEmpty() {
        Ciudad selected = this.getSelected();
        if (selected != null) {
            CiudadFacade ejbFacade = (CiudadFacade) this.getFacade();
            this.isComunaListEmpty = ejbFacade.isComunaListEmpty(selected);
        } else {
            this.isComunaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Comuna entities that are
     * retrieved from Ciudad and returns the navigation outcome.
     *
     * @return navigation outcome for Comuna page
     */
    public String navigateComunaList() {
        Ciudad selected = this.getSelected();
        if (selected != null) {
            CiudadFacade ejbFacade = (CiudadFacade) this.getFacade();
            List<Comuna> selectedComunaList = ejbFacade.findComunaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Comuna_items", selectedComunaList);
        }
        return "/app/comuna/index";
    }

}
