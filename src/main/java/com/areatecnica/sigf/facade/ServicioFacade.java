/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Servicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Servicio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CalleServicio;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.FrecuenciaServicio;
import com.areatecnica.sigf.entities.ControlServicio;
import com.areatecnica.sigf.entities.HorarioServicio;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.entities.RegistroBoleto;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ServicioFacade extends AbstractFacade<Servicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFacade() {
        super(Servicio.class);
    }

    public boolean isCalleServicioListEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotEmpty(servicio.get(Servicio_.calleServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CalleServicio> findCalleServicioList(Servicio entity) {
        Servicio mergedEntity = this.getMergedEntity(entity);
        List<CalleServicio> calleServicioList = mergedEntity.getCalleServicioList();
        calleServicioList.size();
        return calleServicioList;
    }

    public boolean isDespachoListEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotEmpty(servicio.get(Servicio_.despachoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Despacho> findDespachoList(Servicio entity) {
        Servicio mergedEntity = this.getMergedEntity(entity);
        List<Despacho> despachoList = mergedEntity.getDespachoList();
        despachoList.size();
        return despachoList;
    }

    public boolean isFrecuenciaServicioListEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotEmpty(servicio.get(Servicio_.frecuenciaServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FrecuenciaServicio> findFrecuenciaServicioList(Servicio entity) {
        Servicio mergedEntity = this.getMergedEntity(entity);
        List<FrecuenciaServicio> frecuenciaServicioList = mergedEntity.getFrecuenciaServicioList();
        frecuenciaServicioList.size();
        return frecuenciaServicioList;
    }

    public boolean isControlServicioListEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotEmpty(servicio.get(Servicio_.controlServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ControlServicio> findControlServicioList(Servicio entity) {
        Servicio mergedEntity = this.getMergedEntity(entity);
        List<ControlServicio> controlServicioList = mergedEntity.getControlServicioList();
        controlServicioList.size();
        return controlServicioList;
    }

    public boolean isHorarioServicioListEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotEmpty(servicio.get(Servicio_.horarioServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HorarioServicio> findHorarioServicioList(Servicio entity) {
        Servicio mergedEntity = this.getMergedEntity(entity);
        List<HorarioServicio> horarioServicioList = mergedEntity.getHorarioServicioList();
        horarioServicioList.size();
        return horarioServicioList;
    }

    public boolean isServicioIdCuentaEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotNull(servicio.get(Servicio_.servicioIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findServicioIdCuenta(Servicio entity) {
        return this.getMergedEntity(entity).getServicioIdCuenta();
    }

    public boolean isServicioIdGrupoServicioEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotNull(servicio.get(Servicio_.servicioIdGrupoServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public GrupoServicio findServicioIdGrupoServicio(Servicio entity) {
        return this.getMergedEntity(entity).getServicioIdGrupoServicio();
    }

    public boolean isServicioIdTerminalEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotNull(servicio.get(Servicio_.servicioIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findServicioIdTerminal(Servicio entity) {
        return this.getMergedEntity(entity).getServicioIdTerminal();
    }

    public boolean isServicioIdUnidadEmpty(Servicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Servicio> servicio = cq.from(Servicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(servicio, entity), cb.isNotNull(servicio.get(Servicio_.servicioIdUnidad)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public UnidadNegocio findServicioIdUnidad(Servicio entity) {
        return this.getMergedEntity(entity).getServicioIdUnidad();
    }

}
