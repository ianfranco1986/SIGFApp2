/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TipoTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoTrabajadorFacade extends AbstractFacade<TipoTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTrabajadorFacade() {
        super(TipoTrabajador.class);
    }

    public boolean isRelacionLaboralListEmpty(TipoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoTrabajador> tipoTrabajador = cq.from(TipoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoTrabajador, entity), cb.isNotEmpty(tipoTrabajador.get(TipoTrabajador_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(TipoTrabajador entity) {
        TipoTrabajador mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }

    public boolean isTipoTrabajadorIdCuentaEmpty(TipoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoTrabajador> tipoTrabajador = cq.from(TipoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoTrabajador, entity), cb.isNotNull(tipoTrabajador.get(TipoTrabajador_.tipoTrabajadorIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoTrabajadorIdCuenta(TipoTrabajador entity) {
        return this.getMergedEntity(entity).getTipoTrabajadorIdCuenta();
    }
    
}
