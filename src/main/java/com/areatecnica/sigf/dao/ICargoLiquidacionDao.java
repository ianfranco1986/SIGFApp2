/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICargoLiquidacionDao<T> extends IGenericDAO<T> {

    public CargoLiquidacion findById(int id);
    
    public CargoLiquidacion findByLiquidacion(LiquidacionEmpresa liquidacion);

    public List<CargoLiquidacion> findByEmpresaBetweenDates(Empresa empresa, Date desde, Date hasta);
    
}
