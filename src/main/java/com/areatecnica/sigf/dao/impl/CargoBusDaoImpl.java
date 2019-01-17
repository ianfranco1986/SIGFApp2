/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICargoBusDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class CargoBusDaoImpl extends GenericDAOImpl<CargoBus> implements ICargoBusDao<CargoBus> {

    public CargoBusDaoImpl() {
        super(CargoBus.class);
    }

    @Override
    public List<CargoBus> findLast() {
        try {
            return this.entityManager.createNamedQuery("CargoBus.findLast").
                    setMaxResults(50).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByDate(Date from) {
        try {
            return this.entityManager.createNamedQuery("CargoBus.findByCargoBusFechaInicio").
                    setParameter("cargoBusFechaInicio", from).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByTipoCargoDate(TipoCargo tipoCargo, Date from) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByCargoBusFechaInicio").
                    setParameter("cargoBusFechaInicio", from).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findBetweenDates(Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByTipoCargoBetweenDates(TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByTipoCargoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByBusBetweenDates(Bus bus, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByBusBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busId", bus).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByFlotaBetweenDates(Flota flota, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByFlotaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdFlota", flota).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByProcesoRecaudacionBetweenDates(ProcesoRecaudacion proceso, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByProcesoRecaudacionBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdProcesoRecaudacion", proceso).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByTerminalBetweenDates(Terminal terminal, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdTerminal", terminal).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByEmpresaBetweenDates(Empresa empresa, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByEmpresaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdEmpresa", empresa).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByUnidadNegocioBetweenDates(UnidadNegocio unidad, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByUnidadNegocioBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdUnidadNegocio", unidad).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByBusTipoCargoBetweenDates(Bus bus, TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByBusTipoCargoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("cargoBusIdBus", bus).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByEmpresaTipoCargoBetweenDates(Empresa empresa, TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByEmpresaBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdEmpresa", empresa).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByFlotaTipoCargoBetweenDates(Flota flota, TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByFlotaTipoCargoBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdFlota", flota).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByTerminalTipoCargoBetweenDates(Terminal terminal, TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdTerminal", terminal).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<CargoBus> findByProcesoRecaudacionTipoCargoBetweenDates(ProcesoRecaudacion proceso, TipoCargo tipoCargo, Date from, Date to) {
        try {
            return this.entityManager.
                    createNamedQuery("CargoBus.findByTerminalBetweenDates").
                    setParameter("from", from).
                    setParameter("to", to).
                    setParameter("busIdProcesoRecaudacion", proceso).
                    setParameter("cargoBusIdTipo", tipoCargo).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CargoBus findByBus(Bus bus) {
        try {
            return (CargoBus) this.entityManager.createNamedQuery("Caja.findByCajaProcesoIdCaja").setParameter("cajaProcesoIdCaja", bus).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
