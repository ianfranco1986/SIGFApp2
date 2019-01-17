/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.NumeralSurtidor;
import com.areatecnica.sigf.entities.Surtidor;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface INumeralSurtidorDao<T> extends IGenericDAO<T> {

    public NumeralSurtidor findUltimoBySurtidorFecha(Surtidor surtidor, Date fecha);

    public List<NumeralSurtidor> findBySurtidorFecha(Surtidor surtidor, Date from, Date to);
    
    public List<NumeralSurtidor> findAllByFecha(Date from, Date to);

    public List<NumeralSurtidor> findLastBySurtidor(Surtidor surtidor);

}
