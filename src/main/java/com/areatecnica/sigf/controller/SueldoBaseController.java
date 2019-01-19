package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.SueldoBase;
import com.areatecnica.sigf.facade.SueldoBaseFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "sueldoBaseController")
@ViewScoped
public class SueldoBaseController extends AbstractController<SueldoBase> {

    public SueldoBaseController() {
        // Inform the Abstract parent controller of the concrete SueldoBase Entity
        super(SueldoBase.class);
    }

}
