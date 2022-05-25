package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ObservacionTrabajador;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "observacionTrabajadorController")
@ViewScoped
public class ObservacionTrabajadorController extends AbstractController<ObservacionTrabajador> {

    @Inject
    private TipoObservacionController observacionTrabajadorIdTipoObservacionController;
    @Inject
    private TrabajadorController observacionTrabajadorIdTrabajadorController;

    public ObservacionTrabajadorController() {
        // Inform the Abstract parent controller of the concrete ObservacionTrabajador Entity
        super(ObservacionTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        observacionTrabajadorIdTipoObservacionController.setSelected(null);
        observacionTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoObservacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareObservacionTrabajadorIdTipoObservacion(ActionEvent event) {
        ObservacionTrabajador selected = this.getSelected();
        if (selected != null && observacionTrabajadorIdTipoObservacionController.getSelected() == null) {
            observacionTrabajadorIdTipoObservacionController.setSelected(selected.getObservacionTrabajadorIdTipoObservacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareObservacionTrabajadorIdTrabajador(ActionEvent event) {
        ObservacionTrabajador selected = this.getSelected();
        if (selected != null && observacionTrabajadorIdTrabajadorController.getSelected() == null) {
            observacionTrabajadorIdTrabajadorController.setSelected(selected.getObservacionTrabajadorIdTrabajador());
        }
    }

}
