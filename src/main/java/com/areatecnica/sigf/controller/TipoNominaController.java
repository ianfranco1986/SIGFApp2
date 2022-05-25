package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoNomina;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "tipoNominaController")
@ViewScoped
public class TipoNominaController extends AbstractController<TipoNomina> {

    public TipoNominaController() {
        // Inform the Abstract parent controller of the concrete TipoNomina Entity
        super(TipoNomina.class);
    }

}
