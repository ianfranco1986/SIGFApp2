package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RegistroBoleto;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "registroBoletoController")
@ViewScoped
public class RegistroBoletoController extends AbstractController<RegistroBoleto> {

    @Inject
    private BoletoController registroBoletoIdBoletoController;
    @Inject
    private GuiaController registroBoletoIdGuiaController;
    @Inject
    private ServicioController registroBoletoIdServicioController;

    public RegistroBoletoController() {
        // Inform the Abstract parent controller of the concrete RegistroBoleto Entity
        super(RegistroBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        registroBoletoIdBoletoController.setSelected(null);
        registroBoletoIdGuiaController.setSelected(null);
        registroBoletoIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroBoletoIdBoleto(ActionEvent event) {
        RegistroBoleto selected = this.getSelected();
        if (selected != null && registroBoletoIdBoletoController.getSelected() == null) {
            registroBoletoIdBoletoController.setSelected(selected.getRegistroBoletoIdBoleto());
        }
    }
}
