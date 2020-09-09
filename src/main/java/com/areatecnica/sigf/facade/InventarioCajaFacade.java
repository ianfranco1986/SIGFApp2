/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.InventarioCaja;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.InventarioCaja_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.InventarioInterno;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InventarioCajaFacade extends AbstractFacade<InventarioCaja> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioCajaFacade() {
        super(InventarioCaja.class);
    }

    public boolean isVentaBoletoListEmpty(InventarioCaja entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InventarioCaja> inventarioCaja = cq.from(InventarioCaja.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(inventarioCaja, entity), cb.isNotEmpty(inventarioCaja.get(InventarioCaja_.ventaBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<VentaBoleto> findVentaBoletoList(InventarioCaja entity) {
        InventarioCaja mergedEntity = this.getMergedEntity(entity);
        List<VentaBoleto> ventaBoletoList = mergedEntity.getVentaBoletoList();
        ventaBoletoList.size();
        return ventaBoletoList;
    }

    public boolean isInventarioCajaIdInventarioInternoEmpty(InventarioCaja entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InventarioCaja> inventarioCaja = cq.from(InventarioCaja.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(inventarioCaja, entity), cb.isNotNull(inventarioCaja.get(InventarioCaja_.inventarioCajaIdInventarioInterno)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public InventarioInterno findInventarioCajaIdInventarioInterno(InventarioCaja entity) {
        return this.getMergedEntity(entity).getInventarioCajaIdInventarioInterno();
    }

    public boolean isInventarioCajaIdCajaEmpty(InventarioCaja entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InventarioCaja> inventarioCaja = cq.from(InventarioCaja.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(inventarioCaja, entity), cb.isNotNull(inventarioCaja.get(InventarioCaja_.inventarioCajaIdCaja)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CajaRecaudacion findInventarioCajaIdCaja(InventarioCaja entity) {
        return this.getMergedEntity(entity).getInventarioCajaIdCaja();
    }
    
}
