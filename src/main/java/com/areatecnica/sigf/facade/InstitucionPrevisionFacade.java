/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.InstitucionPrevision;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.InstitucionPrevision_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class InstitucionPrevisionFacade extends AbstractFacade<InstitucionPrevision> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstitucionPrevisionFacade() {
        super(InstitucionPrevision.class);
    }

    public boolean isTrabajadorListEmpty(InstitucionPrevision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionPrevision> institucionPrevision = cq.from(InstitucionPrevision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionPrevision, entity), cb.isNotEmpty(institucionPrevision.get(InstitucionPrevision_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(InstitucionPrevision entity) {
        InstitucionPrevision mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isInstitucionPrevisionIdCuentaEmpty(InstitucionPrevision entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<InstitucionPrevision> institucionPrevision = cq.from(InstitucionPrevision.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(institucionPrevision, entity), cb.isNotNull(institucionPrevision.get(InstitucionPrevision_.institucionPrevisionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findInstitucionPrevisionIdCuenta(InstitucionPrevision entity) {
        return this.getMergedEntity(entity).getInstitucionPrevisionIdCuenta();
    }
    
}
