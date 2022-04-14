package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoLinea;
import com.areatecnica.sigf.facade.TipoLineaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "tipoLineaController")
@ViewScoped
public class TipoLineaController extends AbstractController<TipoLinea> {

    public TipoLineaController() {
        // Inform the Abstract parent controller of the concrete TipoLinea Entity
        super(TipoLinea.class);
    }

}
