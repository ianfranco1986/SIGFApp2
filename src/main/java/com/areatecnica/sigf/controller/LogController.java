package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Log;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "logController")
@ViewScoped
public class LogController extends AbstractController<Log> {

    @Inject
    private PrivilegioController logIdPrivilegioController;
    @Inject
    private UsuarioController logIdUsuarioController;

    public LogController() {
        // Inform the Abstract parent controller of the concrete Log Entity
        super(Log.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        logIdPrivilegioController.setSelected(null);
        logIdUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Privilegio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLogIdPrivilegio(ActionEvent event) {
        Log selected = this.getSelected();
        if (selected != null && logIdPrivilegioController.getSelected() == null) {
            logIdPrivilegioController.setSelected(selected.getLogIdPrivilegio());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLogIdUsuario(ActionEvent event) {
        Log selected = this.getSelected();
        if (selected != null && logIdUsuarioController.getSelected() == null) {
            logIdUsuarioController.setSelected(selected.getLogIdUsuario());
        }
    }

}
