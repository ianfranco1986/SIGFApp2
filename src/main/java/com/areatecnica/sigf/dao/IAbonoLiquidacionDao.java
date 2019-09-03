/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;

/**
 *
 * @author ianfr
 */
public interface IAbonoLiquidacionDao<T> extends IGenericDAO<T> {

    public AbonoLiquidacion findByLiquidacion(LiquidacionEmpresa liquidacion);

}
