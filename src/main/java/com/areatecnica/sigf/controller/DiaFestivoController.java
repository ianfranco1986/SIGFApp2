package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DiaFestivo;
import com.areatecnica.sigf.facade.DiaFestivoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "diaFestivoController")
@ViewScoped
public class DiaFestivoController extends AbstractController<DiaFestivo> {

    public DiaFestivoController() {
        // Inform the Abstract parent controller of the concrete DiaFestivo Entity
        super(DiaFestivo.class);
    }

}
