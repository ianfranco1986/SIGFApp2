package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionDescuentoExtraController")
@ViewScoped
public class RecaudacionDescuentoExtraController extends AbstractController<RecaudacionDescuentoExtra> {

    @Inject
    private DescuentoExtraBusController recaudacionDescuentoExtraIdDctoController;
    
    public RecaudacionDescuentoExtraController() {
        // Inform the Abstract parent controller of the concrete RecaudacionDescuentoExtra Entity
        super(RecaudacionDescuentoExtra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        
    }

    /**
     * Sets the "selected" attribute of the DescuentoExtraBus controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionDescuentoExtraIdDcto(ActionEvent event) {
        RecaudacionDescuentoExtra selected = this.getSelected();
        if (selected != null && recaudacionDescuentoExtraIdDctoController.getSelected() == null) {
            recaudacionDescuentoExtraIdDctoController.setSelected(selected.getRecaudacionDescuentoExtraIdDcto());
        }
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionDescuentoExtraIdRecaudacion(ActionEvent event) {
        
    }

}
