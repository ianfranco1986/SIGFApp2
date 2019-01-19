package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorFijo;
import com.areatecnica.sigf.facade.ValorFijoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "valorFijoController")
@ViewScoped
public class ValorFijoController extends AbstractController<ValorFijo> {

    public ValorFijoController() {
        // Inform the Abstract parent controller of the concrete ValorFijo Entity
        super(ValorFijo.class);
    }

}
