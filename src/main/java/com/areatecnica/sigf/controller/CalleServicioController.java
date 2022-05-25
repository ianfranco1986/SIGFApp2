package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CalleServicio;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "calleServicioController")
@ViewScoped
public class CalleServicioController extends AbstractController<CalleServicio> {

    @Inject
    private CalleController calleServicioIdCalleController;
    @Inject
    private ServicioController calleServicioIdServicioController;

    public CalleServicioController() {
        // Inform the Abstract parent controller of the concrete CalleServicio Entity
        super(CalleServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        calleServicioIdCalleController.setSelected(null);
        calleServicioIdServicioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Calle controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleServicioIdCalle(ActionEvent event) {
        CalleServicio selected = this.getSelected();
        if (selected != null && calleServicioIdCalleController.getSelected() == null) {
            calleServicioIdCalleController.setSelected(selected.getCalleServicioIdCalle());
        }
    }

    /**
     * Sets the "selected" attribute of the Servicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCalleServicioIdServicio(ActionEvent event) {
        CalleServicio selected = this.getSelected();
        if (selected != null && calleServicioIdServicioController.getSelected() == null) {
            calleServicioIdServicioController.setSelected(selected.getCalleServicioIdServicio());
        }
    }

}
