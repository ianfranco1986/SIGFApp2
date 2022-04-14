/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.MonedaPactadaInstitucionSalud;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class TrabajadorAdicionalSaludFacade extends AbstractFacade<TrabajadorAdicionalSalud> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrabajadorAdicionalSaludFacade() {
        super(TrabajadorAdicionalSalud.class);
    }

    public boolean isTrabajadorAdicionalSaludIdMonedaEmpty(TrabajadorAdicionalSalud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TrabajadorAdicionalSalud> trabajadorAdicionalSalud = cq.from(TrabajadorAdicionalSalud.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajadorAdicionalSalud, entity), cb.isNotNull(trabajadorAdicionalSalud.get(TrabajadorAdicionalSalud_.trabajadorAdicionalSaludIdMoneda)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public MonedaPactadaInstitucionSalud findTrabajadorAdicionalSaludIdMoneda(TrabajadorAdicionalSalud entity) {
        return this.getMergedEntity(entity).getTrabajadorAdicionalSaludIdMoneda();
    }

    public boolean isTrabajadorAdicionalSaludIdTrabajadorEmpty(TrabajadorAdicionalSalud entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TrabajadorAdicionalSalud> trabajadorAdicionalSalud = cq.from(TrabajadorAdicionalSalud.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajadorAdicionalSalud, entity), cb.isNotNull(trabajadorAdicionalSalud.get(TrabajadorAdicionalSalud_.trabajadorAdicionalSaludIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findTrabajadorAdicionalSaludIdTrabajador(TrabajadorAdicionalSalud entity) {
        return this.getMergedEntity(entity).getTrabajadorAdicionalSaludIdTrabajador();
    }
    
}
