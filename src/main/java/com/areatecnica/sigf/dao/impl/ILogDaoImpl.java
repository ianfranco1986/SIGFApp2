/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ILogDao;
import com.areatecnica.sigf.entities.Log;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ILogDaoImpl extends GenericDAOImpl<Log> implements ILogDao<Log> {

    @Override
    public Log findById(int id) {
        try {
            return (Log) this.entityManager.createNamedQuery("Log.findById").setParameter("logId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
