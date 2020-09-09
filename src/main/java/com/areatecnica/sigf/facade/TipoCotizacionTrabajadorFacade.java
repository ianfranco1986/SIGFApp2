/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoCotizacionTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoCotizacionTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoCotizacionTrabajadorFacade extends AbstractFacade<TipoCotizacionTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCotizacionTrabajadorFacade() {
        super(TipoCotizacionTrabajador.class);
    }

    public boolean isTrabajadorListEmpty(TipoCotizacionTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCotizacionTrabajador> tipoCotizacionTrabajador = cq.from(TipoCotizacionTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCotizacionTrabajador, entity), cb.isNotEmpty(tipoCotizacionTrabajador.get(TipoCotizacionTrabajador_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(TipoCotizacionTrabajador entity) {
        TipoCotizacionTrabajador mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }
    
}
