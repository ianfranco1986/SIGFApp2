package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CtvResumen;
import com.areatecnica.sigf.facade.CtvResumenFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "ctvResumenController")
@ViewScoped
public class CtvResumenController extends AbstractController<CtvResumen> {

    @Inject
    private ResumenRecaudacionController ctvResumenIdResumenRecaudacionController;

    public CtvResumenController() {
        // Inform the Abstract parent controller of the concrete CtvResumen Entity
        super(CtvResumen.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ctvResumenIdResumenRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCtvResumenIdResumenRecaudacion(ActionEvent event) {
        CtvResumen selected = this.getSelected();
        if (selected != null && ctvResumenIdResumenRecaudacionController.getSelected() == null) {
            ctvResumenIdResumenRecaudacionController.setSelected(selected.getCtvResumenIdResumenRecaudacion());
        }
    }

}
