/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.util.DatePlusValueHelper;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IVentaCombustibleDao<T> extends IGenericDAO<T> {
    
    VentaCombustible findByBoleta(int folio);
    List<VentaCombustible> findTodos();
    List<VentaCombustible> findByDate(Date fecha);
    List<DatePlusValueHelper> findBetweenDates(Date from, Date to);
    List<VentaCombustible> findBySurtidorDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);
    List<VentaCombustible> findByBusAndDate(Bus bus, Date date);
    List<VentaCombustible> findByDefaultBus();
    List<VentaCombustible> findByBusSinRecaudar(Bus bus);
    VentaCombustible findByBus(Bus bus, Boleto boleto);
    List<VentaCombustible> findByTerminalDate(Terminal terminal, Date fecha);
    List<VentaCombustible> findByTerminalSinRecaudar(Terminal terminal, Boolean recaudado);
    int findLastNumeroBoleta(Terminal terminal);
    void update2(VentaCombustible venta);
    List<VentaCombustible> findByBusBetweenDates(Bus bus, Date from, Date to);
    List<Object> findResumenByFlotaDates(Flota flota, Date from, Date to);
}
