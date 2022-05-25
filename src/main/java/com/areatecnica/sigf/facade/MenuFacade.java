/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Menu;
import com.areatecnica.sigf.entities.Privilegio;
import com.areatecnica.sigf.entities.RolMenu;

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
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    public boolean isRolMenuListEmpty(Menu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Menu> menu = cq.from(Menu.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(menu, entity), cb.isNotEmpty(menu.get(Menu_.rolMenuList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RolMenu> findRolMenuList(Menu entity) {
        Menu mergedEntity = this.getMergedEntity(entity);
        List<RolMenu> rolMenuList = mergedEntity.getRolMenuList();
        rolMenuList.size();
        return rolMenuList;
    }

    public boolean isPrivilegioListEmpty(Menu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Menu> menu = cq.from(Menu.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(menu, entity), cb.isNotEmpty(menu.get(Menu_.privilegioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Privilegio> findPrivilegioList(Menu entity) {
        Menu mergedEntity = this.getMergedEntity(entity);
        List<Privilegio> privilegioList = mergedEntity.getPrivilegioList();
        privilegioList.size();
        return privilegioList;
    }
    
}
