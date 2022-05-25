/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICuentaMayorDao;
import com.areatecnica.sigf.entities.CuentaMayor;

import java.util.List;

/**
 *
 * @author ianfr
 */
public class ICuentaMayorDaoImpl extends GenericDAOImpl<CuentaMayor> implements ICuentaMayorDao<CuentaMayor> {

    public ICuentaMayorDaoImpl() {
        super(CuentaMayor.class);
    }

    @Override
    public List<CuentaMayor> findALL() {
        return this.entityManager.createNamedQuery("CuentaMayor.findAll").getResultList();
    }

}
