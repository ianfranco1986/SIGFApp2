/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ILoginAdministradorDao;
import com.areatecnica.sigf.entities.Administrador;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ILoginAdministradorDaoImpl extends GenericDAOImpl<Administrador> implements ILoginAdministradorDao<Administrador>{

    private static final long serialVersionUID = 1602695723923335094L;

    public ILoginAdministradorDaoImpl() {
        super(Administrador.class);
    }
    
    @Override
    public Administrador login(String rut, String pass) {
        try {
            return (Administrador) this.entityManager.createNamedQuery("Administrador.findByUsuarioRutAndPass").setParameter("administradorRut", rut).setParameter("administradorPass", pass).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
