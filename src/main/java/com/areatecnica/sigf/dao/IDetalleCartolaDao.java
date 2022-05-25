/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;
import com.areatecnica.sigf.entities.Empresa;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IDetalleCartolaDao<T> extends IGenericDAO<T> {

    List<DetalleCartola> findByEmpresa(Empresa empresa, Date from, Date to);

    List<DetalleCartola> findAllByCuenta(Cuenta cuenta);

    List<DetalleCartola> findByCuentaBancariaBetweenDates(CuentaBancaria cuentaBancaria, Date from, Date to);

    List<DetalleCartola> findLastByCuentaBancaria(CuentaBancaria cuentaBancaria);

    DetalleCartola fingByCuentaBancoFecha(CuentaBancaria cuenta, Date from, Date to);

}
