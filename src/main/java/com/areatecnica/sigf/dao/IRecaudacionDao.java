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
public interface IRecaudacionDao<T> extends IGenericDAO<T> {

    Recaudacion findByCuentaFolio(Cuenta cuenta, int folio);

    List<Recaudacion> findByProcesoFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion);

    List<Recaudacion> findByProcesoCajaFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, CajaRecaudacion caja, Date fechaRecaudacion);

    List<Recaudacion> findByCajaFechaRecaudacion(CajaRecaudacion caja, Date fechaRecaudacion);

    List<Recaudacion> findByTerminalCajaFechaRecaudacion(Terminal terminal, CajaRecaudacion caja, Date fechaRecaudacion);

    List<Recaudacion> findByProcesoFechaGuia(ProcesoRecaudacion procesoRecaudacion, Date fechaGuia);

    List<Recaudacion> findByBusFechaRecaudacion(Bus bus, Date fecha);

    List<Recaudacion> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino);
    
    List<Recaudacion> findByTrabajadorBetweenFechaRecaudacion(Trabajador trabajador, Date inicio, Date termino);

    List<Recaudacion> findByCuentaFecha(Cuenta cuenta, Date fecha);

    List<Recaudacion> findByFechaGrupoServicio(GrupoServicio grupoServicio, Date fecha);

    void delete(Recaudacion guia);

}
