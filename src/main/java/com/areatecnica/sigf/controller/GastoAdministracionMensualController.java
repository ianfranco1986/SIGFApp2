package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.GastoAdministracionMensual;
import com.areatecnica.sigf.facade.GastoAdministracionMensualFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "gastoAdministracionMensualController")
@ViewScoped
public class GastoAdministracionMensualController extends AbstractController<GastoAdministracionMensual> {

    @Inject
    private DepartamentoController gastoAdministracionMensualIdDepartamentoController;

    public GastoAdministracionMensualController() {
        // Inform the Abstract parent controller of the concrete GastoAdministracionMensual Entity
        super(GastoAdministracionMensual.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        gastoAdministracionMensualIdDepartamentoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareGastoAdministracionMensualIdDepartamento(ActionEvent event) {
        GastoAdministracionMensual selected = this.getSelected();
        if (selected != null && gastoAdministracionMensualIdDepartamentoController.getSelected() == null) {
            gastoAdministracionMensualIdDepartamentoController.setSelected(selected.getGastoAdministracionMensualIdDepartamento());
        }
    }

}
