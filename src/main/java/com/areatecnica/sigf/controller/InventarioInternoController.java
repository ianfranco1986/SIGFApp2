package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IBoletoDao;
import com.areatecnica.sigf.dao.impl.IBoletoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;
import com.areatecnica.sigf.facade.InventarioInternoFacade;
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

    private IBoletoDao boletoDao;

    // Flags to indicate if child collections are empty
    private boolean isInventarioCajaListEmpty;

    public InventarioInternoController() {
        // Inform the Abstract parent controller of the concrete InventarioInterno Entity
        super(InventarioInterno.class);
        this.boletoDao = new IBoletoDaoImpl();

        this.itemsBoletos = this.boletoDao.findByCuenta(this.getUserCount());
    }

    public void setItemsBoletos(List<Boleto> itemsBoletos) {
        this.itemsBoletos = itemsBoletos;
    }

    public List<Boleto> getItemsBoletos() {
        return itemsBoletos;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        inventarioInternoIdBoletoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsInventarioCajaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Boleto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareInventarioInternoIdBoleto(ActionEvent event) {
        InventarioInterno selected = this.getSelected();
        if (selected != null && inventarioInternoIdBoletoController.getSelected() == null) {
            inventarioInternoIdBoletoController.setSelected(selected.getInventarioInternoIdBoleto());
        }
    }

    public boolean getIsInventarioCajaListEmpty() {
        return this.isInventarioCajaListEmpty;
    }

    private void setIsInventarioCajaListEmpty() {
        InventarioInterno selected = this.getSelected();
        if (selected != null) {
            InventarioInternoFacade ejbFacade = (InventarioInternoFacade) this.getFacade();
            this.isInventarioCajaListEmpty = ejbFacade.isInventarioCajaListEmpty(selected);
        } else {
            this.isInventarioCajaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InventarioCaja entities
     * that are retrieved from InventarioInterno and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InventarioCaja page
     */
    public String navigateInventarioCajaList() {
        InventarioInterno selected = this.getSelected();
        if (selected != null) {
            InventarioInternoFacade ejbFacade = (InventarioInternoFacade) this.getFacade();
            List<InventarioCaja> selectedInventarioCajaList = ejbFacade.findInventarioCajaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InventarioCaja_items", selectedInventarioCajaList);
        }
        return "/app/inventarioCaja/index";
    }

}
