package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Cuenta;
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
import com.areatecnica.sigf.entities.TipoHaberTrabajador;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.InstitucionApv;
import com.areatecnica.sigf.entities.TipoContrato;
import com.areatecnica.sigf.entities.DescuentoExtra;
import com.areatecnica.sigf.entities.Sindicato;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.TipoDescuentoTrabajador;
import com.areatecnica.sigf.entities.InstitucionPrevision;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.CompraPetroleo;
import com.areatecnica.sigf.entities.Mutual;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.UnidadNegocio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Servicio;
import com.areatecnica.sigf.entities.TipoTrabajador;
import com.areatecnica.sigf.entities.CajaCompensacion;
import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Departamento;
import java.util.List;
import com.areatecnica.sigf.facade.CuentaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cuentaController")
@ViewScoped
public class CuentaController extends AbstractController<Cuenta> {

    @Inject
    private TipoCuentaController cuentaIdTipoController;

    // Flags to indicate if child collections are empty
    private boolean isHorarioJornadaListEmpty;
    private boolean isBoletoListEmpty;
    private boolean isPeriodoFrecuenciaListEmpty;
    private boolean isValorMinutoListEmpty;
    private boolean isRepresentanteLegalListEmpty;
    private boolean isBancoListEmpty;
    private boolean isTipoControlListEmpty;
    private boolean isCentroCostoListEmpty;
    private boolean isUsuarioListEmpty;
    private boolean isAsignacionFamiliarListEmpty;
    private boolean isInstitucionSaludListEmpty;
    private boolean isFlotaListEmpty;
    private boolean isEgresoListEmpty;
    private boolean isTipoObservacionListEmpty;
    private boolean isAdministracionMensualListEmpty;
    private boolean isIntervalosAdministracionListEmpty;
    private boolean isTipoHaberTrabajadorListEmpty;
    private boolean isTrabajadorListEmpty;
    private boolean isPrecioCombustibleListEmpty;
    private boolean isInstitucionApvListEmpty;
    private boolean isTipoContratoListEmpty;
    private boolean isDescuentoExtraListEmpty;
    private boolean isSindicatoListEmpty;
    private boolean isCompraBoletoListEmpty;
    private boolean isTipoCargoListEmpty;
    private boolean isTipoDescuentoTrabajadorListEmpty;
    private boolean isInstitucionPrevisionListEmpty;
    private boolean isGrupoServicioListEmpty;
    private boolean isCajaRecaudacionListEmpty;
    private boolean isCompraCombustibleListEmpty;
    private boolean isMutualListEmpty;
    private boolean isTipoAbonoListEmpty;
    private boolean isEmpresaListEmpty;
    private boolean isUnidadNegocioListEmpty;
    private boolean isProcesoRecaudacionListEmpty;
    private boolean isServicioListEmpty;
    private boolean isTipoTrabajadorListEmpty;
    private boolean isCajaCompensacionListEmpty;
    private boolean isControlListEmpty;
    private boolean isTerminalListEmpty;
    private boolean isDepartamentoListEmpty;

