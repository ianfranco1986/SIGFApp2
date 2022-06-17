/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEgresoDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Egreso;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class EgresoDaoImpl extends GenericDAOImpl<Egreso> implements IEgresoDao<Egreso> {

    @Override
    public List<Egreso> findAllByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("Egreso.findAllByCuenta").setParameter("idCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Egreso> findAllActived() {
        try {
            return this.entityManager.createNamedQuery("Egreso.findAllActived").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public Egreso getBoleto() {
        return (Egreso) this.entityManager.createNamedQuery("Egreso.findByEgresoId").setParameter("egresoId", 4).getSingleResult();
    }

}
