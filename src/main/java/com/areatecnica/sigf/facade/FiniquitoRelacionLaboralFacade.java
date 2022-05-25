/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CausalFiniquito;
import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import com.areatecnica.sigf.entities.RelacionLaboral;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class FiniquitoRelacionLaboralFacade extends AbstractFacade<FiniquitoRelacionLaboral> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiniquitoRelacionLaboralFacade() {
        super(FiniquitoRelacionLaboral.class);
    }

    public boolean isFiniquitoRelacionLaboralIdCausalFiniquitoEmpty(FiniquitoRelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FiniquitoRelacionLaboral> finiquitoRelacionLaboral = cq.from(FiniquitoRelacionLaboral.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(finiquitoRelacionLaboral, entity), cb.isNotNull(finiquitoRelacionLaboral.get(FiniquitoRelacionLaboral_.finiquitoRelacionLaboralIdCausalFiniquito)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CausalFiniquito findFiniquitoRelacionLaboralIdCausalFiniquito(FiniquitoRelacionLaboral entity) {
        return this.getMergedEntity(entity).getFiniquitoRelacionLaboralIdCausalFiniquito();
    }

    public boolean isFiniquitoRelacionLaboralIdRelacionLaboralEmpty(FiniquitoRelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FiniquitoRelacionLaboral> finiquitoRelacionLaboral = cq.from(FiniquitoRelacionLaboral.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(finiquitoRelacionLaboral, entity), cb.isNotNull(finiquitoRelacionLaboral.get(FiniquitoRelacionLaboral_.finiquitoRelacionLaboralIdRelacionLaboral)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public RelacionLaboral findFiniquitoRelacionLaboralIdRelacionLaboral(FiniquitoRelacionLaboral entity) {
        return this.getMergedEntity(entity).getFiniquitoRelacionLaboralIdRelacionLaboral();
    }
    
}
