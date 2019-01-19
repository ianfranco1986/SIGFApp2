package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.DetalleResumenRecaudacion;
import com.areatecnica.sigf.facade.DetalleResumenRecaudacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "detalleResumenRecaudacionController")
@ViewScoped
public class DetalleResumenRecaudacionController extends AbstractController<DetalleResumenRecaudacion> {

    @Inject
    private ResumenRecaudacionController detalleResumenRecaudacionIdResumenController;

    public DetalleResumenRecaudacionController() {
        // Inform the Abstract parent controller of the concrete DetalleResumenRecaudacion Entity
        super(DetalleResumenRecaudacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        detalleResumenRecaudacionIdResumenController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the ResumenRecaudacion controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDetalleResumenRecaudacionIdResumen(ActionEvent event) {
        DetalleResumenRecaudacion selected = this.getSelected();
        if (selected != null && detalleResumenRecaudacionIdResumenController.getSelected() == null) {
            detalleResumenRecaudacionIdResumenController.setSelected(selected.getDetalleResumenRecaudacionIdResumen());
        }
    }

}
