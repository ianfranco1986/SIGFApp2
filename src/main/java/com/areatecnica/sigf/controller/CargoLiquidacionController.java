package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.facade.CargoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cargoLiquidacionController")
@ViewScoped
public class CargoLiquidacionController extends AbstractController<CargoLiquidacion> {

    @Inject
    private CargoBusController cargoLiquidacionIdCargoController;
    @Inject
    private LiquidacionEmpresaController cargoLiquidacionIdLiquidacionController;

    public CargoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete CargoLiquidacion Entity
        super(CargoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargoLiquidacionIdCargoController.setSelected(null);
        cargoLiquidacionIdLiquidacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CargoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoLiquidacionIdCargo(ActionEvent event) {
        CargoLiquidacion selected = this.getSelected();
        if (selected != null && cargoLiquidacionIdCargoController.getSelected() == null) {
            cargoLiquidacionIdCargoController.setSelected(selected.getCargoLiquidacionIdCargo());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionEmpresa controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargoLiquidacionIdLiquidacion(ActionEvent event) {
        CargoLiquidacion selected = this.getSelected();
        if (selected != null && cargoLiquidacionIdLiquidacionController.getSelected() == null) {
            cargoLiquidacionIdLiquidacionController.setSelected(selected.getCargoLiquidacionIdLiquidacion());
        }
    }

}
