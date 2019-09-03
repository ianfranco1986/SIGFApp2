package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Proveedor;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "proveedorController")
@ViewScoped
public class ProveedorController extends AbstractController<Proveedor> {

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public ProveedorController() {
        // Inform the Abstract parent controller of the concrete Proveedor Entity
        super(Proveedor.class);
    }
}
