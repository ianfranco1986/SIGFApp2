package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import com.areatecnica.sigf.facade.DescuentoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "descuentoLiquidacionController")
@ViewScoped
public class DescuentoLiquidacionController extends AbstractController<DescuentoLiquidacion> {

    @Inject
    private DescuentoTrabajadorController descuentoLiquidacionIdDescuentoTrabajadorController;
    @Inject
    private LiquidacionSueldoController descuentoLiquidacionIdLiquidacionSueldoController;

    public DescuentoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete DescuentoLiquidacion Entity
        super(DescuentoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoLiquidacionIdDescuentoTrabajadorController.setSelected(null);
        descuentoLiquidacionIdLiquidacionSueldoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the DescuentoTrabajador controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoLiquidacionIdDescuentoTrabajador(ActionEvent event) {
        DescuentoLiquidacion selected = this.getSelected();
        if (selected != null && descuentoLiquidacionIdDescuentoTrabajadorController.getSelected() == null) {
            descuentoLiquidacionIdDescuentoTrabajadorController.setSelected(selected.getDescuentoLiquidacionIdDescuentoTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionSueldo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoLiquidacionIdLiquidacionSueldo(ActionEvent event) {
        DescuentoLiquidacion selected = this.getSelected();
        if (selected != null && descuentoLiquidacionIdLiquidacionSueldoController.getSelected() == null) {
            descuentoLiquidacionIdLiquidacionSueldoController.setSelected(selected.getDescuentoLiquidacionIdLiquidacionSueldo());
        }
    }

}
