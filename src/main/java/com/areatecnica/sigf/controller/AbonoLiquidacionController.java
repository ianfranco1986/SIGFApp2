package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "abonoLiquidacionController")
@ViewScoped
public class AbonoLiquidacionController extends AbstractController<AbonoLiquidacion> {

    
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
        
        abonoLiquidacionIdLiquidacionEmpresaController.setSelected(null);
    }

   

}
