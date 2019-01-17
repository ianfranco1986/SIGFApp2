/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.OperadorTransporte;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.OperadorTransporte_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RepresentanteLegal;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class OperadorTransporteFacade extends AbstractFacade<OperadorTransporte> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperadorTransporteFacade() {
        super(OperadorTransporte.class);
    }

    public boolean isOperadorTransporteIdRepresentanteEmpty(OperadorTransporte entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<OperadorTransporte> operadorTransporte = cq.from(OperadorTransporte.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(operadorTransporte, entity), cb.isNotNull(operadorTransporte.get(OperadorTransporte_.operadorTransporteIdRepresentante)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public RepresentanteLegal findOperadorTransporteIdRepresentante(OperadorTransporte entity) {
        return this.getMergedEntity(entity).getOperadorTransporteIdRepresentante();
    }

    public boolean isRelacionLaboralListEmpty(OperadorTransporte entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<OperadorTransporte> operadorTransporte = cq.from(OperadorTransporte.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(operadorTransporte, entity), cb.isNotEmpty(operadorTransporte.get(OperadorTransporte_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(OperadorTransporte entity) {
        OperadorTransporte mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }

    public boolean isUnidadNegocioListEmpty(OperadorTransporte entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<OperadorTransporte> operadorTransporte = cq.from(OperadorTransporte.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(operadorTransporte, entity), cb.isNotEmpty(operadorTransporte.get(OperadorTransporte_.unidadNegocioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<UnidadNegocio> findUnidadNegocioList(OperadorTransporte entity) {
        OperadorTransporte mergedEntity = this.getMergedEntity(entity);
        List<UnidadNegocio> unidadNegocioList = mergedEntity.getUnidadNegocioList();
        unidadNegocioList.size();
        return unidadNegocioList;
    }
    
}
