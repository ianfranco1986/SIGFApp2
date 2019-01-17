/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Log;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Log_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.entities.Usuario;

/**
 *
 * @author ianfr
 */
@Stateless
public class LogFacade extends AbstractFacade<Log> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
        super(Log.class);
    }

    public boolean isLogIdPrivilegioEmpty(Log entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Log> log = cq.from(Log.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(log, entity), cb.isNotNull(log.get(Log_.logIdPrivilegio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Privilegio findLogIdPrivilegio(Log entity) {
        return this.getMergedEntity(entity).getLogIdPrivilegio();
    }

    public boolean isLogIdUsuarioEmpty(Log entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Log> log = cq.from(Log.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(log, entity), cb.isNotNull(log.get(Log_.logIdUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findLogIdUsuario(Log entity) {
        return this.getMergedEntity(entity).getLogIdUsuario();
    }
    
}
