package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TarifaGrupoServicio;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "tarifaGrupoServicioController")
@ViewScoped
public class TarifaGrupoServicioController extends AbstractController<TarifaGrupoServicio> {

    @Inject
    private BoletoController tarifaGrupoServicioIdBoletoController;
    

    public TarifaGrupoServicioController() {
        // Inform the Abstract parent controller of the concrete TarifaGrupoServicio Entity
        super(TarifaGrupoServicio.class);
    }

}
