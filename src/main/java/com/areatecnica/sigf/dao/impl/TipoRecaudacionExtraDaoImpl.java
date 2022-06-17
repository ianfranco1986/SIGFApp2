/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITipoRecaudacionExtraDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.TipoRecaudacionExtra;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class TipoRecaudacionExtraDaoImpl extends GenericDAOImpl<TipoRecaudacionExtra> implements ITipoRecaudacionExtraDao<TipoRecaudacionExtra> {

    public TipoRecaudacionExtraDaoImpl() {
        super(TipoRecaudacionExtra.class);
    }

    @Override
    public List<TipoRecaudacionExtra> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("TipoRecaudacionExtra.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
