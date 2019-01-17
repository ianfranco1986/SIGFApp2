/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AuditEntry;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.AuditField;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class AuditEntryFacade extends AbstractFacade<AuditEntry> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuditEntryFacade() {
        super(AuditEntry.class);
    }


    public List<AuditField> findAuditFieldList(AuditEntry entity) {
        AuditEntry mergedEntity = this.getMergedEntity(entity);
        List<AuditField> auditFieldList = mergedEntity.getAuditFieldList();
        auditFieldList.size();
        return auditFieldList;
    }
    
}
