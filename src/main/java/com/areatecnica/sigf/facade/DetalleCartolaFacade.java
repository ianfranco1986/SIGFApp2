/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.DetalleCartola;

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
public class DetalleCartolaFacade extends AbstractFacade<DetalleCartola> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCartolaFacade() {
        super(DetalleCartola.class);
    }

    public boolean isDetalleCartolaCartolaBancoIdEmpty(DetalleCartola entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleCartola> detalleCartola = cq.from(DetalleCartola.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleCartola, entity), cb.isNotNull(detalleCartola.get(DetalleCartola.detalleCartolaCartolaBancoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CartolaBanco findDetalleCartolaCartolaBancoId(DetalleCartola entity) {
        return this.getMergedEntity(entity).getDetalleCartolaCartolaBancoId();
    }
    
}
