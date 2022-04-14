/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Ciudad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Ciudad_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Region;
import com.areatecnica.sigf.entities.Comuna;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    public boolean isCiudadIdRegionEmpty(Ciudad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ciudad> ciudad = cq.from(Ciudad.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ciudad, entity), cb.isNotNull(ciudad.get(Ciudad_.ciudadIdRegion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Region findCiudadIdRegion(Ciudad entity) {
        return this.getMergedEntity(entity).getCiudadIdRegion();
    }

    public boolean isComunaListEmpty(Ciudad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ciudad> ciudad = cq.from(Ciudad.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ciudad, entity), cb.isNotEmpty(ciudad.get(Ciudad_.comunaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Comuna> findComunaList(Ciudad entity) {
        Ciudad mergedEntity = this.getMergedEntity(entity);
        List<Comuna> comunaList = mergedEntity.getComunaList();
        comunaList.size();
        return comunaList;
    }
    
}
