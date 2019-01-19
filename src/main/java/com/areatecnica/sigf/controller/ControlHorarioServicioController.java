package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ControlHorarioServicio;
import com.areatecnica.sigf.facade.ControlHorarioServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "controlHorarioServicioController")
@ViewScoped
public class ControlHorarioServicioController extends AbstractController<ControlHorarioServicio> {

    @Inject
    private ControlController controlHorarioServicioIdControlController;
    @Inject
    private HorarioServicioController controlHorarioServicioIdHorarioController;

    public ControlHorarioServicioController() {
        // Inform the Abstract parent controller of the concrete ControlHorarioServicio Entity
        super(ControlHorarioServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlHorarioServicioIdControlController.setSelected(null);
        controlHorarioServicioIdHorarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Control controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlHorarioServicioIdControl(ActionEvent event) {
        ControlHorarioServicio selected = this.getSelected();
        if (selected != null && controlHorarioServicioIdControlController.getSelected() == null) {
            controlHorarioServicioIdControlController.setSelected(selected.getControlHorarioServicioIdControl());
        }
    }

    /**
     * Sets the "selected" attribute of the HorarioServicio controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlHorarioServicioIdHorario(ActionEvent event) {
        ControlHorarioServicio selected = this.getSelected();
        if (selected != null && controlHorarioServicioIdHorarioController.getSelected() == null) {
            controlHorarioServicioIdHorarioController.setSelected(selected.getControlHorarioServicioIdHorario());
        }
    }

}
