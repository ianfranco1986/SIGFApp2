package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ParametroInterpolacion;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "parametroInterpolacionController")
@ViewScoped
public class ParametroInterpolacionController extends AbstractController<ParametroInterpolacion> {

    public ParametroInterpolacionController() {
        // Inform the Abstract parent controller of the concrete ParametroInterpolacion Entity
        super(ParametroInterpolacion.class);
    }

}
