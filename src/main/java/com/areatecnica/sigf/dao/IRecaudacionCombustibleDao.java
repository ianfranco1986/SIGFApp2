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
public interface IRecaudacionCombustibleDao<T> extends IGenericDAO<T> {

    List<RecaudacionCombustible> findTodos();

    List<RecaudacionCombustible> findByDate(Date fecha);

    List<RecaudacionCombustible> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    List<RecaudacionCombustible> findByBusAndDate(Bus bus);

    List<RecaudacionCombustible> findByDefaultBus();

    List<RecaudacionCombustible> findByBusSinRecaudar(Bus bus);

    RecaudacionCombustible findByBus(Bus bus, Boleto boleto);

    List<RecaudacionCombustible> findByTerminalDate(Terminal terminal, Date fecha);

    int findLastNumeroBoleta(Terminal terminal);

    void update2(RecaudacionCombustible venta);

    void detach(RecaudacionCombustible venta);
}
