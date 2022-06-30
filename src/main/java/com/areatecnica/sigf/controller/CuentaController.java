package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.*;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "cuentaController")
@ViewScoped
public class CuentaController extends AbstractController<Cuenta> {

    @Inject
    private TipoCuentaController cuentaIdTipoController;

    public CuentaController() {
        // Inform the Abstract parent controller of the concrete Cuenta Entity
        super(Cuenta.class);
    }

}
