/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.entities.Bus;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
@Named(value = "informeBoletoVueltaController")
@ViewScoped
public class InformeBoletoVueltaController implements Serializable{

    private Bus bus;
    private Date fecha;
    private List<Bus> items;
    private IBusDao dao;

    /**
     * Creates a new instance of InformeBoletoVueltaController
     */
    public InformeBoletoVueltaController() {
        this.dao = new IBusDaoImpl();
        this.items = this.dao.findAll();
        //Data access object 
        //POJO -> Plain Old Java Object 
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Bus> getItems() {
        return items;
    }

    public void setItems(List<Bus> items) {
        this.items = items;
    }

    public IBusDao getDao() {
        return dao;
    }

    public void setDao(IBusDao dao) {
        this.dao = dao;
    }

}
