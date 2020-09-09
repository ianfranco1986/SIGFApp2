/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IPrivilegioDao;
import com.areatecnica.sigf.entities.Privilegio;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IPrivilegioDaoImpl extends GenericDAOImpl<Privilegio> implements IPrivilegioDao<Privilegio> {

    @Override
    public Privilegio findById(int id) {
        try {
            return (Privilegio) this.entityManager.createNamedQuery("Privilegio.findByPrivilegioId").setParameter("privilegioId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
