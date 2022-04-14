package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ObservacionTrabajador;
import com.areatecnica.sigf.facade.ObservacionTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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
