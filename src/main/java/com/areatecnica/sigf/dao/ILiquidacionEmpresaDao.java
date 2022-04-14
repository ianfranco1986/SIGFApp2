/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import java.util.Date;

/**
 *
 * @author ianfr
 */
public interface ILiquidacionEmpresaDao<T> extends IGenericDAO<T> {

    public LiquidacionEmpresa findByEmpresaBetweenDate(Empresa empresa, Date from, Date to);

}
