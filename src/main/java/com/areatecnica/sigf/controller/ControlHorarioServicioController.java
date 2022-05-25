package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ControlHorarioServicio;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controlHorarioServicioController")
@ViewScoped
public class ControlHorarioServicioController extends AbstractController<ControlHorarioServicio> {


    public ControlHorarioServicioController() {
        // Inform the Abstract parent controller of the concrete ControlHorarioServicio Entity
        super(ControlHorarioServicio.class);
    }


}
