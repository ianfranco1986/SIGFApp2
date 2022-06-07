/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICuentaUnicaDao;
import com.areatecnica.sigf.entities.CuentaUnica;
import java.util.List;

import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class CuentaUnicaDaoImpl extends GenericDAOImpl<CuentaUnica> implements ICuentaUnicaDao<CuentaUnica> {

    @Override
    public CuentaUnica findById(int id) {
        try {
            return (CuentaUnica) this.entityManager.createNamedQuery("CuentaUnica.findByCuentaUnicaId").setParameter("privilegioId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CuentaUnica> find() {
        return this.entityManager.createNamedQuery("CuentaUnica.findAll").getResultList();
    }

    @Override
    public List<CuentaUnica> findAllActivated() {
        return this.entityManager.createNamedQuery("CuentaUnica.findAllActivated").getResultList();
    }

    @Override
    public List<CuentaUnica> findAllDeactivated() {
        return this.entityManager.createNamedQuery("CuentaUnica.findAllDeactivated").getResultList();
    }

}
