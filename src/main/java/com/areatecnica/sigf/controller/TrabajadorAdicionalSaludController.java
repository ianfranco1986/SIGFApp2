package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "trabajadorAdicionalSaludController")
@ViewScoped
public class TrabajadorAdicionalSaludController extends AbstractController<TrabajadorAdicionalSalud> {

    @Inject
    private MonedaPactadaInstitucionSaludController trabajadorAdicionalSaludIdMonedaController;
    @Inject
    private TrabajadorController trabajadorAdicionalSaludIdTrabajadorController;

    public TrabajadorAdicionalSaludController() {
        // Inform the Abstract parent controller of the concrete TrabajadorAdicionalSalud Entity
        super(TrabajadorAdicionalSalud.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        trabajadorAdicionalSaludIdMonedaController.setSelected(null);
        trabajadorAdicionalSaludIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the MonedaPactadaInstitucionSalud
     * controller in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorAdicionalSaludIdMoneda(ActionEvent event) {
        TrabajadorAdicionalSalud selected = this.getSelected();
        if (selected != null && trabajadorAdicionalSaludIdMonedaController.getSelected() == null) {
            trabajadorAdicionalSaludIdMonedaController.setSelected(selected.getTrabajadorAdicionalSaludIdMoneda());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorAdicionalSaludIdTrabajador(ActionEvent event) {
        TrabajadorAdicionalSalud selected = this.getSelected();
        if (selected != null && trabajadorAdicionalSaludIdTrabajadorController.getSelected() == null) {
            trabajadorAdicionalSaludIdTrabajadorController.setSelected(selected.getTrabajadorAdicionalSaludIdTrabajador());
        }
    }

}
