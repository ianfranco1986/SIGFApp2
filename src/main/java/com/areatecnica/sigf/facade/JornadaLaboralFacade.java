/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.HorarioJornada;
import com.areatecnica.sigf.entities.JornadaLaboral;
import com.areatecnica.sigf.entities.JornadaTrabajador;

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
public class JornadaLaboralFacade extends AbstractFacade<JornadaLaboral> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JornadaLaboralFacade() {
        super(JornadaLaboral.class);
    }

    public boolean isJornadaLaboralIdHorarioJornadaEmpty(JornadaLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<JornadaLaboral> jornadaLaboral = cq.from(JornadaLaboral.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(jornadaLaboral, entity), cb.isNotNull(jornadaLaboral.get(JornadaLaboral_.jornadaLaboralIdHorarioJornada)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public HorarioJornada findJornadaLaboralIdHorarioJornada(JornadaLaboral entity) {
        return this.getMergedEntity(entity).getJornadaLaboralIdHorarioJornada();
    }

    public boolean isJornadaTrabajadorListEmpty(JornadaLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<JornadaLaboral> jornadaLaboral = cq.from(JornadaLaboral.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(jornadaLaboral, entity), cb.isNotEmpty(jornadaLaboral.get(JornadaLaboral_.jornadaTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<JornadaTrabajador> findJornadaTrabajadorList(JornadaLaboral entity) {
        JornadaLaboral mergedEntity = this.getMergedEntity(entity);
        List<JornadaTrabajador> jornadaTrabajadorList = mergedEntity.getJornadaTrabajadorList();
        jornadaTrabajadorList.size();
        return jornadaTrabajadorList;
    }
    
}
