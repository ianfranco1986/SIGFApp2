/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.MovimientoMes;
import com.areatecnica.sigf.entities.TipoMovimiento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IMovimientoMesDao<T> extends IGenericDAO<T> {

    public List<MovimientoMes> findByDates(Date from, Date to);
    
    public List<MovimientoMes> findByIngresosDates(Date from, Date to);
    
    public List<MovimientoMes> findByEgresosDates(Date from, Date to);

    public List<MovimientoMes> findByDocumento(int documento);

    public List<MovimientoMes> findByEmpresaAndDates(Empresa empresa, Date from, Date to);

    public List<MovimientoMes> findByTipoAndDates(TipoMovimiento tipo, Date from, Date to);

    public List<MovimientoMes> findByCuentaAndDates(CuentaBancaria cuenta, Date from, Date to);

    public MovimientoMes findLastByCuenta(CuentaBancaria cuenta);
}
