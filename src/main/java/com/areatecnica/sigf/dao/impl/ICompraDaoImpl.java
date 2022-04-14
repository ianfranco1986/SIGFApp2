/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICompraDao;
import com.areatecnica.sigf.entities.Compra;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ICompraDaoImpl extends GenericDAOImpl<Compra> implements ICompraDao<Compra> {

    public ICompraDaoImpl() {
        super(Compra.class);
    }

    @Override
    public List<Compra> findCompraBetweenDates(Date from, Date to) {
        return this.entityManager.createNamedQuery("Compra.findByCompraBetweenDates").setParameter("from", from).setParameter("to", to).getResultList();
    }

}
