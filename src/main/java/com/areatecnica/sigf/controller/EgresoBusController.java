package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.EgresoBus;
import com.areatecnica.sigf.facade.EgresoBusFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "egresoBusController")
@ViewScoped
public class EgresoBusController extends AbstractController<EgresoBus> {

    @Inject
    private EgresoController egresoBusIdEgresoController;
    @Inject
    private BusController egresoBusIdBusController;

    public EgresoBusController() {
        // Inform the Abstract parent controller of the concrete EgresoBus Entity
        super(EgresoBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        egresoBusIdEgresoController.setSelected(null);
        egresoBusIdBusController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoBusIdEgreso(ActionEvent event) {
        EgresoBus selected = this.getSelected();
        if (selected != null && egresoBusIdEgresoController.getSelected() == null) {
            egresoBusIdEgresoController.setSelected(selected.getEgresoBusIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEgresoBusIdBus(ActionEvent event) {
        EgresoBus selected = this.getSelected();
        if (selected != null && egresoBusIdBusController.getSelected() == null) {
            egresoBusIdBusController.setSelected(selected.getEgresoBusIdBus());
        }
    }

}
