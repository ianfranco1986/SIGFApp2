package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ImpuestoSegundaCategoria;
import com.areatecnica.sigf.facade.ImpuestoSegundaCategoriaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "impuestoSegundaCategoriaController")
@ViewScoped
public class ImpuestoSegundaCategoriaController extends AbstractController<ImpuestoSegundaCategoria> {

    public ImpuestoSegundaCategoriaController() {
        // Inform the Abstract parent controller of the concrete ImpuestoSegundaCategoria Entity
        super(ImpuestoSegundaCategoria.class);
    }

}
