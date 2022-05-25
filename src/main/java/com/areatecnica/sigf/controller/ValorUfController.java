package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorUf;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "valorUfController")
@ViewScoped
public class ValorUfController extends AbstractController<ValorUf> {

    public ValorUfController() {
        // Inform the Abstract parent controller of the concrete ValorUf Entity
        super(ValorUf.class);
    }

}
