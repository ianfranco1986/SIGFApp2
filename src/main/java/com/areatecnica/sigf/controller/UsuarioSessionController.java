package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.UsuarioSession;
import com.areatecnica.sigf.facade.UsuarioSessionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioSessionController")
@ViewScoped
public class UsuarioSessionController extends AbstractController<UsuarioSession> {

    @Inject
    private UsuarioController usuarioSessionIdUsuarioController;

    public UsuarioSessionController() {
        // Inform the Abstract parent controller of the concrete UsuarioSession Entity
        super(UsuarioSession.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioSessionIdUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuarioSessionIdUsuario(ActionEvent event) {
        UsuarioSession selected = this.getSelected();
        if (selected != null && usuarioSessionIdUsuarioController.getSelected() == null) {
            usuarioSessionIdUsuarioController.setSelected(selected.getUsuarioSessionIdUsuario());
        }
    }

}
