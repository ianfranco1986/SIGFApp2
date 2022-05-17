/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Usuario;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICajaRecaudacionDao<T> extends IGenericDAO<T> {

    public List<CajaRecaudacion> findAllByUser(Usuario usuario);
    
    public List<CajaRecaudacion> findAllActive();
    
    public CajaRecaudacion findById(int id);
}
