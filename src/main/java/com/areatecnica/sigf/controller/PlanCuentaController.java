package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.PlanCuenta;
import com.areatecnica.sigf.facade.PlanCuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "planCuentaController")
@ViewScoped
public class PlanCuentaController extends AbstractController<PlanCuenta> {

    public PlanCuentaController() {
        // Inform the Abstract parent controller of the concrete PlanCuenta Entity
        super(PlanCuenta.class);
    }

}
