/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionExtra;
import com.areatecnica.sigf.entities.Terminal;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionExtraDao<T> extends IGenericDAO<T> {

    List<RecaudacionExtra> findByDate(Date fecha);

    List<RecaudacionExtra> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    List<RecaudacionExtra> findByTerminalDate(Terminal terminal, Date fecha);

}
