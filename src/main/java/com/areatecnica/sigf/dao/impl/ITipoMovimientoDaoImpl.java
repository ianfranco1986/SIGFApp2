/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoMovimientoDao;
import com.areatecnica.sigf.entities.TipoMovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ITipoMovimientoDaoImpl extends GenericDAOImpl<TipoMovimiento> implements ITipoMovimientoDao<TipoMovimiento> {

    public ITipoMovimientoDaoImpl() {
        super(TipoMovimiento.class);
    }

    @Override
    public TipoMovimiento findById(int tipoAbonoId) {
        try {
            return (TipoMovimiento) this.entityManager.createNamedQuery("TipoMovimiento.findByTipoMovimientoId").setParameter("tipoMovimientoId", tipoAbonoId).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    public List<TipoMovimiento> findALL() {
        try {
            return this.entityManager.createNamedQuery("TipoMovimiento.findAll").
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<TipoMovimiento> findByIngreso() {
        try {
            return this.entityManager.createNamedQuery("TipoMovimiento.findByTipoMovimientoAbono").
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<TipoMovimiento> findByEgreso() {
        try {
            return this.entityManager.createNamedQuery("TipoMovimiento.findByTipoMovimientoDescuento").
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

}
