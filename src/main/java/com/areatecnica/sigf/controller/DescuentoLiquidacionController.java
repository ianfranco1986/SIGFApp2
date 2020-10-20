package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "descuentoLiquidacionController")
@ViewScoped
public class DescuentoLiquidacionController extends AbstractController<DescuentoLiquidacion> {

    public DescuentoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete DescuentoLiquidacion Entity
        super(DescuentoLiquidacion.class);
    }

}
