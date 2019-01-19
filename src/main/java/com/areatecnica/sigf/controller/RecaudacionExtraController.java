package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.facade.RecaudacionExtraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionExtraController")
@ViewScoped
public class RecaudacionExtraController extends AbstractController<RecaudacionExtra> {

    @Inject
    private RecaudacionController recaudacionExtraIdRecaudacionController;

    public RecaudacionExtraController() {
        // Inform the Abstract parent controller of the concrete RecaudacionExtra Entity
        super(RecaudacionExtra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionExtraIdRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionExtraIdRecaudacion(ActionEvent event) {
        RecaudacionExtra selected = this.getSelected();
        if (selected != null && recaudacionExtraIdRecaudacionController.getSelected() == null) {
            recaudacionExtraIdRecaudacionController.setSelected(selected.getRecaudacionExtraIdRecaudacion());
        }
    }

}
