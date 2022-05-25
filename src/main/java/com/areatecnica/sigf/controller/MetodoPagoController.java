package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.MetodoPago;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "metodoPagoController")
@ViewScoped
public class MetodoPagoController extends AbstractController<MetodoPago> {

    public MetodoPagoController() {
        // Inform the Abstract parent controller of the concrete MetodoPago Entity
        super(MetodoPago.class);
    }

}
