/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

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

/**
 *
 * @author ianfr
 */
public interface IAbonoBusDao<T> extends IGenericDAO<T> {

    public List<AbonoBus> findLast();
    
    public List<AbonoBus> findByDate(Date from);

    public List<AbonoBus> findByTipoAbonoDate(TipoAbono tipoAbono, Date from);

    public List<AbonoBus> findBetweenDates(Date from, Date to);

    public List<AbonoBus> findByTipoAbonoBetweenDates(TipoAbono tipoAbono, Date from, Date to);

    public List<AbonoBus> findByBusBetweenDates(Bus bus, Date from, Date to);

    public List<AbonoBus> findByFlotaBetweenDates(Flota flota, Date from, Date to);

    public List<AbonoBus> findByProcesoRecaudacionBetweenDates(ProcesoRecaudacion proceso, Date from, Date to);

    public List<AbonoBus> findByTerminalBetweenDates(Terminal terminal, Date from, Date to);

    public List<AbonoBus> findByEmpresaBetweenDates(Empresa empresa, Date from, Date to);

    public List<AbonoBus> findByUnidadNegocioBetweenDates(UnidadNegocio unidad, Date from, Date to);

    public List<AbonoBus> findByBusTipoAbonoBetweenDates(Bus bus, TipoAbono tipoAbono, Date from, Date to);

    public List<AbonoBus> findByEmpresaTipoAbonoBetweenDates(Empresa empresa, TipoAbono tipoAbono, Date from, Date to);

    public List<AbonoBus> findByFlotaTipoAbonoBetweenDates(Flota flota, TipoAbono tipoAbono, Date from, Date to);

    public List<AbonoBus> findByTerminalTipoAbonoBetweenDates(Terminal terminal, TipoAbono tipoAbono, Date from, Date to);

    public List<AbonoBus> findByProcesoRecaudacionTipoAbonoBetweenDates(ProcesoRecaudacion proceso, TipoAbono tipoAbono, Date from, Date to);

    public AbonoBus findByBus(Bus bus);

}
