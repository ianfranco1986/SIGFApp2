package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import com.areatecnica.sigf.facade.DetalleIntervalosMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleIntervalosMensualController")
@ViewScoped
public class DetalleIntervalosMensualController extends AbstractController<DetalleIntervalosMensual> {

    @Inject
    private AdministracionMensualController detalleIntervalosMensualIdAdministracionController;
    @Inject
    private IntervalosAdministracionController detalleIntervalosMensualIdIntervaloAdministracionController;

    public DetalleIntervalosMensualController() {
        // Inform the Abstract parent controller of the concrete DetalleIntervalosMensual Entity
        super(DetalleIntervalosMensual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleIntervalosMensualIdAdministracionController.setSelected(null);
        detalleIntervalosMensualIdIntervaloAdministracionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AdministracionMensual controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleIntervalosMensualIdAdministracion(ActionEvent event) {
        DetalleIntervalosMensual selected = this.getSelected();
        if (selected != null && detalleIntervalosMensualIdAdministracionController.getSelected() == null) {
            detalleIntervalosMensualIdAdministracionController.setSelected(selected.getDetalleIntervalosMensualIdAdministracion());
        }
    }

    /**
     * Sets the "selected" attribute of the IntervalosAdministracion controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleIntervalosMensualIdIntervaloAdministracion(ActionEvent event) {
        DetalleIntervalosMensual selected = this.getSelected();
        if (selected != null && detalleIntervalosMensualIdIntervaloAdministracionController.getSelected() == null) {
            detalleIntervalosMensualIdIntervaloAdministracionController.setSelected(selected.getDetalleIntervalosMensualIdIntervaloAdministracion());
        }
    }

}
