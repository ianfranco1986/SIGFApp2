/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Departamento;
import com.areatecnica.sigf.entities.GastoAdministracionMensual;

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
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public boolean isGastoAdministracionMensualListEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.gastoAdministracionMensualList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<GastoAdministracionMensual> findGastoAdministracionMensualList(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        List<GastoAdministracionMensual> gastoAdministracionMensualList = mergedEntity.getGastoAdministracionMensualList();
        gastoAdministracionMensualList.size();
        return gastoAdministracionMensualList;
    }

    public boolean isDepartamentoIdCuentaEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotNull(departamento.get(Departamento_.departamentoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findDepartamentoIdCuenta(Departamento entity) {
        return this.getMergedEntity(entity).getDepartamentoIdCuenta();
    }
    
}
