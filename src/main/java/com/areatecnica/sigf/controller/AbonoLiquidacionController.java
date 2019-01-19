package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.facade.AbonoLiquidacionFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "abonoLiquidacionController")
@ViewScoped
public class AbonoLiquidacionController extends AbstractController<AbonoLiquidacion> {

    @Inject
    private AbonoBusController abonoLiquidacionIdAbonoController;
    @Inject
    private LiquidacionEmpresaController abonoLiquidacionIdLiquidacionEmpresaController;

    public AbonoLiquidacionController() {
        // Inform the Abstract parent controller of the concrete AbonoLiquidacion Entity
        super(AbonoLiquidacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        abonoLiquidacionIdAbonoController.setSelected(null);
        abonoLiquidacionIdLiquidacionEmpresaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the AbonoBus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoLiquidacionIdAbono(ActionEvent event) {
        AbonoLiquidacion selected = this.getSelected();
        if (selected != null && abonoLiquidacionIdAbonoController.getSelected() == null) {
            abonoLiquidacionIdAbonoController.setSelected(selected.getAbonoLiquidacionIdAbono());
        }
    }

    /**
     * Sets the "selected" attribute of the LiquidacionEmpresa controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAbonoLiquidacionIdLiquidacionEmpresa(ActionEvent event) {
        AbonoLiquidacion selected = this.getSelected();
        if (selected != null && abonoLiquidacionIdLiquidacionEmpresaController.getSelected() == null) {
            abonoLiquidacionIdLiquidacionEmpresaController.setSelected(selected.getAbonoLiquidacionIdLiquidacionEmpresa());
        }
    }

}
