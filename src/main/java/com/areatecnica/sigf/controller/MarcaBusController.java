package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.MarcaBus;
import com.areatecnica.sigf.entities.ModeloMarcaBus;
import com.areatecnica.sigf.facade.MarcaBusFacade;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "marcaBusController")
@ViewScoped
public class MarcaBusController extends AbstractController<MarcaBus> {

    // Flags to indicate if child collections are empty
    private boolean isModeloMarcaBusListEmpty;

    public MarcaBusController() {
        // Inform the Abstract parent controller of the concrete MarcaBus Entity
        super(MarcaBus.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsModeloMarcaBusListEmpty();
    }

    public boolean getIsModeloMarcaBusListEmpty() {
        return this.isModeloMarcaBusListEmpty;
    }

    private void setIsModeloMarcaBusListEmpty() {
        MarcaBus selected = this.getSelected();
        if (selected != null) {
            MarcaBusFacade ejbFacade = (MarcaBusFacade) this.getFacade();
            this.isModeloMarcaBusListEmpty = ejbFacade.isModeloMarcaBusListEmpty(selected);
        } else {
            this.isModeloMarcaBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ModeloMarcaBus entities
     * that are retrieved from MarcaBus and returns the navigation outcome.
     *
     * @return navigation outcome for ModeloMarcaBus page
     */
    public String navigateModeloMarcaBusList() {
        MarcaBus selected = this.getSelected();
        if (selected != null) {
            MarcaBusFacade ejbFacade = (MarcaBusFacade) this.getFacade();
            List<ModeloMarcaBus> selectedModeloMarcaBusList = ejbFacade.findModeloMarcaBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ModeloMarcaBus_items", selectedModeloMarcaBusList);
        }
        return "/app/modeloMarcaBus/index";
    }

}
