/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.*;

import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IBusDao<T> extends IGenericDAO<T> {

    int findMaxNumeroUnidad(UnidadNegocio unidadNegocio);

    List<Bus> findByUnidad(UnidadNegocio unidadNegocio);

    List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion);

    List<Bus> findByGrupoServicio(GrupoServicio grupoServicio);

    List<Bus> findAllByTerminal(Terminal terminal);
    
    List<Bus> findByEmpresa(Empresa empresa);
    
    List<Bus> findByEmpresaUnidad(Empresa empresa, UnidadNegocio unidad);

    List<Bus> findAllByFlota(Flota flota);

    List<Bus> findAllByFlotaUnidad(Flota flota, UnidadNegocio negocio);

    Bus findDefaultBus(EstadoBus estadoBus);
}
