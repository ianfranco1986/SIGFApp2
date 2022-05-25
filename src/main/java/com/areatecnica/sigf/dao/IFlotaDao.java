/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.UnidadNegocio;

import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IFlotaDao<T> extends IGenericDAO<T> {
    
    List<Flota> findByCuenta(Cuenta cuenta);
    
    List<Flota> findByUnidad(UnidadNegocio unidad);
        
}
