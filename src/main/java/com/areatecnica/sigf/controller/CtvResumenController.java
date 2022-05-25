package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CtvResumen;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "ctvResumenController")
@ViewScoped
public class CtvResumenController extends AbstractController<CtvResumen> {

    public CtvResumenController() {
        // Inform the Abstract parent controller of the concrete CtvResumen Entity
        super(CtvResumen.class);
    }

}
