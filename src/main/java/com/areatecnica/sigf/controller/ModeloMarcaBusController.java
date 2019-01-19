package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ModeloMarcaBus;
import com.areatecnica.sigf.entities.Bus;
import java.util.List;
import com.areatecnica.sigf.facade.ModeloMarcaBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "modeloMarcaBusController")
@ViewScoped
public class ModeloMarcaBusController extends AbstractController<ModeloMarcaBus> {

    @Inject
    private MarcaBusController modeloMarcaBusIdMarcaController;

    // Flags to indicate if child collections are empty
    private boolean isBusListEmpty;

    public ModeloMarcaBusController() {
        // Inform the Abstract parent controller of the concrete ModeloMarcaBus Entity
        super(ModeloMarcaBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        modeloMarcaBusIdMarcaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsBusListEmpty();
    }

    /**
     * Sets the "selected" attribute of the MarcaBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareModeloMarcaBusIdMarca(ActionEvent event) {
        ModeloMarcaBus selected = this.getSelected();
        if (selected != null && modeloMarcaBusIdMarcaController.getSelected() == null) {
            modeloMarcaBusIdMarcaController.setSelected(selected.getModeloMarcaBusIdMarca());
        }
    }

    public boolean getIsBusListEmpty() {
        return this.isBusListEmpty;
    }

    private void setIsBusListEmpty() {
        ModeloMarcaBus selected = this.getSelected();
        if (selected != null) {
            ModeloMarcaBusFacade ejbFacade = (ModeloMarcaBusFacade) this.getFacade();
            this.isBusListEmpty = ejbFacade.isBusListEmpty(selected);
        } else {
            this.isBusListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bus entities that are
     * retrieved from ModeloMarcaBus and returns the navigation outcome.
     *
     * @return navigation outcome for Bus page
     */
    public String navigateBusList() {
        ModeloMarcaBus selected = this.getSelected();
        if (selected != null) {
            ModeloMarcaBusFacade ejbFacade = (ModeloMarcaBusFacade) this.getFacade();
            List<Bus> selectedBusList = ejbFacade.findBusList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bus_items", selectedBusList);
        }
        return "/app/bus/index";
    }

}
