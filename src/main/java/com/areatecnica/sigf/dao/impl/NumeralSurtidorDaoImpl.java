/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.INumeralSurtidorDao;
import com.areatecnica.sigf.entities.NumeralSurtidor;
import com.areatecnica.sigf.entities.Surtidor;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class NumeralSurtidorDaoImpl extends GenericDAOImpl<NumeralSurtidor> implements INumeralSurtidorDao<NumeralSurtidor> {

    public NumeralSurtidorDaoImpl() {
        super(NumeralSurtidor.class);
    }

    @Override
    public NumeralSurtidor findUltimoBySurtidorFecha(Surtidor surtidor, Date fecha) {
        try {
            return (NumeralSurtidor) this.entityManager.createNamedQuery("NumeralSurtidor.findBySurtidorFecha").
                    setParameter("numeralSurtidorFechaMedicion", fecha).
                    setParameter("numeralSurtidorIdSurtidor", surtidor).
                    setMaxResults(1).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<NumeralSurtidor> findBySurtidorFecha(Surtidor surtidor, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("NumeralSurtidor.findBySurtidorFecha").
                    setParameter("numeralSurtidorFechaMedicion", from).
                    setParameter("numeralSurtidorIdSurtidor", surtidor).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<NumeralSurtidor> findAllByFecha(Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("NumeralSurtidor.findAllByFecha").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<NumeralSurtidor> findLastBySurtidor(Surtidor surtidor) {
        try {
            return this.entityManager.createNamedQuery("NumeralSurtidor.findBySurtidor").
                    setParameter("numeralSurtidorIdSurtidor", surtidor).
                    setMaxResults(50).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
