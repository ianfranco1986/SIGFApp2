package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CuentaBancoTrabajador;
import com.areatecnica.sigf.facade.CuentaBancoTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cuentaBancoTrabajadorController")
@ViewScoped
public class CuentaBancoTrabajadorController extends AbstractController<CuentaBancoTrabajador> {

    @Inject
    private CuentaBancariaController cuentaBancoTrabajadorCuentaController;
    @Inject
    private TrabajadorController cuentaBancoTrabajadorTrabajadorController;

    public CuentaBancoTrabajadorController() {
        // Inform the Abstract parent controller of the concrete CuentaBancoTrabajador Entity
        super(CuentaBancoTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaBancoTrabajadorCuentaController.setSelected(null);
        cuentaBancoTrabajadorTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CuentaBancaria controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancoTrabajadorCuenta(ActionEvent event) {
        CuentaBancoTrabajador selected = this.getSelected();
        if (selected != null && cuentaBancoTrabajadorCuentaController.getSelected() == null) {
            cuentaBancoTrabajadorCuentaController.setSelected(selected.getCuentaBancoTrabajadorCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancoTrabajadorTrabajador(ActionEvent event) {
        CuentaBancoTrabajador selected = this.getSelected();
        if (selected != null && cuentaBancoTrabajadorTrabajadorController.getSelected() == null) {
            cuentaBancoTrabajadorTrabajadorController.setSelected(selected.getCuentaBancoTrabajadorTrabajador());
        }
    }

}
