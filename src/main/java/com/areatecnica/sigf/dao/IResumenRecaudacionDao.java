/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IResumenRecaudacionDao<T> extends IGenericDAO<T> {
    
    ResumenRecaudacion findByCajaProcesoDate(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion);
    
    List<ResumenRecaudacion> findAllByCajaProcesoBetweenDates(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion, Date from, Date to);
    
    void editar(ResumenRecaudacion resumen);
}
