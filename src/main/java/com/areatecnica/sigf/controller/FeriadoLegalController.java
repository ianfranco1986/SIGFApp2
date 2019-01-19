package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.FeriadoLegal;
import com.areatecnica.sigf.facade.FeriadoLegalFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "feriadoLegalController")
@ViewScoped
public class FeriadoLegalController extends AbstractController<FeriadoLegal> {

    @Inject
    private TrabajadorController feriadoLegalIdTrabajadorController;

    public FeriadoLegalController() {
        // Inform the Abstract parent controller of the concrete FeriadoLegal Entity
        super(FeriadoLegal.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        feriadoLegalIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareFeriadoLegalIdTrabajador(ActionEvent event) {
        FeriadoLegal selected = this.getSelected();
        if (selected != null && feriadoLegalIdTrabajadorController.getSelected() == null) {
            feriadoLegalIdTrabajadorController.setSelected(selected.getFeriadoLegalIdTrabajador());
        }
    }

}
