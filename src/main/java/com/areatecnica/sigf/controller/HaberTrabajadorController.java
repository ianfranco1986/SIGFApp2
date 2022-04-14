package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.HaberLiquidacion;
import java.util.List;
import com.areatecnica.sigf.facade.HaberTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "haberTrabajadorController")
@ViewScoped
public class HaberTrabajadorController extends AbstractController<HaberTrabajador> {

    @Inject
    private TipoHaberTrabajadorController haberTrabajadorIdHaberController;
    @Inject
    private TrabajadorController haberTrabajadorIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isHaberLiquidacionListEmpty;

    public HaberTrabajadorController() {
        // Inform the Abstract parent controller of the concrete HaberTrabajador Entity
        super(HaberTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        haberTrabajadorIdHaberController.setSelected(null);
        haberTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHaberLiquidacionListEmpty();
    }

    /**
     * Sets the "selected" attribute of the TipoHaberTrabajador controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberTrabajadorIdHaber(ActionEvent event) {
        HaberTrabajador selected = this.getSelected();
        if (selected != null && haberTrabajadorIdHaberController.getSelected() == null) {
            haberTrabajadorIdHaberController.setSelected(selected.getHaberTrabajadorIdHaber());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberTrabajadorIdTrabajador(ActionEvent event) {
        HaberTrabajador selected = this.getSelected();
        if (selected != null && haberTrabajadorIdTrabajadorController.getSelected() == null) {
            haberTrabajadorIdTrabajadorController.setSelected(selected.getHaberTrabajadorIdTrabajador());
        }
    }

    public boolean getIsHaberLiquidacionListEmpty() {
        return this.isHaberLiquidacionListEmpty;
    }

    private void setIsHaberLiquidacionListEmpty() {
        HaberTrabajador selected = this.getSelected();
        if (selected != null) {
            HaberTrabajadorFacade ejbFacade = (HaberTrabajadorFacade) this.getFacade();
            this.isHaberLiquidacionListEmpty = ejbFacade.isHaberLiquidacionListEmpty(selected);
        } else {
            this.isHaberLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HaberLiquidacion entities
     * that are retrieved from HaberTrabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HaberLiquidacion page
     */
    public String navigateHaberLiquidacionList() {
        HaberTrabajador selected = this.getSelected();
        if (selected != null) {
            HaberTrabajadorFacade ejbFacade = (HaberTrabajadorFacade) this.getFacade();
            List<HaberLiquidacion> selectedHaberLiquidacionList = ejbFacade.findHaberLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberLiquidacion_items", selectedHaberLiquidacionList);
        }
        return "/app/haberLiquidacion/index";
    }

}
