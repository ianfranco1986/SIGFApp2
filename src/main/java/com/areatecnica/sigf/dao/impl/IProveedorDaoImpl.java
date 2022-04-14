/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IProveedorDao;
import com.areatecnica.sigf.entities.Proveedor;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IProveedorDaoImpl extends GenericDAOImpl<Proveedor> implements IProveedorDao<Proveedor> {

    public IProveedorDaoImpl() {
        super(Proveedor.class);
    }

    @Override
    public Proveedor findByRut(String rut) {
        try {
            return (Proveedor) this.entityManager.
                    createNamedQuery("Proveedor.findByProveedorRut").
                    setParameter("proveedorRut", rut).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
//
//    public List<Proveedor> findByNandu() {
//        try {
//            return this.entityManager.createNamedQuery("Proveedor.findByNandu").getResultList();
//        } catch (NoResultException ne) {
//            return null;
//        }
//    }

}
