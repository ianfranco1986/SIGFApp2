package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CargoLiquidacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "cargoLiquidacionController")
@ViewScoped
public class CargoLiquidacionController extends AbstractController<CargoLiquidacion> {

    
    @Inject
    private LiquidacionEmpresaController cargoLiquidacionIdLiquidacionController;

    public CargoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete CargoLiquidacion Entity
        super(CargoLiquidacion.class);
    }


}
