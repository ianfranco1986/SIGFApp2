/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionGuiaDao<T> extends IGenericDAO<T> {

    public RecaudacionGuia findByCuentaFolio(Cuenta cuenta, int folio);

    public List<RecaudacionGuia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino);
    

}
