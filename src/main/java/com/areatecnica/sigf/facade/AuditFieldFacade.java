/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AuditField;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.AuditField_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.AuditEntry;

/**
 *
 * @author ianfr
 */
@Stateless
public class AuditFieldFacade extends AbstractFacade<AuditField> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuditFieldFacade() {
        super(AuditField.class);
    }

    public boolean isAuditEntryIdEmpty(AuditField entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AuditField> auditField = cq.from(AuditField.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(auditField, entity), cb.isNotNull(auditField.get(AuditField_.auditEntryId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public AuditEntry findAuditEntryId(AuditField entity) {
        return this.getMergedEntity(entity).getAuditEntryId();
    }
    
}
