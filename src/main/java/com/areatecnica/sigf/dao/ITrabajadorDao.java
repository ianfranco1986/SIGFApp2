/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Trabajador;

import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ITrabajadorDao<T> extends IGenericDAO<T> {
    
    int findMaxCodigoCuenta(Cuenta cuenta);
    
    List<Trabajador> findByTerminal(Terminal terminal);
    
    List<Trabajador> findNandu();
    
    Trabajador findByTrabajadorRutAndCuenta(String rut, Cuenta cuenta);
    
    Trabajador findByDefaultTrabajador(Integer id, Cuenta cuenta);
    
}
