/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ProgramacionTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ProgramacionTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfrancoconcha
 */
@Stateless
public class ProgramacionTrabajadorFacade extends AbstractFacade<ProgramacionTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramacionTrabajadorFacade() {
        super(ProgramacionTrabajador.class);
    }

    public boolean isProgramacionTrabajadorIdAdministradorEmpty(ProgramacionTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProgramacionTrabajador> programacionTrabajador = cq.from(ProgramacionTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(programacionTrabajador, entity), cb.isNotNull(programacionTrabajador.get(ProgramacionTrabajador_.programacionTrabajadorIdAdministrador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Administrador findProgramacionTrabajadorIdAdministrador(ProgramacionTrabajador entity) {
        return this.getMergedEntity(entity).getProgramacionTrabajadorIdAdministrador();
    }

    public boolean isProgramacionTrabajadorIdTrabajadorEmpty(ProgramacionTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProgramacionTrabajador> programacionTrabajador = cq.from(ProgramacionTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(programacionTrabajador, entity), cb.isNotNull(programacionTrabajador.get(ProgramacionTrabajador_.programacionTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findProgramacionTrabajadorIdTrabajador(ProgramacionTrabajador entity) {
        return this.getMergedEntity(entity).getProgramacionTrabajadorIdTrabajador();
    }
    
}
