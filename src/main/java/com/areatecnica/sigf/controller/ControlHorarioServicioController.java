package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ControlHorarioServicio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "controlHorarioServicioController")
@ViewScoped
public class ControlHorarioServicioController extends AbstractController<ControlHorarioServicio> {


    public ControlHorarioServicioController() {
        // Inform the Abstract parent controller of the concrete ControlHorarioServicio Entity
        super(ControlHorarioServicio.class);
    }


}
