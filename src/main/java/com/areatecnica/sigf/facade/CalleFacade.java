/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Calle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Calle_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CalleServicio;
import com.areatecnica.sigf.entities.Comuna;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CalleFacade extends AbstractFacade<Calle> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalleFacade() {
        super(Calle.class);
    }

    public boolean isCalleServicioListEmpty(Calle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Calle> calle = cq.from(Calle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calle, entity), cb.isNotEmpty(calle.get(Calle_.calleServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CalleServicio> findCalleServicioList(Calle entity) {
        Calle mergedEntity = this.getMergedEntity(entity);
        List<CalleServicio> calleServicioList = mergedEntity.getCalleServicioList();
        calleServicioList.size();
        return calleServicioList;
    }

    public boolean isCalleIdComunaEmpty(Calle entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Calle> calle = cq.from(Calle.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calle, entity), cb.isNotNull(calle.get(Calle_.calleIdComuna)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Comuna findCalleIdComuna(Calle entity) {
        return this.getMergedEntity(entity).getCalleIdComuna();
    }
    
}