    public CuentaController() {
        // Inform the Abstract parent controller of the concrete Cuenta Entity
        super(Cuenta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cuentaIdTipoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsHorarioJornadaListEmpty();
        this.setIsBoletoListEmpty();
        this.setIsPeriodoFrecuenciaListEmpty();
        this.setIsValorMinutoListEmpty();
        this.setIsRepresentanteLegalListEmpty();
        this.setIsBancoListEmpty();
        this.setIsTipoControlListEmpty();
        this.setIsCentroCostoListEmpty();
        this.setIsUsuarioListEmpty();
        this.setIsAsignacionFamiliarListEmpty();
        this.setIsInstitucionSaludListEmpty();
        this.setIsFlotaListEmpty();
        this.setIsEgresoListEmpty();
        this.setIsTipoObservacionListEmpty();
        this.setIsAdministracionMensualListEmpty();
        this.setIsIntervalosAdministracionListEmpty();
        this.setIsTipoHaberTrabajadorListEmpty();
        this.setIsTrabajadorListEmpty();
        this.setIsPrecioCombustibleListEmpty();
        this.setIsInstitucionApvListEmpty();
        this.setIsTipoContratoListEmpty();
        this.setIsDescuentoExtraListEmpty();
        this.setIsSindicatoListEmpty();
        this.setIsCompraBoletoListEmpty();
        this.setIsTipoCargoListEmpty();
        this.setIsTipoDescuentoTrabajadorListEmpty();
        this.setIsInstitucionPrevisionListEmpty();
        this.setIsGrupoServicioListEmpty();
        this.setIsCajaRecaudacionListEmpty();
        this.setIsCompraCombustibleListEmpty();
        this.setIsMutualListEmpty();
        this.setIsTipoAbonoListEmpty();
        this.setIsEmpresaListEmpty();
        this.setIsUnidadNegocioListEmpty();
        this.setIsProcesoRecaudacionListEmpty();
        this.setIsServicioListEmpty();
        this.setIsTipoTrabajadorListEmpty();
        this.setIsCajaCompensacionListEmpty();
        this.setIsControlListEmpty();
        this.setIsTerminalListEmpty();
        this.setIsDepartamentoListEmpty();
    }

    public boolean getIsHorarioJornadaListEmpty() {
        return this.isHorarioJornadaListEmpty;
    }

    private void setIsHorarioJornadaListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isHorarioJornadaListEmpty = ejbFacade.isHorarioJornadaListEmpty(selected);
        } else {
            this.isHorarioJornadaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HorarioJornada entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for HorarioJornada page
     */
    public String navigateHorarioJornadaList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<HorarioJornada> selectedHorarioJornadaList = ejbFacade.findHorarioJornadaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HorarioJornada_items", selectedHorarioJornadaList);
        }
        return "/app/horarioJornada/index";
    }

    public boolean getIsBoletoListEmpty() {
        return this.isBoletoListEmpty;
    }

    private void setIsBoletoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isBoletoListEmpty = ejbFacade.isBoletoListEmpty(selected);
        } else {
            this.isBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Boleto entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Boleto page
     */
    public String navigateBoletoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Boleto> selectedBoletoList = ejbFacade.findBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Boleto_items", selectedBoletoList);
        }
        return "/app/boleto/index";
    }

    public boolean getIsPeriodoFrecuenciaListEmpty() {
        return this.isPeriodoFrecuenciaListEmpty;
    }

    private void setIsPeriodoFrecuenciaListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isPeriodoFrecuenciaListEmpty = ejbFacade.isPeriodoFrecuenciaListEmpty(selected);
        } else {
            this.isPeriodoFrecuenciaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PeriodoFrecuencia
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PeriodoFrecuencia page
     */
    public String navigatePeriodoFrecuenciaList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<PeriodoFrecuencia> selectedPeriodoFrecuenciaList = ejbFacade.findPeriodoFrecuenciaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PeriodoFrecuencia_items", selectedPeriodoFrecuenciaList);
        }
        return "/app/periodoFrecuencia/index";
    }

    public boolean getIsValorMinutoListEmpty() {
        return this.isValorMinutoListEmpty;
    }

    private void setIsValorMinutoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isValorMinutoListEmpty = ejbFacade.isValorMinutoListEmpty(selected);
        } else {
            this.isValorMinutoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ValorMinuto entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for ValorMinuto page
     */
    public String navigateValorMinutoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<ValorMinuto> selectedValorMinutoList = ejbFacade.findValorMinutoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ValorMinuto_items", selectedValorMinutoList);
        }
        return "/app/valorMinuto/index";
    }

    public boolean getIsRepresentanteLegalListEmpty() {
        return this.isRepresentanteLegalListEmpty;
    }

    private void setIsRepresentanteLegalListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isRepresentanteLegalListEmpty = ejbFacade.isRepresentanteLegalListEmpty(selected);
        } else {
            this.isRepresentanteLegalListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RepresentanteLegal
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for RepresentanteLegal page
     */
    public String navigateRepresentanteLegalList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<RepresentanteLegal> selectedRepresentanteLegalList = ejbFacade.findRepresentanteLegalList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RepresentanteLegal_items", selectedRepresentanteLegalList);
        }
        return "/app/representanteLegal/index";
    }

    public boolean getIsBancoListEmpty() {
        return this.isBancoListEmpty;
    }

    private void setIsBancoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isBancoListEmpty = ejbFacade.isBancoListEmpty(selected);
        } else {
            this.isBancoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Banco entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Banco page
     */
    public String navigateBancoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Banco> selectedBancoList = ejbFacade.findBancoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Banco_items", selectedBancoList);
        }
        return "/app/banco/index";
    }

    public boolean getIsTipoControlListEmpty() {
        return this.isTipoControlListEmpty;
    }

    private void setIsTipoControlListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoControlListEmpty = ejbFacade.isTipoControlListEmpty(selected);
        } else {
            this.isTipoControlListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoControl entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoControl page
     */
    public String navigateTipoControlList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoControl> selectedTipoControlList = ejbFacade.findTipoControlList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoControl_items", selectedTipoControlList);
        }
        return "/app/tipoControl/index";
    }

    public boolean getIsCentroCostoListEmpty() {
        return this.isCentroCostoListEmpty;
    }

    private void setIsCentroCostoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isCentroCostoListEmpty = ejbFacade.isCentroCostoListEmpty(selected);
        } else {
            this.isCentroCostoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CentroCosto entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for CentroCosto page
     */
    public String navigateCentroCostoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<CentroCosto> selectedCentroCostoList = ejbFacade.findCentroCostoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CentroCosto_items", selectedCentroCostoList);
        }
        return "/app/centroCosto/index";
    }

    public boolean getIsUsuarioListEmpty() {
        return this.isUsuarioListEmpty;
    }

    private void setIsUsuarioListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isUsuarioListEmpty = ejbFacade.isUsuarioListEmpty(selected);
        } else {
            this.isUsuarioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Usuario> selectedUsuarioList = ejbFacade.findUsuarioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioList);
        }
        return "/app/usuario/index";
    }

    public boolean getIsAsignacionFamiliarListEmpty() {
        return this.isAsignacionFamiliarListEmpty;
    }

    private void setIsAsignacionFamiliarListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isAsignacionFamiliarListEmpty = ejbFacade.isAsignacionFamiliarListEmpty(selected);
        } else {
            this.isAsignacionFamiliarListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AsignacionFamiliar
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AsignacionFamiliar page
     */
    public String navigateAsignacionFamiliarList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<AsignacionFamiliar> selectedAsignacionFamiliarList = ejbFacade.findAsignacionFamiliarList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AsignacionFamiliar_items", selectedAsignacionFamiliarList);
        }
        return "/app/asignacionFamiliar/index";
    }

    public boolean getIsInstitucionSaludListEmpty() {
        return this.isInstitucionSaludListEmpty;
    }

    private void setIsInstitucionSaludListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isInstitucionSaludListEmpty = ejbFacade.isInstitucionSaludListEmpty(selected);
        } else {
            this.isInstitucionSaludListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionSalud entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for InstitucionSalud page
     */
    public String navigateInstitucionSaludList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<InstitucionSalud> selectedInstitucionSaludList = ejbFacade.findInstitucionSaludList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionSalud_items", selectedInstitucionSaludList);
        }
        return "/app/institucionSalud/index";
    }

    public boolean getIsFlotaListEmpty() {
        return this.isFlotaListEmpty;
    }

    private void setIsFlotaListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isFlotaListEmpty = ejbFacade.isFlotaListEmpty(selected);
        } else {
            this.isFlotaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Flota entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Flota page
     */
    public String navigateFlotaList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Flota> selectedFlotaList = ejbFacade.findFlotaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Flota_items", selectedFlotaList);
        }
        return "/app/flota/index";
    }

    public boolean getIsEgresoListEmpty() {
        return this.isEgresoListEmpty;
    }

    private void setIsEgresoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isEgresoListEmpty = ejbFacade.isEgresoListEmpty(selected);
        } else {
            this.isEgresoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Egreso entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Egreso page
     */
    public String navigateEgresoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Egreso> selectedEgresoList = ejbFacade.findEgresoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Egreso_items", selectedEgresoList);
        }
        return "/app/egreso/index";
    }

    public boolean getIsTipoObservacionListEmpty() {
        return this.isTipoObservacionListEmpty;
    }

    private void setIsTipoObservacionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoObservacionListEmpty = ejbFacade.isTipoObservacionListEmpty(selected);
        } else {
            this.isTipoObservacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoObservacion entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoObservacion page
     */
    public String navigateTipoObservacionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoObservacion> selectedTipoObservacionList = ejbFacade.findTipoObservacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoObservacion_items", selectedTipoObservacionList);
        }
        return "/app/tipoObservacion/index";
    }

    public boolean getIsAdministracionMensualListEmpty() {
        return this.isAdministracionMensualListEmpty;
    }

    private void setIsAdministracionMensualListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isAdministracionMensualListEmpty = ejbFacade.isAdministracionMensualListEmpty(selected);
        } else {
            this.isAdministracionMensualListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of AdministracionMensual
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for AdministracionMensual page
     */
    public String navigateAdministracionMensualList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
//            List<AdministracionMensual> selectedAdministracionMensualList = ejbFacade.findAdministracionMensualList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("AdministracionMensual_items", selectedAdministracionMensualList);
        }
        return "/app/administracionMensual/index";
    }

    public boolean getIsIntervalosAdministracionListEmpty() {
        return this.isIntervalosAdministracionListEmpty;
    }

    private void setIsIntervalosAdministracionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isIntervalosAdministracionListEmpty = ejbFacade.isIntervalosAdministracionListEmpty(selected);
        } else {
            this.isIntervalosAdministracionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of IntervalosAdministracion
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for IntervalosAdministracion page
     */
    public String navigateIntervalosAdministracionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
//            List<IntervalosAdministracion> selectedIntervalosAdministracionList = ejbFacade.findIntervalosAdministracionList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("IntervalosAdministracion_items", selectedIntervalosAdministracionList);
        }
        return "/app/intervalosAdministracion/index";
    }

    public boolean getIsTipoHaberTrabajadorListEmpty() {
        return this.isTipoHaberTrabajadorListEmpty;
    }

    private void setIsTipoHaberTrabajadorListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoHaberTrabajadorListEmpty = ejbFacade.isTipoHaberTrabajadorListEmpty(selected);
        } else {
            this.isTipoHaberTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoHaberTrabajador
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoHaberTrabajador page
     */
    public String navigateTipoHaberTrabajadorList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoHaberTrabajador> selectedTipoHaberTrabajadorList = ejbFacade.findTipoHaberTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoHaberTrabajador_items", selectedTipoHaberTrabajadorList);
        }
        return "/app/tipoHaberTrabajador/index";
    }

    public boolean getIsTrabajadorListEmpty() {
        return this.isTrabajadorListEmpty;
    }

    private void setIsTrabajadorListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTrabajadorListEmpty = ejbFacade.isTrabajadorListEmpty(selected);
        } else {
            this.isTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Trabajador entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Trabajador page
     */
    public String navigateTrabajadorList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Trabajador> selectedTrabajadorList = ejbFacade.findTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Trabajador_items", selectedTrabajadorList);
        }
        return "/app/trabajador/index";
    }

    public boolean getIsPrecioCombustibleListEmpty() {
        return this.isPrecioCombustibleListEmpty;
    }

    private void setIsPrecioCombustibleListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isPrecioCombustibleListEmpty = ejbFacade.isPrecioCombustibleListEmpty(selected);
        } else {
            this.isPrecioCombustibleListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PrecioCombustible
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PrecioCombustible page
     */
    public String navigatePrecioCombustibleList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<PrecioCombustible> selectedPrecioCombustibleList = ejbFacade.findPrecioCombustibleList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PrecioCombustible_items", selectedPrecioCombustibleList);
        }
        return "/app/precioCombustible/index";
    }

    public boolean getIsInstitucionApvListEmpty() {
        return this.isInstitucionApvListEmpty;
    }

    private void setIsInstitucionApvListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isInstitucionApvListEmpty = ejbFacade.isInstitucionApvListEmpty(selected);
        } else {
            this.isInstitucionApvListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionApv entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for InstitucionApv page
     */
    public String navigateInstitucionApvList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<InstitucionApv> selectedInstitucionApvList = ejbFacade.findInstitucionApvList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionApv_items", selectedInstitucionApvList);
        }
        return "/app/institucionApv/index";
    }

    public boolean getIsTipoContratoListEmpty() {
        return this.isTipoContratoListEmpty;
    }

    private void setIsTipoContratoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoContratoListEmpty = ejbFacade.isTipoContratoListEmpty(selected);
        } else {
            this.isTipoContratoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoContrato entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoContrato page
     */
    public String navigateTipoContratoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoContrato> selectedTipoContratoList = ejbFacade.findTipoContratoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoContrato_items", selectedTipoContratoList);
        }
        return "/app/tipoContrato/index";
    }

    public boolean getIsDescuentoExtraListEmpty() {
        return this.isDescuentoExtraListEmpty;
    }

    private void setIsDescuentoExtraListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isDescuentoExtraListEmpty = ejbFacade.isDescuentoExtraListEmpty(selected);
        } else {
            this.isDescuentoExtraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoExtra entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for DescuentoExtra page
     */
    public String navigateDescuentoExtraList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<DescuentoExtra> selectedDescuentoExtraList = ejbFacade.findDescuentoExtraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoExtra_items", selectedDescuentoExtraList);
        }
        return "/app/descuentoExtra/index";
    }

    public boolean getIsSindicatoListEmpty() {
        return this.isSindicatoListEmpty;
    }

    private void setIsSindicatoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isSindicatoListEmpty = ejbFacade.isSindicatoListEmpty(selected);
        } else {
            this.isSindicatoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Sindicato entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Sindicato page
     */
    public String navigateSindicatoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Sindicato> selectedSindicatoList = ejbFacade.findSindicatoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Sindicato_items", selectedSindicatoList);
        }
        return "/app/sindicato/index";
    }

    public boolean getIsCompraBoletoListEmpty() {
        return this.isCompraBoletoListEmpty;
    }

    private void setIsCompraBoletoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isCompraBoletoListEmpty = ejbFacade.isCompraBoletoListEmpty(selected);
        } else {
            this.isCompraBoletoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CompraBoleto entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for CompraBoleto page
     */
    public String navigateCompraBoletoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<CompraBoleto> selectedCompraBoletoList = ejbFacade.findCompraBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CompraBoleto_items", selectedCompraBoletoList);
        }
        return "/app/compraBoleto/index";
    }

    public boolean getIsTipoCargoListEmpty() {
        return this.isTipoCargoListEmpty;
    }

    private void setIsTipoCargoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoCargoListEmpty = ejbFacade.isTipoCargoListEmpty(selected);
        } else {
            this.isTipoCargoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoCargo entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoCargo page
     */
    public String navigateTipoCargoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
//            List<TipoCargo> selectedTipoCargoList = ejbFacade.findTipoCargoList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoCargo_items", selectedTipoCargoList);
        }
        return "/app/tipoCargo/index";
    }

    public boolean getIsTipoDescuentoTrabajadorListEmpty() {
        return this.isTipoDescuentoTrabajadorListEmpty;
    }

    private void setIsTipoDescuentoTrabajadorListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoDescuentoTrabajadorListEmpty = ejbFacade.isTipoDescuentoTrabajadorListEmpty(selected);
        } else {
            this.isTipoDescuentoTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoDescuentoTrabajador
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoDescuentoTrabajador page
     */
    public String navigateTipoDescuentoTrabajadorList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoDescuentoTrabajador> selectedTipoDescuentoTrabajadorList = ejbFacade.findTipoDescuentoTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoDescuentoTrabajador_items", selectedTipoDescuentoTrabajadorList);
        }
        return "/app/tipoDescuentoTrabajador/index";
    }

    public boolean getIsInstitucionPrevisionListEmpty() {
        return this.isInstitucionPrevisionListEmpty;
    }

    private void setIsInstitucionPrevisionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isInstitucionPrevisionListEmpty = ejbFacade.isInstitucionPrevisionListEmpty(selected);
        } else {
            this.isInstitucionPrevisionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of InstitucionPrevision
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for InstitucionPrevision page
     */
    public String navigateInstitucionPrevisionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<InstitucionPrevision> selectedInstitucionPrevisionList = ejbFacade.findInstitucionPrevisionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("InstitucionPrevision_items", selectedInstitucionPrevisionList);
        }
        return "/app/institucionPrevision/index";
    }

    public boolean getIsGrupoServicioListEmpty() {
        return this.isGrupoServicioListEmpty;
    }

    private void setIsGrupoServicioListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isGrupoServicioListEmpty = ejbFacade.isGrupoServicioListEmpty(selected);
        } else {
            this.isGrupoServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of GrupoServicio entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for GrupoServicio page
     */
    public String navigateGrupoServicioList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<GrupoServicio> selectedGrupoServicioList = ejbFacade.findGrupoServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("GrupoServicio_items", selectedGrupoServicioList);
        }
        return "/app/grupoServicio/index";
    }

    public boolean getIsCajaRecaudacionListEmpty() {
        return this.isCajaRecaudacionListEmpty;
    }

    private void setIsCajaRecaudacionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isCajaRecaudacionListEmpty = ejbFacade.isCajaRecaudacionListEmpty(selected);
        } else {
            this.isCajaRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaRecaudacion entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for CajaRecaudacion page
     */
    public String navigateCajaRecaudacionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<CajaRecaudacion> selectedCajaRecaudacionList = ejbFacade.findCajaRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaRecaudacion_items", selectedCajaRecaudacionList);
        }
        return "/app/cajaRecaudacion/index";
    }

    public boolean getIsCompraCombustibleListEmpty() {
        return this.isCompraCombustibleListEmpty;
    }

    private void setIsCompraCombustibleListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isCompraCombustibleListEmpty = ejbFacade.isCompraCombustibleListEmpty(selected);
        } else {
            this.isCompraCombustibleListEmpty = true;
        }
    }

    

    public boolean getIsMutualListEmpty() {
        return this.isMutualListEmpty;
    }

    private void setIsMutualListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isMutualListEmpty = ejbFacade.isMutualListEmpty(selected);
        } else {
            this.isMutualListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Mutual entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Mutual page
     */
    public String navigateMutualList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Mutual> selectedMutualList = ejbFacade.findMutualList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Mutual_items", selectedMutualList);
        }
        return "/app/mutual/index";
    }

    public boolean getIsTipoAbonoListEmpty() {
        return this.isTipoAbonoListEmpty;
    }

    private void setIsTipoAbonoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoAbonoListEmpty = ejbFacade.isTipoAbonoListEmpty(selected);
        } else {
            this.isTipoAbonoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoAbono entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoAbono page
     */
    public String navigateTipoAbonoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
//            List<TipoAbono> selectedTipoAbonoList = ejbFacade.findTipoAbonoList(selected);
//            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoAbono_items", selectedTipoAbonoList);
        }
        return "/app/tipoAbono/index";
    }

    public boolean getIsEmpresaListEmpty() {
        return this.isEmpresaListEmpty;
    }

    private void setIsEmpresaListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isEmpresaListEmpty = ejbFacade.isEmpresaListEmpty(selected);
        } else {
            this.isEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Empresa> selectedEmpresaList = ejbFacade.findEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", selectedEmpresaList);
        }
        return "/app/empresa/index";
    }

    public boolean getIsUnidadNegocioListEmpty() {
        return this.isUnidadNegocioListEmpty;
    }

    private void setIsUnidadNegocioListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isUnidadNegocioListEmpty = ejbFacade.isUnidadNegocioListEmpty(selected);
        } else {
            this.isUnidadNegocioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of UnidadNegocio entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for UnidadNegocio page
     */
    public String navigateUnidadNegocioList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<UnidadNegocio> selectedUnidadNegocioList = ejbFacade.findUnidadNegocioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("UnidadNegocio_items", selectedUnidadNegocioList);
        }
        return "/app/unidadNegocio/index";
    }

    public boolean getIsProcesoRecaudacionListEmpty() {
        return this.isProcesoRecaudacionListEmpty;
    }

    private void setIsProcesoRecaudacionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isProcesoRecaudacionListEmpty = ejbFacade.isProcesoRecaudacionListEmpty(selected);
        } else {
            this.isProcesoRecaudacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ProcesoRecaudacion
     * entities that are retrieved from Cuenta and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ProcesoRecaudacion page
     */
    public String navigateProcesoRecaudacionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<ProcesoRecaudacion> selectedProcesoRecaudacionList = ejbFacade.findProcesoRecaudacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProcesoRecaudacion_items", selectedProcesoRecaudacionList);
        }
        return "/app/procesoRecaudacion/index";
    }

    /**
     * Sets the "selected" attribute of the TipoCuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCuentaIdTipo(ActionEvent event) {
        Cuenta selected = this.getSelected();
        if (selected != null && cuentaIdTipoController.getSelected() == null) {
            cuentaIdTipoController.setSelected(selected.getCuentaIdTipo());
        }
    }

    public boolean getIsServicioListEmpty() {
        return this.isServicioListEmpty;
    }

    private void setIsServicioListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isServicioListEmpty = ejbFacade.isServicioListEmpty(selected);
        } else {
            this.isServicioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Servicio entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Servicio page
     */
    public String navigateServicioList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Servicio> selectedServicioList = ejbFacade.findServicioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Servicio_items", selectedServicioList);
        }
        return "/app/servicio/index";
    }

    public boolean getIsTipoTrabajadorListEmpty() {
        return this.isTipoTrabajadorListEmpty;
    }

    private void setIsTipoTrabajadorListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTipoTrabajadorListEmpty = ejbFacade.isTipoTrabajadorListEmpty(selected);
        } else {
            this.isTipoTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoTrabajador entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for TipoTrabajador page
     */
    public String navigateTipoTrabajadorList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<TipoTrabajador> selectedTipoTrabajadorList = ejbFacade.findTipoTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoTrabajador_items", selectedTipoTrabajadorList);
        }
        return "/app/tipoTrabajador/index";
    }

    public boolean getIsCajaCompensacionListEmpty() {
        return this.isCajaCompensacionListEmpty;
    }

    private void setIsCajaCompensacionListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isCajaCompensacionListEmpty = ejbFacade.isCajaCompensacionListEmpty(selected);
        } else {
            this.isCajaCompensacionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CajaCompensacion entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for CajaCompensacion page
     */
    public String navigateCajaCompensacionList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<CajaCompensacion> selectedCajaCompensacionList = ejbFacade.findCajaCompensacionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CajaCompensacion_items", selectedCajaCompensacionList);
        }
        return "/app/cajaCompensacion/index";
    }

    public boolean getIsControlListEmpty() {
        return this.isControlListEmpty;
    }

    private void setIsControlListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isControlListEmpty = ejbFacade.isControlListEmpty(selected);
        } else {
            this.isControlListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Control entities that are
     * retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Control page
     */
    public String navigateControlList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Control> selectedControlList = ejbFacade.findControlList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Control_items", selectedControlList);
        }
        return "/app/control/index";
    }

    public boolean getIsTerminalListEmpty() {
        return this.isTerminalListEmpty;
    }

    private void setIsTerminalListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isTerminalListEmpty = ejbFacade.isTerminalListEmpty(selected);
        } else {
            this.isTerminalListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Terminal entities that
     * are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Terminal page
     */
    public String navigateTerminalList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Terminal> selectedTerminalList = ejbFacade.findTerminalList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Terminal_items", selectedTerminalList);
        }
        return "/app/terminal/index";
    }

    public boolean getIsDepartamentoListEmpty() {
        return this.isDepartamentoListEmpty;
    }

    private void setIsDepartamentoListEmpty() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            this.isDepartamentoListEmpty = ejbFacade.isDepartamentoListEmpty(selected);
        } else {
            this.isDepartamentoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Departamento entities
     * that are retrieved from Cuenta and returns the navigation outcome.
     *
     * @return navigation outcome for Departamento page
     */
    public String navigateDepartamentoList() {
        Cuenta selected = this.getSelected();
        if (selected != null) {
            CuentaFacade ejbFacade = (CuentaFacade) this.getFacade();
            List<Departamento> selectedDepartamentoList = ejbFacade.findDepartamentoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Departamento_items", selectedDepartamentoList);
        }
        return "/app/departamento/index";
    }

}
