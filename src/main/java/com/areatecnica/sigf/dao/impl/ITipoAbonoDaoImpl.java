/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoAbonoDao;
import com.areatecnica.sigf.entities.TipoAbono;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ITipoAbonoDaoImpl extends GenericDAOImpl<TipoAbono> implements ITipoAbonoDao<TipoAbono> {

    public ITipoAbonoDaoImpl() {
        super(TipoAbono.class);
    }

    @Override
    public TipoAbono findAllById(int tipoAbonoId) {
        try {
            return (TipoAbono) this.entityManager.createNamedQuery("TipoAbono.findByTipoAbonoId").setParameter("tipoAbonoId", tipoAbonoId).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
