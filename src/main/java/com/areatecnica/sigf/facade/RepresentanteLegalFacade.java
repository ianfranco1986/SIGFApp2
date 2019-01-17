/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RepresentanteLegal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RepresentanteLegal_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.entities.OperadorTransporte;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class RepresentanteLegalFacade extends AbstractFacade<RepresentanteLegal> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepresentanteLegalFacade() {
        super(RepresentanteLegal.class);
    }

    public boolean isRepresentanteLegalIdCuentaEmpty(RepresentanteLegal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RepresentanteLegal> representanteLegal = cq.from(RepresentanteLegal.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(representanteLegal, entity), cb.isNotNull(representanteLegal.get(RepresentanteLegal_.representanteLegalIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findRepresentanteLegalIdCuenta(RepresentanteLegal entity) {
        return this.getMergedEntity(entity).getRepresentanteLegalIdCuenta();
    }

    public boolean isRepresentanteEmpresaListEmpty(RepresentanteLegal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RepresentanteLegal> representanteLegal = cq.from(RepresentanteLegal.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(representanteLegal, entity), cb.isNotEmpty(representanteLegal.get(RepresentanteLegal_.representanteEmpresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RepresentanteEmpresa> findRepresentanteEmpresaList(RepresentanteLegal entity) {
        RepresentanteLegal mergedEntity = this.getMergedEntity(entity);
        List<RepresentanteEmpresa> representanteEmpresaList = mergedEntity.getRepresentanteEmpresaList();
        representanteEmpresaList.size();
        return representanteEmpresaList;
    }

    public boolean isOperadorTransporteListEmpty(RepresentanteLegal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RepresentanteLegal> representanteLegal = cq.from(RepresentanteLegal.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(representanteLegal, entity), cb.isNotEmpty(representanteLegal.get(RepresentanteLegal_.operadorTransporteList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<OperadorTransporte> findOperadorTransporteList(RepresentanteLegal entity) {
        RepresentanteLegal mergedEntity = this.getMergedEntity(entity);
        List<OperadorTransporte> operadorTransporteList = mergedEntity.getOperadorTransporteList();
        operadorTransporteList.size();
        return operadorTransporteList;
    }
    
}
