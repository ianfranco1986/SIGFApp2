/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoContrato;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.TipoContrato_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.RelacionLaboral;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoContratoFacade extends AbstractFacade<TipoContrato> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoContratoFacade() {
        super(TipoContrato.class);
    }

    public boolean isLiquidacionSueldoListEmpty(TipoContrato entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoContrato> tipoContrato = cq.from(TipoContrato.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoContrato, entity), cb.isNotEmpty(tipoContrato.get(TipoContrato_.liquidacionSueldoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LiquidacionSueldo> findLiquidacionSueldoList(TipoContrato entity) {
        TipoContrato mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionSueldo> liquidacionSueldoList = mergedEntity.getLiquidacionSueldoList();
        liquidacionSueldoList.size();
        return liquidacionSueldoList;
    }

    public boolean isTipoContratoIdCuentaEmpty(TipoContrato entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoContrato> tipoContrato = cq.from(TipoContrato.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoContrato, entity), cb.isNotNull(tipoContrato.get(TipoContrato_.tipoContratoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoContratoIdCuenta(TipoContrato entity) {
        return this.getMergedEntity(entity).getTipoContratoIdCuenta();
    }

    public boolean isRelacionLaboralListEmpty(TipoContrato entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoContrato> tipoContrato = cq.from(TipoContrato.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoContrato, entity), cb.isNotEmpty(tipoContrato.get(TipoContrato_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(TipoContrato entity) {
        TipoContrato mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }
    
}
