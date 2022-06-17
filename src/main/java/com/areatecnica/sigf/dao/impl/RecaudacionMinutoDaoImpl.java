/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRecaudacionMinutoDao;
import com.areatecnica.sigf.entities.*;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class RecaudacionMinutoDaoImpl extends GenericDAOImpl<RecaudacionMinuto> implements IRecaudacionMinutoDao<RecaudacionMinuto> {

    @Override
    public List<RecaudacionMinuto> findByDate(Date fecha) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionMinuto.findByFechaRecaudacionMinuto").
                    setParameter("recaudacionFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionMinuto.findByFechaRecaudacionMinutoCaja").
                    setParameter("recaudacionIdCaja", cajaRecaudacion).
                    setParameter("recaudacionFecha", fechaVenta).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findByBusAndDate(Bus bus, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionMinuto.findByBusAndDate").
                    setParameter("recaudacionFecha", from).
                    setParameter("recaudacionIdBus", bus).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findRecibidosBusAndDate(Bus bus, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionMinuto.findRecibidosBusFechas").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("registroMinutoHastaIdBus", bus).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findByDefaultBus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecaudacionMinuto findByBus(Bus bus, Boleto boleto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecaudacionMinuto> findByBusSinRecaudar(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleBusRecaudado").
                    setParameter("ventaCombustibleIdBus", bus).
                    setParameter("ventaCombustibleRecaudado", Boolean.FALSE).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findByTerminalDate(Terminal terminal, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionMinuto.findByTerminalDate").
                    setParameter("busIdTerminal", terminal).
                    setParameter("recaudacionFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionMinuto> findTodos() {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findAll").
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public int findLastNumeroBoleta(Terminal terminal) {
        try {

            return (int) entityManager.createQuery("SELECT MAX(v.ventaCombustibleNumeroBoleta) FROM VentaCombustible v where v.ventaCombustibleIdSurtidor.surtidorIdTerminal = :idTerminal")
                    .setParameter("idTerminal", terminal)
                    .getSingleResult() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    public int findMinutosRecibidos(Empresa empresa, Date from, Date to) {
        try {// FROM RecaudacionMinuto r WHERE r.recaudacionMinutoIdRegistroMinuto.registroMinutoHastaIdBus = :registroMinutoHastaIdBus AND r.recaudacionMinutoIdRecaudacion.recaudacionFecha BETWEEN :from AND :to

            BigDecimal aux = (BigDecimal) entityManager.createQuery("SELECT COALESCE(SUM(r.recaudacionMinutoMonto), 0) "
                    + "FROM  RecaudacionMinuto r "
                    + "WHERE r.recaudacionMinutoIdRegistroMinuto.registroMinutoHastaIdBus.busIdEmpresa = :empresa "
                    + "AND r.recaudacionMinutoIdRecaudacion.recaudacionFecha "
                    + "BETWEEN :from AND :to").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("empresa", empresa)
                    .getSingleResult();
            int xua = aux.intValue();
            return xua;
        } catch (NoResultException ne) {
            return 0;
        }

    }

}
