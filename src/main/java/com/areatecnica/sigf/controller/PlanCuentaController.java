package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.PlanCuenta;
import com.areatecnica.sigf.facade.PlanCuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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

    /**
     * Sets the "selected" attribute of the PlanCuentaSubTipo controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanCuentaSubTipoId(ActionEvent event) {
        PlanCuenta selected = this.getSelected();
        if (selected != null && planCuentaSubTipoIdController.getSelected() == null) {
            planCuentaSubTipoIdController.setSelected(selected.getPlanCuentaSubTipoId());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoPlanCuenta controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePlanCuentaTipoId(ActionEvent event) {
        PlanCuenta selected = this.getSelected();
        if (selected != null && planCuentaTipoIdController.getSelected() == null) {
            planCuentaTipoIdController.setSelected(selected.getPlanCuentaTipoId());
        }
    }

}
