/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICentroCostoDao;
import com.areatecnica.sigf.entities.CentroCosto;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class CentroCostoDaoImpl extends GenericDAOImpl<CentroCosto> implements ICentroCostoDao<CentroCosto> {

    @Override
    public CentroCosto findById(int id) {
        try {
            return (CentroCosto) this.entityManager.createNamedQuery("CentroCosto.findByCentroCostoId").setParameter("centroCostoId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
