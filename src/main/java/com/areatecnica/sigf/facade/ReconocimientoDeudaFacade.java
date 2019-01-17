/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.ReconocimientoDeuda_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class ReconocimientoDeudaFacade extends AbstractFacade<ReconocimientoDeuda> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReconocimientoDeudaFacade() {
        super(ReconocimientoDeuda.class);
    }

    public boolean isReconocimientoDeudaIdEmpresaEmpty(ReconocimientoDeuda entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ReconocimientoDeuda> reconocimientoDeuda = cq.from(ReconocimientoDeuda.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reconocimientoDeuda, entity), cb.isNotNull(reconocimientoDeuda.get(ReconocimientoDeuda_.reconocimientoDeudaIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findReconocimientoDeudaIdEmpresa(ReconocimientoDeuda entity) {
        return this.getMergedEntity(entity).getReconocimientoDeudaIdEmpresa();
    }

    public boolean isReconocimientoDeudaIdTrabajadorEmpty(ReconocimientoDeuda entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ReconocimientoDeuda> reconocimientoDeuda = cq.from(ReconocimientoDeuda.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reconocimientoDeuda, entity), cb.isNotNull(reconocimientoDeuda.get(ReconocimientoDeuda_.reconocimientoDeudaIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findReconocimientoDeudaIdTrabajador(ReconocimientoDeuda entity) {
        return this.getMergedEntity(entity).getReconocimientoDeudaIdTrabajador();
    }
    
}
