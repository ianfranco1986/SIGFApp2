/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Bus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Bus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.EstadoBus;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.ModeloMarcaBus;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import com.areatecnica.sigf.entities.VentaCombustible;
import com.areatecnica.sigf.entities.EgresoBus;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.RegistroMinuto;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class BusFacade extends AbstractFacade<Bus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BusFacade() {
        super(Bus.class);
    }

    

}
