/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RelacionLaboral;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RelacionLaboral_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.TipoContrato;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.OperadorTransporte;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.TipoTrabajador;
import com.areatecnica.sigf.entities.FiniquitoRelacionLaboral;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class RelacionLaboralFacade extends AbstractFacade<RelacionLaboral> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionLaboralFacade() {
        super(RelacionLaboral.class);
    }

    public boolean isRelacionLaboralIdTipoContratoEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdTipoContrato)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoContrato findRelacionLaboralIdTipoContrato(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdTipoContrato();
    }

    public boolean isRelacionLaboralIdEmpresaEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findRelacionLaboralIdEmpresa(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdEmpresa();
    }

    public boolean isRelacionLaboralIdOperadorEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdOperador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public OperadorTransporte findRelacionLaboralIdOperador(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdOperador();
    }

    public boolean isRelacionLaboralIdTerminalEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findRelacionLaboralIdTerminal(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdTerminal();
    }

    public boolean isRelacionLaboralIdTrabajadorEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findRelacionLaboralIdTrabajador(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdTrabajador();
    }

    public boolean isRelacionLaboralIdTipoTrabajadorEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotNull(relacionLaboral.get(RelacionLaboral_.relacionLaboralIdTipoTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoTrabajador findRelacionLaboralIdTipoTrabajador(RelacionLaboral entity) {
        return this.getMergedEntity(entity).getRelacionLaboralIdTipoTrabajador();
    }

    public boolean isFiniquitoRelacionLaboralListEmpty(RelacionLaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RelacionLaboral> relacionLaboral = cq.from(RelacionLaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(relacionLaboral, entity), cb.isNotEmpty(relacionLaboral.get(RelacionLaboral_.finiquitoRelacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FiniquitoRelacionLaboral> findFiniquitoRelacionLaboralList(RelacionLaboral entity) {
        RelacionLaboral mergedEntity = this.getMergedEntity(entity);
        List<FiniquitoRelacionLaboral> finiquitoRelacionLaboralList = mergedEntity.getFiniquitoRelacionLaboralList();
        finiquitoRelacionLaboralList.size();
        return finiquitoRelacionLaboralList;
    }
    
}
