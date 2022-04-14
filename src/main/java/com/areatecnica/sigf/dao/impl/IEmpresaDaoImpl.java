/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Empresa;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEmpresaDaoImpl extends GenericDAOImpl<Empresa> implements IEmpresaDao<Empresa> {

    public IEmpresaDaoImpl() {
        super(Empresa.class);
    }

    @Override
    public Empresa findById(int id) {
        try {
            return (Empresa) this.entityManager.createNamedQuery("Empresa.findByEmpresaId").setParameter("empresaId", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Empresa> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("Empresa.findAllByCuenta").setParameter("idCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public List<Empresa> findByNandu() {
        try {
            return this.entityManager.createNamedQuery("Empresa.findByNandu").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
