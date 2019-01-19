package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.facade.ReconocimientoDeudaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "reconocimientoDeudaController")
@ViewScoped
public class ReconocimientoDeudaController extends AbstractController<ReconocimientoDeuda> {

    @Inject
    private EmpresaController reconocimientoDeudaIdEmpresaController;
    @Inject
    private TrabajadorController reconocimientoDeudaIdTrabajadorController;

    public ReconocimientoDeudaController() {
        // Inform the Abstract parent controller of the concrete ReconocimientoDeuda Entity
        super(ReconocimientoDeuda.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        reconocimientoDeudaIdEmpresaController.setSelected(null);
        reconocimientoDeudaIdTrabajadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareReconocimientoDeudaIdEmpresa(ActionEvent event) {
        ReconocimientoDeuda selected = this.getSelected();
        if (selected != null && reconocimientoDeudaIdEmpresaController.getSelected() == null) {
            reconocimientoDeudaIdEmpresaController.setSelected(selected.getReconocimientoDeudaIdEmpresa());
        }
    }

    /**
     * Sets the "selected" attribute of the Trabajador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareReconocimientoDeudaIdTrabajador(ActionEvent event) {
        ReconocimientoDeuda selected = this.getSelected();
        if (selected != null && reconocimientoDeudaIdTrabajadorController.getSelected() == null) {
            reconocimientoDeudaIdTrabajadorController.setSelected(selected.getReconocimientoDeudaIdTrabajador());
        }
    }

}
