package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.LiquidacionEmpresa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "liquidacionEmpresaController")
@ViewScoped
public class LiquidacionEmpresaController extends AbstractController<LiquidacionEmpresa> {

    @Inject
    private EmpresaController liquidacionEmpresaIdEmpresaController;

    // Flags to indicate if child collections are empty
    private boolean isCargoLiquidacionListEmpty;
    private boolean isAbonoLiquidacionListEmpty;

    public LiquidacionEmpresaController() {
        // Inform the Abstract parent controller of the concrete LiquidacionEmpresa Entity
        super(LiquidacionEmpresa.class);
    }




}
