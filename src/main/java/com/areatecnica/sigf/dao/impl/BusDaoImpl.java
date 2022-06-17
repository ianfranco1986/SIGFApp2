/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IBusDao;
import com.areatecnica.sigf.entities.*;

import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class BusDaoImpl extends GenericDAOImpl<Bus> implements IBusDao<Bus> {

    public BusDaoImpl() {
        super(Bus.class);
    }

    @Override
    public List<Bus> findAll() {
        try {
            return this.entityManager.createNamedQuery("Bus.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public int findMaxNumeroUnidad(UnidadNegocio unidadNegocio) {
        try {
            Bus bus = (Bus) this.entityManager.createNamedQuery("Bus.findMaxNumeroUnidad").setParameter("busIdUnidadNegocio", unidadNegocio).setMaxResults(1).getSingleResult();
            return bus.getBusNumero() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public Bus findDefaultBus(EstadoBus estadoBus) {
        try {
            Bus bus = (Bus) this.entityManager.
                    createNamedQuery("Bus.findDefaultBus").
                    setParameter("busIdEstadoBus", estadoBus).
                    getSingleResult();
            return bus;
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByEmpresa(Empresa empresa) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByEmpresa").setParameter("busIdEmpresa", empresa).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByEmpresaUnidad(Empresa empresa, UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.
                    createNamedQuery("Bus.findByEmpresaUnidad").
                    setParameter("busIdEmpresa", empresa).
                    setParameter("busIdUnidadNegocio", unidadNegocio).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByUnidad(UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByBusIdUnidadNegocio").setParameter("busIdUnidadNegocio", unidadNegocio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByProcesoRecaudacion").setParameter("busIdProcesoRecaudacion", procesoRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByGrupoServicio(GrupoServicio grupoServicio) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByGrupoServicio").setParameter("busIdGrupoServicio", grupoServicio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findAllByTerminal(Terminal terminal) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByTerminal").setParameter("busIdTerminal", terminal).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findAllByFlota(Flota flota) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByFlota").setParameter("busIdFlota", flota).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findAllByFlotaUnidad(Flota flota, UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.
                    createNamedQuery("Bus.findByBusUnidadNegocio").
                    setParameter("busIdFlota", flota).
                    setParameter("busIdUnidadNegocio", unidadNegocio).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
