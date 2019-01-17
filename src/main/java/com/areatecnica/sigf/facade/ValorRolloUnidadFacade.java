/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ValorRolloUnidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.ValorRolloUnidad_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.UnidadNegocio;

/**
 *
 * @author ianfr
 */
@Stateless
public class ValorRolloUnidadFacade extends AbstractFacade<ValorRolloUnidad> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValorRolloUnidadFacade() {
        super(ValorRolloUnidad.class);
    }

    public boolean isValorRolloUnidadIdUnidadEmpty(ValorRolloUnidad entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ValorRolloUnidad> valorRolloUnidad = cq.from(ValorRolloUnidad.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(valorRolloUnidad, entity), cb.isNotNull(valorRolloUnidad.get(ValorRolloUnidad_.valorRolloUnidadIdUnidad)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public UnidadNegocio findValorRolloUnidadIdUnidad(ValorRolloUnidad entity) {
        return this.getMergedEntity(entity).getValorRolloUnidadIdUnidad();
    }
    
}
