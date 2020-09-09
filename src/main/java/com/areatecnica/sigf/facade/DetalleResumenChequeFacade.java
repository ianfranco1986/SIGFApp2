/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DetalleResumenCheque;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.DetalleResumenCheque_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Banco;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class DetalleResumenChequeFacade extends AbstractFacade<DetalleResumenCheque> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleResumenChequeFacade() {
        super(DetalleResumenCheque.class);
    }

    public boolean isDetalleResumenChequeIdBancoEmpty(DetalleResumenCheque entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleResumenCheque> detalleResumenCheque = cq.from(DetalleResumenCheque.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleResumenCheque, entity), cb.isNotNull(detalleResumenCheque.get(DetalleResumenCheque_.detalleResumenChequeIdBanco)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Banco findDetalleResumenChequeIdBanco(DetalleResumenCheque entity) {
        return this.getMergedEntity(entity).getDetalleResumenChequeIdBanco();
    }

    public boolean isDetalleResumenChequeIdResumenEmpty(DetalleResumenCheque entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleResumenCheque> detalleResumenCheque = cq.from(DetalleResumenCheque.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleResumenCheque, entity), cb.isNotNull(detalleResumenCheque.get(DetalleResumenCheque_.detalleResumenChequeIdResumen)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ResumenRecaudacion findDetalleResumenChequeIdResumen(DetalleResumenCheque entity) {
        return this.getMergedEntity(entity).getDetalleResumenChequeIdResumen();
    }
    
}
