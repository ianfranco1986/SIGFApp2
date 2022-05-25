package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.UsuarioSession;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "usuarioSessionController")
@ViewScoped
public class UsuarioSessionController extends AbstractController<UsuarioSession> {


    public UsuarioSessionController() {
        // Inform the Abstract parent controller of the concrete UsuarioSession Entity
        super(UsuarioSession.class);
    }

   

}
