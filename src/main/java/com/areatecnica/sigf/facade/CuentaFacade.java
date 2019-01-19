/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.Cuenta_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.HorarioJornada;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.PeriodoFrecuencia;
import com.areatecnica.sigf.entities.ValorMinuto;
import com.areatecnica.sigf.entities.RepresentanteLegal;
import com.areatecnica.sigf.entities.Banco;
import com.areatecnica.sigf.entities.TipoControl;
import com.areatecnica.sigf.entities.CentroCosto;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.AsignacionFamiliar;
import com.areatecnica.sigf.entities.InstitucionSalud;
import com.areatecnica.sigf.entities.Flota;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.TipoObservacion;
import com.areatecnica.sigf.entities.AdministracionMensual;
import com.areatecnica.sigf.entities.IntervalosAdministracion;
import com.areatecnica.sigf.entities.TipoHaberTrabajador;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.TipoContrato;
import com.areatecnica.sigf.entities.DescuentoExtra;
import com.areatecnica.sigf.entities.Sindicato;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.TipoCargo;
import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import com.areatecnica.sigf.entities.InstitucionPrevision;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.CompraCombustible;
import com.areatecnica.sigf.entities.Mutual;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.TipoCuenta;
import com.areatecnica.sigf.entities.Servicio;
import com.areatecnica.sigf.entities.TipoTrabajador;
import com.areatecnica.sigf.entities.CajaCompensacion;
import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Departamento;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    public boolean isHorarioJornadaListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.horarioJornadaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HorarioJornada> findHorarioJornadaList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<HorarioJornada> horarioJornadaList = mergedEntity.getHorarioJornadaList();
        horarioJornadaList.size();
        return horarioJornadaList;
    }

    public boolean isBoletoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.boletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Boleto> findBoletoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Boleto> boletoList = mergedEntity.getBoletoList();
        boletoList.size();
        return boletoList;
    }

    public boolean isPeriodoFrecuenciaListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.periodoFrecuenciaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PeriodoFrecuencia> findPeriodoFrecuenciaList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<PeriodoFrecuencia> periodoFrecuenciaList = mergedEntity.getPeriodoFrecuenciaList();
        periodoFrecuenciaList.size();
        return periodoFrecuenciaList;
    }

    public boolean isValorMinutoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.valorMinutoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ValorMinuto> findValorMinutoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<ValorMinuto> valorMinutoList = mergedEntity.getValorMinutoList();
        valorMinutoList.size();
        return valorMinutoList;
    }

    public boolean isRepresentanteLegalListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.representanteLegalList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RepresentanteLegal> findRepresentanteLegalList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<RepresentanteLegal> representanteLegalList = mergedEntity.getRepresentanteLegalList();
        representanteLegalList.size();
        return representanteLegalList;
    }

    public boolean isBancoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.bancoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Banco> findBancoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Banco> bancoList = mergedEntity.getBancoList();
        bancoList.size();
        return bancoList;
    }

    public boolean isTipoControlListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoControlList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoControl> findTipoControlList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoControl> tipoControlList = mergedEntity.getTipoControlList();
        tipoControlList.size();
        return tipoControlList;
    }

    public boolean isCentroCostoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.centroCostoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CentroCosto> findCentroCostoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<CentroCosto> centroCostoList = mergedEntity.getCentroCostoList();
        centroCostoList.size();
        return centroCostoList;
    }

    public boolean isUsuarioListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.usuarioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuario> findUsuarioList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Usuario> usuarioList = mergedEntity.getUsuarioList();
        usuarioList.size();
        return usuarioList;
    }

    public boolean isAsignacionFamiliarListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.asignacionFamiliarList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AsignacionFamiliar> findAsignacionFamiliarList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<AsignacionFamiliar> asignacionFamiliarList = mergedEntity.getAsignacionFamiliarList();
        asignacionFamiliarList.size();
        return asignacionFamiliarList;
    }

    public boolean isInstitucionSaludListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.institucionSaludList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InstitucionSalud> findInstitucionSaludList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<InstitucionSalud> institucionSaludList = mergedEntity.getInstitucionSaludList();
        institucionSaludList.size();
        return institucionSaludList;
    }

    public boolean isFlotaListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.flotaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Flota> findFlotaList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Flota> flotaList = mergedEntity.getFlotaList();
        flotaList.size();
        return flotaList;
    }

    public boolean isEgresoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.egresoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Egreso> findEgresoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Egreso> egresoList = mergedEntity.getEgresoList();
        egresoList.size();
        return egresoList;
    }

    public boolean isTipoObservacionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoObservacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoObservacion> findTipoObservacionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoObservacion> tipoObservacionList = mergedEntity.getTipoObservacionList();
        tipoObservacionList.size();
        return tipoObservacionList;
    }

    public boolean isAdministracionMensualListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.administracionMensualList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AdministracionMensual> findAdministracionMensualList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<AdministracionMensual> administracionMensualList = mergedEntity.getAdministracionMensualList();
        administracionMensualList.size();
        return administracionMensualList;
    }

    public boolean isIntervalosAdministracionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.intervalosAdministracionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<IntervalosAdministracion> findIntervalosAdministracionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<IntervalosAdministracion> intervalosAdministracionList = mergedEntity.getIntervalosAdministracionList();
        intervalosAdministracionList.size();
        return intervalosAdministracionList;
    }

    public boolean isTipoHaberTrabajadorListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoHaberTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoHaberTrabajador> findTipoHaberTrabajadorList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoHaberTrabajador> tipoHaberTrabajadorList = mergedEntity.getTipoHaberTrabajadorList();
        tipoHaberTrabajadorList.size();
        return tipoHaberTrabajadorList;
    }

    public boolean isTrabajadorListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.trabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Trabajador> findTrabajadorList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Trabajador> trabajadorList = mergedEntity.getTrabajadorList();
        trabajadorList.size();
        return trabajadorList;
    }

    public boolean isPrecioCombustibleListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.precioCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PrecioCombustible> findPrecioCombustibleList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<PrecioCombustible> precioCombustibleList = mergedEntity.getPrecioCombustibleList();
        precioCombustibleList.size();
        return precioCombustibleList;
    }

    public boolean isInstitucionApvListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.institucionApvList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InstitucionApv> findInstitucionApvList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<InstitucionApv> institucionApvList = mergedEntity.getInstitucionApvList();
        institucionApvList.size();
        return institucionApvList;
    }

    public boolean isTipoContratoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoContratoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoContrato> findTipoContratoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoContrato> tipoContratoList = mergedEntity.getTipoContratoList();
        tipoContratoList.size();
        return tipoContratoList;
    }

    public boolean isDescuentoExtraListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.descuentoExtraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoExtra> findDescuentoExtraList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<DescuentoExtra> descuentoExtraList = mergedEntity.getDescuentoExtraList();
        descuentoExtraList.size();
        return descuentoExtraList;
    }

    public boolean isSindicatoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.sindicatoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Sindicato> findSindicatoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Sindicato> sindicatoList = mergedEntity.getSindicatoList();
        sindicatoList.size();
        return sindicatoList;
    }

    public boolean isCompraBoletoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.compraBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CompraBoleto> findCompraBoletoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<CompraBoleto> compraBoletoList = mergedEntity.getCompraBoletoList();
        compraBoletoList.size();
        return compraBoletoList;
    }

    public boolean isTipoCargoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoCargoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoCargo> findTipoCargoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoCargo> tipoCargoList = mergedEntity.getTipoCargoList();
        tipoCargoList.size();
        return tipoCargoList;
    }

    public boolean isTipoDescuentoTrabajadorListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoDescuentoTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoDescuentoTrabajador> findTipoDescuentoTrabajadorList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoDescuentoTrabajador> tipoDescuentoTrabajadorList = mergedEntity.getTipoDescuentoTrabajadorList();
        tipoDescuentoTrabajadorList.size();
        return tipoDescuentoTrabajadorList;
    }

    public boolean isInstitucionPrevisionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.institucionPrevisionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InstitucionPrevision> findInstitucionPrevisionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<InstitucionPrevision> institucionPrevisionList = mergedEntity.getInstitucionPrevisionList();
        institucionPrevisionList.size();
        return institucionPrevisionList;
    }

    public boolean isGrupoServicioListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.grupoServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<GrupoServicio> findGrupoServicioList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<GrupoServicio> grupoServicioList = mergedEntity.getGrupoServicioList();
        grupoServicioList.size();
        return grupoServicioList;
    }

    public boolean isCajaRecaudacionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.cajaRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaRecaudacion> findCajaRecaudacionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<CajaRecaudacion> cajaRecaudacionList = mergedEntity.getCajaRecaudacionList();
        cajaRecaudacionList.size();
        return cajaRecaudacionList;
    }

    public boolean isCompraCombustibleListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.compraCombustibleList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CompraCombustible> findCompraCombustibleList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<CompraCombustible> compraCombustibleList = mergedEntity.getCompraCombustibleList();
        compraCombustibleList.size();
        return compraCombustibleList;
    }

    public boolean isMutualListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.mutualList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Mutual> findMutualList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Mutual> mutualList = mergedEntity.getMutualList();
        mutualList.size();
        return mutualList;
    }

    public boolean isTipoAbonoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoAbonoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoAbono> findTipoAbonoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoAbono> tipoAbonoList = mergedEntity.getTipoAbonoList();
        tipoAbonoList.size();
        return tipoAbonoList;
    }

    public boolean isEmpresaListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.empresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Empresa> findEmpresaList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Empresa> empresaList = mergedEntity.getEmpresaList();
        empresaList.size();
        return empresaList;
    }

    public boolean isUnidadNegocioListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.unidadNegocioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<UnidadNegocio> findUnidadNegocioList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<UnidadNegocio> unidadNegocioList = mergedEntity.getUnidadNegocioList();
        unidadNegocioList.size();
        return unidadNegocioList;
    }

    public boolean isProcesoRecaudacionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.procesoRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ProcesoRecaudacion> findProcesoRecaudacionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<ProcesoRecaudacion> procesoRecaudacionList = mergedEntity.getProcesoRecaudacionList();
        procesoRecaudacionList.size();
        return procesoRecaudacionList;
    }

    public boolean isCuentaIdTipoEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotNull(cuenta.get(Cuenta_.cuentaIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoCuenta findCuentaIdTipo(Cuenta entity) {
        return this.getMergedEntity(entity).getCuentaIdTipo();
    }

    public boolean isServicioListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.servicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Servicio> findServicioList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Servicio> servicioList = mergedEntity.getServicioList();
        servicioList.size();
        return servicioList;
    }

    public boolean isTipoTrabajadorListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.tipoTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TipoTrabajador> findTipoTrabajadorList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<TipoTrabajador> tipoTrabajadorList = mergedEntity.getTipoTrabajadorList();
        tipoTrabajadorList.size();
        return tipoTrabajadorList;
    }

    public boolean isCajaCompensacionListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.cajaCompensacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CajaCompensacion> findCajaCompensacionList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<CajaCompensacion> cajaCompensacionList = mergedEntity.getCajaCompensacionList();
        cajaCompensacionList.size();
        return cajaCompensacionList;
    }

    public boolean isControlListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.controlList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Control> findControlList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Control> controlList = mergedEntity.getControlList();
        controlList.size();
        return controlList;
    }

    public boolean isTerminalListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.terminalList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Terminal> findTerminalList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Terminal> terminalList = mergedEntity.getTerminalList();
        terminalList.size();
        return terminalList;
    }

    public boolean isDepartamentoListEmpty(Cuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cuenta> cuenta = cq.from(Cuenta.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuenta, entity), cb.isNotEmpty(cuenta.get(Cuenta_.departamentoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Departamento> findDepartamentoList(Cuenta entity) {
        Cuenta mergedEntity = this.getMergedEntity(entity);
        List<Departamento> departamentoList = mergedEntity.getDepartamentoList();
        departamentoList.size();
        return departamentoList;
    }
    
}
