/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICuentaBancariaDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.CuentaBancaria;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ICuentaBancariaDaoImpl extends GenericDAOImpl<CuentaBancaria> implements ICuentaBancariaDao<CuentaBancaria> {

    public ICuentaBancariaDaoImpl() {
        super(CuentaBancaria.class);
    }

    @Override
    public List<CuentaBancaria> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("CuentaBancaria.findByCuenta").setParameter("empresaIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CuentaBancaria findById(int id) {
        try {
            CuentaBancaria c = (CuentaBancaria) this.entityManager.
                    createNamedQuery("CuentaBancaria.findByCuentaBancariaId").
                    setParameter("cuentaBancariaId", id).
                    getSingleResult();
            return c;
        } catch (NoResultException ne) {
            return null;
        }
    }

}
