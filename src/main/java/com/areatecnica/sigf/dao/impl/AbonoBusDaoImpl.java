/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IAbonoBusDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class AbonoBusDaoImpl extends GenericDAOImpl<AbonoBus> implements IAbonoBusDao<AbonoBus> {

    public AbonoBusDaoImpl() {
        super(AbonoBus.class);
    }

    @Override
    public List<AbonoBus> findLast() {
        try {
            return this.entityManager.createNamedQuery("AbonoBus.findLast").
                    setMaxResults(50).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByDate(Date from) {
        try {
            return this.entityManager.createNamedQuery("AbonoBus.findByAbonoBusFechaInicio").
                    setParameter("abonoBusFechaInicio", from).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByTipoAbonoDate(TipoAbono tipoAbono, Date from) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByAbonoBusFechaInicio").
                    setParameter("abonoBusFechaInicio", from).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findBetweenDates(Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByTipoAbonoBetweenDates(TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByTipoAbonoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByBusBetweenDates(Bus bus, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByBusBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busId", bus).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByFlotaBetweenDates(Flota flota, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByFlotaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdFlota", flota).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByProcesoRecaudacionBetweenDates(ProcesoRecaudacion proceso, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByProcesoRecaudacionBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdProcesoRecaudacion", proceso).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByTerminalBetweenDates(Terminal terminal, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdTerminal", terminal).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByEmpresaBetweenDates(Empresa empresa, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByEmpresaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdEmpresa", empresa).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByUnidadNegocioBetweenDates(UnidadNegocio unidad, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByUnidadNegocioBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdUnidadNegocio", unidad).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByBusTipoAbonoBetweenDates(Bus bus, TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByBusTipoAbonoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("abonoBusIdBus", bus).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByEmpresaTipoAbonoBetweenDates(Empresa empresa, TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByEmpresaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdEmpresa", empresa).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByFlotaTipoAbonoBetweenDates(Flota flota, TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByFlotaTipoAbonoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdFlota", flota).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByTerminalTipoAbonoBetweenDates(Terminal terminal, TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdTerminal", terminal).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<AbonoBus> findByProcesoRecaudacionTipoAbonoBetweenDates(ProcesoRecaudacion proceso, TipoAbono tipoAbono, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("AbonoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdProcesoRecaudacion", proceso).
                    setParameter("abonoBusIdTipoAbono", tipoAbono).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public AbonoBus findByBus(Bus bus) {
        try {
            return (AbonoBus) this.entityManager.createNamedQuery("Caja.findByCajaProcesoIdCaja").setParameter("cajaProcesoIdCaja", bus).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
