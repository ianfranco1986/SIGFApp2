package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.ICajaRecaudacionDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionExtraDaoImpl;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.models.RecaudacionExtraDataModel;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

@Named(value = "recaudacionExtraController")
@ViewScoped
public class RecaudacionExtraController implements Serializable {

    @Inject
    private RecaudacionController recaudacionExtraIdRecaudacionController;
    private RecaudacionExtraDataModel recaudacionExtraDataModel;

    private Date fecha;
    private CajaRecaudacion cajaRecaudacion;
    private RecaudacionExtra selected;
    private Recaudacion recaudacion;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private List<RecaudacionExtra> items;

    private int totalRecaudacion;

    private NumberFormat nf = NumberFormat.getInstance();

    public RecaudacionExtraController() {

    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.cajaRecaudacionItems = new ICajaRecaudacionDaoImpl().findAll();

    }

    public void load() {
        if (this.cajaRecaudacion != null) {
            this.items = new IRecaudacionExtraDaoImpl().findByCajaDate(cajaRecaudacion, fecha);
            this.totalRecaudacion = 0;
            if (!this.items.isEmpty()) {
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

                for (RecaudacionExtra r : this.items) {
                    this.totalRecaudacion = this.totalRecaudacion + r.getRecaudacionExtraMonto();
                }

                this.recaudacionExtraDataModel = new RecaudacionExtraDataModel(items);
            } else {
                JsfUtil.addErrorMessage("No se han encontrado registros");
            }
        }
    }

    public void setSelected(RecaudacionExtra selected) {
        this.selected = selected;
    }

    public RecaudacionExtra getSelected() {
        return selected;
    }

    public Recaudacion getRecaudacion() {
        return recaudacion;
    }

    public RecaudacionController getRecaudacionExtraIdRecaudacionController() {
        return recaudacionExtraIdRecaudacionController;
    }

    public void setRecaudacion(Recaudacion recaudacion) {
        this.recaudacion = recaudacion;
    }

    public void setRecaudacionExtraIdRecaudacionController(RecaudacionController recaudacionExtraIdRecaudacionController) {
        this.recaudacionExtraIdRecaudacionController = recaudacionExtraIdRecaudacionController;
    }

    public void setRecaudacionExtraDataModel(RecaudacionExtraDataModel recaudacionExtraDataModel) {
        this.recaudacionExtraDataModel = recaudacionExtraDataModel;
    }

    public RecaudacionExtraDataModel getRecaudacionExtraDataModel() {
        return recaudacionExtraDataModel;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public List<RecaudacionExtra> getItems() {
        return items;
    }

    public void setItems(List<RecaudacionExtra> items) {
        this.items = items;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudacion(int totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void delete() {
        if (this.selected != null) {
            new IRecaudacionExtraDaoImpl().delete(this.selected);

            this.items.remove(this.selected);

            JsfUtil.addSuccessMessage("Se ha anulado la recaudación");
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
        }
    }

    public void onRowEdit(RowEditEvent event) {
        RecaudacionExtra temp = null;
        try {
            temp = (RecaudacionExtra) event.getObject();

            new IRecaudacionExtraDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado la Recaudación: " + temp.getRecaudacionExtraIdRecaudacion().getRecaudacionId());

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha dd dddd error al registrar los cambios" + e.toString());
            System.err.println("ERROR:" + e.getMessage());
            System.err.println("ERROR:" + e.getLocalizedMessage());
            System.err.println("ERROR:" + e.toString());
        }

    }

}
