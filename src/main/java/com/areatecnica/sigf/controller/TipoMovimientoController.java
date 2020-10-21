package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoMovimiento;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;

@Named(value = "tipoMovimientoController")
@ViewScoped
public class TipoMovimientoController extends AbstractController<TipoMovimiento> {

    
    public TipoMovimientoController() {
        // Inform the Abstract parent controller of the concrete TipoMovimiento Entity
        super(TipoMovimiento.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {

    }

    @Override
    public TipoMovimiento prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        return getSelected();
    }

}
