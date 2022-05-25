/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

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
public class UnidadNegocioFacade extends AbstractFacade<UnidadNegocio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadNegocioFacade() {
        super(UnidadNegocio.class);
    }

    public boolean isContratoUnidadListEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotEmpty(unidadNegocio.get(UnidadNegocio_.contratoUnidadList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ContratoUnidad> findContratoUnidadList(UnidadNegocio entity) {
        UnidadNegocio mergedEntity = this.getMergedEntity(entity);
        List<ContratoUnidad> contratoUnidadList = mergedEntity.getContratoUnidadList();
        contratoUnidadList.size();
        return contratoUnidadList;
    }

    public boolean isBusListEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotEmpty(unidadNegocio.get(UnidadNegocio_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(UnidadNegocio entity) {
        UnidadNegocio mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isValorRolloUnidadListEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotEmpty(unidadNegocio.get(UnidadNegocio_.valorRolloUnidadList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ValorRolloUnidad> findValorRolloUnidadList(UnidadNegocio entity) {
        UnidadNegocio mergedEntity = this.getMergedEntity(entity);
        List<ValorRolloUnidad> valorRolloUnidadList = mergedEntity.getValorRolloUnidadList();
        valorRolloUnidadList.size();
        return valorRolloUnidadList;
    }

    public boolean isUnidadNegocioIdCuentaEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotNull(unidadNegocio.get(UnidadNegocio_.unidadNegocioIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findUnidadNegocioIdCuenta(UnidadNegocio entity) {
        return this.getMergedEntity(entity).getUnidadNegocioIdCuenta();
    }

    public boolean isUnidadNegocioIdOperadorTransporteEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotNull(unidadNegocio.get(UnidadNegocio_.unidadNegocioIdOperadorTransporte)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public OperadorTransporte findUnidadNegocioIdOperadorTransporte(UnidadNegocio entity) {
        return this.getMergedEntity(entity).getUnidadNegocioIdOperadorTransporte();
    }

    public boolean isUnidadNegocioIdRegionEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotNull(unidadNegocio.get(UnidadNegocio_.unidadNegocioIdRegion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Region findUnidadNegocioIdRegion(UnidadNegocio entity) {
        return this.getMergedEntity(entity).getUnidadNegocioIdRegion();
    }

    public boolean isServicioListEmpty(UnidadNegocio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadNegocio> unidadNegocio = cq.from(UnidadNegocio.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(unidadNegocio, entity), cb.isNotEmpty(unidadNegocio.get(UnidadNegocio_.servicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Servicio> findServicioList(UnidadNegocio entity) {
        UnidadNegocio mergedEntity = this.getMergedEntity(entity);
        List<Servicio> servicioList = mergedEntity.getServicioList();
        servicioList.size();
        return servicioList;
    }
    
}
