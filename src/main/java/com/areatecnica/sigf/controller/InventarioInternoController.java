package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;
import com.areatecnica.sigf.facade.InventarioInternoFacade;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "inventarioInternoController")
@ViewScoped
public class InventarioInternoController extends AbstractController<InventarioInterno> {

    @Inject
    private BoletoController inventarioInternoIdBoletoController;

    private List<Boleto> itemsBoletos;

    private List<InventarioInterno> items2;
    private List<InventarioInterno> selectedItems;

    // Flags to indicate if child collections are empty
    private boolean isInventarioCajaListEmpty;

    public InventarioInternoController() {
        // Inform the Abstract parent controller of the concrete InventarioInterno Entity
        super(InventarioInterno.class);
        this.itemsBoletos = new IBoletoDaoImpl().findByCuenta(this.getUserCount());
        this.items2 = new IInventarioInternoDaoImpl().findByEstado(false, this.getUserCount());
    }

    public List<InventarioInterno> getItems2() {
        return items2;
    }

    public void setItems2(List<InventarioInterno> items) {
        this.items2 = items;
    }

    public void setItemsBoletos(List<Boleto> itemsBoletos) {
        this.itemsBoletos = itemsBoletos;
    }

    public List<Boleto> getItemsBoletos() {
        return itemsBoletos;
    }

    public List<InventarioInterno> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<InventarioInterno> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " recaudaciones seleccionadas" : "1 recaudación seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {

            this.selectedItems = new ArrayList<>();
        }
    }

}
