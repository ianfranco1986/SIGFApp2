package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.facade.RecaudacionCombustibleFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "recaudacionCombustibleController")
@ViewScoped
public class RecaudacionCombustibleController extends AbstractController<RecaudacionCombustible> {

    @Inject
    private RecaudacionController recaudacionCombustibleIdRecaudacionController;
    @Inject
    private VentaCombustibleController recaudacionCombustibleIdVentaCombustibleController;

    public RecaudacionCombustibleController() {
        // Inform the Abstract parent controller of the concrete RecaudacionCombustible Entity
        super(RecaudacionCombustible.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionCombustibleIdRecaudacionController.setSelected(null);
        recaudacionCombustibleIdVentaCombustibleController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdRecaudacion(ActionEvent event) {
        RecaudacionCombustible selected = this.getSelected();
        if (selected != null && recaudacionCombustibleIdRecaudacionController.getSelected() == null) {
            recaudacionCombustibleIdRecaudacionController.setSelected(selected.getRecaudacionCombustibleIdRecaudacion());
        }
    }

    /**
     * Sets the "selected" attribute of the VentaCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdVentaCombustible(ActionEvent event) {
        RecaudacionCombustible selected = this.getSelected();
        if (selected != null && recaudacionCombustibleIdVentaCombustibleController.getSelected() == null) {
            recaudacionCombustibleIdVentaCombustibleController.setSelected(selected.getRecaudacionCombustibleIdVentaCombustible());
        }
    }

}
