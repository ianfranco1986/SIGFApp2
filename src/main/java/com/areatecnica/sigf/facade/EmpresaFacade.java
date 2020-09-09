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

    public boolean isLiquidacionEmpresaListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.liquidacionEmpresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LiquidacionEmpresa> findLiquidacionEmpresaList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionEmpresa> liquidacionEmpresaList = mergedEntity.getLiquidacionEmpresaList();
        liquidacionEmpresaList.size();
        return liquidacionEmpresaList;
    }

    public boolean isRepresentanteEmpresaListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.representanteEmpresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RepresentanteEmpresa> findRepresentanteEmpresaList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<RepresentanteEmpresa> representanteEmpresaList = mergedEntity.getRepresentanteEmpresaList();
        representanteEmpresaList.size();
        return representanteEmpresaList;
    }

//    public boolean isCuentaBancoEmpresaListEmpty(Empresa entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.cuentaBancoEmpresaList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<CuentaBancoEmpresa> findCuentaBancoEmpresaList(Empresa entity) {
//        Empresa mergedEntity = this.getMergedEntity(entity);
//        List<CuentaBancoEmpresa> cuentaBancoEmpresaList = mergedEntity.getCuentaBancoEmpresaList();
//        cuentaBancoEmpresaList.size();
//        return cuentaBancoEmpresaList;
//    }

    public boolean isLiquidacionSueldoListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.liquidacionSueldoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<LiquidacionSueldo> findLiquidacionSueldoList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<LiquidacionSueldo> liquidacionSueldoList = mergedEntity.getLiquidacionSueldoList();
        liquidacionSueldoList.size();
        return liquidacionSueldoList;
    }

    public boolean isReconocimientoDeudaListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.reconocimientoDeudaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ReconocimientoDeuda> findReconocimientoDeudaList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<ReconocimientoDeuda> reconocimientoDeudaList = mergedEntity.getReconocimientoDeudaList();
        reconocimientoDeudaList.size();
        return reconocimientoDeudaList;
    }

    public boolean isBusListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.busList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Bus> findBusList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<Bus> busList = mergedEntity.getBusList();
        busList.size();
        return busList;
    }

    public boolean isRelacionLaboralListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get(Empresa_.relacionLaboralList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RelacionLaboral> findRelacionLaboralList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<RelacionLaboral> relacionLaboralList = mergedEntity.getRelacionLaboralList();
        relacionLaboralList.size();
        return relacionLaboralList;
    }

    public boolean isEmpresaIdCajaCompensacionEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotNull(empresa.get(Empresa_.empresaIdCajaCompensacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CajaCompensacion findEmpresaIdCajaCompensacion(Empresa entity) {
        return this.getMergedEntity(entity).getEmpresaIdCajaCompensacion();
    }

    public boolean isEmpresaIdCuentaEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotNull(empresa.get(Empresa_.empresaIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findEmpresaIdCuenta(Empresa entity) {
        return this.getMergedEntity(entity).getEmpresaIdCuenta();
    }

    public boolean isEmpresaIdMutualEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotNull(empresa.get(Empresa_.empresaIdMutual)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Mutual findEmpresaIdMutual(Empresa entity) {
        return this.getMergedEntity(entity).getEmpresaIdMutual();
    }
    
}
