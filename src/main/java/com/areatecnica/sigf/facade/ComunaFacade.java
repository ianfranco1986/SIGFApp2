/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ComunaFacade extends AbstractFacade<Comuna> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComunaFacade() {
        super(Comuna.class);
    }

    public boolean isCalleListEmpty(Comuna entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comuna> comuna = cq.from(Comuna.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comuna, entity), cb.isNotEmpty(comuna.get(Comuna_.calleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Calle> findCalleList(Comuna entity) {
        Comuna mergedEntity = this.getMergedEntity(entity);
        List<Calle> calleList = mergedEntity.getCalleList();
        calleList.size();
        return calleList;
    }

    public boolean isTrabajadorListEmpty(Comuna entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comuna> comuna = cq.from(Comuna.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comuna, entity), cb.isNotEmpty(comuna.get(Comuna_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(Comuna entity) {
        Comuna mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isTerminalListEmpty(Comuna entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comuna> comuna = cq.from(Comuna.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comuna, entity), cb.isNotEmpty(comuna.get(Comuna_.terminalList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Terminal> findTerminalList(Comuna entity) {
        Comuna mergedEntity = this.getMergedEntity(entity);
        List<Terminal> terminalList = mergedEntity.getTerminalList();
        terminalList.size();
        return terminalList;
    }

    public boolean isComunaIdCiudadEmpty(Comuna entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Comuna> comuna = cq.from(Comuna.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(comuna, entity), cb.isNotNull(comuna.get(Comuna_.comunaIdCiudad)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Ciudad findComunaIdCiudad(Comuna entity) {
        return this.getMergedEntity(entity).getComunaIdCiudad();
    }
    
}
