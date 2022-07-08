/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IProcesoRecaudacionDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ProcesoRecaudacionDaoImpl extends GenericDAOImpl<ProcesoRecaudacion> implements IProcesoRecaudacionDao<ProcesoRecaudacion> {

    @Override
    public List<ProcesoRecaudacion> findAllByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("ProcesoRecaudacion.findByProcesoRecaudacionIdCuenta").setParameter("procesoRecaudacionIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public List<ProcesoRecaudacion> findByNandu() {
        try {
            return this.entityManager.createNamedQuery("ProcesoRecaudacion.findByNandu").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public ProcesoRecaudacion findById(int id) {
        return (ProcesoRecaudacion) this.entityManager.createNamedQuery("ProcesoRecaudacion.findByProcesoRecaudacionId").setParameter("procesoRecaudacionId", id).getSingleResult();
    }

}
