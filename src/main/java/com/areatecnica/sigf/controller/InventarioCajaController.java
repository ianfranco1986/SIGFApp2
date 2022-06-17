package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.BoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.CajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.InventarioCajaDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.InventarioCaja;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "inventarioCajaController")
@ViewScoped
public class InventarioCajaController extends AbstractController<InventarioCaja> {

    @Inject
    private InventarioInternoController inventarioCajaIdInventarioInternoController;
    @Inject
    private CajaRecaudacionController inventarioCajaIdCajaController;

    private List<Boleto> itemsBoletos;
    private List<CajaRecaudacion> itemsCajaRecaudacion;
    private List<InventarioCaja> items2;
    private List<InventarioCaja> selectedItems;

    // Flags to indicate if child collections are empty
    private boolean isVentaBoletoListEmpty;

    public InventarioCajaController() {
        // Inform the Abstract parent controller of the concrete InventarioCaja Entity
        super(InventarioCaja.class);
        this.itemsBoletos = new BoletoDaoImpl().findByCuenta(this.getUserCount());
        this.itemsCajaRecaudacion = new CajaRecaudacionDaoImpl().findAllActive();

        this.items2 = new InventarioCajaDaoImpl().findByEstado(false, this.getUserCount());
    }

    public List<InventarioCaja> getItems2() {
        return items2;
    }

    public void setItems2(List<InventarioCaja> items2) {
        this.items2 = items2;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        inventarioCajaIdInventarioInternoController.setSelected(null);
        inventarioCajaIdCajaController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    
    /**
     * Sets the "selected" attribute of the InventarioInterno controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioCajaIdInventarioInterno(ActionEvent event) {
        InventarioCaja selected = this.getSelected();
        if (selected != null && inventarioCajaIdInventarioInternoController.getSelected() == null) {
            inventarioCajaIdInventarioInternoController.setSelected(selected.getInventarioCajaIdInventarioInterno());
        }
    }

    /**
     * Sets the "selected" attribute of the CajaRecaudacion controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioCajaIdCaja(ActionEvent event) {
        InventarioCaja selected = this.getSelected();
        if (selected != null && inventarioCajaIdCajaController.getSelected() == null) {
            inventarioCajaIdCajaController.setSelected(selected.getInventarioCajaIdCaja());
        }
    }

    public List<Boleto> getItemsBoletos() {
        return itemsBoletos;
    }

    public void setItemsBoletos(List<Boleto> itemsBoletos) {
        this.itemsBoletos = itemsBoletos;
    }

    public List<CajaRecaudacion> getItemsCajaRecaudacion() {
        return itemsCajaRecaudacion;
    }

    public void setItemsCajaRecaudacion(List<CajaRecaudacion> itemsCajaRecaudacion) {
        this.itemsCajaRecaudacion = itemsCajaRecaudacion;
    }

    public void setSelectedItems(List<InventarioCaja> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<InventarioCaja> getSelectedItems() {
        return selectedItems;
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
