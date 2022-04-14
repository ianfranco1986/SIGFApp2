package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;
import com.areatecnica.sigf.facade.BusFacade;
import com.areatecnica.sigf.models.BusDataModel;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@Named(value = "busController")
@ViewScoped
public class BusController extends AbstractController<Bus> {

    private BusDataModel model;

    public BusController() {
        // Inform the Abstract parent controller of the concrete Bus Entity
        super(Bus.class);

        //this.getItems().removeIf(e -> e.getBusId() == 1);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {

    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {

    }

    public void findMaxByUnidad() {
        UnidadNegocio unidad = this.getSelected().getBusIdUnidadNegocio();
        this.getSelected().setBusNumero(new IBusDaoImpl().findMaxNumeroUnidad(unidad));
    }


    public void deletet(ActionEvent evt) {
        this.delete(evt);

    }

    public boolean hasSelectedProducts() {
        return this.getSelected() != null;
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            return "Eliminar Bus N° " + this.getSelected().getBusNumero() + " PPU " + this.getSelected().getBusPatente();
        }

        return "Debe seleccionar un bus";
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event);
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Bus product = (Bus) event.getData();
//            if (product.getOrders() == null) {
//                product.setOrders(orderService.getOrders((int) (Math.random() * 10)));
//            }
        }
    }

    public void setModel(BusDataModel model) {
        this.model = model;
    }

    public BusDataModel getModel() {
        return model;
    }

}
