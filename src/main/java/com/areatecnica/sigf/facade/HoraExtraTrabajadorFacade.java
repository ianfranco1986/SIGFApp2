/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.HoraExtraTrabajador;
import com.areatecnica.sigf.entities.Trabajador;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class HoraExtraTrabajadorFacade extends AbstractFacade<HoraExtraTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HoraExtraTrabajadorFacade() {
        super(HoraExtraTrabajador.class);
    }

    public boolean isHoraExtraTrabajadorIdTrabajadorEmpty(HoraExtraTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HoraExtraTrabajador> horaExtraTrabajador = cq.from(HoraExtraTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(horaExtraTrabajador, entity), cb.isNotNull(horaExtraTrabajador.get(HoraExtraTrabajador_.horaExtraTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findHoraExtraTrabajadorIdTrabajador(HoraExtraTrabajador entity) {
        return this.getMergedEntity(entity).getHoraExtraTrabajadorIdTrabajador();
    }
    
}
