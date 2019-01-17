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
import com.areatecnica.sigf.entities.Bus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.CargoBus;
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

    public boolean isAbonoBusListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.abonoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AbonoBus> findAbonoBusList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<AbonoBus> abonoBusList = mergedEntity.getAbonoBusList();
        abonoBusList.size();
        return abonoBusList;
    }

    public boolean isCargoBusListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.cargoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargoBus> findCargoBusList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<CargoBus> cargoBusList = mergedEntity.getCargoBusList();
        cargoBusList.size();
        return cargoBusList;
    }

    public boolean isDespachoListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.despachoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Despacho> findDespachoList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<Despacho> despachoList = mergedEntity.getDespachoList();
        despachoList.size();
        return despachoList;
    }

    public boolean isVentaBoletoListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.ventaBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<VentaBoleto> findVentaBoletoList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<VentaBoleto> ventaBoletoList = mergedEntity.getVentaBoletoList();
        ventaBoletoList.size();
        return ventaBoletoList;
    }

    public boolean isBusIdEmpresaEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findBusIdEmpresa(Bus entity) {
        return this.getMergedEntity(entity).getBusIdEmpresa();
    }

    public boolean isBusIdEstadoBusEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdEstadoBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public EstadoBus findBusIdEstadoBus(Bus entity) {
        return this.getMergedEntity(entity).getBusIdEstadoBus();
    }

    public boolean isBusIdFlotaEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdFlota)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Flota findBusIdFlota(Bus entity) {
        return this.getMergedEntity(entity).getBusIdFlota();
    }

    public boolean isBusIdGrupoServicioEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdGrupoServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public GrupoServicio findBusIdGrupoServicio(Bus entity) {
        return this.getMergedEntity(entity).getBusIdGrupoServicio();
    }

    public boolean isBusIdModeloEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdModelo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ModeloMarcaBus findBusIdModelo(Bus entity) {
        return this.getMergedEntity(entity).getBusIdModelo();
    }

    public boolean isBusIdProcesoRecaudacionEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdProcesoRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ProcesoRecaudacion findBusIdProcesoRecaudacion(Bus entity) {
        return this.getMergedEntity(entity).getBusIdProcesoRecaudacion();
    }

    public boolean isBusIdTerminalEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findBusIdTerminal(Bus entity) {
        return this.getMergedEntity(entity).getBusIdTerminal();
    }

    public boolean isBusIdUnidadNegocioEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotNull(bus.get(Bus_.busIdUnidadNegocio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public UnidadNegocio findBusIdUnidadNegocio(Bus entity) {
        return this.getMergedEntity(entity).getBusIdUnidadNegocio();
    }

    public boolean isDescuentoExtraBusListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.descuentoExtraBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoExtraBus> findDescuentoExtraBusList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<DescuentoExtraBus> descuentoExtraBusList = mergedEntity.getDescuentoExtraBusList();
        descuentoExtraBusList.size();
        return descuentoExtraBusList;
    }

    public boolean isVentaCombustibleListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.ventaCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<VentaCombustible> findVentaCombustibleList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<VentaCombustible> ventaCombustibleList = mergedEntity.getVentaCombustibleList();
        ventaCombustibleList.size();
        return ventaCombustibleList;
    }

    public boolean isEgresoBusListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.egresoBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<EgresoBus> findEgresoBusList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<EgresoBus> egresoBusList = mergedEntity.getEgresoBusList();
        egresoBusList.size();
        return egresoBusList;
    }

    public boolean isGuiaListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.guiaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Guia> findGuiaList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<Guia> guiaList = mergedEntity.getGuiaList();
        guiaList.size();
        return guiaList;
    }

    public boolean isRegistroMinutoListEmpty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.registroMinutoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RegistroMinuto> findRegistroMinutoList(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<RegistroMinuto> registroMinutoList = mergedEntity.getRegistroMinutoList();
        registroMinutoList.size();
        return registroMinutoList;
    }

    public boolean isRegistroMinutoList1Empty(Bus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bus> bus = cq.from(Bus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bus, entity), cb.isNotEmpty(bus.get(Bus_.registroMinutoList1)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RegistroMinuto> findRegistroMinutoList1(Bus entity) {
        Bus mergedEntity = this.getMergedEntity(entity);
        List<RegistroMinuto> registroMinutoList1 = mergedEntity.getRegistroMinutoList1();
        registroMinutoList1.size();
        return registroMinutoList1;
    }
    
}
