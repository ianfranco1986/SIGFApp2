package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HaberLiquidacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "haberLiquidacionController")
@ViewScoped
public class HaberLiquidacionController extends AbstractController<HaberLiquidacion> {

    @Inject
    private LiquidacionSueldoController haberLiquidacionIdLiquidacionSueldoController;

    public HaberLiquidacionController() {
        // Inform the Abstract parent controller of the concrete HaberLiquidacion Entity
        super(HaberLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        haberLiquidacionIdLiquidacionSueldoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the LiquidacionSueldo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHaberLiquidacionIdLiquidacionSueldo(ActionEvent event) {
        HaberLiquidacion selected = this.getSelected();
        if (selected != null && haberLiquidacionIdLiquidacionSueldoController.getSelected() == null) {
            haberLiquidacionIdLiquidacionSueldoController.setSelected(selected.getHaberLiquidacionIdLiquidacionSueldo());
        }
    }

}
