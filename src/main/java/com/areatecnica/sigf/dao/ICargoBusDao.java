/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

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

/**
 *
 * @author ianfr
 */
public interface ICargoBusDao<T> extends IGenericDAO<T> {

    public List<CargoBus> findLast();
    
    public List<CargoBus> findByDate(Date from);

    public List<CargoBus> findByTipoCargoDate(TipoCargo tipoCargo, Date from);

    public List<CargoBus> findBetweenDates(Date from, Date to);

    public List<CargoBus> findByTipoCargoBetweenDates(TipoCargo tipoCargo, Date from, Date to);

    public List<CargoBus> findByBusBetweenDates(Bus bus, Date from, Date to);

    public List<CargoBus> findByFlotaBetweenDates(Flota flota, Date from, Date to);

    public List<CargoBus> findByProcesoRecaudacionBetweenDates(ProcesoRecaudacion proceso, Date from, Date to);

    public List<CargoBus> findByTerminalBetweenDates(Terminal terminal, Date from, Date to);

    public List<CargoBus> findByEmpresaBetweenDates(Empresa empresa, Date from, Date to);

    public List<CargoBus> findByUnidadNegocioBetweenDates(UnidadNegocio unidad, Date from, Date to);

    public List<CargoBus> findByBusTipoCargoBetweenDates(Bus bus, TipoCargo tipoCargo, Date from, Date to);

    public List<CargoBus> findByEmpresaTipoCargoBetweenDates(Empresa empresa, TipoCargo tipoCargo, Date from, Date to);

    public List<CargoBus> findByFlotaTipoCargoBetweenDates(Flota flota, TipoCargo tipoCargo, Date from, Date to);

    public List<CargoBus> findByTerminalTipoCargoBetweenDates(Terminal terminal, TipoCargo tipoCargo, Date from, Date to);

    public List<CargoBus> findByProcesoRecaudacionTipoCargoBetweenDates(ProcesoRecaudacion proceso, TipoCargo tipoCargo, Date from, Date to);

    public CargoBus findByBus(Bus bus);

}
