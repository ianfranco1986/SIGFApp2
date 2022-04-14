/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.MovimientoMes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.MovimientoMes;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CartolaBanco;

/**
 *
 * @author ianfr
 */
@Stateless
public class MovimientoMesFacade extends AbstractFacade<MovimientoMes> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoMesFacade() {
        super(MovimientoMes.class);
    }

    public boolean isMovimientoMesCartolaBancoIdEmpty(MovimientoMes entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MovimientoMes> detalleCartola = cq.from(MovimientoMes.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleCartola, entity), cb.isNotNull(detalleCartola.get(MovimientoMes.detalleCartolaCartolaBancoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    
    
}
