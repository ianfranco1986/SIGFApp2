package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HoraExtraTrabajador;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "horaExtraTrabajadorController")
@ViewScoped
public class HoraExtraTrabajadorController extends AbstractController<HoraExtraTrabajador> {

    @Inject
    private TrabajadorController horaExtraTrabajadorIdTrabajadorController;

    public HoraExtraTrabajadorController() {
        // Inform the Abstract parent controller of the concrete HoraExtraTrabajador Entity
        super(HoraExtraTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        horaExtraTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHoraExtraTrabajadorIdTrabajador(ActionEvent event) {
        HoraExtraTrabajador selected = this.getSelected();
        if (selected != null && horaExtraTrabajadorIdTrabajadorController.getSelected() == null) {
            horaExtraTrabajadorIdTrabajadorController.setSelected(selected.getHoraExtraTrabajadorIdTrabajador());
        }
    }

}
