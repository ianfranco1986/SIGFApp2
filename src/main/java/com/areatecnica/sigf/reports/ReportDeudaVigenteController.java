/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import com.areatecnica.sigf.controller.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "reportDeudaVigenteController")
@ViewScoped
public class ReportDeudaVigenteController implements Serializable {

    private List<String> items;
    private String selected;
    private Date fecha;
    private String informe;

    /**
     * Creates a new instance of ReportControllerGuiaCajaFecha
     */
    public ReportDeudaVigenteController() {

    }

    @PostConstruct
    private void init() {
        this.items = new ArrayList<>();
        this.items.add("Petroleos");
        this.items.add("Minutos");
        //this.items.add("Extras");

        this.informe = "inf-deudas_vigentes_petroleo";
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        map.put("1", 1);
        return map;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public void setPath() {

        switch (this.selected) {
            case "Petroleos":
                this.informe = "inf-deudas_vigentes_petroleo";
                break;
            case "Minutos":
                this.informe = "inf-deudas_vigentes_minutos";
                break;
            case "Extras":
                this.informe = "inf-recaudacion_general_caja";
                break;
        }
    }

    public String getInforme() {
        return informe;
    }

    public void falta() {
        JsfUtil.addErrorMessage("No se encuentran registros");
    }

}
