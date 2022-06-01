/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IPlanCuentaSubTipoDao;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;
import com.areatecnica.sigf.entities.TipoPlanCuenta;
import java.util.List;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class PlanCuentaSubTipoDaoImpl extends GenericDAOImpl<PlanCuentaSubTipo> implements IPlanCuentaSubTipoDao<PlanCuentaSubTipo> {

    @Override
    public PlanCuentaSubTipo findById(int id) {
        try {
            return (PlanCuentaSubTipo) this.entityManager.createNamedQuery("PlanCuentaSubTipo.findByPlanCuentaSubTipoId").setParameter("privilegioId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<PlanCuentaSubTipo> find() {
        return this.entityManager.createNamedQuery("PlanCuentaSubTipo.findAll").getResultList();
    }

    @Override
    public List<PlanCuentaSubTipo> findByTipoPlan(TipoPlanCuenta tipo) {
        return this.entityManager.createNamedQuery("PlanCuentaSubTipo.findByTipoPlan").setParameter("planCuentaSubTipoIdTipoPlan", tipo).getResultList();
    }

}
