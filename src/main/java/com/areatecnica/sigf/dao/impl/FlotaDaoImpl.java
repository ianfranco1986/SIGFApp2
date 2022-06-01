/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.UnidadNegocio;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class FlotaDaoImpl extends GenericDAOImpl<Flota> implements IFlotaDao<Flota> {

    @Override
    public List<Flota> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("Flota.findAllByCuenta").setParameter("idCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }
    
    @Override
    public List<Flota> findByUnidad(UnidadNegocio unidad) {
        try {
            return this.entityManager.createNamedQuery("Flota.findAllByCuenta").setParameter("idCuenta", unidad).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

}
