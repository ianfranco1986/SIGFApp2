/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CentroCosto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CentroCosto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CentroCostoFacade extends AbstractFacade<CentroCosto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CentroCostoFacade() {
        super(CentroCosto.class);
    }

    public boolean isCentroCostoIdCuentaEmpty(CentroCosto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CentroCosto> centroCosto = cq.from(CentroCosto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(centroCosto, entity), cb.isNotNull(centroCosto.get(CentroCosto_.centroCostoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findCentroCostoIdCuenta(CentroCosto entity) {
        return this.getMergedEntity(entity).getCentroCostoIdCuenta();
    }

    public boolean isTrabajadorListEmpty(CentroCosto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CentroCosto> centroCosto = cq.from(CentroCosto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(centroCosto, entity), cb.isNotEmpty(centroCosto.get(CentroCosto_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(CentroCosto entity) {
        CentroCosto mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }
    
}
