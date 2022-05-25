package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Banco;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "bancoController")
@ViewScoped
public class BancoController extends AbstractController<Banco> {

    @Inject
    private CuentaController bancoIdCuentaController;

    // Flags to indicate if child collections are empty
    private boolean isCuentaBancariaListEmpty;
    private boolean isDetalleResumenChequeListEmpty;

    public BancoController() {
        // Inform the Abstract parent controller of the concrete Banco Entity
        super(Banco.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
//        bancoIdCuentaController.setSelected(null);
    }

    @Override
    public Banco prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setBancoIdCuenta(this.getUserCount());
        return this.getSelected();
    }
    
    

}
