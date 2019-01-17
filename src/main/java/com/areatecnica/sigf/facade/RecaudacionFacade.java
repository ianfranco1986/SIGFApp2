/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Recaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Recaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RecaudacionBoleto;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionFacade extends AbstractFacade<Recaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionFacade() {
        super(Recaudacion.class);
    }

    public boolean isRecaudacionBoletoListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionBoleto> findRecaudacionBoletoList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionBoleto> recaudacionBoletoList = mergedEntity.getRecaudacionBoletoList();
        recaudacionBoletoList.size();
        return recaudacionBoletoList;
    }

    public boolean isRecaudacionMinutoListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionMinutoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionMinuto> findRecaudacionMinutoList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionMinuto> recaudacionMinutoList = mergedEntity.getRecaudacionMinutoList();
        recaudacionMinutoList.size();
        return recaudacionMinutoList;
    }

    public boolean isRecaudacionCombustibleListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionCombustible> findRecaudacionCombustibleList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionCombustible> recaudacionCombustibleList = mergedEntity.getRecaudacionCombustibleList();
        recaudacionCombustibleList.size();
        return recaudacionCombustibleList;
    }

    public boolean isRecaudacionIdCajaEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotNull(recaudacion.get(Recaudacion_.recaudacionIdCaja)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CajaRecaudacion findRecaudacionIdCaja(Recaudacion entity) {
        return this.getMergedEntity(entity).getRecaudacionIdCaja();
    }

    public boolean isRecaudacionDescuentoExtraListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionDescuentoExtraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionDescuentoExtra> findRecaudacionDescuentoExtraList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionDescuentoExtra> recaudacionDescuentoExtraList = mergedEntity.getRecaudacionDescuentoExtraList();
        recaudacionDescuentoExtraList.size();
        return recaudacionDescuentoExtraList;
    }

    public boolean isRecaudacionGuiaListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionGuiaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionGuia> findRecaudacionGuiaList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionGuia> recaudacionGuiaList = mergedEntity.getRecaudacionGuiaList();
        recaudacionGuiaList.size();
        return recaudacionGuiaList;
    }

    public boolean isRecaudacionExtraListEmpty(Recaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Recaudacion> recaudacion = cq.from(Recaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacion, entity), cb.isNotEmpty(recaudacion.get(Recaudacion_.recaudacionExtraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionExtra> findRecaudacionExtraList(Recaudacion entity) {
        Recaudacion mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionExtra> recaudacionExtraList = mergedEntity.getRecaudacionExtraList();
        recaudacionExtraList.size();
        return recaudacionExtraList;
    }
    
}
