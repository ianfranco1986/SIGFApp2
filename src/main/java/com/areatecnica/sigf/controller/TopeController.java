package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Tope;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "topeController")
@ViewScoped
public class TopeController extends AbstractController<Tope> {

    public TopeController() {
        // Inform the Abstract parent controller of the concrete Tope Entity
        super(Tope.class);
    }

}
