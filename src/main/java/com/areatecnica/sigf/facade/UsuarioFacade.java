/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ianfr
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    Logger log = Logger.getLogger(this.getClass().getName());

    private final EntityManager em = Persistence.createEntityManagerFactory("com.areatecnica_SIGFapp_war_1PU").createEntityManager();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isUsuarioSessionListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.usuarioSessionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<UsuarioSession> findUsuarioSessionList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<UsuarioSession> usuarioSessionList = mergedEntity.getUsuarioSessionList();
        usuarioSessionList.size();
        return usuarioSessionList;
    }

    public boolean isDespachoListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.despachoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Despacho> findDespachoList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<Despacho> despachoList = mergedEntity.getDespachoList();
        despachoList.size();
        return despachoList;
    }

    public boolean isUsuarioIdCuentaEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.usuarioIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findUsuarioIdCuenta(Usuario entity) {
        return this.getMergedEntity(entity).getUsuarioIdCuenta();
    }

    public boolean isUsuarioIdRolEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.usuarioIdRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findUsuarioIdRol(Usuario entity) {
        return this.getMergedEntity(entity).getUsuarioIdRol();
    }

    public boolean isUsuarioIdTerminalEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.usuarioIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findUsuarioIdTerminal(Usuario entity) {
        return this.getMergedEntity(entity).getUsuarioIdTerminal();
    }

    public boolean isLogListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.logList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public boolean isCajaRecaudacionListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get(Usuario_.cajaRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaRecaudacion> findCajaRecaudacionList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<CajaRecaudacion> cajaRecaudacionList = mergedEntity.getCajaRecaudacionList();
        cajaRecaudacionList.size();
        return cajaRecaudacionList;
    }

}
