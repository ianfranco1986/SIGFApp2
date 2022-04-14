package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ValorRolloUnidad;
import com.areatecnica.sigf.facade.ValorRolloUnidadFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "valorRolloUnidadController")
@ViewScoped
public class ValorRolloUnidadController extends AbstractController<ValorRolloUnidad> {

    @Inject
    private UnidadNegocioController valorRolloUnidadIdUnidadController;

    public ValorRolloUnidadController() {
        // Inform the Abstract parent controller of the concrete ValorRolloUnidad Entity
        super(ValorRolloUnidad.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        valorRolloUnidadIdUnidadController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the UnidadNegocio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareValorRolloUnidadIdUnidad(ActionEvent event) {
        ValorRolloUnidad selected = this.getSelected();
        if (selected != null && valorRolloUnidadIdUnidadController.getSelected() == null) {
            valorRolloUnidadIdUnidadController.setSelected(selected.getValorRolloUnidadIdUnidad());
        }
    }

}
