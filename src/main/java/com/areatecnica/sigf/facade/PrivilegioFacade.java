/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Privilegio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Privilegio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Menu;
import com.areatecnica.sigf.entities.Log;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class PrivilegioFacade extends AbstractFacade<Privilegio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegioFacade() {
        super(Privilegio.class);
    }

    public boolean isPrivilegioIdMenuEmpty(Privilegio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Privilegio> privilegio = cq.from(Privilegio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(privilegio, entity), cb.isNotNull(privilegio.get(Privilegio_.privilegioIdMenu)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Menu findPrivilegioIdMenu(Privilegio entity) {
        return this.getMergedEntity(entity).getPrivilegioIdMenu();
    }

    public boolean isLogListEmpty(Privilegio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Privilegio> privilegio = cq.from(Privilegio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(privilegio, entity), cb.isNotEmpty(privilegio.get(Privilegio_.logList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Log> findLogList(Privilegio entity) {
        Privilegio mergedEntity = this.getMergedEntity(entity);
        List<Log> logList = mergedEntity.getLogList();
        logList.size();
        return logList;
    }
    
}
