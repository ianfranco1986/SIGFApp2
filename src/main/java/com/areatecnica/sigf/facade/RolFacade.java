/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Rol_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RolMenu;
import com.areatecnica.sigf.entities.Usuario;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public boolean isRolMenuListEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.rolMenuList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RolMenu> findRolMenuList(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        List<RolMenu> rolMenuList = mergedEntity.getRolMenuList();
        rolMenuList.size();
        return rolMenuList;
    }

    public boolean isUsuarioListEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.usuarioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuario> findUsuarioList(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        List<Usuario> usuarioList = mergedEntity.getUsuarioList();
        usuarioList.size();
        return usuarioList;
    }
    
}
