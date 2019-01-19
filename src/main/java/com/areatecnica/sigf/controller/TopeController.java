package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Tope;
import com.areatecnica.sigf.facade.TopeFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "topeController")
@ViewScoped
public class TopeController extends AbstractController<Tope> {

    public TopeController() {
        // Inform the Abstract parent controller of the concrete Tope Entity
        super(Tope.class);
    }

}
