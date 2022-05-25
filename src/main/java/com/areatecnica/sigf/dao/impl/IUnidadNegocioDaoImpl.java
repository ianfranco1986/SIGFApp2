/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IUnidadNegocioDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.UnidadNegocio;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class IUnidadNegocioDaoImpl extends GenericDAOImpl<UnidadNegocio> implements IUnidadNegocioDao<UnidadNegocio> {

    public IUnidadNegocioDaoImpl() {
        super(UnidadNegocio.class);
    }

    @Override
    public UnidadNegocio findById(int idUnidadNegocio) {
        try {
            return (UnidadNegocio) this.entityManager.createNamedQuery("UnidadNegocio.findByUnidadNegocioId").setParameter("unidadNegocioId", idUnidadNegocio).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<UnidadNegocio> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("UnidadNegocio.findAllByCuenta").setParameter("idCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<UnidadNegocio> findNandu() {
        try {
            return this.entityManager.createNamedQuery("UnidadNegocio.findNandu").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
