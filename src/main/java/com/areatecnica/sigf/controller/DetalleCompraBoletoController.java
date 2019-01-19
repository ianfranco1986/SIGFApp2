package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.facade.DetalleCompraBoletoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleCompraBoletoController")
@ViewScoped
public class DetalleCompraBoletoController extends AbstractController<DetalleCompraBoleto> {

    @Inject
    private BoletoController detalleCompraBoletoIdBoletoController;
    @Inject
    private CompraBoletoController detalleCompraBoletoIdCompraBoletoController;

    public DetalleCompraBoletoController() {
        // Inform the Abstract parent controller of the concrete DetalleCompraBoleto Entity
        super(DetalleCompraBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleCompraBoletoIdBoletoController.setSelected(null);
        detalleCompraBoletoIdCompraBoletoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleCompraBoletoIdBoleto(ActionEvent event) {
        DetalleCompraBoleto selected = this.getSelected();
        if (selected != null && detalleCompraBoletoIdBoletoController.getSelected() == null) {
            detalleCompraBoletoIdBoletoController.setSelected(selected.getDetalleCompraBoletoIdBoleto());
        }
    }

    /**
     * Sets the "selected" attribute of the CompraBoleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleCompraBoletoIdCompraBoleto(ActionEvent event) {
        DetalleCompraBoleto selected = this.getSelected();
        if (selected != null && detalleCompraBoletoIdCompraBoletoController.getSelected() == null) {
            detalleCompraBoletoIdCompraBoletoController.setSelected(selected.getDetalleCompraBoletoIdCompraBoleto());
        }
    }

}
