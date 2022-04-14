package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AdministradorBus;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "administradorBusController")
@ViewScoped
public class AdministradorBusController extends AbstractController<AdministradorBus> {

    @Inject
    private AdministradorController administradorBusIdAdminController;

    public AdministradorBusController() {
        // Inform the Abstract parent controller of the concrete AdministradorBus Entity
        super(AdministradorBus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        administradorBusIdAdminController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Administrador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAdministradorBusIdAdmin(ActionEvent event) {
        AdministradorBus selected = this.getSelected();
        if (selected != null && administradorBusIdAdminController.getSelected() == null) {
            administradorBusIdAdminController.setSelected(selected.getAdministradorBusIdAdmin());
        }
    }

}
