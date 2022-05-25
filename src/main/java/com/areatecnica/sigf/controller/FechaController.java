package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Fecha;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "fechaController")
@ViewScoped
public class FechaController extends AbstractController<Fecha> {

    public FechaController() {
        // Inform the Abstract parent controller of the concrete Fecha Entity
        super(Fecha.class);
    }

}
