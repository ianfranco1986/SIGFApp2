/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AdministradorBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.AdministradorBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.Bus;

/**
 *
 * @author ianfrancoconcha
 */
@Stateless
public class AdministradorBusFacade extends AbstractFacade<AdministradorBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorBusFacade() {
        super(AdministradorBus.class);
    }

    public boolean isAdministradorBusIdAdminEmpty(AdministradorBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministradorBus> administradorBus = cq.from(AdministradorBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administradorBus, entity), cb.isNotNull(administradorBus.get(AdministradorBus_.administradorBusIdAdmin)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Administrador findAdministradorBusIdAdmin(AdministradorBus entity) {
        return this.getMergedEntity(entity).getAdministradorBusIdAdmin();
    }

    public boolean isAdministradorBusIdBusEmpty(AdministradorBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministradorBus> administradorBus = cq.from(AdministradorBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administradorBus, entity), cb.isNotNull(administradorBus.get(AdministradorBus_.administradorBusIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findAdministradorBusIdBus(AdministradorBus entity) {
        return this.getMergedEntity(entity).getAdministradorBusIdBus();
    }
    
}
