/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoDescuentoTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.DescuentoTrabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoDescuentoTrabajadorFacade extends AbstractFacade<TipoDescuentoTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDescuentoTrabajadorFacade() {
        super(TipoDescuentoTrabajador.class);
    }

    public boolean isTipoDescuentoTrabajadorIdCuentaEmpty(TipoDescuentoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDescuentoTrabajador> tipoDescuentoTrabajador = cq.from(TipoDescuentoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDescuentoTrabajador, entity), cb.isNotNull(tipoDescuentoTrabajador.get(TipoDescuentoTrabajador_.tipoDescuentoTrabajadorIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoDescuentoTrabajadorIdCuenta(TipoDescuentoTrabajador entity) {
        return this.getMergedEntity(entity).getTipoDescuentoTrabajadorIdCuenta();
    }

    public boolean isDescuentoTrabajadorListEmpty(TipoDescuentoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDescuentoTrabajador> tipoDescuentoTrabajador = cq.from(TipoDescuentoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDescuentoTrabajador, entity), cb.isNotEmpty(tipoDescuentoTrabajador.get(TipoDescuentoTrabajador_.descuentoTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoTrabajador> findDescuentoTrabajadorList(TipoDescuentoTrabajador entity) {
        TipoDescuentoTrabajador mergedEntity = this.getMergedEntity(entity);
        List<DescuentoTrabajador> descuentoTrabajadorList = mergedEntity.getDescuentoTrabajadorList();
        descuentoTrabajadorList.size();
        return descuentoTrabajadorList;
    }
    
}
