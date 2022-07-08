package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.dao.impl.VentaCombustibleDaoImpl;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.facade.VentaCombustibleFacade;
import com.areatecnica.sigf.util.CurrentDate;
import com.areatecnica.sigf.util.LocalDateConverter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Named(value = "ventaCombustibleController")
@ViewScoped
public class VentaCombustibleController extends AbstractController<VentaCombustible> {

    private List<VentaCombustible> items;
    private VentaCombustible selected; 
    
    private LocalDate date;
    private LocalDateConverter dc;

    private float precio;
    private int total = 0;
    
    
    private String informe = "inf-venta_petroleo";
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public VentaCombustibleController() {
        // Inform the Abstract parent controller of the concrete VentaCombustible Entity
        super(VentaCombustible.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        this.setDate(LocalDate.now());
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();

        map.put("fecha", this.dc.getDate());

        return map;
    }

    public void load() {
        if (this.date != null) {
            this.total = 0;
            this.items = new VentaCombustibleDaoImpl().findByDate(this.dc.getDate());

            if (!this.items.isEmpty()) {
                for (VentaCombustible v : this.items) {
                    this.total = this.total + v.getVentaCombustibleTotal();

                }
                this.precio = this.items.get(0).getVentaCombustiblePrecio();
            }

        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la fecha");
        }
    }

    public List<VentaCombustible> getItems() {
        return items; 
    }

    public void setItems(List<VentaCombustible> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String totalCombustible() {
        return "$ " + decimalFormat.format(this.total);
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getInforme() {
        return informe;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public LocalDateConverter getDc() {
        return dc;
    }

    public void setDc(LocalDateConverter dc) {
        this.dc = dc;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.dc = new LocalDateConverter(date);
    }

    public LocalDate getDate() {
        return date;
    }

}
