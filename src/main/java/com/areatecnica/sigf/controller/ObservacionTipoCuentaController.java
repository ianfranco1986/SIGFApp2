package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.ObservacionTipoCuenta;
import com.areatecnica.sigf.facade.ObservacionTipoCuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "observacionTipoCuentaController")
@ViewScoped
public class ObservacionTipoCuentaController extends AbstractController<ObservacionTipoCuenta> {

    @Inject
    private TipoCuentaController observacionTipoCuentaIdTipoController;

    public ObservacionTipoCuentaController() {
        // Inform the Abstract parent controller of the concrete ObservacionTipoCuenta Entity
        super(ObservacionTipoCuenta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        observacionTipoCuentaIdTipoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoCuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareObservacionTipoCuentaIdTipo(ActionEvent event) {
        ObservacionTipoCuenta selected = this.getSelected();
        if (selected != null && observacionTipoCuentaIdTipoController.getSelected() == null) {
            observacionTipoCuentaIdTipoController.setSelected(selected.getObservacionTipoCuentaIdTipo());
        }
    }

}
