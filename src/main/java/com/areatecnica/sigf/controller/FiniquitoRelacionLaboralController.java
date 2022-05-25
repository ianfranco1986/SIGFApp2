package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "finiquitoRelacionLaboralController")
@ViewScoped
public class FiniquitoRelacionLaboralController extends AbstractController<FiniquitoRelacionLaboral> {


    public FiniquitoRelacionLaboralController() {
        // Inform the Abstract parent controller of the concrete FiniquitoRelacionLaboral Entity
        super(FiniquitoRelacionLaboral.class);
    }

   

}
