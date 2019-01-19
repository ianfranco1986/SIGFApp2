package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ControlServicio;
import com.areatecnica.sigf.facade.ControlServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "controlServicioController")
@ViewScoped
public class ControlServicioController extends AbstractController<ControlServicio> {

    @Inject
    private ControlController controlServicioIdControlController;
    @Inject
    private ServicioController controlServicioIdServicioController;

    public ControlServicioController() {
        // Inform the Abstract parent controller of the concrete ControlServicio Entity
        super(ControlServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        controlServicioIdControlController.setSelected(null);
        controlServicioIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Control controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlServicioIdControl(ActionEvent event) {
        ControlServicio selected = this.getSelected();
        if (selected != null && controlServicioIdControlController.getSelected() == null) {
            controlServicioIdControlController.setSelected(selected.getControlServicioIdControl());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareControlServicioIdServicio(ActionEvent event) {
        ControlServicio selected = this.getSelected();
        if (selected != null && controlServicioIdServicioController.getSelected() == null) {
            controlServicioIdServicioController.setSelected(selected.getControlServicioIdServicio());
        }
    }

}
