/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoDemanda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoDemanda_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.FrecuenciaServicio;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoDemandaFacade extends AbstractFacade<TipoDemanda> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDemandaFacade() {
        super(TipoDemanda.class);
    }

    public boolean isFrecuenciaServicioListEmpty(TipoDemanda entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDemanda> tipoDemanda = cq.from(TipoDemanda.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDemanda, entity), cb.isNotEmpty(tipoDemanda.get(TipoDemanda_.frecuenciaServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FrecuenciaServicio> findFrecuenciaServicioList(TipoDemanda entity) {
        TipoDemanda mergedEntity = this.getMergedEntity(entity);
        List<FrecuenciaServicio> frecuenciaServicioList = mergedEntity.getFrecuenciaServicioList();
        frecuenciaServicioList.size();
        return frecuenciaServicioList;
    }
    
}
