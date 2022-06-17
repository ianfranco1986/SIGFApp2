/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IInventarioCajaDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InventarioCaja;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class InventarioCajaDaoImpl extends GenericDAOImpl<InventarioCaja> implements IInventarioCajaDao<InventarioCaja> {

    public InventarioCajaDaoImpl() {
        super(InventarioCaja.class);
    }

    @Override
    public List<InventarioCaja> findByBoletoEstado(CajaRecaudacion cajaRecaudacion, Boleto boleto, Boolean estado) {
        try {
            return this.entityManager.createNamedQuery("InventarioCaja.findByInventarioCajaBoletoEstadoCaja").
                    setParameter("inventarioCajaIdCaja", cajaRecaudacion).
                    setParameter("inventarioCajaEstado", estado).
                    setParameter("inventarioInternoIdBoleto", boleto).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<InventarioCaja> findByEstado(Boolean estado, Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("InventarioCaja.findByInventarioCajaEstado").
                    setParameter("inventarioCajaEstado", estado).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
