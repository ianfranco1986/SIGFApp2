/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IDetalleCartolaDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;
import com.areatecnica.sigf.entities.Empresa;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DetalleCartolaDaoImpl extends GenericDAOImpl<DetalleCartola> implements IDetalleCartolaDao<DetalleCartola> {

    public DetalleCartolaDaoImpl() {
        super(DetalleCartola.class);
    }

    @Override
    public List<DetalleCartola> findByEmpresa(Empresa empresa, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CartolaBanco.findByEmpresaBetweenDates").
                    setParameter("cuentaBancariaIdEmpresa", empresa).
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<DetalleCartola> findByCuentaBancariaBetweenDates(CuentaBancaria cuentaBancaria, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CartolaBanco.findByCuentaBancoBetweenDates").
                    setParameter("cartolaBancoIdCuentaBancaria", cuentaBancaria).
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<DetalleCartola> findLastByCuentaBancaria(CuentaBancaria cuenta) {
        return this.entityManager.
                createNamedQuery("DetalleCartola.findByCuenta").
                setParameter("cartolaBancoIdCuentaBancaria", cuenta).
                setMaxResults(1).
                getResultList();
    }

    @Override
    public DetalleCartola fingByCuentaBancoFecha(CuentaBancaria cuenta, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DetalleCartola> findAllByCuenta(Cuenta cuenta) {
        return this.entityManager.
                createNamedQuery("DetalleCartola.findAllByCuenta").
                setParameter("", cuenta).
                getResultList();
    }

}
