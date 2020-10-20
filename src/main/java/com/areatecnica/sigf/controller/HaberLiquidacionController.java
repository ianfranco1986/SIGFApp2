package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HaberLiquidacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "haberLiquidacionController")
@ViewScoped
public class HaberLiquidacionController extends AbstractController<HaberLiquidacion> {

    public HaberLiquidacionController() {
        // Inform the Abstract parent controller of the concrete HaberLiquidacion Entity
        super(HaberLiquidacion.class);
    }

}
