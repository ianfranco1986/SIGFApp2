/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ControlAsistencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.ControlAsistencia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class ControlAsistenciaFacade extends AbstractFacade<ControlAsistencia> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlAsistenciaFacade() {
        super(ControlAsistencia.class);
    }

    public boolean isControlAsistenciaIdTrabajadorEmpty(ControlAsistencia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ControlAsistencia> controlAsistencia = cq.from(ControlAsistencia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(controlAsistencia, entity), cb.isNotNull(controlAsistencia.get(ControlAsistencia_.controlAsistenciaIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findControlAsistenciaIdTrabajador(ControlAsistencia entity) {
        return this.getMergedEntity(entity).getControlAsistenciaIdTrabajador();
    }
    
}
