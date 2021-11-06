/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Empresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Empresa_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.CajaCompensacion;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Mutual;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }

}
