package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.UsuarioSession;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioSessionController")
@ViewScoped
public class UsuarioSessionController extends AbstractController<UsuarioSession> {


    public UsuarioSessionController() {
        // Inform the Abstract parent controller of the concrete UsuarioSession Entity
        super(UsuarioSession.class);
    }

   

}
