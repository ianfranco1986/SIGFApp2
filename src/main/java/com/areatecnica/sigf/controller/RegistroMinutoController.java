package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import java.util.List;
import com.areatecnica.sigf.facade.RegistroMinutoFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "registroMinutoController")
@ViewScoped
public class RegistroMinutoController extends AbstractController<RegistroMinuto> {

    @Inject
    private BusController registroMinutoDesdeIdBusController;
    @Inject
    private BusController registroMinutoHastaIdBusController;

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionMinutoListEmpty;

    private List<RegistroMinuto> items;

    private IRegistroMinutoDao dao;
    private Date fecha;
    private int total;

    public RegistroMinutoController() {
        // Inform the Abstract parent controller of the concrete RegistroMinuto Entity
        super(RegistroMinuto.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        this.dao = new IRegistroMinutoDaoImpl();
        this.fecha = new Date();
    }

    public void load() {
        if (fecha != null) {
            this.items = this.dao.findByDate(fecha);

            if (!this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                this.total = 0;

                for (RegistroMinuto r : this.items) {
                    this.total += r.getRegistroMinutoMonto();
                }
            } else {
                JsfUtil.addErrorMessage("No se han encontrado registros");
            }

        }
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setItems(List<RegistroMinuto> items) {
        this.items = items;
    }

    public List<RegistroMinuto> getItems() {
        return items;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        registroMinutoDesdeIdBusController.setSelected(null);
        registroMinutoHastaIdBusController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRecaudacionMinutoListEmpty();
    }

    public boolean getIsRecaudacionMinutoListEmpty() {
        return this.isRecaudacionMinutoListEmpty;
    }

    private void setIsRecaudacionMinutoListEmpty() {
        RegistroMinuto selected = this.getSelected();
        if (selected != null) {
            RegistroMinutoFacade ejbFacade = (RegistroMinutoFacade) this.getFacade();
            this.isRecaudacionMinutoListEmpty = ejbFacade.isRecaudacionMinutoListEmpty(selected);
        } else {
            this.isRecaudacionMinutoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RecaudacionMinuto
     * entities that are retrieved from RegistroMinuto and returns the
     * navigation outcome.
     *
     * @return navigation outcome for RecaudacionMinuto page
     */
    public String navigateRecaudacionMinutoList() {
        RegistroMinuto selected = this.getSelected();
        if (selected != null) {
            RegistroMinutoFacade ejbFacade = (RegistroMinutoFacade) this.getFacade();
            List<RecaudacionMinuto> selectedRecaudacionMinutoList = ejbFacade.findRecaudacionMinutoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RecaudacionMinuto_items", selectedRecaudacionMinutoList);
        }
        return "/app/recaudacionMinuto/index";
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoDesdeIdBus(ActionEvent event) {
        RegistroMinuto selected = this.getSelected();
        if (selected != null && registroMinutoDesdeIdBusController.getSelected() == null) {
            registroMinutoDesdeIdBusController.setSelected(selected.getRegistroMinutoDesdeIdBus());
        }
    }

    /**
     * Sets the "selected" attribute of the Bus controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegistroMinutoHastaIdBus(ActionEvent event) {
        RegistroMinuto selected = this.getSelected();
        if (selected != null && registroMinutoHastaIdBusController.getSelected() == null) {
            registroMinutoHastaIdBusController.setSelected(selected.getRegistroMinutoHastaIdBus());
        }
    }

}
