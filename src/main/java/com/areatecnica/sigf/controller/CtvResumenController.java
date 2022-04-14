package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CtvResumen;
import com.areatecnica.sigf.facade.CtvResumenFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ctvResumenController")
@ViewScoped
public class CtvResumenController extends AbstractController<CtvResumen> {

    public CtvResumenController() {
        // Inform the Abstract parent controller of the concrete CtvResumen Entity
        super(CtvResumen.class);
    }

}
