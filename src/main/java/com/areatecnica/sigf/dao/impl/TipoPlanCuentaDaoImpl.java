/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoPlanCuentaDao;
import com.areatecnica.sigf.entities.TipoPlanCuenta;
import java.util.List;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class TipoPlanCuentaDaoImpl extends GenericDAOImpl<TipoPlanCuenta> implements ITipoPlanCuentaDao<TipoPlanCuenta> {

    
    
    
    @Override
    public TipoPlanCuenta findById(int id) {
        try {
            return (TipoPlanCuenta) this.entityManager.createNamedQuery("TipoPlanCuenta.findByTipoPlanCuentaId").setParameter("privilegioId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<TipoPlanCuenta> find() {
        return this.entityManager.createNamedQuery("TipoPlanCuenta.findAll").getResultList();
        }

}
