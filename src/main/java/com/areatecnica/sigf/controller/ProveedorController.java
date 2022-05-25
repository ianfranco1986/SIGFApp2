package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Proveedor;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "proveedorController")
@ViewScoped
public class ProveedorController extends AbstractController<Proveedor> {

    // Flags to indicate if child collections are empty
    private boolean isCompraListEmpty;

    public ProveedorController() {
        // Inform the Abstract parent controller of the concrete Proveedor Entity
        super(Proveedor.class);
    }

    public void delete(ActionEvent event) {
        if (getSelected() != null) {
              
        }
    }

}
