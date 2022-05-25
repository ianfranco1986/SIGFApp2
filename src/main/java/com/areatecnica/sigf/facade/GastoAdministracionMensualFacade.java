/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Departamento;
import com.areatecnica.sigf.entities.GastoAdministracionMensual;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class GastoAdministracionMensualFacade extends AbstractFacade<GastoAdministracionMensual> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GastoAdministracionMensualFacade() {
        super(GastoAdministracionMensual.class);
    }

    public boolean isGastoAdministracionMensualIdDepartamentoEmpty(GastoAdministracionMensual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<GastoAdministracionMensual> gastoAdministracionMensual = cq.from(GastoAdministracionMensual.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(gastoAdministracionMensual, entity), cb.isNotNull(gastoAdministracionMensual.get(GastoAdministracionMensual_.gastoAdministracionMensualIdDepartamento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findGastoAdministracionMensualIdDepartamento(GastoAdministracionMensual entity) {
        return this.getMergedEntity(entity).getGastoAdministracionMensualIdDepartamento();
    }
    
}
