/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCargaFamiliar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoCargaFamiliar_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargaTrabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCargaFamiliarFacade extends AbstractFacade<TipoCargaFamiliar> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCargaFamiliarFacade() {
        super(TipoCargaFamiliar.class);
    }

    public boolean isCargaTrabajadorListEmpty(TipoCargaFamiliar entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCargaFamiliar> tipoCargaFamiliar = cq.from(TipoCargaFamiliar.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCargaFamiliar, entity), cb.isNotEmpty(tipoCargaFamiliar.get(TipoCargaFamiliar_.cargaTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargaTrabajador> findCargaTrabajadorList(TipoCargaFamiliar entity) {
        TipoCargaFamiliar mergedEntity = this.getMergedEntity(entity);
        List<CargaTrabajador> cargaTrabajadorList = mergedEntity.getCargaTrabajadorList();
        cargaTrabajadorList.size();
        return cargaTrabajadorList;
    }
    
}
