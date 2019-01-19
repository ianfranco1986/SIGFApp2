package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ParametroInterpolacion;
import com.areatecnica.sigf.facade.ParametroInterpolacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "parametroInterpolacionController")
@ViewScoped
public class ParametroInterpolacionController extends AbstractController<ParametroInterpolacion> {

    public ParametroInterpolacionController() {
        // Inform the Abstract parent controller of the concrete ParametroInterpolacion Entity
        super(ParametroInterpolacion.class);
    }

}
