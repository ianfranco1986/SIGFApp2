package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.entities.VentaBoleto;
import java.util.List;
import com.areatecnica.sigf.facade.InventarioCajaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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

    // Flags to indicate if child collections are empty
    private boolean isVentaBoletoListEmpty;

    public InventarioCajaController() {
        // Inform the Abstract parent controller of the concrete InventarioCaja Entity
        super(InventarioCaja.class);
        this.itemsBoletos = new IBoletoDaoImpl().findByCuenta(this.getUserCount());
        this.itemsCajaRecaudacion = new ICajaRecaudacionDaoImpl().findAll();

        this.items2 = new IInventarioCajaDaoImpl().findByEstado(false, this.getUserCount());
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

}
