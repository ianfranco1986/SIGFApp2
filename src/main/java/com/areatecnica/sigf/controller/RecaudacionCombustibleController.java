package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionCombustibleDaoImpl;
import com.areatecnica.sigf.dao.impl.IVentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.models.RecaudacionCombustibleDataModel;
import com.areatecnica.sigf.models.VentaCombustibleModel;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;

@Named(value = "recaudacionCombustibleController")
@ViewScoped
public class RecaudacionCombustibleController extends AbstractController<RecaudacionCombustible> {

    private List<CajaRecaudacion> cajasItems;
    private List<RecaudacionCombustible> items;
    private RecaudacionCombustibleDataModel recaudacionCombustibleDataModel;
    private VentaCombustibleModel deudasModel;
    private VentaCombustible ventaCombustible;
    private CajaRecaudacion cajaRecaudacion;
    private Date fecha;
    private int totalRecaudacion = 0;
    private boolean print;

    private NumberFormat nf = NumberFormat.getInstance();

    public RecaudacionCombustibleController() {
        // Inform the Abstract parent controller of the concrete RecaudacionCombustible Entity
        super(RecaudacionCombustible.class);

    }

    @PostConstruct
    private void init() {
        this.fecha = new Date();
        this.cajasItems = new ICajaRecaudacionDaoImpl().findAll();
    }

    public void load() {
        if (this.cajaRecaudacion != null) {
            this.items = new IRecaudacionCombustibleDaoImpl().findByCajaDate(cajaRecaudacion, fecha);
            
            if (!this.items.isEmpty()) {
                this.recaudacionCombustibleDataModel = new RecaudacionCombustibleDataModel(items);
                this.totalRecaudacion = 0;
                for (RecaudacionCombustible r : this.items) {
                    this.totalRecaudacion = this.totalRecaudacion + r.getRecaudacionCombustibleMonto();
                }
            } else {
                JsfUtil.addErrorMessage("No se han encontrado recaudaciones");
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la caja de recaudación");
        }
    }

    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {
            try {
                this.items.remove(this.getSelected());
                this.ventaCombustible = this.getSelected().getRecaudacionCombustibleIdVentaCombustible();

                this.ventaCombustible.setVentaCombustibleRecaudado(Boolean.FALSE);

                new IVentaCombustibleDaoImpl().update(ventaCombustible);

                super.delete(event);
                this.setSelected(null);
                JsfUtil.addSuccessMessage("Se ha eliminado la recaudación");
            } catch (NullPointerException e) {
                this.setSelected(null);
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar una recaudación");
            this.setSelected(null);
        }

    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdRecaudacion(ActionEvent event) {

    }

    /**
     * Sets the "selected" attribute of the VentaCombustible controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionCombustibleIdVentaCombustible(ActionEvent event) {
    }

    public void setVentaCombustible(VentaCombustible ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    public VentaCombustible getVentaCombustible() {
        return ventaCombustible;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public List<CajaRecaudacion> getCajasItems() {
        return cajasItems;
    }

    public void setCajasItems(List<CajaRecaudacion> cajasItems) {
        this.cajasItems = cajasItems;
    }

    public List<RecaudacionCombustible> getItems() {
        return items;
    }

    public void setItems(List<RecaudacionCombustible> items) {
        this.items = items;
    }

    public RecaudacionCombustibleDataModel getRecaudacionCombustibleDataModel() {
        return recaudacionCombustibleDataModel;
    }

    public void setRecaudacionCombustibleDataModel(RecaudacionCombustibleDataModel recaudacionCombustibleDataModel) {
        this.recaudacionCombustibleDataModel = recaudacionCombustibleDataModel;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public VentaCombustibleModel getDeudasModel() {
        return deudasModel;
    }

    public void setDeudasModel(VentaCombustibleModel deudasModel) {
        this.deudasModel = deudasModel;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isPrint() {
        return print;
    }

}
