/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Usuario;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class CajaRecaudacionDaoImpl extends GenericDAOImpl<CajaRecaudacion> implements ICajaRecaudacionDao<CajaRecaudacion> {

    public CajaRecaudacionDaoImpl() {
        super(CajaRecaudacion.class);
    }

    @Override
    public List<CajaRecaudacion> findAllByUser(Usuario usuario) {
        try {
            return this.entityManager.createNamedQuery("CajaRecaudacion.findAllByCuenta").setParameter("cajaRecaudacionIdUsuario", usuario).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public List<CajaRecaudacion> findAllActive() {
        try {
            return this.entityManager.createNamedQuery("CajaRecaudacion.findAllActive").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CajaRecaudacion create(CajaRecaudacion t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public CajaRecaudacion update(CajaRecaudacion t) {
        return this.entityManager.merge(t);
    }

    @Override
    public CajaRecaudacion findById(int id) {
        try {
            return (CajaRecaudacion) this.entityManager.createNamedQuery("CajaRecaudacion.findByCajaRecaudacionId").setParameter("cajaRecaudacionId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
