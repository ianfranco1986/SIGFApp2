package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ParametroCesantia;
import com.areatecnica.sigf.facade.ParametroCesantiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "parametroCesantiaController")
@ViewScoped
public class ParametroCesantiaController extends AbstractController<ParametroCesantia> {

    public ParametroCesantiaController() {
        // Inform the Abstract parent controller of the concrete ParametroCesantia Entity
        super(ParametroCesantia.class);
    }

}
