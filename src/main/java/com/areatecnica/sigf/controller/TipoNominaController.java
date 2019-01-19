package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoNomina;
import com.areatecnica.sigf.facade.TipoNominaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "tipoNominaController")
@ViewScoped
public class TipoNominaController extends AbstractController<TipoNomina> {

    public TipoNominaController() {
        // Inform the Abstract parent controller of the concrete TipoNomina Entity
        super(TipoNomina.class);
    }

}
