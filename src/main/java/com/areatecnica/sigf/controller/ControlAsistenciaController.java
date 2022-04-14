package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ControlAsistencia;
import com.areatecnica.sigf.facade.ControlAsistenciaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "controlAsistenciaController")
@ViewScoped
public class ControlAsistenciaController extends AbstractController<ControlAsistencia> {

    @Inject
    private TrabajadorController controlAsistenciaIdTrabajadorController;

    public ControlAsistenciaController() {
        // Inform the Abstract parent controller of the concrete ControlAsistencia Entity
        super(ControlAsistencia.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlAsistenciaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlAsistenciaIdTrabajador(ActionEvent event) {
        ControlAsistencia selected = this.getSelected();
        if (selected != null && controlAsistenciaIdTrabajadorController.getSelected() == null) {
            controlAsistenciaIdTrabajadorController.setSelected(selected.getControlAsistenciaIdTrabajador());
        }
    }

}
