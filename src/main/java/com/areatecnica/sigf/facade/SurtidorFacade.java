/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Surtidor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.VentaCombustible;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class SurtidorFacade extends AbstractFacade<Surtidor> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SurtidorFacade() {
        super(Surtidor.class);
    }

    public boolean isSurtidorIdTerminalEmpty(Surtidor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Surtidor> surtidor = cq.from(Surtidor.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(surtidor, entity), cb.isNotNull(surtidor.get(Surtidor_.surtidorIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findSurtidorIdTerminal(Surtidor entity) {
        return this.getMergedEntity(entity).getSurtidorIdTerminal();
    }

    public boolean isVentaCombustibleListEmpty(Surtidor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Surtidor> surtidor = cq.from(Surtidor.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(surtidor, entity), cb.isNotEmpty(surtidor.get(Surtidor_.ventaCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<VentaCombustible> findVentaCombustibleList(Surtidor entity) {
        Surtidor mergedEntity = this.getMergedEntity(entity);
        List<VentaCombustible> ventaCombustibleList = mergedEntity.getVentaCombustibleList();
        ventaCombustibleList.size();
        return ventaCombustibleList;
    }

    public boolean isNumeralSurtidorListEmpty(Surtidor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Surtidor> surtidor = cq.from(Surtidor.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(surtidor, entity), cb.isNotEmpty(surtidor.get(Surtidor_.numeralSurtidorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

}
