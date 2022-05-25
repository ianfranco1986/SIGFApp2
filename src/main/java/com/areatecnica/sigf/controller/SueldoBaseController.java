package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.SueldoBase;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "sueldoBaseController")
@ViewScoped
public class SueldoBaseController extends AbstractController<SueldoBase> {

    public SueldoBaseController() {
        // Inform the Abstract parent controller of the concrete SueldoBase Entity
        super(SueldoBase.class);
    }

}
