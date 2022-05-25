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
 */
public interface IGuiaDao<T> extends IGenericDAO<T> {
    
    Guia findByCuentaFolio(Cuenta cuenta, int folio);
    
    Guia findLastGuiaByBusFecha(Bus bus, Date fecha);
    
    Guia findLastGuiaByBusEqualsFecha(Bus bus, Date fecha);
    
    int findLastFolio(Terminal terminal);
        
    List<Guia> findByProcesoFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion);
    
    List<Guia> findByProcesoFechaGuia(ProcesoRecaudacion procesoRecaudacion, Date fechaGuia);
    
    List<Guia> findByBusFecha(Bus bus, Date fecha);
    
    List<Guia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino);
    
    List<Guia> findByBusBetweenFecha(Bus bus, Date inicio, Date termino);
    
    List<Guia> findByBusPendientes(Bus bus);
    
    List<Guia> findByCuentaFecha(Cuenta cuenta, Date fecha);
    
    List<Guia> findByFechaGrupoServicio(GrupoServicio grupoServicio, Date fecha);
    
    void delete(Guia guia);
    
}
