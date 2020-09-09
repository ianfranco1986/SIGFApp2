/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.PeriodoFrecuencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.PeriodoFrecuencia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.FrecuenciaServicio;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class PeriodoFrecuenciaFacade extends AbstractFacade<PeriodoFrecuencia> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoFrecuenciaFacade() {
        super(PeriodoFrecuencia.class);
    }

    public boolean isPeriodoFrecuenciaIdCuentaEmpty(PeriodoFrecuencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PeriodoFrecuencia> periodoFrecuencia = cq.from(PeriodoFrecuencia.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(periodoFrecuencia, entity), cb.isNotNull(periodoFrecuencia.get(PeriodoFrecuencia_.periodoFrecuenciaIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findPeriodoFrecuenciaIdCuenta(PeriodoFrecuencia entity) {
        return this.getMergedEntity(entity).getPeriodoFrecuenciaIdCuenta();
    }

    public boolean isFrecuenciaServicioListEmpty(PeriodoFrecuencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PeriodoFrecuencia> periodoFrecuencia = cq.from(PeriodoFrecuencia.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(periodoFrecuencia, entity), cb.isNotEmpty(periodoFrecuencia.get(PeriodoFrecuencia_.frecuenciaServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FrecuenciaServicio> findFrecuenciaServicioList(PeriodoFrecuencia entity) {
        PeriodoFrecuencia mergedEntity = this.getMergedEntity(entity);
        List<FrecuenciaServicio> frecuenciaServicioList = mergedEntity.getFrecuenciaServicioList();
        frecuenciaServicioList.size();
        return frecuenciaServicioList;
    }
    
}
