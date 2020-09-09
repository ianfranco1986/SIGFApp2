/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CtvResumen;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.CtvResumen_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class CtvResumenFacade extends AbstractFacade<CtvResumen> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtvResumenFacade() {
        super(CtvResumen.class);
    }

    public boolean isCtvResumenIdResumenRecaudacionEmpty(CtvResumen entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CtvResumen> ctvResumen = cq.from(CtvResumen.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ctvResumen, entity), cb.isNotNull(ctvResumen.get(CtvResumen_.ctvResumenIdResumenRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ResumenRecaudacion findCtvResumenIdResumenRecaudacion(CtvResumen entity) {
        return this.getMergedEntity(entity).getCtvResumenIdResumenRecaudacion();
    }
    
}
