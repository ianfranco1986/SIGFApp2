/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Sindicato;
import com.areatecnica.sigf.entities.Trabajador;

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
public class SindicatoFacade extends AbstractFacade<Sindicato> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SindicatoFacade() {
        super(Sindicato.class);
    }

    public boolean isTrabajadorListEmpty(Sindicato entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Sindicato> sindicato = cq.from(Sindicato.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(sindicato, entity), cb.isNotEmpty(sindicato.get(Sindicato_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(Sindicato entity) {
        Sindicato mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isSindicatoIdCuentaEmpty(Sindicato entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Sindicato> sindicato = cq.from(Sindicato.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(sindicato, entity), cb.isNotNull(sindicato.get(Sindicato_.sindicatoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findSindicatoIdCuenta(Sindicato entity) {
        return this.getMergedEntity(entity).getSindicatoIdCuenta();
    }
    
}
