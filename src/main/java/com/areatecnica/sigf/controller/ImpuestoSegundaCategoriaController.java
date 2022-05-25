package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ImpuestoSegundaCategoria;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "impuestoSegundaCategoriaController")
@ViewScoped
public class ImpuestoSegundaCategoriaController extends AbstractController<ImpuestoSegundaCategoria> {

    public ImpuestoSegundaCategoriaController() {
        // Inform the Abstract parent controller of the concrete ImpuestoSegundaCategoria Entity
        super(ImpuestoSegundaCategoria.class);
    }

}
