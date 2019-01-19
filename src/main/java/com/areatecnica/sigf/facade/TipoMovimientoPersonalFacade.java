/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoMovimientoPersonal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoMovimientoPersonal_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoMovimientoPersonalFacade extends AbstractFacade<TipoMovimientoPersonal> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMovimientoPersonalFacade() {
        super(TipoMovimientoPersonal.class);
    }

    public boolean isLiquidacionSueldoListEmpty(TipoMovimientoPersonal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoMovimientoPersonal> tipoMovimientoPersonal = cq.from(TipoMovimientoPersonal.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoMovimientoPersonal, entity), cb.isNotEmpty(tipoMovimientoPersonal.get(TipoMovimientoPersonal_.liquidacionSueldoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LiquidacionSueldo> findLiquidacionSueldoList(TipoMovimientoPersonal entity) {
        TipoMovimientoPersonal mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionSueldo> liquidacionSueldoList = mergedEntity.getLiquidacionSueldoList();
        liquidacionSueldoList.size();
        return liquidacionSueldoList;
    }
    
}
