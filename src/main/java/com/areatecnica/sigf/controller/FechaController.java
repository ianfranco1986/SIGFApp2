package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Fecha;
import com.areatecnica.sigf.facade.FechaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "fechaController")
@ViewScoped
public class FechaController extends AbstractController<Fecha> {

    public FechaController() {
        // Inform the Abstract parent controller of the concrete Fecha Entity
        super(Fecha.class);
    }

}
