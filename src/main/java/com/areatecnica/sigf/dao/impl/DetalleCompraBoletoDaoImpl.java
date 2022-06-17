/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IDetalleCompraBoletoDao;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class DetalleCompraBoletoDaoImpl extends GenericDAOImpl<DetalleCompraBoleto> implements IDetalleCompraBoletoDao<DetalleCompraBoleto>  {

    @Override
    public List<DetalleCompraBoleto> findByCompraBoleto(CompraBoleto compraBoleto) {
        try {
            return this.entityManager.createNamedQuery("DetalleCompraBoleto.findByDetalleCompraBoletoIdCompraBoleto").setParameter("detalleCompraBoletoIdCompraBoleto", compraBoleto).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    
}
