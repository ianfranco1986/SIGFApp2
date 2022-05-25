package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorUtm;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "valorUtmController")
@ViewScoped
public class ValorUtmController extends AbstractController<ValorUtm> {

    public ValorUtmController() {
        // Inform the Abstract parent controller of the concrete ValorUtm Entity
        super(ValorUtm.class);
    }

}
