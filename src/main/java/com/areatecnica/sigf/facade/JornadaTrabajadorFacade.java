/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.JornadaTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.JornadaTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.JornadaLaboral;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class JornadaTrabajadorFacade extends AbstractFacade<JornadaTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JornadaTrabajadorFacade() {
        super(JornadaTrabajador.class);
    }

    public boolean isJornadaTrabajadorIdJornadaLaboralEmpty(JornadaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<JornadaTrabajador> jornadaTrabajador = cq.from(JornadaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(jornadaTrabajador, entity), cb.isNotNull(jornadaTrabajador.get(JornadaTrabajador_.jornadaTrabajadorIdJornadaLaboral)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public JornadaLaboral findJornadaTrabajadorIdJornadaLaboral(JornadaTrabajador entity) {
        return this.getMergedEntity(entity).getJornadaTrabajadorIdJornadaLaboral();
    }

    public boolean isJornadaTrabajadorIdTrabajadorEmpty(JornadaTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<JornadaTrabajador> jornadaTrabajador = cq.from(JornadaTrabajador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(jornadaTrabajador, entity), cb.isNotNull(jornadaTrabajador.get(JornadaTrabajador_.jornadaTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findJornadaTrabajadorIdTrabajador(JornadaTrabajador entity) {
        return this.getMergedEntity(entity).getJornadaTrabajadorIdTrabajador();
    }
    
}
