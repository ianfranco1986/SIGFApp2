/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoDiaFrecuencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoDiaFrecuencia_;
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
public class TipoDiaFrecuenciaFacade extends AbstractFacade<TipoDiaFrecuencia> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDiaFrecuenciaFacade() {
        super(TipoDiaFrecuencia.class);
    }

    public boolean isFrecuenciaServicioListEmpty(TipoDiaFrecuencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDiaFrecuencia> tipoDiaFrecuencia = cq.from(TipoDiaFrecuencia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDiaFrecuencia, entity), cb.isNotEmpty(tipoDiaFrecuencia.get(TipoDiaFrecuencia_.frecuenciaServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FrecuenciaServicio> findFrecuenciaServicioList(TipoDiaFrecuencia entity) {
        TipoDiaFrecuencia mergedEntity = this.getMergedEntity(entity);
        List<FrecuenciaServicio> frecuenciaServicioList = mergedEntity.getFrecuenciaServicioList();
        frecuenciaServicioList.size();
        return frecuenciaServicioList;
    }
    
}
