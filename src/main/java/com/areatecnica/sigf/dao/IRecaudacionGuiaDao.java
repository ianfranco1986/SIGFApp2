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
public interface IRecaudacionGuiaDao<T> extends IGenericDAO<T> {

    RecaudacionGuia findByCuentaFolio(Cuenta cuenta, int folio);
    
    RecaudacionGuia findByRecaudacionEgreso(int recaudacion, Egreso egreso);

    List<RecaudacionGuia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino);
    
    List<RecaudacionGuia> findByCajaFecha(CajaRecaudacion caja, Date fecha);
    

}
