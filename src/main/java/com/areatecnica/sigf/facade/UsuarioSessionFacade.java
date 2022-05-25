/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.UsuarioSession;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.logging.Logger;

/**
 *
 * @author ianfr
 */
@Stateless
public class UsuarioSessionFacade extends AbstractFacade<UsuarioSession> {

    Logger log = Logger.getLogger(this.getClass().getName());

    private final EntityManager em = Persistence.createEntityManagerFactory("com.areatecnica_SIGFapp_war_1PU").createEntityManager();

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
