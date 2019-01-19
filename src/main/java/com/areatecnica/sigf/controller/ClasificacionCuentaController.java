package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ClasificacionCuenta;
import com.areatecnica.sigf.facade.ClasificacionCuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "clasificacionCuentaController")
@ViewScoped
public class ClasificacionCuentaController extends AbstractController<ClasificacionCuenta> {

    public ClasificacionCuentaController() {
        // Inform the Abstract parent controller of the concrete ClasificacionCuenta Entity
        super(ClasificacionCuenta.class);
    }

}
