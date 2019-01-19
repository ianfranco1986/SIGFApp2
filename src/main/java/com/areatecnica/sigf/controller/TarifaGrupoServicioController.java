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
    @Inject
    private GrupoServicioController tarifaGrupoServicioIdGrupoController;

    public TarifaGrupoServicioController() {
        // Inform the Abstract parent controller of the concrete TarifaGrupoServicio Entity
        super(TarifaGrupoServicio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tarifaGrupoServicioIdBoletoController.setSelected(null);
        tarifaGrupoServicioIdGrupoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTarifaGrupoServicioIdBoleto(ActionEvent event) {
        TarifaGrupoServicio selected = this.getSelected();
        if (selected != null && tarifaGrupoServicioIdBoletoController.getSelected() == null) {
            tarifaGrupoServicioIdBoletoController.setSelected(selected.getTarifaGrupoServicioIdBoleto());
        }
    }

    /**
     * Sets the "selected" attribute of the GrupoServicio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTarifaGrupoServicioIdGrupo(ActionEvent event) {
        TarifaGrupoServicio selected = this.getSelected();
        if (selected != null && tarifaGrupoServicioIdGrupoController.getSelected() == null) {
            tarifaGrupoServicioIdGrupoController.setSelected(selected.getTarifaGrupoServicioIdGrupo());
        }
    }

}
