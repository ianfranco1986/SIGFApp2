package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.TipoInstitucionApv;
import com.areatecnica.sigf.facade.TipoInstitucionApvFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "tipoInstitucionApvController")
@ViewScoped
public class TipoInstitucionApvController extends AbstractController<TipoInstitucionApv> {

    // Flags to indicate if child collections are empty
    private boolean isInstitucionApvListEmpty;

    public TipoInstitucionApvController() {
        // Inform the Abstract parent controller of the concrete TipoInstitucionApv Entity
        super(TipoInstitucionApv.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsInstitucionApvListEmpty();
    }

    public boolean getIsInstitucionApvListEmpty() {
        return this.isInstitucionApvListEmpty;
    }

    private void setIsInstitucionApvListEmpty() {
        TipoInstitucionApv selected = this.getSelected();
        if (selected != null) {
            TipoInstitucionApvFacade ejbFacade = (TipoInstitucionApvFacade) this.getFacade();
            this.isInstitucionApvListEmpty = ejbFacade.isInstitucionApvListEmpty(selected);
        } else {
            this.isInstitucionApvListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionApv entities
     * that are retrieved from TipoInstitucionApv and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InstitucionApv page
     */
    public String navigateInstitucionApvList() {
        TipoInstitucionApv selected = this.getSelected();
        if (selected != null) {
            TipoInstitucionApvFacade ejbFacade = (TipoInstitucionApvFacade) this.getFacade();
            List<InstitucionApv> selectedInstitucionApvList = ejbFacade.findInstitucionApvList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionApv_items", selectedInstitucionApvList);
        }
        return "/app/institucionApv/index";
    }

}
