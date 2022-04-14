/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.PlanCuenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.PlanCuenta_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.entities.TipoPlanCuenta;

/**
 *
 * @author ianfr
 */
@Stateless
public class PlanCuentaFacade extends AbstractFacade<PlanCuenta> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanCuentaFacade() {
        super(PlanCuenta.class);
    }

    public boolean isPlanCuentaEmpresaIdEmpty(PlanCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PlanCuenta> planCuenta = cq.from(PlanCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(planCuenta, entity), cb.isNotNull(planCuenta.get(PlanCuenta_.planCuentaEmpresaId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findPlanCuentaEmpresaId(PlanCuenta entity) {
        return this.getMergedEntity(entity).getPlanCuentaEmpresaId();
    }

    public boolean isPlanCuentaSubTipoIdEmpty(PlanCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PlanCuenta> planCuenta = cq.from(PlanCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(planCuenta, entity), cb.isNotNull(planCuenta.get(PlanCuenta_.planCuentaSubTipoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public boolean isPlanCuentaTipoIdEmpty(PlanCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PlanCuenta> planCuenta = cq.from(PlanCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(planCuenta, entity), cb.isNotNull(planCuenta.get(PlanCuenta_.planCuentaTipoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }
    
}
