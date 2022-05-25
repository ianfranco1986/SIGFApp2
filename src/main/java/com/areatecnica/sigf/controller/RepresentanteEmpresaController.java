package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.RepresentanteEmpresa;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "representanteEmpresaController")
@ViewScoped
public class RepresentanteEmpresaController extends AbstractController<RepresentanteEmpresa> {

    @Inject
    private EmpresaController representanteEmpresaIdEmpresaController;
    @Inject
    private RepresentanteLegalController representanteEmpresaIdRepresentanteLegalController;

    public RepresentanteEmpresaController() {
        // Inform the Abstract parent controller of the concrete RepresentanteEmpresa Entity
        super(RepresentanteEmpresa.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        representanteEmpresaIdEmpresaController.setSelected(null);
        representanteEmpresaIdRepresentanteLegalController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRepresentanteEmpresaIdEmpresa(ActionEvent event) {
        RepresentanteEmpresa selected = this.getSelected();
        if (selected != null && representanteEmpresaIdEmpresaController.getSelected() == null) {
            representanteEmpresaIdEmpresaController.setSelected(selected.getRepresentanteEmpresaIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the RepresentanteLegal controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRepresentanteEmpresaIdRepresentanteLegal(ActionEvent event) {
        RepresentanteEmpresa selected = this.getSelected();
        if (selected != null && representanteEmpresaIdRepresentanteLegalController.getSelected() == null) {
            representanteEmpresaIdRepresentanteLegalController.setSelected(selected.getRepresentanteEmpresaIdRepresentanteLegal());
        }
    }

}
