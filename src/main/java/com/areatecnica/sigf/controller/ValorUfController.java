package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorUf;
import com.areatecnica.sigf.facade.ValorUfFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "valorUfController")
@ViewScoped
public class ValorUfController extends AbstractController<ValorUf> {

    public ValorUfController() {
        // Inform the Abstract parent controller of the concrete ValorUf Entity
        super(ValorUf.class);
    }

}
