/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ControlHorarioServicio;
import com.areatecnica.sigf.entities.HorarioServicio;
import com.areatecnica.sigf.entities.Servicio;
import com.areatecnica.sigf.entities.TipoEstacionalidad;

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
public class HorarioServicioFacade extends AbstractFacade<HorarioServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioServicioFacade() {
        super(HorarioServicio.class);
    }

    public boolean isControlHorarioServicioListEmpty(HorarioServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HorarioServicio> horarioServicio = cq.from(HorarioServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horarioServicio, entity), cb.isNotEmpty(horarioServicio.get(HorarioServicio_.controlHorarioServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ControlHorarioServicio> findControlHorarioServicioList(HorarioServicio entity) {
        HorarioServicio mergedEntity = this.getMergedEntity(entity);
        List<ControlHorarioServicio> controlHorarioServicioList = mergedEntity.getControlHorarioServicioList();
        controlHorarioServicioList.size();
        return controlHorarioServicioList;
    }

    public boolean isHorarioServicioIdTipoEstacionalidadEmpty(HorarioServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HorarioServicio> horarioServicio = cq.from(HorarioServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horarioServicio, entity), cb.isNotNull(horarioServicio.get(HorarioServicio_.horarioServicioIdTipoEstacionalidad)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoEstacionalidad findHorarioServicioIdTipoEstacionalidad(HorarioServicio entity) {
        return this.getMergedEntity(entity).getHorarioServicioIdTipoEstacionalidad();
    }

    public boolean isHorarioServicioIdServicioEmpty(HorarioServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HorarioServicio> horarioServicio = cq.from(HorarioServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horarioServicio, entity), cb.isNotNull(horarioServicio.get(HorarioServicio_.horarioServicioIdServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Servicio findHorarioServicioIdServicio(HorarioServicio entity) {
        return this.getMergedEntity(entity).getHorarioServicioIdServicio();
    }
    
}
