package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CuentaBancoProceso;
import com.areatecnica.sigf.facade.CuentaBancoProcesoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cuentaBancoProcesoController")
@ViewScoped
public class CuentaBancoProcesoController extends AbstractController<CuentaBancoProceso> {

    @Inject
    private CuentaBancariaController cuentaBancoProcesoIdBancoController;
    @Inject
    private ProcesoRecaudacionController cuentaBancoProcesoIdProcesoController;

    public CuentaBancoProcesoController() {
        // Inform the Abstract parent controller of the concrete CuentaBancoProceso Entity
        super(CuentaBancoProceso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaBancoProcesoIdBancoController.setSelected(null);
        cuentaBancoProcesoIdProcesoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CuentaBancaria controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancoProcesoIdBanco(ActionEvent event) {
        CuentaBancoProceso selected = this.getSelected();
        if (selected != null && cuentaBancoProcesoIdBancoController.getSelected() == null) {
            cuentaBancoProcesoIdBancoController.setSelected(selected.getCuentaBancoProcesoIdBanco());
        }
    }

    /**
     * Sets the "selected" attribute of the ProcesoRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaBancoProcesoIdProceso(ActionEvent event) {
        CuentaBancoProceso selected = this.getSelected();
        if (selected != null && cuentaBancoProcesoIdProcesoController.getSelected() == null) {
            cuentaBancoProcesoIdProcesoController.setSelected(selected.getCuentaBancoProcesoIdProceso());
        }
    }

}
