package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionBoleto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionBoletoController")
@ViewScoped
public class RecaudacionBoletoController extends AbstractController<RecaudacionBoleto> {

    @Inject
    private RecaudacionController recaudacionBoletoIdRecaudacionController;
    
    public RecaudacionBoletoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionBoleto Entity
        super(RecaudacionBoleto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionBoletoIdRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionBoletoIdRecaudacion(ActionEvent event) {
        RecaudacionBoleto selected = this.getSelected();
        if (selected != null && recaudacionBoletoIdRecaudacionController.getSelected() == null) {
            recaudacionBoletoIdRecaudacionController.setSelected(selected.getRecaudacionBoletoIdRecaudacion());
        }
    }

}
