/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.TipoAbono;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IAbonoLiquidacionDao<T> extends IGenericDAO<T> {

    public AbonoLiquidacion findById(int id);

    public AbonoLiquidacion findByLiquidacion(LiquidacionEmpresa liquidacion);

    List<AbonoLiquidacion> findByEmpresaBetweenDates(Empresa empresa, Date desde, Date hasta);

    AbonoLiquidacion findByEmpresaTipoBetweenDates(Empresa empresa, TipoAbono abono, Date desde, Date hasta);

}
