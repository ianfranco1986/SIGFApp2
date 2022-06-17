/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IClienteDao;
import com.areatecnica.sigf.entities.Cliente;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ClienteDaoImpl extends GenericDAOImpl<Cliente> implements IClienteDao<Cliente> {

    public ClienteDaoImpl() {
        super(Cliente.class);
    }

    @Override
    public Cliente findByRut(String rut) {
        try {
            return (Cliente) this.entityManager.createNamedQuery("Cliente.findByClienteRut").setParameter("clienteRut", rut).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
