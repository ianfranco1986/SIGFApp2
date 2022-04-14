/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.UsuarioSession;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.UsuarioSession_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Usuario;

/**
 *
 * @author ianfr
 */
@Stateless
public class UsuarioSessionFacade extends AbstractFacade<UsuarioSession> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioSessionFacade() {
        super(UsuarioSession.class);
    }

    public boolean isUsuarioSessionIdUsuarioEmpty(UsuarioSession entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UsuarioSession> usuarioSession = cq.from(UsuarioSession.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuarioSession, entity), cb.isNotNull(usuarioSession.get(UsuarioSession_.usuarioSessionIdUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findUsuarioSessionIdUsuario(UsuarioSession entity) {
        return this.getMergedEntity(entity).getUsuarioSessionIdUsuario();
    }
    
}
