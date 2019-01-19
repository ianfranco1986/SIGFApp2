package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.IDescuentoExtraBusDao;
import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IDescuentoExtraBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IFlotaDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.DescuentoExtra;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import java.util.List;
import com.areatecnica.sigf.facade.DescuentoExtraBusFacade;
import com.areatecnica.sigf.models.DescuentoExtraBusDataModel;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "descuentoExtraBusController")
@ViewScoped
public class DescuentoExtraBusController extends AbstractController<DescuentoExtraBus> {

    @Inject
    private BusController descuentoExtraBusIdBusController;
    @Inject
    private DescuentoExtraController descuentoExtraBusIdDescuentoController;

    private List<Flota> flotaItems;
    private List<Bus> busItems;
    private List<Bus> selectedBusItems;
    private List<DescuentoExtraBus> items;

    private IFlotaDao flotaDao;
    private IBusDao busDao;
    private IDescuentoExtraBusDao dao;
    private DescuentoExtraBus selected;
    private DescuentoExtraBus selectedRow;
    private DescuentoExtra descuentoExtra;

    private DescuentoExtraBusDataModel model;
    private Flota flota;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionDescuentoExtraListEmpty;

    public DescuentoExtraBusController() {
        // Inform the Abstract parent controller of the concrete DescuentoExtraBus Entity
        super(DescuentoExtraBus.class);
        this.setSelected(prepareCreate(null));
    }

    @PostConstruct
    private void init() {
        this.flotaDao = new IFlotaDaoImpl();
        this.flotaItems = this.flotaDao.findByCuenta(this.getUserCount());
        this.dao = new IDescuentoExtraBusDaoImpl();
        this.items = this.dao.findAll();
        this.model = new DescuentoExtraBusDataModel(items);
    }

    @Override
    public void save(ActionEvent event) {
        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {

        for (Bus b : this.selectedBusItems) {
            DescuentoExtraBus d = new DescuentoExtraBus();
            d.setDescuentoExtraBusIdBus(b);
            d.setDescuentoExtraBusIdDescuento(descuentoExtra);
            d.setDescuentoExtraBusMonto(this.selected.getDescuentoExtraBusMonto());
            d.setDescuentoExtraBusRecaudado(Boolean.FALSE);
            this.dao.update(d);

            this.items.add(0, d);
        }

        if (this.selectedBusItems.size() > 1) {
            JsfUtil.addSuccessMessage("Se agregaron "+this.selectedBusItems.size()+" descuentos extras x buses");
        }else{
            JsfUtil.addSuccessMessage("Se agregó un descuento extra x bus");
        }

    }

    public void handleFlotaChange() {
        if (this.flota != null) {
            this.busDao = new IBusDaoImpl();
            this.busItems = this.busDao.findAllByFlota(flota);
        }
    }

    public void handleDescuentoChange() {
        if (this.descuentoExtra != null) {
            this.selected.setDescuentoExtraBusIdDescuento(descuentoExtra);
            this.selected.setDescuentoExtraBusMonto(descuentoExtra.getDescuentoExtraMonto());
        }
    }

    public DescuentoExtraBusDataModel getModel() {
        return model;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        descuentoExtraBusIdBusController.setSelected(null);
        descuentoExtraBusIdDescuentoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRecaudacionDescuentoExtraListEmpty();
    }

    public boolean getIsRecaudacionDescuentoExtraListEmpty() {
        return this.isRecaudacionDescuentoExtraListEmpty;
    }

    private void setIsRecaudacionDescuentoExtraListEmpty() {
        DescuentoExtraBus selected = this.getSelected();
        if (selected != null) {
            DescuentoExtraBusFacade ejbFacade = (DescuentoExtraBusFacade) this.getFacade();
            this.isRecaudacionDescuentoExtraListEmpty = ejbFacade.isRecaudacionDescuentoExtraListEmpty(selected);
        } else {
            this.isRecaudacionDescuentoExtraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionDescuentoExtra
     * entities that are retrieved from DescuentoExtraBus and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RecaudacionDescuentoExtra page
     */
    public String navigateRecaudacionDescuentoExtraList() {
        DescuentoExtraBus selected = this.getSelected();
        if (selected != null) {
            DescuentoExtraBusFacade ejbFacade = (DescuentoExtraBusFacade) this.getFacade();
            List<RecaudacionDescuentoExtra> selectedRecaudacionDescuentoExtraList = ejbFacade.findRecaudacionDescuentoExtraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionDescuentoExtra_items", selectedRecaudacionDescuentoExtraList);
        }
        return "/app/recaudacionDescuentoExtra/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoExtraBusIdBus(ActionEvent event) {
        DescuentoExtraBus selected = this.getSelected();
        if (selected != null && descuentoExtraBusIdBusController.getSelected() == null) {
            descuentoExtraBusIdBusController.setSelected(selected.getDescuentoExtraBusIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the DescuentoExtra controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDescuentoExtraBusIdDescuento(ActionEvent event) {
        DescuentoExtraBus selected = this.getSelected();
        if (selected != null && descuentoExtraBusIdDescuentoController.getSelected() == null) {
            descuentoExtraBusIdDescuentoController.setSelected(selected.getDescuentoExtraBusIdDescuento());
        }
    }

    public void setDescuentoExtra(DescuentoExtra descuentoExtra) {
        this.descuentoExtra = descuentoExtra;
    }

    public DescuentoExtra getDescuentoExtra() {
        return descuentoExtra;
    }

    public void setSelectedRow(DescuentoExtraBus selectedRow) {
        this.selectedRow = selectedRow;
    }

    public DescuentoExtraBus getSelectedRow() {
        return selectedRow;
    }

    public List<Flota> getFlotaItems() {
        return flotaItems;
    }

    public void setFlotaItems(List<Flota> flotaItems) {
        this.flotaItems = flotaItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getSelectedBusItems() {
        return selectedBusItems;
    }

    public void setSelectedBusItems(List<Bus> selectedBusItems) {
        this.selectedBusItems = selectedBusItems;
    }

    public List<DescuentoExtraBus> getItems() {
        return items;
    }

    public void setItems(List<DescuentoExtraBus> items) {
        this.items = items;
    }

    public DescuentoExtraBus getSelected() {
        return selected;
    }

    public void setSelected(DescuentoExtraBus selected) {
        this.selected = selected;
    }

    public Flota getFlota() {
        return flota;
    }

    public void setFlota(Flota flota) {
        this.flota = flota;
    }

}
