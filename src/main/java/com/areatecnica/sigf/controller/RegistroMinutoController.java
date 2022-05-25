package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IRegistroMinutoDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IProcesoRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRegistroMinutoDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.facade.RegistroMinutoFacade;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named(value = "registroMinutoController")
@ViewScoped
public class RegistroMinutoController extends AbstractController<RegistroMinuto> {

    // Flags to indicate if child collections are empty
    private boolean isRecaudacionMinutoListEmpty;

    private List<RegistroMinuto> items;
    private List<Bus> fromBusItems;
    private List<Bus> toBusItems;

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
        this.fromBusItems = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
        this.toBusItems = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
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

    public void setToBusItems(List<Bus> toBusItems) {
        this.toBusItems = toBusItems;
    }

    public List<Bus> getToBusItems() {
        return toBusItems;
    }

    public void setFromBusItems(List<Bus> fromBusItems) {
        this.fromBusItems = fromBusItems;
    }

    public List<Bus> getFromBusItems() {
        return fromBusItems;
    }

    public void onRowEdit(RowEditEvent event) {
        RegistroMinuto temp = (RegistroMinuto) event.getObject();

        try {
            new IRegistroMinutoDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado el registro");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
        }
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

}
