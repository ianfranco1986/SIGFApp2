/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.DescuentoExtraBus;

import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IDescuentoExtraBusDao<T> extends IGenericDAO<T> {
    
    List<DescuentoExtraBus> findByBus(Bus bus);
    
    List<DescuentoExtraBus> findByDescuentoExtra(DescuentoExtraBus extra);
        
}
