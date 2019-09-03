package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBoletoDaoImpl;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
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
    
    // Flags to indicate if child collections are empty
    private boolean isVentaBoletoListEmpty;

    public InventarioCajaController() {
        // Inform the Abstract parent controller of the concrete InventarioCaja Entity
        super(InventarioCaja.class);
        this.itemsBoletos = new IBoletoDaoImpl().findByCuenta(this.getUserCount());
        this.itemsCajaRecaudacion = new ICajaRecaudacionDaoImpl().findAll();
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
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsVentaBoletoListEmpty();
    }

    public boolean getIsVentaBoletoListEmpty() {
        return this.isVentaBoletoListEmpty;
    }

    private void setIsVentaBoletoListEmpty() {
        InventarioCaja selected = this.getSelected();
        if (selected != null) {
            InventarioCajaFacade ejbFacade = (InventarioCajaFacade) this.getFacade();
            this.isVentaBoletoListEmpty = ejbFacade.isVentaBoletoListEmpty(selected);
        } else {
            this.isVentaBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of VentaBoleto entities that
     * are retrieved from InventarioCaja and returns the navigation outcome.
     *
     * @return navigation outcome for VentaBoleto page
     */
    public String navigateVentaBoletoList() {
        InventarioCaja selected = this.getSelected();
        if (selected != null) {
            InventarioCajaFacade ejbFacade = (InventarioCajaFacade) this.getFacade();
            List<VentaBoleto> selectedVentaBoletoList = ejbFacade.findVentaBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaBoleto_items", selectedVentaBoletoList);
        }
        return "/app/ventaBoleto/index";
    }

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
