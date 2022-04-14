package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import com.areatecnica.sigf.facade.TarifaGrupoServicioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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
