package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.HoraExtraTrabajador;
import com.areatecnica.sigf.facade.HoraExtraTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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
