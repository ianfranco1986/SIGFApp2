package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.JornadaTrabajador;
import com.areatecnica.sigf.facade.JornadaTrabajadorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "jornadaTrabajadorController")
@ViewScoped
public class JornadaTrabajadorController extends AbstractController<JornadaTrabajador> {

    @Inject
    private JornadaLaboralController jornadaTrabajadorIdJornadaLaboralController;
    @Inject
    private TrabajadorController jornadaTrabajadorIdTrabajadorController;

    public JornadaTrabajadorController() {
        // Inform the Abstract parent controller of the concrete JornadaTrabajador Entity
        super(JornadaTrabajador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        jornadaTrabajadorIdJornadaLaboralController.setSelected(null);
        jornadaTrabajadorIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the JornadaLaboral controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaTrabajadorIdJornadaLaboral(ActionEvent event) {
        JornadaTrabajador selected = this.getSelected();
        if (selected != null && jornadaTrabajadorIdJornadaLaboralController.getSelected() == null) {
            jornadaTrabajadorIdJornadaLaboralController.setSelected(selected.getJornadaTrabajadorIdJornadaLaboral());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareJornadaTrabajadorIdTrabajador(ActionEvent event) {
        JornadaTrabajador selected = this.getSelected();
        if (selected != null && jornadaTrabajadorIdTrabajadorController.getSelected() == null) {
            jornadaTrabajadorIdTrabajadorController.setSelected(selected.getJornadaTrabajadorIdTrabajador());
        }
    }

}
