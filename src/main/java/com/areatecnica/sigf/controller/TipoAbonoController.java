package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoAbono;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoAbonoController")
@ViewScoped
public class TipoAbonoController extends AbstractController<TipoAbono> {

    @Inject
    private CuentaController tipoAbonoIdCuentaController;

    public TipoAbonoController() {
        // Inform the Abstract parent controller of the concrete TipoAbono Entity
        super(TipoAbono.class);
    }

    @Override
    public TipoAbono prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.getSelected().setTipoAbonoIdCuenta(this.getUserCount());
        this.getSelected().setTipoAbonoMontoDefecto(0);
        return getSelected();
    }

}
