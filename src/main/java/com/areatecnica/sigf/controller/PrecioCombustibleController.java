package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.PrecioCombustible;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "precioCombustibleController")
@ViewScoped
public class PrecioCombustibleController extends AbstractController<PrecioCombustible> {

    @Inject
    private CuentaController precioCombustibleIdCuentaController;
    @Inject
    private TipoCombustibleController precioCombustibleIdTipoCombustibleController;

    public PrecioCombustibleController() {
        // Inform the Abstract parent controller of the concrete PrecioCombustible Entity
        super(PrecioCombustible.class);
    }

    @Override
    public PrecioCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setPrecioCombustibleIdCuenta(this.getUserCount());
        this.getSelected().setPrecioCombustibleFechaPrecioCombustible(new Date());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        precioCombustibleIdCuentaController.setSelected(null);
        precioCombustibleIdTipoCombustibleController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePrecioCombustibleIdCuenta(ActionEvent event) {
        PrecioCombustible selected = this.getSelected();
        if (selected != null && precioCombustibleIdCuentaController.getSelected() == null) {
            precioCombustibleIdCuentaController.setSelected(selected.getPrecioCombustibleIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePrecioCombustibleIdTipoCombustible(ActionEvent event) {
        PrecioCombustible selected = this.getSelected();
        if (selected != null && precioCombustibleIdTipoCombustibleController.getSelected() == null) {
            precioCombustibleIdTipoCombustibleController.setSelected(selected.getPrecioCombustibleIdTipoCombustible());
        }
    }

}
