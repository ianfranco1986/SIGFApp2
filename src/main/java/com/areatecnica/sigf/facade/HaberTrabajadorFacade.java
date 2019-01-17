/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.HaberTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.HaberTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.TipoHaberTrabajador;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.HaberLiquidacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class HaberTrabajadorFacade extends AbstractFacade<HaberTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HaberTrabajadorFacade() {
        super(HaberTrabajador.class);
    }

    public boolean isHaberTrabajadorIdHaberEmpty(HaberTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HaberTrabajador> haberTrabajador = cq.from(HaberTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(haberTrabajador, entity), cb.isNotNull(haberTrabajador.get(HaberTrabajador_.haberTrabajadorIdHaber)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoHaberTrabajador findHaberTrabajadorIdHaber(HaberTrabajador entity) {
        return this.getMergedEntity(entity).getHaberTrabajadorIdHaber();
    }

    public boolean isHaberTrabajadorIdTrabajadorEmpty(HaberTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HaberTrabajador> haberTrabajador = cq.from(HaberTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(haberTrabajador, entity), cb.isNotNull(haberTrabajador.get(HaberTrabajador_.haberTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findHaberTrabajadorIdTrabajador(HaberTrabajador entity) {
        return this.getMergedEntity(entity).getHaberTrabajadorIdTrabajador();
    }

    public boolean isHaberLiquidacionListEmpty(HaberTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HaberTrabajador> haberTrabajador = cq.from(HaberTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(haberTrabajador, entity), cb.isNotEmpty(haberTrabajador.get(HaberTrabajador_.haberLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HaberLiquidacion> findHaberLiquidacionList(HaberTrabajador entity) {
        HaberTrabajador mergedEntity = this.getMergedEntity(entity);
        List<HaberLiquidacion> haberLiquidacionList = mergedEntity.getHaberLiquidacionList();
        haberLiquidacionList.size();
        return haberLiquidacionList;
    }
    
}
