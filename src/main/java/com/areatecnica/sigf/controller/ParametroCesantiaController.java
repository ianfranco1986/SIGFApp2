package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ParametroCesantia;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "parametroCesantiaController")
@ViewScoped
public class ParametroCesantiaController extends AbstractController<ParametroCesantia> {

    public ParametroCesantiaController() {
        // Inform the Abstract parent controller of the concrete ParametroCesantia Entity
        super(ParametroCesantia.class);
    }

}
