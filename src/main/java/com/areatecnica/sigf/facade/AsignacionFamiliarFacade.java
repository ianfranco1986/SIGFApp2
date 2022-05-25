/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AsignacionFamiliar;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Trabajador;

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
public class AsignacionFamiliarFacade extends AbstractFacade<AsignacionFamiliar> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionFamiliarFacade() {
        super(AsignacionFamiliar.class);
    }

    public boolean isAsignacionFamiliarIdCuentaEmpty(AsignacionFamiliar entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AsignacionFamiliar> asignacionFamiliar = cq.from(AsignacionFamiliar.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(asignacionFamiliar, entity), cb.isNotNull(asignacionFamiliar.get(AsignacionFamiliar_.asignacionFamiliarIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findAsignacionFamiliarIdCuenta(AsignacionFamiliar entity) {
        return this.getMergedEntity(entity).getAsignacionFamiliarIdCuenta();
    }

    public boolean isTrabajadorListEmpty(AsignacionFamiliar entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AsignacionFamiliar> asignacionFamiliar = cq.from(AsignacionFamiliar.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(asignacionFamiliar, entity), cb.isNotEmpty(asignacionFamiliar.get(AsignacionFamiliar_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(AsignacionFamiliar entity) {
        AsignacionFamiliar mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }
    
}
