/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoCombustibleDao;
import com.areatecnica.sigf.entities.TipoCombustible;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class TipoCombustibleDaoImpl extends GenericDAOImpl<TipoCombustible> implements ITipoCombustibleDao<TipoCombustible> {

    public TipoCombustibleDaoImpl() {
        super(TipoCombustible.class);
    }

    @Override
    public TipoCombustible findTipoCombustibleDefecto() {
        try {
            return (TipoCombustible) this.entityManager.createNamedQuery("TipoCombustible.findByTipoCombustibleId").setParameter("tipoCombustibleId", 1).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
