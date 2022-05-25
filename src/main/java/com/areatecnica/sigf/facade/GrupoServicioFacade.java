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
public class GrupoServicioFacade extends AbstractFacade<GrupoServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoServicioFacade() {
        super(GrupoServicio.class);
    }

    public boolean isBusListEmpty(GrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GrupoServicio> grupoServicio = cq.from(GrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupoServicio, entity), cb.isNotEmpty(grupoServicio.get(GrupoServicio_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(GrupoServicio entity) {
        GrupoServicio mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isGrupoServicioIdCuentaEmpty(GrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GrupoServicio> grupoServicio = cq.from(GrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupoServicio, entity), cb.isNotNull(grupoServicio.get(GrupoServicio_.grupoServicioIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findGrupoServicioIdCuenta(GrupoServicio entity) {
        return this.getMergedEntity(entity).getGrupoServicioIdCuenta();
    }

    public boolean isGrupoServicioIdTerminalEmpty(GrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GrupoServicio> grupoServicio = cq.from(GrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupoServicio, entity), cb.isNotNull(grupoServicio.get(GrupoServicio_.grupoServicioIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findGrupoServicioIdTerminal(GrupoServicio entity) {
        return this.getMergedEntity(entity).getGrupoServicioIdTerminal();
    }

    public boolean isServicioListEmpty(GrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GrupoServicio> grupoServicio = cq.from(GrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupoServicio, entity), cb.isNotEmpty(grupoServicio.get(GrupoServicio_.servicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Servicio> findServicioList(GrupoServicio entity) {
        GrupoServicio mergedEntity = this.getMergedEntity(entity);
        List<Servicio> servicioList = mergedEntity.getServicioList();
        servicioList.size();
        return servicioList;
    }

    public boolean isTarifaGrupoServicioListEmpty(GrupoServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GrupoServicio> grupoServicio = cq.from(GrupoServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grupoServicio, entity), cb.isNotEmpty(grupoServicio.get(GrupoServicio_.tarifaGrupoServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TarifaGrupoServicio> findTarifaGrupoServicioList(GrupoServicio entity) {
        GrupoServicio mergedEntity = this.getMergedEntity(entity);
        List<TarifaGrupoServicio> tarifaGrupoServicioList = mergedEntity.getTarifaGrupoServicioList();
        tarifaGrupoServicioList.size();
        return tarifaGrupoServicioList;
    }
    
}
