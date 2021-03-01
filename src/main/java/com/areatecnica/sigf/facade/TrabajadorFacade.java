/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Trabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Trabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.AsignacionFamiliar;
import com.areatecnica.sigf.entities.CentroCosto;
import com.areatecnica.sigf.entities.Comuna;
import com.areatecnica.sigf.entities.TipoCotizacionTrabajador;
import com.areatecnica.sigf.entities.EstadoCivil;
import com.areatecnica.sigf.entities.FormaPago;
import com.areatecnica.sigf.entities.InstitucionPrevision;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.InstitucionSalud;
import com.areatecnica.sigf.entities.Sindicato;
import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.entities.RelacionLaboral;
import com.areatecnica.sigf.entities.CargaRetroactiva;
import com.areatecnica.sigf.entities.CuentaBancoTrabajador;
import com.areatecnica.sigf.entities.LicenciaMedica;
import com.areatecnica.sigf.entities.HoraExtraTrabajador;
import com.areatecnica.sigf.entities.DescuentoTrabajador;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.FeriadoLegal;
import com.areatecnica.sigf.entities.JornadaTrabajador;
import com.areatecnica.sigf.entities.ControlAsistencia;
import com.areatecnica.sigf.entities.ObservacionTrabajador;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class TrabajadorFacade extends AbstractFacade<Trabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrabajadorFacade() {
        super(Trabajador.class);
    }

    

    public List<HaberTrabajador> findHaberTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<HaberTrabajador> haberTrabajadorList = mergedEntity.getHaberTrabajadorList();
        haberTrabajadorList.size();
        return haberTrabajadorList;
    }


    public List<CargaTrabajador> findCargaTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<CargaTrabajador> cargaTrabajadorList = mergedEntity.getCargaTrabajadorList();
        cargaTrabajadorList.size();
        return cargaTrabajadorList;
    }


    public List<TrabajadorAdicionalSalud> findTrabajadorAdicionalSaludList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<TrabajadorAdicionalSalud> trabajadorAdicionalSaludList = mergedEntity.getTrabajadorAdicionalSaludList();
        trabajadorAdicionalSaludList.size();
        return trabajadorAdicionalSaludList;
    }

    public List<Despacho> findDespachoList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<Despacho> despachoList = mergedEntity.getDespachoList();
        despachoList.size();
        return despachoList;
    }


    public List<VentaBoleto> findVentaBoletoList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<VentaBoleto> ventaBoletoList = mergedEntity.getVentaBoletoList();
        ventaBoletoList.size();
        return ventaBoletoList;
    }


    public List<LiquidacionSueldo> findLiquidacionSueldoList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionSueldo> liquidacionSueldoList = mergedEntity.getLiquidacionSueldoList();
        liquidacionSueldoList.size();
        return liquidacionSueldoList;
    }

    public Cuenta findTrabajadorIdCuenta(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdCuenta();
    }

    public InstitucionApv findTrabajadorIdInstitucionApv(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdInstitucionApv();
    }


    public AsignacionFamiliar findTrabajadorIdAsignacionFamiliar(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdAsignacionFamiliar();
    }


    public CentroCosto findTrabajadorIdCentroCosto(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdCentroCosto();
    }


    public Comuna findTrabajadorIdComuna(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdComuna();
    }


    public TipoCotizacionTrabajador findTrabajadorIdTipoCotizacionTrabajador(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdTipoCotizacionTrabajador();
    }

    public EstadoCivil findTrabajadorIdEstadoCivil(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdEstadoCivil();
    }


    public FormaPago findTrabajadorIdFormaPago(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdFormaPago();
    }

    public boolean isTrabajadorIdInstitucionPrevisionEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotNull(trabajador.get(Trabajador_.trabajadorIdInstitucionPrevision)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public InstitucionPrevision findTrabajadorIdInstitucionPrevision(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdInstitucionPrevision();
    }

    public boolean isTrabajadorIdTerminalEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotNull(trabajador.get(Trabajador_.trabajadorIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findTrabajadorIdTerminal(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdTerminal();
    }

    public boolean isTrabajadorIdInstitucionSaludEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotNull(trabajador.get(Trabajador_.trabajadorIdInstitucionSalud)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public InstitucionSalud findTrabajadorIdInstitucionSalud(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdInstitucionSalud();
    }

    public boolean isTrabajadorIdSindicatoEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotNull(trabajador.get(Trabajador_.trabajadorIdSindicato)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Sindicato findTrabajadorIdSindicato(Trabajador entity) {
        return this.getMergedEntity(entity).getTrabajadorIdSindicato();
    }

    public boolean isReconocimientoDeudaListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.reconocimientoDeudaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ReconocimientoDeuda> findReconocimientoDeudaList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<ReconocimientoDeuda> reconocimientoDeudaList = mergedEntity.getReconocimientoDeudaList();
        reconocimientoDeudaList.size();
        return reconocimientoDeudaList;
    }

    public boolean isRelacionLaboralListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }

    public boolean isCargaRetroactivaListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.cargaRetroactivaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargaRetroactiva> findCargaRetroactivaList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<CargaRetroactiva> cargaRetroactivaList = mergedEntity.getCargaRetroactivaList();
        cargaRetroactivaList.size();
        return cargaRetroactivaList;
    }

    public boolean isCuentaBancoTrabajadorListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.cuentaBancoTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CuentaBancoTrabajador> findCuentaBancoTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<CuentaBancoTrabajador> cuentaBancoTrabajadorList = mergedEntity.getCuentaBancoTrabajadorList();
        cuentaBancoTrabajadorList.size();
        return cuentaBancoTrabajadorList;
    }

    public boolean isLicenciaMedicaListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.licenciaMedicaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LicenciaMedica> findLicenciaMedicaList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<LicenciaMedica> licenciaMedicaList = mergedEntity.getLicenciaMedicaList();
        licenciaMedicaList.size();
        return licenciaMedicaList;
    }

    public boolean isHoraExtraTrabajadorListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.horaExtraTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HoraExtraTrabajador> findHoraExtraTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<HoraExtraTrabajador> horaExtraTrabajadorList = mergedEntity.getHoraExtraTrabajadorList();
        horaExtraTrabajadorList.size();
        return horaExtraTrabajadorList;
    }

    public boolean isDescuentoTrabajadorListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.descuentoTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoTrabajador> findDescuentoTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<DescuentoTrabajador> descuentoTrabajadorList = mergedEntity.getDescuentoTrabajadorList();
        descuentoTrabajadorList.size();
        return descuentoTrabajadorList;
    }

    public boolean isGuiaListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.guiaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Guia> findGuiaList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<Guia> guiaList = mergedEntity.getGuiaList();
        guiaList.size();
        return guiaList;
    }

    public boolean isFeriadoLegalListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.feriadoLegalList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<FeriadoLegal> findFeriadoLegalList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<FeriadoLegal> feriadoLegalList = mergedEntity.getFeriadoLegalList();
        feriadoLegalList.size();
        return feriadoLegalList;
    }

    public boolean isJornadaTrabajadorListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.jornadaTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<JornadaTrabajador> findJornadaTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<JornadaTrabajador> jornadaTrabajadorList = mergedEntity.getJornadaTrabajadorList();
        jornadaTrabajadorList.size();
        return jornadaTrabajadorList;
    }

    public boolean isControlAsistenciaListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.controlAsistenciaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ControlAsistencia> findControlAsistenciaList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<ControlAsistencia> controlAsistenciaList = mergedEntity.getControlAsistenciaList();
        controlAsistenciaList.size();
        return controlAsistenciaList;
    }

    public boolean isObservacionTrabajadorListEmpty(Trabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Trabajador> trabajador = cq.from(Trabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(trabajador, entity), cb.isNotEmpty(trabajador.get(Trabajador_.observacionTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ObservacionTrabajador> findObservacionTrabajadorList(Trabajador entity) {
        Trabajador mergedEntity = this.getMergedEntity(entity);
        List<ObservacionTrabajador> observacionTrabajadorList = mergedEntity.getObservacionTrabajadorList();
        observacionTrabajadorList.size();
        return observacionTrabajadorList;
    }
    
}
