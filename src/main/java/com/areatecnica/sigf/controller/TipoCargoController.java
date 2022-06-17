package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TipoCargo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoCargoController")
@ViewScoped
public class TipoCargoController extends AbstractController<TipoCargo> {

    @Inject
    private CuentaController tipoCargoIdCuentaController;

    public TipoCargoController() {
        // Inform the Abstract parent controller of the concrete TipoCargo Entity
        super(TipoCargo.class);
    }

    @Override
    public TipoCargo prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setTipoCargoIdCuenta(this.getUserCount());
        this.getSelected().setTipoCargoMontoDefecto(0);
        return getSelected();
    }

}
