/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.TipoInstitucionApv;
import com.areatecnica.sigf.entities.Trabajador;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InstitucionApvFacade extends AbstractFacade<InstitucionApv> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitucionApvFacade() {
        super(InstitucionApv.class);
    }

    public boolean isTrabajadorListEmpty(InstitucionApv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionApv> institucionApv = cq.from(InstitucionApv.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionApv, entity), cb.isNotEmpty(institucionApv.get(InstitucionApv_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(InstitucionApv entity) {
        InstitucionApv mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isInstitucionApvIdCuentaEmpty(InstitucionApv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionApv> institucionApv = cq.from(InstitucionApv.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionApv, entity), cb.isNotNull(institucionApv.get(InstitucionApv_.institucionApvIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findInstitucionApvIdCuenta(InstitucionApv entity) {
        return this.getMergedEntity(entity).getInstitucionApvIdCuenta();
    }

    public boolean isInstitucionApvIdTipoEmpty(InstitucionApv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionApv> institucionApv = cq.from(InstitucionApv.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionApv, entity), cb.isNotNull(institucionApv.get(InstitucionApv_.institucionApvIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoInstitucionApv findInstitucionApvIdTipo(InstitucionApv entity) {
        return this.getMergedEntity(entity).getInstitucionApvIdTipo();
    }
    
}
