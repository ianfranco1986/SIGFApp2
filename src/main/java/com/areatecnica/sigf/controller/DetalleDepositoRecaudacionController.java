package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleDepositoRecaudacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleDepositoRecaudacionController")
@ViewScoped
public class DetalleDepositoRecaudacionController extends AbstractController<DetalleDepositoRecaudacion> {

    @Inject
    private CuentaBancariaController detalleDepositoRecaudacionIdCuentaController;
    @Inject
    private ResumenRecaudacionController detalleDepositoRecaudacionIdResumenController;
    

    public DetalleDepositoRecaudacionController() {
        // Inform the Abstract parent controller of the concrete DetalleDepositoRecaudacion Entity
        super(DetalleDepositoRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleDepositoRecaudacionIdCuentaController.setSelected(null);
        detalleDepositoRecaudacionIdResumenController.setSelected(null);
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

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleDepositoRecaudacionIdResumen(ActionEvent event) {
        DetalleDepositoRecaudacion selected = this.getSelected();
        if (selected != null && detalleDepositoRecaudacionIdResumenController.getSelected() == null) {
            detalleDepositoRecaudacionIdResumenController.setSelected(selected.getDetalleDepositoRecaudacionIdResumen());
        }
    }

}
