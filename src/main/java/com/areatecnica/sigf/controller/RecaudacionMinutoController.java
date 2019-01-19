package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.facade.RecaudacionMinutoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionMinutoController")
@ViewScoped
public class RecaudacionMinutoController extends AbstractController<RecaudacionMinuto> {

    @Inject
    private RecaudacionController recaudacionMinutoIdRecaudacionController;
    @Inject
    private RegistroMinutoController recaudacionMinutoIdRegistroMinutoController;

    public RecaudacionMinutoController() {
        // Inform the Abstract parent controller of the concrete RecaudacionMinuto Entity
        super(RecaudacionMinuto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionMinutoIdRecaudacionController.setSelected(null);
        recaudacionMinutoIdRegistroMinutoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionMinutoIdRecaudacion(ActionEvent event) {
        RecaudacionMinuto selected = this.getSelected();
        if (selected != null && recaudacionMinutoIdRecaudacionController.getSelected() == null) {
            recaudacionMinutoIdRecaudacionController.setSelected(selected.getRecaudacionMinutoIdRecaudacion());
        }
    }

    /**
     * Sets the "selected" attribute of the RegistroMinuto controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionMinutoIdRegistroMinuto(ActionEvent event) {
        RecaudacionMinuto selected = this.getSelected();
        if (selected != null && recaudacionMinutoIdRegistroMinutoController.getSelected() == null) {
            recaudacionMinutoIdRegistroMinutoController.setSelected(selected.getRecaudacionMinutoIdRegistroMinuto());
        }
    }

}
