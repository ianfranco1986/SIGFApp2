/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RepresentanteEmpresa_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.RepresentanteLegal;

/**
 *
 * @author ianfr
 */
@Stateless
public class RepresentanteEmpresaFacade extends AbstractFacade<RepresentanteEmpresa> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepresentanteEmpresaFacade() {
        super(RepresentanteEmpresa.class);
    }

    public boolean isRepresentanteEmpresaIdEmpresaEmpty(RepresentanteEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RepresentanteEmpresa> representanteEmpresa = cq.from(RepresentanteEmpresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(representanteEmpresa, entity), cb.isNotNull(representanteEmpresa.get(RepresentanteEmpresa_.representanteEmpresaIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findRepresentanteEmpresaIdEmpresa(RepresentanteEmpresa entity) {
        return this.getMergedEntity(entity).getRepresentanteEmpresaIdEmpresa();
    }

    public boolean isRepresentanteEmpresaIdRepresentanteLegalEmpty(RepresentanteEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RepresentanteEmpresa> representanteEmpresa = cq.from(RepresentanteEmpresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(representanteEmpresa, entity), cb.isNotNull(representanteEmpresa.get(RepresentanteEmpresa_.representanteEmpresaIdRepresentanteLegal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public RepresentanteLegal findRepresentanteEmpresaIdRepresentanteLegal(RepresentanteEmpresa entity) {
        return this.getMergedEntity(entity).getRepresentanteEmpresaIdRepresentanteLegal();
    }
    
}
