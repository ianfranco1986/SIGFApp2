/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IVentaBoletoDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author ianfr
 */
public class IVentaBoletoDaoImpl extends GenericDAOImpl<VentaBoleto> implements IVentaBoletoDao<VentaBoleto> {

    public IVentaBoletoDaoImpl() {
        super(VentaBoleto.class);
    }

    @Override
    public List<VentaBoleto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdCajaDate").
                    setParameter("inventarioCajaIdCaja", cajaRecaudacion).
                    setParameter("ventaBoletoFecha", fechaVenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<VentaBoleto> findByBus(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdGuiaBus").setParameter("ventaBoletoIdBus", bus).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<VentaBoleto> findByBusEstado(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdGuiaBusEstado").setParameter("ventaBoletoIdBus", bus).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<VentaBoleto> findByDefaultBus() {
        try {
            return this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoDefaultIdGuia").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public VentaBoleto findByBusBoletoEstado(Bus bus, Boleto boleto) {
        try {
            return (VentaBoleto) this.entityManager.createNamedQuery("VentaBoleto.findByVentaBoletoIdGuiaBusBoletoEstado").
                    setParameter("ventaBoletoIdBus", bus).
                    setParameter("inventarioInternoIdBoleto", boleto).
                    setMaxResults(1).
                    getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public VentaBoleto findBySerie(int serie) {
        try {

//            Query query = this.entityManager.createNativeQuery("SELECT * FROM ventaBoleto v LEFT JOIN inventario_caja c ON v.venta_boleto_id_inventario_caja = c.inventario_caja_id WHERE :inventarioCajaSerie BETWEEN c.inventario_caja_serie AND (c.inventario_caja_serie + 1000)", VentaBoleto.class).setParameter("inventarioCajaSerie", serie);
            return (VentaBoleto) this.entityManager.createNamedQuery("VentaBoleto.findBySerie").
                    setParameter("inventarioCajaSerie", serie).
                    setMaxResults(1).
                    getSingleResult();

//            return (VentaBoleto) query.getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
