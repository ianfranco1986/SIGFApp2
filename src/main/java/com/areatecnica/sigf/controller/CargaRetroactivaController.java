package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.CargaRetroactiva;
import com.areatecnica.sigf.facade.CargaRetroactivaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cargaRetroactivaController")
@ViewScoped
public class CargaRetroactivaController extends AbstractController<CargaRetroactiva> {

    @Inject
    private CargaTrabajadorController cargaRetroactivaIdCargaTrabajadorController;
    @Inject
    private TrabajadorController cargaRetroactivaIdTrabajadorController;

    public CargaRetroactivaController() {
        // Inform the Abstract parent controller of the concrete CargaRetroactiva Entity
        super(CargaRetroactiva.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cargaRetroactivaIdCargaTrabajadorController.setSelected(null);
        cargaRetroactivaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CargaTrabajador controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaRetroactivaIdCargaTrabajador(ActionEvent event) {
        CargaRetroactiva selected = this.getSelected();
        if (selected != null && cargaRetroactivaIdCargaTrabajadorController.getSelected() == null) {
            cargaRetroactivaIdCargaTrabajadorController.setSelected(selected.getCargaRetroactivaIdCargaTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCargaRetroactivaIdTrabajador(ActionEvent event) {
        CargaRetroactiva selected = this.getSelected();
        if (selected != null && cargaRetroactivaIdTrabajadorController.getSelected() == null) {
            cargaRetroactivaIdTrabajadorController.setSelected(selected.getCargaRetroactivaIdTrabajador());
        }
    }

}
