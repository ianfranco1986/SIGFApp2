/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ICuentaMayorDao;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;

import java.util.List;

/**
 *
 * @author ianfr
 */
public class ICuentaMayorDaoImpl extends GenericDAOImpl<CuentaMayor> implements ICuentaMayorDao<CuentaMayor> {

    public ICuentaMayorDaoImpl() {
        super(CuentaMayor.class);
    }

    @Override
    public List<CuentaMayor> findALL() {
        return this.entityManager.createNamedQuery("CuentaMayor.findAll").getResultList();
    }

    @Override
    public List<CuentaMayor> findBySubTipo(PlanCuentaSubTipo cuentaSubTipo) {
        return this.entityManager.createNamedQuery("CuentaMayor.findBySubTipo").setParameter("cuentaMayorSubTipoId", cuentaSubTipo).getResultList();
    }

    public List<CuentaMayor> findByCompras(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorCompras").getResultList();
    }
    
    public List<CuentaMayor> findByHonorarios(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorHonorarios").getResultList();
    }
    
    public List<CuentaMayor> findByActivosFijos(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorActivosFijos").getResultList();
    }
    
    public List<CuentaMayor> findByBanco(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorBanco").getResultList();
    }
    
    public List<CuentaMayor> findByPresupuesto(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorPresupuesto").getResultList();
    }
    
    public List<CuentaMayor> findByRemuneraciones(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorRemuneraciones").getResultList();
    }
    
    public List<CuentaMayor> findByVentas(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorVentas").getResultList();
    }
    
    public List<CuentaMayor> findByTesoreria(){
        return this.entityManager.createNamedQuery("CuentaMayor.findByCuentaMayorTesoreria").getResultList();
    }
    
    public boolean remove(CuentaMayor c) {
        if (c != null) {

            long compras = new CompraDaoImpl().countByCuentaMayor(c);

            if (compras > 0) {
                JsfUtil.addErrorMessage("ATENCIÓN: No se puede eliminar la Cuenta, ya que posee movimientos (compras: "+compras+")");
                return false;
            }

            try {
                beginTransaction();
                c = this.entityManager.merge(c);
                this.entityManager.remove(c);
                commit();
                return true;
            } catch (Exception e) {
                JsfUtil.addErrorMessage("ATENCIÓN: No se puede eliminar la Cuenta, ya que posee movimientos " + e.getLocalizedMessage());
                rollback();
            }
        }
        return false;
    }

}
