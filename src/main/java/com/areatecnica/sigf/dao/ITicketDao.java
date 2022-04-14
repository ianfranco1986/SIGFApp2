/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Ticket;
import com.areatecnica.sigf.entities.Usuario;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ITicketDao<T> extends IGenericDAO<T> {

    public List<Ticket> findAllByUser(Usuario usuario);

}
