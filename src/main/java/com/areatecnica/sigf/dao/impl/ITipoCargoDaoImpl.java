/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoCargoDao;
import com.areatecnica.sigf.entities.TipoCargo;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ITipoCargoDaoImpl extends GenericDAOImpl<TipoCargo> implements ITipoCargoDao<TipoCargo> {

    public ITipoCargoDaoImpl() {
        super(TipoCargo.class);
    }

    @Override
    public TipoCargo findAllById(int tipoCargoId) {
        try {
            return (TipoCargo) this.entityManager.createNamedQuery("TipoCargo.findByTipoCargoId").setParameter("tipoCargoId", tipoCargoId).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
