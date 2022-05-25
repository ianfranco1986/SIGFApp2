package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleResumenCheque;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "detalleResumenChequeController")
@ViewScoped
public class DetalleResumenChequeController extends AbstractController<DetalleResumenCheque> {

    @Inject
    private BancoController detalleResumenChequeIdBancoController;

    public DetalleResumenChequeController() {
        // Inform the Abstract parent controller of the concrete DetalleResumenCheque Entity
        super(DetalleResumenCheque.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleResumenChequeIdBancoController.setSelected(null);

    }

    /**
     * Sets the "selected" attribute of the Banco controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleResumenChequeIdBanco(ActionEvent event) {
        DetalleResumenCheque selected = this.getSelected();
        if (selected != null && detalleResumenChequeIdBancoController.getSelected() == null) {
            detalleResumenChequeIdBancoController.setSelected(selected.getDetalleResumenChequeIdBanco());
        }
    }

}
