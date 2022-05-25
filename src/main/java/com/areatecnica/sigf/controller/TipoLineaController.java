package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoLinea;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "tipoLineaController")
@ViewScoped
public class TipoLineaController extends AbstractController<TipoLinea> {

    public TipoLineaController() {
        // Inform the Abstract parent controller of the concrete TipoLinea Entity
        super(TipoLinea.class);
    }

}
