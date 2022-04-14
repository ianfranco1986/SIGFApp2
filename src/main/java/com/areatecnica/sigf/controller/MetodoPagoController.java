package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.MetodoPago;
import com.areatecnica.sigf.facade.MetodoPagoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "metodoPagoController")
@ViewScoped
public class MetodoPagoController extends AbstractController<MetodoPago> {

    public MetodoPagoController() {
        // Inform the Abstract parent controller of the concrete MetodoPago Entity
        super(MetodoPago.class);
    }

}
