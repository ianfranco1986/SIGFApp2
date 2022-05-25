package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleDepositoRecaudacion;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "detalleDepositoRecaudacionController")
@ViewScoped
public class DetalleDepositoRecaudacionController extends AbstractController<DetalleDepositoRecaudacion> {

    @Inject
    private CuentaBancariaController detalleDepositoRecaudacionIdCuentaController;
    
    public DetalleDepositoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete DetalleDepositoRecaudacion Entity
        super(DetalleDepositoRecaudacion.class);
    }


    /**
     * Sets the "selected" attribute of the CuentaBancaria controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleDepositoRecaudacionIdCuenta(ActionEvent event) {
        DetalleDepositoRecaudacion selected = this.getSelected();
        if (selected != null && detalleDepositoRecaudacionIdCuentaController.getSelected() == null) {
            detalleDepositoRecaudacionIdCuentaController.setSelected(selected.getDetalleDepositoRecaudacionIdCuenta());
        }
    }

   

}
