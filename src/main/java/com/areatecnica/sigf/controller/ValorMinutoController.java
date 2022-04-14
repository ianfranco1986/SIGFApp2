package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorMinuto;
import com.areatecnica.sigf.facade.ValorMinutoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "valorMinutoController")
@ViewScoped
public class ValorMinutoController extends AbstractController<ValorMinuto> {

    @Inject
    private CuentaController valorMinutoIdCuentaController;

    public ValorMinutoController() {
        // Inform the Abstract parent controller of the concrete ValorMinuto Entity
        super(ValorMinuto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        valorMinutoIdCuentaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareValorMinutoIdCuenta(ActionEvent event) {
        ValorMinuto selected = this.getSelected();
        if (selected != null && valorMinutoIdCuentaController.getSelected() == null) {
            valorMinutoIdCuentaController.setSelected(selected.getValorMinutoIdCuenta());
        }
    }

}
