/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CargaRetroactiva;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.CargaRetroactiva_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class CargaRetroactivaFacade extends AbstractFacade<CargaRetroactiva> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargaRetroactivaFacade() {
        super(CargaRetroactiva.class);
    }

    public boolean isCargaRetroactivaIdCargaTrabajadorEmpty(CargaRetroactiva entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaRetroactiva> cargaRetroactiva = cq.from(CargaRetroactiva.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaRetroactiva, entity), cb.isNotNull(cargaRetroactiva.get(CargaRetroactiva_.cargaRetroactivaIdCargaTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CargaTrabajador findCargaRetroactivaIdCargaTrabajador(CargaRetroactiva entity) {
        return this.getMergedEntity(entity).getCargaRetroactivaIdCargaTrabajador();
    }

    public boolean isCargaRetroactivaIdTrabajadorEmpty(CargaRetroactiva entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargaRetroactiva> cargaRetroactiva = cq.from(CargaRetroactiva.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargaRetroactiva, entity), cb.isNotNull(cargaRetroactiva.get(CargaRetroactiva_.cargaRetroactivaIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findCargaRetroactivaIdTrabajador(CargaRetroactiva entity) {
        return this.getMergedEntity(entity).getCargaRetroactivaIdTrabajador();
    }
    
}
