/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Administrador;
import com.areatecnica.sigf.entities.AdministradorFlota;
import com.areatecnica.sigf.entities.Flota;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfrancoconcha
 */
@Stateless
public class AdministradorFlotaFacade extends AbstractFacade<AdministradorFlota> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFlotaFacade() {
        super(AdministradorFlota.class);
    }

    public boolean isAdministradorFlotaIdAdminEmpty(AdministradorFlota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministradorFlota> administradorFlota = cq.from(AdministradorFlota.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administradorFlota, entity), cb.isNotNull(administradorFlota.get(AdministradorFlota_.administradorFlotaIdAdmin)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Administrador findAdministradorFlotaIdAdmin(AdministradorFlota entity) {
        return this.getMergedEntity(entity).getAdministradorFlotaIdAdmin();
    }

    public boolean isAdministradorFlotaIdFlotaEmpty(AdministradorFlota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministradorFlota> administradorFlota = cq.from(AdministradorFlota.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administradorFlota, entity), cb.isNotNull(administradorFlota.get(AdministradorFlota_.administradorFlotaIdFlota)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Flota findAdministradorFlotaIdFlota(AdministradorFlota entity) {
        return this.getMergedEntity(entity).getAdministradorFlotaIdFlota();
    }
    
}
