/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICompraBoletoDao;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.Cuenta;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class CompraBoletoDaoImpl extends GenericDAOImpl<CompraBoleto> implements ICompraBoletoDao<CompraBoleto> {

    public CompraBoletoDaoImpl() {
        super(CompraBoleto.class);
    }

    @Override
    public List<CompraBoleto> findAll() {
        try {
            return this.entityManager.createNamedQuery("CompraBoleto.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CompraBoleto> findByFecha(Date fecha, Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("CompraBoleto.findByCompraBoletoFecha").setParameter("compraBoletoIdCuenta", cuenta).setParameter("compraBoletoFecha", fecha).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CompraBoleto findByFolioFactura(int folio) {
        try {
            return (CompraBoleto) this.entityManager.createNamedQuery("CompraBoleto.findByCompraBoletoFolioFactura").setParameter("compraBoletoFolioFactura", folio).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CompraBoleto> findAllBy(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("CompraBoleto.findByCompraBoletoIdCuenta").setParameter("compraBoletoIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
