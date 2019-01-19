package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LicenciaMedica;
import com.areatecnica.sigf.facade.LicenciaMedicaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "licenciaMedicaController")
@ViewScoped
public class LicenciaMedicaController extends AbstractController<LicenciaMedica> {

    @Inject
    private TrabajadorController licenciaMedicaIdTrabajadorController;

    public LicenciaMedicaController() {
        // Inform the Abstract parent controller of the concrete LicenciaMedica Entity
        super(LicenciaMedica.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        licenciaMedicaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLicenciaMedicaIdTrabajador(ActionEvent event) {
        LicenciaMedica selected = this.getSelected();
        if (selected != null && licenciaMedicaIdTrabajadorController.getSelected() == null) {
            licenciaMedicaIdTrabajadorController.setSelected(selected.getLicenciaMedicaIdTrabajador());
        }
    }

}
