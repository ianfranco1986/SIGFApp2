/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.NumeralSurtidor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.NumeralSurtidor_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Surtidor;

/**
 *
 * @author ianfr
 */
@Stateless
public class NumeralSurtidorFacade extends AbstractFacade<NumeralSurtidor> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NumeralSurtidorFacade() {
        super(NumeralSurtidor.class);
    }

    public boolean isNumeralSurtidorIdSurtidorEmpty(NumeralSurtidor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<NumeralSurtidor> numeralSurtidor = cq.from(NumeralSurtidor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(numeralSurtidor, entity), cb.isNotNull(numeralSurtidor.get(NumeralSurtidor_.numeralSurtidorIdSurtidor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Surtidor findNumeralSurtidorIdSurtidor(NumeralSurtidor entity) {
        return this.getMergedEntity(entity).getNumeralSurtidorIdSurtidor();
    }
    
}
