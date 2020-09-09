/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.PlanCuentaSubTipo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.PlanCuenta;
import com.areatecnica.sigf.entities.CuentaMayor;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class PlanCuentaSubTipoFacade extends AbstractFacade<PlanCuentaSubTipo> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanCuentaSubTipoFacade() {
        super(PlanCuentaSubTipo.class);
    }

    public boolean isPlanCuentaListEmpty(PlanCuentaSubTipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PlanCuentaSubTipo> planCuentaSubTipo = cq.from(PlanCuentaSubTipo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(planCuentaSubTipo, entity), cb.isNotEmpty(planCuentaSubTipo.get(PlanCuentaSubTipo_.planCuentaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PlanCuenta> findPlanCuentaList(PlanCuentaSubTipo entity) {
        PlanCuentaSubTipo mergedEntity = this.getMergedEntity(entity);
        List<PlanCuenta> planCuentaList = mergedEntity.getPlanCuentaList();
        planCuentaList.size();
        return planCuentaList;
    }

    public boolean isCuentaMayorListEmpty(PlanCuentaSubTipo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PlanCuentaSubTipo> planCuentaSubTipo = cq.from(PlanCuentaSubTipo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(planCuentaSubTipo, entity), cb.isNotEmpty(planCuentaSubTipo.get(PlanCuentaSubTipo_.cuentaMayorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CuentaMayor> findCuentaMayorList(PlanCuentaSubTipo entity) {
        PlanCuentaSubTipo mergedEntity = this.getMergedEntity(entity);
        List<CuentaMayor> cuentaMayorList = mergedEntity.getCuentaMayorList();
        cuentaMayorList.size();
        return cuentaMayorList;
    }
    
}
