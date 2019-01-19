/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.dao.IInventarioInternoDao;
import com.areatecnica.sigf.dao.impl.IInventarioCajaDaoImpl;
import com.areatecnica.sigf.dao.impl.IInventarioInternoDaoImpl;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.InventarioCaja;
import com.areatecnica.sigf.entities.InventarioInterno;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author ianfr
 */
@Named(value = "traspasoBoletoCajaController")
@ViewScoped
public class TraspasoBoletoCajaController extends AbstractController<InventarioCaja> {

    @Inject
    private InventarioInternoController inventarioCajaIdInventarioInternoController;
    @Inject
    private CajaRecaudacionController inventarioCajaIdCajaController;

    private Boleto boletoItem;
    private InventarioInterno inventarioInterno;
    private List<InventarioInterno> inventarioInternoList;
    private List<InventarioCaja> inventarioCajaList;
    private List<InventarioInterno> inventarioCajaSelectedItems;
    private IInventarioInternoDao inventarioInternoDao;
    private IInventarioCajaDao dao;

    private Date fecha;

    /**
     * Creates a new instance of TraspasoBoletoCajaController
     */
    public TraspasoBoletoCajaController() {
        super(InventarioCaja.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.

        this.setSelected(new InventarioCaja());
//        this.getSelected().setInventarioCajaFechaIngreso(new Date());
        this.inventarioInternoList = new ArrayList<>();
        this.inventarioCajaList = new ArrayList<>();
        this.inventarioInternoDao = new IInventarioInternoDaoImpl();
        this.dao = new IInventarioCajaDaoImpl();
        this.fecha = new Date();
    }

    /**
     * @return the inventarioCajaList
     */
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    /**
     * @param inventarioCajaList the inventarioCajaList to set
     */
    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    /**
     * @return the inventarioInterno
     */
    public InventarioInterno getInventarioInterno() {
        return inventarioInterno;
    }

    /**
     * @param inventarioInterno the inventarioInterno to set
     */
    public void setInventarioInterno(InventarioInterno inventarioInterno) {
        this.inventarioInterno = inventarioInterno;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    /**
     * @return the inventarioInternoList
     */
    public List<InventarioInterno> getInventarioInternoList() {
        return inventarioInternoList;
    }

    public void setInventarioCajaSelectedItems(List<InventarioInterno> inventarioCajaSelectedItems) {
        this.inventarioCajaSelectedItems = inventarioCajaSelectedItems;
    }

    public List<InventarioInterno> getInventarioCajaSelectedItems() {
        return inventarioCajaSelectedItems;
    }

    /**
     * @param inventarioInternoList the inventarioInternoList to set
     */
    public void setInventarioInternoList(List<InventarioInterno> inventarioInternoList) {
        this.inventarioInternoList = inventarioInternoList;
    }

    /**
     * @return the boletoItem
     */
    public Boleto getBoletoItem() {
        return boletoItem;
    }

    /**
     * @param boletoItem the boletoItem to set
     */
    public void setBoletoItem(Boleto boletoItem) {
        this.boletoItem = boletoItem;
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        inventarioCajaIdInventarioInternoController.setSelected(null);
        inventarioCajaIdCajaController.setSelected(null);
    }

    public void handleBoletoChange(ActionEvent event) {
        if (this.boletoItem != null) {
            this.inventarioInternoList = this.inventarioInternoDao.findByBoletoEstado(this.boletoItem, Boolean.FALSE);
            for (InventarioCaja c : this.inventarioCajaList) {
                if (this.inventarioInternoList.contains(c.getInventarioCajaIdInventarioInterno())) {
                    this.inventarioInternoList.remove(c.getInventarioCajaIdInventarioInterno());
                }
            }
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar un boleto");
        }

    }

    public void addInventarioCaja(ActionEvent event) {

        CajaRecaudacion caja = this.getSelected().getInventarioCajaIdCaja();

        this.inventarioCajaSelectedItems.forEach((ic) -> {
            InventarioCaja i = new InventarioCaja();
            //i.setInventarioCajaFechaIngreso(new Date());
            i.setInventarioCajaEstado(Boolean.FALSE);
            i.setInventarioCajaIdCaja(caja);
            i.setInventarioCajaIdInventarioInterno(ic);
            i.setInventarioCajaIdentificador(ic.getInventarioInternoIdentificador());
            i.setInventarioCajaSerie(ic.getInventarioInternoSerie());
            ic.setInventarioInternoEstado(Boolean.TRUE);

            this.inventarioInternoList.remove(ic);

            this.inventarioCajaList.add(i);
        });

        this.setSelected(null);
        this.setSelected(new InventarioCaja());
        this.getSelected().setInventarioCajaIdCaja(caja);

    }

    @Override
    public void saveNew(ActionEvent event) {

        for (InventarioCaja i : this.inventarioCajaList) {
            i.getInventarioCajaIdInventarioInterno().setInventarioInternoEstado(Boolean.TRUE);
            try {
                this.inventarioInternoDao.update(i.getInventarioCajaIdInventarioInterno());
                this.dao.update(i);
                JsfUtil.addSuccessMessage("Se han registrado los cambios");
            } catch (EJBException ex) {
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }

        this.inventarioCajaList = null;

    }

}
