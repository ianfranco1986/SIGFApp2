package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ProgramacionTrabajador;
import com.areatecnica.sigf.facade.ProgramacionTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "programacionTrabajadorController")
@ViewScoped
public class ProgramacionTrabajadorController extends AbstractController<ProgramacionTrabajador> {

    @Inject
    private AdministradorController programacionTrabajadorIdAdministradorController;
    @Inject
    private TrabajadorController programacionTrabajadorIdTrabajadorController;

    public ProgramacionTrabajadorController() {
        // Inform the Abstract parent controller of the concrete ProgramacionTrabajador Entity
        super(ProgramacionTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        programacionTrabajadorIdAdministradorController.setSelected(null);
        programacionTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Administrador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProgramacionTrabajadorIdAdministrador(ActionEvent event) {
        ProgramacionTrabajador selected = this.getSelected();
        if (selected != null && programacionTrabajadorIdAdministradorController.getSelected() == null) {
            programacionTrabajadorIdAdministradorController.setSelected(selected.getProgramacionTrabajadorIdAdministrador());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProgramacionTrabajadorIdTrabajador(ActionEvent event) {
        ProgramacionTrabajador selected = this.getSelected();
        if (selected != null && programacionTrabajadorIdTrabajadorController.getSelected() == null) {
            programacionTrabajadorIdTrabajadorController.setSelected(selected.getProgramacionTrabajadorIdTrabajador());
        }
    }

}
