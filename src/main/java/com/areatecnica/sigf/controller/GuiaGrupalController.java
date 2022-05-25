/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IGuiaDaoImpl;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.models.GuiaGrupalDataModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Named(value = "guiaGrupalController")
@ViewScoped
public class GuiaGrupalController extends AbstractController<Guia> {

    private Date fecha;
    private int folioInicio;
    private GrupoServicio grupoServicio;
    private GuiaGrupalDataModel model;
    private Guia selected;
    private Terminal terminal;
    private boolean mantenerConductor;
    private boolean mantenerEstado;
    private boolean deshabilitarFolio;
    private String formatFecha;
    private static final String pattern = "###,###.###";
    private static final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    private List<Guia> items;
    private List<GrupoServicio> grupoServicioItems;

    /**
     * Creates a new instance of GuiaGrupalController
     */
    public GuiaGrupalController() {
        super(Guia.class);
    }

    @PostConstruct
    private void init() {
        this.fecha = new Date();
        this.mantenerConductor = true;
        this.mantenerEstado = true;
        this.deshabilitarFolio = true;
        this.terminal = this.getCurrentUser().getUsuarioIdTerminal();
        this.grupoServicioItems = new ArrayList<GrupoServicio>(this.terminal.getGrupoServicioList());

    }

    public void load() {
        this.items = new ArrayList<>();

        if (this.grupoServicio != null) {
            for (Bus b : this.grupoServicio.getBusList()) {
                Guia g = new Guia();
                g.setGuiaFecha(fecha);
                g.setGuiaFolio(folioInicio);
                g.setGuiaIdBus(b);

                Guia aux = new IGuiaDaoImpl().findLastGuiaByBusFecha(b, fecha);

                if (aux != null) {
                    g.setGuiaIdTrabajador(aux.getGuiaIdTrabajador());
                }

                g.setGuiaTotalIngreso(0);

                this.folioInicio++;

                this.items.add(g);
            }

            this.model = new GuiaGrupalDataModel(items);
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un Grupo/Servicio");
        }
    }

    public int getFolioInicio() {
        return folioInicio;
    }

    public void setFolioInicio(int folioInicio) {
        this.folioInicio = folioInicio;
    }

    public GrupoServicio getGrupoServicio() {
        return grupoServicio;
    }

    public void setGrupoServicio(GrupoServicio grupoServicio) {
        this.grupoServicio = grupoServicio;
    }

    public GuiaGrupalDataModel getModel() {
        return model;
    }

    public void setModel(GuiaGrupalDataModel model) {
        this.model = model;
    }

    public Guia getSelected() {
        return selected;
    }

    public void setSelected(Guia selected) {
        this.selected = selected;
    }

    public boolean isMantenerConductor() {
        return mantenerConductor;
    }

    public void setMantenerConductor(boolean mantenerConductor) {
        this.mantenerConductor = mantenerConductor;
    }

    public boolean isMantenerEstado() {
        return mantenerEstado;
    }

    public void setMantenerEstado(boolean mantenerEstado) {
        this.mantenerEstado = mantenerEstado;
    }

    public boolean isDeshabilitarFolio() {
        return deshabilitarFolio;
    }

    public void setDeshabilitarFolio(boolean deshabilitarFolio) {
        this.deshabilitarFolio = deshabilitarFolio;
    }

    public List<Guia> getItems() {
        return items;
    }

    public void setItems(List<Guia> items) {
        this.items = items;
    }

    public List<GrupoServicio> getGrupoServicioItems() {
        return grupoServicioItems;
    }

    public void setGrupoServicioItems(List<GrupoServicio> grupoServicioItems) {
        this.grupoServicioItems = grupoServicioItems;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormatFecha() {
        return formatFecha;
    }

    public void setFormatFecha(String formatFecha) {
        this.formatFecha = formatFecha;
    }

}
