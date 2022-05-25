/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IFacturaDao;
import com.areatecnica.sigf.entities.Factura;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class IFacturaDaoImpl extends GenericDAOImpl<Factura> implements IFacturaDao<Factura> {

    public IFacturaDaoImpl() {
        super(Factura.class);
    }

    @Override
    public Factura findById(int id) {
        try {
            return (Factura) this.entityManager.
                    createNamedQuery("Factura.findByFacturaId").
                    setParameter("facturaId", id).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Factura findByFolio(int folio) {
        try {
            return (Factura) this.entityManager.
                    createNamedQuery("Factura.findByFacturaFolio").
                    setParameter("facturaFolio", folio).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Factura> findBetweenDates(Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("Factura.findBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return new ArrayList<>();
        }
    }

    public int findLastFolio() {
        try {
            return (int) entityManager.createQuery("SELECT MAX(f.facturaFolio) FROM  Factura f ")
                    .getSingleResult() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

}
