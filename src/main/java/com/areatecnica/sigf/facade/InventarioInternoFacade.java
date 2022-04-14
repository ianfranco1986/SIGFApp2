/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.InventarioInterno;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.InventarioInterno_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.InventarioCaja;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InventarioInternoFacade extends AbstractFacade<InventarioInterno> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioInternoFacade() {
        super(InventarioInterno.class);
    }

    public boolean isInventarioInternoIdBoletoEmpty(InventarioInterno entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InventarioInterno> inventarioInterno = cq.from(InventarioInterno.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(inventarioInterno, entity), cb.isNotNull(inventarioInterno.get(InventarioInterno_.inventarioInternoIdBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Boleto findInventarioInternoIdBoleto(InventarioInterno entity) {
        return this.getMergedEntity(entity).getInventarioInternoIdBoleto();
    }

    public boolean isInventarioCajaListEmpty(InventarioInterno entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InventarioInterno> inventarioInterno = cq.from(InventarioInterno.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(inventarioInterno, entity), cb.isNotEmpty(inventarioInterno.get(InventarioInterno_.inventarioCajaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InventarioCaja> findInventarioCajaList(InventarioInterno entity) {
        InventarioInterno mergedEntity = this.getMergedEntity(entity);
        List<InventarioCaja> inventarioCajaList = mergedEntity.getInventarioCajaList();
        inventarioCajaList.size();
        return inventarioCajaList;
    }
    
}
