package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ContratoUnidad;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "contratoUnidadController")
@ViewScoped
public class ContratoUnidadController extends AbstractController<ContratoUnidad> {

    @Inject
    private UnidadNegocioController contratoUnidadIdUnidadNegocioController;

    public ContratoUnidadController() {
        // Inform the Abstract parent controller of the concrete ContratoUnidad Entity
        super(ContratoUnidad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        contratoUnidadIdUnidadNegocioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareContratoUnidadIdUnidadNegocio(ActionEvent event) {
        ContratoUnidad selected = this.getSelected();
        if (selected != null && contratoUnidadIdUnidadNegocioController.getSelected() == null) {
            contratoUnidadIdUnidadNegocioController.setSelected(selected.getContratoUnidadIdUnidadNegocio());
        }
    }

}
