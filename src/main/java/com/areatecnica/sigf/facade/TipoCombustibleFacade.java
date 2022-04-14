/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCombustible;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoCombustible_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.CompraPetroleo;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCombustibleFacade extends AbstractFacade<TipoCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCombustibleFacade() {
        super(TipoCombustible.class);
    }

    public boolean isPrecioCombustibleListEmpty(TipoCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCombustible> tipoCombustible = cq.from(TipoCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCombustible, entity), cb.isNotEmpty(tipoCombustible.get(TipoCombustible_.precioCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PrecioCombustible> findPrecioCombustibleList(TipoCombustible entity) {
        TipoCombustible mergedEntity = this.getMergedEntity(entity);
        List<PrecioCombustible> precioCombustibleList = mergedEntity.getPrecioCombustibleList();
        precioCombustibleList.size();
        return precioCombustibleList;
    }

    public boolean isCompraCombustibleListEmpty(TipoCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCombustible> tipoCombustible = cq.from(TipoCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCombustible, entity), cb.isNotEmpty(tipoCombustible.get(TipoCombustible_.compraCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

}
