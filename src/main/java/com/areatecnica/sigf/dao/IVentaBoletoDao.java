/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.VentaBoleto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IVentaBoletoDao<T> extends IGenericDAO<T> {
    
    List<VentaBoleto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);
    
    List<VentaBoleto> findByBus(Bus bus);
    
    List<VentaBoleto> findByBusEstado(Bus bus);
    
    List<VentaBoleto> findByDefaultBus();
    
    VentaBoleto findByBusBoletoEstado(Bus bus, Boleto boleto);
    
    VentaBoleto findBySerie(int serie);
}
