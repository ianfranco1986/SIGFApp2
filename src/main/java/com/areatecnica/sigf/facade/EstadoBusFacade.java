/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.EstadoBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.EstadoBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Bus;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class EstadoBusFacade extends AbstractFacade<EstadoBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoBusFacade() {
        super(EstadoBus.class);
    }

    public boolean isBusListEmpty(EstadoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstadoBus> estadoBus = cq.from(EstadoBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estadoBus, entity), cb.isNotEmpty(estadoBus.get(EstadoBus_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(EstadoBus entity) {
        EstadoBus mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }
    
}
