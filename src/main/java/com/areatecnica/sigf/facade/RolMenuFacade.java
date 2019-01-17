/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RolMenu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RolMenu_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Menu;
import com.areatecnica.sigf.entities.Rol;

/**
 *
 * @author ianfr
 */
@Stateless
public class RolMenuFacade extends AbstractFacade<RolMenu> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolMenuFacade() {
        super(RolMenu.class);
    }

    public boolean isRolMenuIdMenuEmpty(RolMenu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RolMenu> rolMenu = cq.from(RolMenu.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rolMenu, entity), cb.isNotNull(rolMenu.get(RolMenu_.rolMenuIdMenu)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Menu findRolMenuIdMenu(RolMenu entity) {
        return this.getMergedEntity(entity).getRolMenuIdMenu();
    }

    public boolean isRolMenuIdRolEmpty(RolMenu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RolMenu> rolMenu = cq.from(RolMenu.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rolMenu, entity), cb.isNotNull(rolMenu.get(RolMenu_.rolMenuIdRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findRolMenuIdRol(RolMenu entity) {
        return this.getMergedEntity(entity).getRolMenuIdRol();
    }
    
}
