package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CompraCombustible;
import com.areatecnica.sigf.facade.CompraCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "compraCombustibleController")
@ViewScoped
public class CompraCombustibleController extends AbstractController<CompraCombustible> {

    @Inject
    private CuentaController compraCombustibleIdCuentaController;
    @Inject
    private TipoCombustibleController compraCombustibleIdTipoController;

    public CompraCombustibleController() {
        // Inform the Abstract parent controller of the concrete CompraCombustible Entity
        super(CompraCombustible.class);
    }

    @Override
    public CompraCombustible prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setCompraCombustibleIdCuenta(this.getUserCount());
        return this.getSelected();
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        compraCombustibleIdCuentaController.setSelected(null);
        compraCombustibleIdTipoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdCuenta(ActionEvent event) {
        CompraCombustible selected = this.getSelected();
        if (selected != null && compraCombustibleIdCuentaController.getSelected() == null) {
            compraCombustibleIdCuentaController.setSelected(selected.getCompraCombustibleIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCompraCombustibleIdTipo(ActionEvent event) {
        CompraCombustible selected = this.getSelected();
        if (selected != null && compraCombustibleIdTipoController.getSelected() == null) {
            compraCombustibleIdTipoController.setSelected(selected.getCompraCombustibleIdTipo());
        }
    }

}
