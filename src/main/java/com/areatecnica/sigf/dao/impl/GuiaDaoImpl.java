/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IGuiaDao;
import com.areatecnica.sigf.entities.*;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class GuiaDaoImpl extends GenericDAOImpl<Guia> implements IGuiaDao<Guia> {

    public GuiaDaoImpl() {
        super(Guia.class);
    }

    @Override
    public List<Guia> findByProcesoFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByProcesoFechaRecaudacion").setParameter("busIdProcesoRecaudacion", procesoRecaudacion).setParameter("guiaRecaudacion", fechaRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Guia> findByProcesoFechaGuia(ProcesoRecaudacion procesoRecaudacion, Date fechaGuia) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByProcesoFechaGuia").setParameter("busIdProcesoRecaudacion", procesoRecaudacion).setParameter("guiaFecha", fechaGuia).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Guia findByCuentaFolio(Cuenta cuenta, int folio) {
        try {
            return (Guia) this.entityManager.createNamedQuery("Guia.findByCuentaFolio").setParameter("guiaIdCuenta", cuenta).setParameter("guiaFolio", folio).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Guia> findByBusFecha(Bus bus, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByBusFecha").setParameter("guiaIdBus", bus).setParameter("guiaFecha", fecha).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Guia> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByBusBetweenFechaRecaudacion").setParameter("guiaIdBus", bus).setParameter("inicio", inicio).setParameter("termino", inicio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    public List<Guia> findBetweenFechaRecaudacion(Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("Guia.findBetweenDates").
                    setParameter("inicio", from).
                    setParameter("termino", to).getResultList();
        } catch (NoResultException ne) {
            return new ArrayList();
        }
    }

    @Override
    public List<Guia> findByBusBetweenFecha(Bus bus, Date inicio, Date termino) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByGuiaBetweenDate").setParameter("guiaIdBus", bus).setParameter("inicio", inicio).setParameter("termino", termino).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Guia> findByCuentaFecha(Cuenta cuenta, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByCuentaFecha").setParameter("guiaIdCuenta", cuenta).setParameter("guiaFecha", fecha).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Guia findLastGuiaByBusFecha(Bus bus, Date fecha) {
        try {
            return (Guia) this.entityManager.createNamedQuery("Guia.findLastGuiaByBusFecha").setParameter("guiaIdBus", bus).setParameter("guiaFecha", fecha).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Guia findLastGuiaByBusEqualsFecha(Bus bus, Date fecha) {
        try {
            return (Guia) this.entityManager.createNamedQuery("Guia.findLastGuiaByBusFecha").setParameter("guiaIdBus", bus).setParameter("guiaFecha", fecha).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Guia> findByFechaGrupoServicio(GrupoServicio grupoServicio, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByFechaGrupoServicio").setParameter("grupoServicioId", grupoServicio).setParameter("guiaFecha", fecha).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public void delete(Guia guia) {

    }

    @Override
    public int findLastFolio(Terminal terminal) {
        try {

            return (int) entityManager.createQuery("SELECT MAX(g.guiaFolio) FROM  Guia g where g.guiaIdBus.busIdTerminal = :busIdTerminal")
                    .setParameter("busIdTerminal", terminal)
                    .getSingleResult() + 1;

        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public List<Guia> findByBusPendientes(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("Guia.findByBusFechaPendiente").setParameter("guiaIdBus", bus).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
