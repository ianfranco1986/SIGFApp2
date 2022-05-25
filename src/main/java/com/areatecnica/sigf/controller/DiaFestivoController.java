package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DiaFestivo;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "diaFestivoController")
@ViewScoped
public class DiaFestivoController extends AbstractController<DiaFestivo> {

    public DiaFestivoController() {
        // Inform the Abstract parent controller of the concrete DiaFestivo Entity
        super(DiaFestivo.class);
    }

}
