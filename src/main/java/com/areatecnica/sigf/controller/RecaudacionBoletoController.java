package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionBoleto;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "recaudacionBoletoController")
@ViewScoped
public class RecaudacionBoletoController extends AbstractController<RecaudacionBoleto> {

    public RecaudacionBoletoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionBoleto Entity
        super(RecaudacionBoleto.class);
    }

}
