/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICompraPetroleoDao;
import com.areatecnica.sigf.entities.CompraPetroleo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ICompraPetroleoDaoImpl extends GenericDAOImpl<CompraPetroleo> implements ICompraPetroleoDao<CompraPetroleo> {

    public ICompraPetroleoDaoImpl() {
        super(CompraPetroleo.class);
    }

    @Override
    public List<CompraPetroleo> findCompraBetweenDates(Date from, Date to) {
        return this.entityManager.createNamedQuery("CompraPetroleo.findByCompraBetweenDates").setParameter("from", from).setParameter("to", to).getResultList();
    }

}
