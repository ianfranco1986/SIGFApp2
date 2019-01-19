/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Administrador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Administrador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.ProgramacionTrabajador;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.AdministradorBus;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }

//    public boolean isProgramacionTrabajadorListEmpty(Administrador entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Administrador> administrador = cq.from(Administrador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administrador, entity), cb.isNotEmpty(administrador.get(Administrador_.programacionTrabajadorList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<ProgramacionTrabajador> findProgramacionTrabajadorList(Administrador entity) {
//        Administrador mergedEntity = this.getMergedEntity(entity);
//        List<ProgramacionTrabajador> programacionTrabajadorList = mergedEntity.getProgramacionTrabajadorList();
//        programacionTrabajadorList.size();
//        return programacionTrabajadorList;
//    }

    public boolean isAdministradorFlotaListEmpty(Administrador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Administrador> administrador = cq.from(Administrador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administrador, entity), cb.isNotEmpty(administrador.get(Administrador_.administradorFlotaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AdministradorFlota> findAdministradorFlotaList(Administrador entity) {
        Administrador mergedEntity = this.getMergedEntity(entity);
        List<AdministradorFlota> administradorFlotaList = mergedEntity.getAdministradorFlotaList();
        administradorFlotaList.size();
        return administradorFlotaList;
    }

    public boolean isAdministradorBusListEmpty(Administrador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Administrador> administrador = cq.from(Administrador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administrador, entity), cb.isNotEmpty(administrador.get(Administrador_.administradorBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AdministradorBus> findAdministradorBusList(Administrador entity) {
        Administrador mergedEntity = this.getMergedEntity(entity);
        List<AdministradorBus> administradorBusList = mergedEntity.getAdministradorBusList();
        administradorBusList.size();
        return administradorBusList;
    }
    
}
