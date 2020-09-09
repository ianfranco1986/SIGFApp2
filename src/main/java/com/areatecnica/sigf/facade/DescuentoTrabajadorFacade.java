/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DescuentoTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.DescuentoTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class DescuentoTrabajadorFacade extends AbstractFacade<DescuentoTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentoTrabajadorFacade() {
        super(DescuentoTrabajador.class);
    }

    public boolean isDescuentoLiquidacionListEmpty(DescuentoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoTrabajador> descuentoTrabajador = cq.from(DescuentoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoTrabajador, entity), cb.isNotEmpty(descuentoTrabajador.get(DescuentoTrabajador_.descuentoLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoLiquidacion> findDescuentoLiquidacionList(DescuentoTrabajador entity) {
        DescuentoTrabajador mergedEntity = this.getMergedEntity(entity);
        List<DescuentoLiquidacion> descuentoLiquidacionList = mergedEntity.getDescuentoLiquidacionList();
        descuentoLiquidacionList.size();
        return descuentoLiquidacionList;
    }

    public boolean isDescuentoTrabajadorIdDescuentoEmpty(DescuentoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoTrabajador> descuentoTrabajador = cq.from(DescuentoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoTrabajador, entity), cb.isNotNull(descuentoTrabajador.get(DescuentoTrabajador_.descuentoTrabajadorIdDescuento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDescuentoTrabajador findDescuentoTrabajadorIdDescuento(DescuentoTrabajador entity) {
        return this.getMergedEntity(entity).getDescuentoTrabajadorIdDescuento();
    }

    public boolean isDescuentoTrabajadorIdTrabajadorEmpty(DescuentoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoTrabajador> descuentoTrabajador = cq.from(DescuentoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoTrabajador, entity), cb.isNotNull(descuentoTrabajador.get(DescuentoTrabajador_.descuentoTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findDescuentoTrabajadorIdTrabajador(DescuentoTrabajador entity) {
        return this.getMergedEntity(entity).getDescuentoTrabajadorIdTrabajador();
    }
    
}
