/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

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
public class TerminalFacade extends AbstractFacade<Terminal> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerminalFacade() {
        super(Terminal.class);
    }

    public boolean isSurtidorListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.surtidorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Surtidor> findSurtidorList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<Surtidor> surtidorList = mergedEntity.getSurtidorList();
        surtidorList.size();
        return surtidorList;
    }

    public boolean isUsuarioListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.usuarioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuario> findUsuarioList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<Usuario> usuarioList = mergedEntity.getUsuarioList();
        usuarioList.size();
        return usuarioList;
    }

    public boolean isLiquidacionSueldoListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.liquidacionSueldoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LiquidacionSueldo> findLiquidacionSueldoList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionSueldo> liquidacionSueldoList = mergedEntity.getLiquidacionSueldoList();
        liquidacionSueldoList.size();
        return liquidacionSueldoList;
    }

    public boolean isTrabajadorListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isBusListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isRelacionLaboralListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }

    public boolean isGrupoServicioListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.grupoServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<GrupoServicio> findGrupoServicioList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<GrupoServicio> grupoServicioList = mergedEntity.getGrupoServicioList();
        grupoServicioList.size();
        return grupoServicioList;
    }

    public boolean isCajaRecaudacionListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.cajaRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaRecaudacion> findCajaRecaudacionList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<CajaRecaudacion> cajaRecaudacionList = mergedEntity.getCajaRecaudacionList();
        cajaRecaudacionList.size();
        return cajaRecaudacionList;
    }

    public boolean isServicioListEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotEmpty(terminal.get(Terminal_.servicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Servicio> findServicioList(Terminal entity) {
        Terminal mergedEntity = this.getMergedEntity(entity);
        List<Servicio> servicioList = mergedEntity.getServicioList();
        servicioList.size();
        return servicioList;
    }

    public boolean isTerminalIdComunaEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotNull(terminal.get(Terminal_.terminalIdComuna)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Comuna findTerminalIdComuna(Terminal entity) {
        return this.getMergedEntity(entity).getTerminalIdComuna();
    }

    public boolean isTerminalIdCuentaEmpty(Terminal entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Terminal> terminal = cq.from(Terminal.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(terminal, entity), cb.isNotNull(terminal.get(Terminal_.terminalIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTerminalIdCuenta(Terminal entity) {
        return this.getMergedEntity(entity).getTerminalIdCuenta();
    }
    
}
