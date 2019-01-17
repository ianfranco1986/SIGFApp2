/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.VentaCombustible;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Surtidor;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class VentaCombustibleFacade extends AbstractFacade<VentaCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaCombustibleFacade() {
        super(VentaCombustible.class);
    }

    public boolean isRecaudacionCombustibleListEmpty(VentaCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaCombustible> ventaCombustible = cq.from(VentaCombustible.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaCombustible, entity), cb.isNotEmpty(ventaCombustible.get(VentaCombustible_.recaudacionCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionCombustible> findRecaudacionCombustibleList(VentaCombustible entity) {
        VentaCombustible mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionCombustible> recaudacionCombustibleList = mergedEntity.getRecaudacionCombustibleList();
        recaudacionCombustibleList.size();
        return recaudacionCombustibleList;
    }

    public boolean isVentaCombustibleIdBusEmpty(VentaCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaCombustible> ventaCombustible = cq.from(VentaCombustible.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaCombustible, entity), cb.isNotNull(ventaCombustible.get(VentaCombustible_.ventaCombustibleIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findVentaCombustibleIdBus(VentaCombustible entity) {
        return this.getMergedEntity(entity).getVentaCombustibleIdBus();
    }

    public boolean isVentaCombustibleIdSurtidorEmpty(VentaCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaCombustible> ventaCombustible = cq.from(VentaCombustible.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaCombustible, entity), cb.isNotNull(ventaCombustible.get(VentaCombustible_.ventaCombustibleIdSurtidor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Surtidor findVentaCombustibleIdSurtidor(VentaCombustible entity) {
        return this.getMergedEntity(entity).getVentaCombustibleIdSurtidor();
    }
    
}
