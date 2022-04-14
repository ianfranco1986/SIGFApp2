package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DescuentoTrabajador;
import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import java.util.List;
import com.areatecnica.sigf.facade.DescuentoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "descuentoTrabajadorController")
@ViewScoped
public class DescuentoTrabajadorController extends AbstractController<DescuentoTrabajador> {

    @Inject
    private TipoDescuentoTrabajadorController descuentoTrabajadorIdDescuentoController;
    @Inject
    private TrabajadorController descuentoTrabajadorIdTrabajadorController;

    // Flags to indicate if child collections are empty
    private boolean isDescuentoLiquidacionListEmpty;

    public DescuentoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete DescuentoTrabajador Entity
        super(DescuentoTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoTrabajadorIdDescuentoController.setSelected(null);
        descuentoTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDescuentoLiquidacionListEmpty();
    }

    public boolean getIsDescuentoLiquidacionListEmpty() {
        return this.isDescuentoLiquidacionListEmpty;
    }

    private void setIsDescuentoLiquidacionListEmpty() {
        DescuentoTrabajador selected = this.getSelected();
        if (selected != null) {
            DescuentoTrabajadorFacade ejbFacade = (DescuentoTrabajadorFacade) this.getFacade();
            this.isDescuentoLiquidacionListEmpty = ejbFacade.isDescuentoLiquidacionListEmpty(selected);
        } else {
            this.isDescuentoLiquidacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoLiquidacion
     * entities that are retrieved from DescuentoTrabajador and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DescuentoLiquidacion page
     */
    public String navigateDescuentoLiquidacionList() {
        DescuentoTrabajador selected = this.getSelected();
        if (selected != null) {
            DescuentoTrabajadorFacade ejbFacade = (DescuentoTrabajadorFacade) this.getFacade();
            List<DescuentoLiquidacion> selectedDescuentoLiquidacionList = ejbFacade.findDescuentoLiquidacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoLiquidacion_items", selectedDescuentoLiquidacionList);
        }
        return "/app/descuentoLiquidacion/index";
    }

    /**
     * Sets the "selected" attribute of the TipoDescuentoTrabajador controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoTrabajadorIdDescuento(ActionEvent event) {
        DescuentoTrabajador selected = this.getSelected();
        if (selected != null && descuentoTrabajadorIdDescuentoController.getSelected() == null) {
            descuentoTrabajadorIdDescuentoController.setSelected(selected.getDescuentoTrabajadorIdDescuento());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoTrabajadorIdTrabajador(ActionEvent event) {
        DescuentoTrabajador selected = this.getSelected();
        if (selected != null && descuentoTrabajadorIdTrabajadorController.getSelected() == null) {
            descuentoTrabajadorIdTrabajadorController.setSelected(selected.getDescuentoTrabajadorIdTrabajador());
        }
    }

}
