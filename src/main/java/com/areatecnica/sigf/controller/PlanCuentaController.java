package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.PlanCuenta;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "planCuentaController")
@ViewScoped
public class PlanCuentaController extends AbstractController<PlanCuenta> {

    @Inject
    private EmpresaController planCuentaEmpresaIdController;
    @Inject
    private PlanCuentaSubTipoController planCuentaSubTipoIdController;
    @Inject
    private TipoPlanCuentaController planCuentaTipoIdController;

    public PlanCuentaController() {
        // Inform the Abstract parent controller of the concrete PlanCuenta Entity
        super(PlanCuenta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        planCuentaEmpresaIdController.setSelected(null);
        planCuentaSubTipoIdController.setSelected(null);
        planCuentaTipoIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanCuentaEmpresaId(ActionEvent event) {
        PlanCuenta selected = this.getSelected();
        if (selected != null && planCuentaEmpresaIdController.getSelected() == null) {
            planCuentaEmpresaIdController.setSelected(selected.getPlanCuentaEmpresaId());
        }
    }

}
