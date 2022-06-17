/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IControlVentaPetroleoDao;
import com.areatecnica.sigf.entities.ControlVentaPetroleo;
import com.areatecnica.sigf.entities.Cuenta;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class ControlVentaPetroleoDaoImpl extends GenericDAOImpl<ControlVentaPetroleo> implements IControlVentaPetroleoDao<ControlVentaPetroleo> {

    public ControlVentaPetroleoDaoImpl() {
        super(ControlVentaPetroleo.class);
    }

    @Override
    public ControlVentaPetroleo findById(int id) {
        return (ControlVentaPetroleo) this.entityManager.createNamedQuery("ControlVentaPetroleo.findByCuenta").setParameter("id", id).getSingleResult();
    }
    
    @Override
    public List<ControlVentaPetroleo> findByDates(Date from, Date to, Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("ControlVentaPetroleo.findByDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("idCuenta", cuenta).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
