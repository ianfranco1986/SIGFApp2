/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICartolaBancoDao;
import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.Empresa;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class CartolaBancoDaoImpl extends GenericDAOImpl<CartolaBanco> implements ICartolaBancoDao<CartolaBanco> {

    @Override
    public List<CartolaBanco> findByEmpresa(Empresa empresa, Date from, Date to) {
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
    public List<CartolaBanco> findByCuentaBancariaBetweenDates(CuentaBancaria cuentaBancaria, Date from, Date to) {
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
    public CartolaBanco fingByCuentaBancoFecha(CuentaBancaria cuenta, Date from, Date to) {
        return (CartolaBanco) this.entityManager.
                createNamedQuery("CartolaBanco.findByCuentaBancoFecha").
                setParameter("cartolaBancoIdCuentaBancaria", cuenta).
                setParameter("from", from).
                setParameter("to", to).
                setMaxResults(1).
                getSingleResult();
    }

}
