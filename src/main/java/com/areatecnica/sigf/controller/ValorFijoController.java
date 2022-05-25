package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorFijo;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "valorFijoController")
@ViewScoped
public class ValorFijoController extends AbstractController<ValorFijo> {

    public ValorFijoController() {
        // Inform the Abstract parent controller of the concrete ValorFijo Entity
        super(ValorFijo.class);
    }

}
