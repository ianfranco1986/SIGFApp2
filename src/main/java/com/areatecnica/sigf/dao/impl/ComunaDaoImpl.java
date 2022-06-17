/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IComunaDao;
import com.areatecnica.sigf.entities.Comuna;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ComunaDaoImpl extends GenericDAOImpl<Comuna> implements IComunaDao<Comuna> {

    @Override
    public Comuna findById(int id) {
        try {
            return (Comuna) this.entityManager.createNamedQuery("Comuna.findByComunaId").setParameter("comunaId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
