/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ContratoUnidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ContratoUnidad_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.UnidadNegocio;

/**
 *
 * @author ianfr
 */
@Stateless
public class ContratoUnidadFacade extends AbstractFacade<ContratoUnidad> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoUnidadFacade() {
        super(ContratoUnidad.class);
    }

    public boolean isContratoUnidadIdUnidadNegocioEmpty(ContratoUnidad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ContratoUnidad> contratoUnidad = cq.from(ContratoUnidad.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(contratoUnidad, entity), cb.isNotNull(contratoUnidad.get(ContratoUnidad_.contratoUnidadIdUnidadNegocio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public UnidadNegocio findContratoUnidadIdUnidadNegocio(ContratoUnidad entity) {
        return this.getMergedEntity(entity).getContratoUnidadIdUnidadNegocio();
    }
    
}
