/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.*;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionMinutoDao<T> extends IGenericDAO<T> {

    List<RecaudacionMinuto> findTodos();
    
    List<RecaudacionMinuto> findByDate(Date fecha);
    
    List<RecaudacionMinuto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    List<RecaudacionMinuto> findByBusAndDate(Bus bus, Date from, Date to);
    
    List<RecaudacionMinuto> findRecibidosBusAndDate(Bus bus, Date from, Date to);

    List<RecaudacionMinuto> findByDefaultBus();

    List<RecaudacionMinuto> findByBusSinRecaudar(Bus bus);
    
    RecaudacionMinuto findByBus(Bus bus, Boleto boleto);
    
    List<RecaudacionMinuto> findByTerminalDate(Terminal terminal, Date fecha);
    
    int findLastNumeroBoleta(Terminal terminal);

}
