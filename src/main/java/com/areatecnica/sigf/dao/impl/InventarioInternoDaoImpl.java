/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IInventarioInternoDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InventarioInterno;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class InventarioInternoDaoImpl extends GenericDAOImpl<InventarioInterno> implements IInventarioInternoDao<InventarioInterno> {

    public InventarioInternoDaoImpl() {
        super(InventarioInterno.class);
    }

    @Override
    public List<InventarioInterno> findByBoletoEstado(Boleto boleto, Boolean estado) {
        try {
            return this.entityManager.createNamedQuery("InventarioInterno.findByInventarioInternoBoletoEstado").setParameter("inventarioInternoIdBoleto", boleto).setParameter("inventarioInternoEstado", estado).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<InventarioInterno> findByEstado(Boolean estado, Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("InventarioInterno.findByInventarioInternoEstado").setParameter("inventarioInternoEstado", estado).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
