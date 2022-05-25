/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Mutual;

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
public class MutualFacade extends AbstractFacade<Mutual> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MutualFacade() {
        super(Mutual.class);
    }

    public boolean isMutualIdCuentaEmpty(Mutual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mutual> mutual = cq.from(Mutual.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mutual, entity), cb.isNotNull(mutual.get(Mutual_.mutualIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findMutualIdCuenta(Mutual entity) {
        return this.getMergedEntity(entity).getMutualIdCuenta();
    }

    public boolean isEmpresaListEmpty(Mutual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mutual> mutual = cq.from(Mutual.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(mutual, entity), cb.isNotEmpty(mutual.get(Mutual_.empresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Empresa> findEmpresaList(Mutual entity) {
        Mutual mergedEntity = this.getMergedEntity(entity);
        List<Empresa> empresaList = mergedEntity.getEmpresaList();
        empresaList.size();
        return empresaList;
    }
    
}
