/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.LicenciaMedica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.LicenciaMedica_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class LicenciaMedicaFacade extends AbstractFacade<LicenciaMedica> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenciaMedicaFacade() {
        super(LicenciaMedica.class);
    }

    public boolean isLicenciaMedicaIdTrabajadorEmpty(LicenciaMedica entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LicenciaMedica> licenciaMedica = cq.from(LicenciaMedica.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(licenciaMedica, entity), cb.isNotNull(licenciaMedica.get(LicenciaMedica_.licenciaMedicaIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findLicenciaMedicaIdTrabajador(LicenciaMedica entity) {
        return this.getMergedEntity(entity).getLicenciaMedicaIdTrabajador();
    }
    
}
