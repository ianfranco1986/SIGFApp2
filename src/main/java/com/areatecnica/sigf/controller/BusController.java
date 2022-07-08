package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.BusDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.UnidadNegocio;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "busController")
@ViewScoped
public class BusController extends AbstractController<Bus> {

    private List<Bus> items;
    private Bus selected;

    public BusController() {
        // Inform the Abstract parent controller of the concrete Bus Entity
        super(Bus.class);

        //this.getItems().removeIf(e -> e.getBusId() == 1);
    }

    @PostConstruct
    public void init() {
        this.items = new BusDaoImpl().findAll();
        this.items.removeIf(e -> e.getBusId() == 1);
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public void setSelected(Bus selected) {
        this.selected = selected;
    }

    public Bus getSelected() {
        return this.selected;
    }

    public Bus prepareCreate(ActionEvent event) {
        
        this.selected = new Bus();
        
        return this.selected;
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
        this.getSelected().setBusNumero(new BusDaoImpl().findMaxNumeroUnidad(unidad));
    }

    public void deletet(ActionEvent evt) {
        JsfUtil.addErrorMessage("Error al eliminar el bus ");
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

    public void save(ActionEvent event) {
        if (this.selected != null) {
            Bus bus = new BusDaoImpl().update(this.selected);
            if (bus != null) {
                JsfUtil.addSuccessMessage("Se ha actualizado el bus");
            } else {
                JsfUtil.addErrorMessage("Error al registrar los cambios");
            }
        } else {
            JsfUtil.addErrorMessage("Error al registrar los cambios ");
        }

    }

    public void saveNew(ActionEvent event) {
        if (this.selected != null) {
            Bus bus = new BusDaoImpl().create(this.selected);
            if (bus != null) {
                JsfUtil.addSuccessMessage("Se ha registrado el bus");
                this.items.add(bus);
            } else {
                JsfUtil.addErrorMessage("Error al registrar los cambios");
            }
        } else {
            JsfUtil.addErrorMessage("Error al registrar los cambios ");
        }
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Bus product = (Bus) event.getData();
//            if (product.getOrders() == null) {
//                product.setOrders(orderService.getOrders((int) (Math.random() * 10)));
//            }
        }
    }
}
