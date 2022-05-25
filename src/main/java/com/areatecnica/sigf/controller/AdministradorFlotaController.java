package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AdministradorFlota;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "administradorFlotaController")
@ViewScoped
public class AdministradorFlotaController extends AbstractController<AdministradorFlota> {

    @Inject
    private AdministradorController administradorFlotaIdAdminController;
    @Inject
    private FlotaController administradorFlotaIdFlotaController;

    public AdministradorFlotaController() {
        // Inform the Abstract parent controller of the concrete AdministradorFlota Entity
        super(AdministradorFlota.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        administradorFlotaIdAdminController.setSelected(null);
        administradorFlotaIdFlotaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Administrador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAdministradorFlotaIdAdmin(ActionEvent event) {
        AdministradorFlota selected = this.getSelected();
        if (selected != null && administradorFlotaIdAdminController.getSelected() == null) {
            administradorFlotaIdAdminController.setSelected(selected.getAdministradorFlotaIdAdmin());
        }
    }

    /**
     * Sets the "selected" attribute of the Flota controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAdministradorFlotaIdFlota(ActionEvent event) {
        AdministradorFlota selected = this.getSelected();
        if (selected != null && administradorFlotaIdFlotaController.getSelected() == null) {
            administradorFlotaIdFlotaController.setSelected(selected.getAdministradorFlotaIdFlota());
        }
    }

}
