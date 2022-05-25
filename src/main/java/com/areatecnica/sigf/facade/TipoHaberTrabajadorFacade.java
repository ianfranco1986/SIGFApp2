/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.TipoHaberTrabajador;

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
public class TipoHaberTrabajadorFacade extends AbstractFacade<TipoHaberTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoHaberTrabajadorFacade() {
        super(TipoHaberTrabajador.class);
    }

    public boolean isHaberTrabajadorListEmpty(TipoHaberTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoHaberTrabajador> tipoHaberTrabajador = cq.from(TipoHaberTrabajador.class);
////        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoHaberTrabajador, entity), cb.isNotEmpty(tipoHaberTrabajador.get(TipoHaberTrabajador_.haberTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HaberTrabajador> findHaberTrabajadorList(TipoHaberTrabajador entity) {
        TipoHaberTrabajador mergedEntity = this.getMergedEntity(entity);
        List<HaberTrabajador> haberTrabajadorList = mergedEntity.getHaberTrabajadorList();
        haberTrabajadorList.size();
        return haberTrabajadorList;
    }

    public boolean isTipoHaberTrabajadorIdCuentaEmpty(TipoHaberTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoHaberTrabajador> tipoHaberTrabajador = cq.from(TipoHaberTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoHaberTrabajador, entity), cb.isNotNull(tipoHaberTrabajador.get(TipoHaberTrabajador_.tipoHaberTrabajadorIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoHaberTrabajadorIdCuenta(TipoHaberTrabajador entity) {
        return this.getMergedEntity(entity).getTipoHaberTrabajadorIdCuenta();
    }
    
}
