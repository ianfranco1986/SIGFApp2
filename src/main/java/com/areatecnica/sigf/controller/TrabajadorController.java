package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.dao.impl.ITrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.CargaTrabajador;
import com.areatecnica.sigf.entities.TrabajadorAdicionalSalud;
import com.areatecnica.sigf.entities.Despacho;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
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
import com.areatecnica.sigf.facade.TrabajadorFacade;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.joda.time.DateTime;

@Named(value = "trabajadorController")
@ViewScoped
public class TrabajadorController extends AbstractController<Trabajador> {

    @Inject
    private CuentaController trabajadorIdCuentaController;
    @Inject
    private InstitucionApvController trabajadorIdInstitucionApvController;
    @Inject
    private AsignacionFamiliarController trabajadorIdAsignacionFamiliarController;
    @Inject
    private CentroCostoController trabajadorIdCentroCostoController;
    @Inject
    private ComunaController trabajadorIdComunaController;
    @Inject
    private TipoCotizacionTrabajadorController trabajadorIdTipoCotizacionTrabajadorController;
    @Inject
    private EstadoCivilController trabajadorIdEstadoCivilController;
    @Inject
    private FormaPagoController trabajadorIdFormaPagoController;
    @Inject
    private InstitucionPrevisionController trabajadorIdInstitucionPrevisionController;
    @Inject
    private TerminalController trabajadorIdTerminalController;
    @Inject
    private InstitucionSaludController trabajadorIdInstitucionSaludController;
    @Inject
    private SindicatoController trabajadorIdSindicatoController;

    // Flags to indicate if child collections are empty
    private boolean isHaberTrabajadorListEmpty;
    private boolean isCargaTrabajadorListEmpty;
    private boolean isTrabajadorAdicionalSaludListEmpty;
    private boolean isDespachoListEmpty;
    private boolean isVentaBoletoListEmpty;
    private boolean isLiquidacionSueldoListEmpty;
    private boolean isReconocimientoDeudaListEmpty;
    private boolean isRelacionLaboralListEmpty;
    private boolean isCargaRetroactivaListEmpty;
    private boolean isCuentaBancoTrabajadorListEmpty;
    private boolean isLicenciaMedicaListEmpty;
    private boolean isHoraExtraTrabajadorListEmpty;
    private boolean isDescuentoTrabajadorListEmpty;
    private boolean isGuiaListEmpty;
    private boolean isFeriadoLegalListEmpty;
    private boolean isJornadaTrabajadorListEmpty;
    private boolean isControlAsistenciaListEmpty;
    private boolean isObservacionTrabajadorListEmpty;

    private Date minFechaNacimiento;
    private Date maxFechaNacimiento;
    private int regimenPrevisional;
    private boolean errorTrabajador;

    private ITrabajadorDao trabajadorDao;

    public TrabajadorController() {
        // Inform the Abstract parent controller of the concrete Trabajador Entity
        super(Trabajador.class);
//        this.setLimitedByCuenta(Boolean.TRUE);
//        this.setNamedQuery("Trabajador.findAllByCuenta");
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR) - 20, 1, 1, 0, 0);
        this.minFechaNacimiento = dateTime.toDate();
    }

    @Override
    public Trabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.trabajadorDao = new ITrabajadorDaoImpl();

        this.getSelected().setTrabajadorCodigo(this.trabajadorDao.findMaxCodigoCuenta(this.getUserCount()));
        this.getSelected().setTrabajadorIdCuenta(this.getUserCount());
        this.getSelected().setTrabajadorNacionalidad(Boolean.TRUE);
        this.getSelected().setTrabajadorSexo(Boolean.TRUE);
        this.getSelected().setTrabajadorSubsidioJoven(Boolean.FALSE);
        this.getSelected().setTrabajadorIdEstadoCivil(this.trabajadorIdEstadoCivilController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdAsignacionFamiliar(this.trabajadorIdAsignacionFamiliarController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdCentroCosto(this.trabajadorIdCentroCostoController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdComuna(this.trabajadorIdComunaController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdSindicato(this.trabajadorIdSindicatoController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdTipoCotizacionTrabajador(this.trabajadorIdTipoCotizacionTrabajadorController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdInstitucionSalud(this.trabajadorIdInstitucionSaludController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorFechaNacimiento(this.minFechaNacimiento);
        this.getSelected().setTrabajadorIdInstitucionApv(this.trabajadorIdInstitucionApvController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
        this.getSelected().setTrabajadorFormaPagoApv(Boolean.TRUE);
        this.getSelected().setTrabajadorMontoApv(0);
        this.getSelected().setTrabajadorFonasa(Boolean.TRUE);
        this.getSelected().setTrabajadorPoseeApv(Boolean.FALSE);
        this.getSelected().setTrabajadorPoseeCargas(Boolean.FALSE);
        this.getSelected().setTrabajadorPoseeCuentaBanco(Boolean.FALSE);
        this.getSelected().setTrabajadorJubilado(Boolean.FALSE);
        this.getSelected().setTrabajadorIps(Boolean.FALSE);
        this.getSelected().setTrabajadorContratado(Boolean.FALSE);
        this.setRegimenPrevisional(0);
        return this.getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {

        if (!errorTrabajador) {
            switch (this.regimenPrevisional) {
                case 0:
                    break;
                case 1:
                    this.getSelected().setTrabajadorJubilado(Boolean.TRUE);
                    this.getSelected().setTrabajadorIps(Boolean.FALSE);
                    this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                case 2:
                    this.getSelected().setTrabajadorIps(Boolean.TRUE);
                    this.getSelected().setTrabajadorJubilado(Boolean.FALSE);
                    this.getSelected().setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                default:
                    break;
            }
            super.saveNew(event);
        } else {
            JsfUtil.addErrorMessage("El rut se enncuentra registrado");
        }
    }
    
    public void findTrabajador() {
        if (!this.getSelected().getTrabajadorRut().equals("")) {
            FacesMessage msg = new FacesMessage("Rut ya registrado", "Error de validación");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            Trabajador auxTrabajador = this.trabajadorDao.findByTrabajadorRutAndCuenta(this.getSelected().getTrabajadorRut(), this.getUserCount());
            if (auxTrabajador != null) {
                this.errorTrabajador = Boolean.TRUE;
                JsfUtil.addErrorMessage("Rut ya registrado");
            }else{
                this.errorTrabajador = Boolean.FALSE;
            }
        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        trabajadorIdCuentaController.setSelected(null);
        trabajadorIdInstitucionApvController.setSelected(null);
        trabajadorIdAsignacionFamiliarController.setSelected(null);
        trabajadorIdCentroCostoController.setSelected(null);
        trabajadorIdComunaController.setSelected(null);
        trabajadorIdTipoCotizacionTrabajadorController.setSelected(null);
        trabajadorIdEstadoCivilController.setSelected(null);
        trabajadorIdFormaPagoController.setSelected(null);
        trabajadorIdInstitucionPrevisionController.setSelected(null);
        trabajadorIdTerminalController.setSelected(null);
        trabajadorIdInstitucionSaludController.setSelected(null);
        trabajadorIdSindicatoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsReconocimientoDeudaListEmpty();
        this.setIsRelacionLaboralListEmpty();
        this.setIsCargaRetroactivaListEmpty();
        this.setIsCuentaBancoTrabajadorListEmpty();
        this.setIsLicenciaMedicaListEmpty();
        this.setIsHoraExtraTrabajadorListEmpty();
        this.setIsDescuentoTrabajadorListEmpty();
        this.setIsGuiaListEmpty();
        this.setIsFeriadoLegalListEmpty();
        this.setIsJornadaTrabajadorListEmpty();
        this.setIsControlAsistenciaListEmpty();
        this.setIsObservacionTrabajadorListEmpty();
    }

    public boolean getIsHaberTrabajadorListEmpty() {
        return this.isHaberTrabajadorListEmpty;
    }

    /**
     * Sets the "items" attribute with a collection of HaberTrabajador entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for HaberTrabajador page
     */
    public String navigateHaberTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<HaberTrabajador> selectedHaberTrabajadorList = ejbFacade.findHaberTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HaberTrabajador_items", selectedHaberTrabajadorList);
        }
        return "/app/haberTrabajador/index";
    }

    public boolean getIsCargaTrabajadorListEmpty() {
        return this.isCargaTrabajadorListEmpty;
    }


    /**
     * Sets the "items" attribute with a collection of CargaTrabajador entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for CargaTrabajador page
     */
    public String navigateCargaTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<CargaTrabajador> selectedCargaTrabajadorList = ejbFacade.findCargaTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaTrabajador_items", selectedCargaTrabajadorList);
        }
        return "/app/cargaTrabajador/index";
    }

    public boolean getIsTrabajadorAdicionalSaludListEmpty() {
        return this.isTrabajadorAdicionalSaludListEmpty;
    }


    /**
     * Sets the "items" attribute with a collection of TrabajadorAdicionalSalud
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TrabajadorAdicionalSalud page
     */
    public String navigateTrabajadorAdicionalSaludList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<TrabajadorAdicionalSalud> selectedTrabajadorAdicionalSaludList = ejbFacade.findTrabajadorAdicionalSaludList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TrabajadorAdicionalSalud_items", selectedTrabajadorAdicionalSaludList);
        }
        return "/app/trabajadorAdicionalSalud/index";
    }

    public boolean getIsDespachoListEmpty() {
        return this.isDespachoListEmpty;
    }


    /**
     * Sets the "items" attribute with a collection of Despacho entities that
     * are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for Despacho page
     */
    public String navigateDespachoList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<Despacho> selectedDespachoList = ejbFacade.findDespachoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Despacho_items", selectedDespachoList);
        }
        return "/app/despacho/index";
    }

    public boolean getIsVentaBoletoListEmpty() {
        return this.isVentaBoletoListEmpty;
    }


    /**
     * Sets the "items" attribute with a collection of VentaBoleto entities that
     * are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for VentaBoleto page
     */
    public String navigateVentaBoletoList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<VentaBoleto> selectedVentaBoletoList = ejbFacade.findVentaBoletoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("VentaBoleto_items", selectedVentaBoletoList);
        }
        return "/app/ventaBoleto/index";
    }

    public boolean getIsLiquidacionSueldoListEmpty() {
        return this.isLiquidacionSueldoListEmpty;
    }

    /**
     * Sets the "items" attribute with a collection of LiquidacionSueldo
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for LiquidacionSueldo page
     */
    public String navigateLiquidacionSueldoList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<LiquidacionSueldo> selectedLiquidacionSueldoList = ejbFacade.findLiquidacionSueldoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LiquidacionSueldo_items", selectedLiquidacionSueldoList);
        }
        return "/app/liquidacionSueldo/index";
    }

    /**
     * Sets the "selected" attribute of the Cuenta controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdCuenta(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdCuentaController.getSelected() == null) {
            trabajadorIdCuentaController.setSelected(selected.getTrabajadorIdCuenta());
        }
    }

    /**
     * Sets the "selected" attribute of the InstitucionApv controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionApv(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdInstitucionApvController.getSelected() == null) {
            trabajadorIdInstitucionApvController.setSelected(selected.getTrabajadorIdInstitucionApv());
        }
    }

    /**
     * Sets the "selected" attribute of the AsignacionFamiliar controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdAsignacionFamiliar(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdAsignacionFamiliarController.getSelected() == null) {
            trabajadorIdAsignacionFamiliarController.setSelected(selected.getTrabajadorIdAsignacionFamiliar());
        }
    }

    /**
     * Sets the "selected" attribute of the CentroCosto controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdCentroCosto(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdCentroCostoController.getSelected() == null) {
            trabajadorIdCentroCostoController.setSelected(selected.getTrabajadorIdCentroCosto());
        }
    }

    /**
     * Sets the "selected" attribute of the Comuna controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdComuna(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdComunaController.getSelected() == null) {
            trabajadorIdComunaController.setSelected(selected.getTrabajadorIdComuna());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoCotizacionTrabajador controller
     * in order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdTipoCotizacionTrabajador(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdTipoCotizacionTrabajadorController.getSelected() == null) {
            trabajadorIdTipoCotizacionTrabajadorController.setSelected(selected.getTrabajadorIdTipoCotizacionTrabajador());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoCivil controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdEstadoCivil(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdEstadoCivilController.getSelected() == null) {
            trabajadorIdEstadoCivilController.setSelected(selected.getTrabajadorIdEstadoCivil());
        }
    }

    /**
     * Sets the "selected" attribute of the FormaPago controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdFormaPago(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdFormaPagoController.getSelected() == null) {
            trabajadorIdFormaPagoController.setSelected(selected.getTrabajadorIdFormaPago());
        }
    }

    /**
     * Sets the "selected" attribute of the InstitucionPrevision controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionPrevision(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdInstitucionPrevisionController.getSelected() == null) {
            trabajadorIdInstitucionPrevisionController.setSelected(selected.getTrabajadorIdInstitucionPrevision());
        }
    }

    /**
     * Sets the "selected" attribute of the Terminal controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdTerminal(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdTerminalController.getSelected() == null) {
            trabajadorIdTerminalController.setSelected(selected.getTrabajadorIdTerminal());
        }
    }

    /**
     * Sets the "selected" attribute of the InstitucionSalud controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdInstitucionSalud(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdInstitucionSaludController.getSelected() == null) {
            trabajadorIdInstitucionSaludController.setSelected(selected.getTrabajadorIdInstitucionSalud());
        }
    }

    /**
     * Sets the "selected" attribute of the Sindicato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrabajadorIdSindicato(ActionEvent event) {
        Trabajador selected = this.getSelected();
        if (selected != null && trabajadorIdSindicatoController.getSelected() == null) {
            trabajadorIdSindicatoController.setSelected(selected.getTrabajadorIdSindicato());
        }
    }

    public boolean getIsReconocimientoDeudaListEmpty() {
        return this.isReconocimientoDeudaListEmpty;
    }

    private void setIsReconocimientoDeudaListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isReconocimientoDeudaListEmpty = ejbFacade.isReconocimientoDeudaListEmpty(selected);
        } else {
            this.isReconocimientoDeudaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ReconocimientoDeuda
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ReconocimientoDeuda page
     */
    public String navigateReconocimientoDeudaList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<ReconocimientoDeuda> selectedReconocimientoDeudaList = ejbFacade.findReconocimientoDeudaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ReconocimientoDeuda_items", selectedReconocimientoDeudaList);
        }
        return "/app/reconocimientoDeuda/index";
    }

    public boolean getIsRelacionLaboralListEmpty() {
        return this.isRelacionLaboralListEmpty;
    }

    private void setIsRelacionLaboralListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isRelacionLaboralListEmpty = ejbFacade.isRelacionLaboralListEmpty(selected);
        } else {
            this.isRelacionLaboralListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of RelacionLaboral entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for RelacionLaboral page
     */
    public String navigateRelacionLaboralList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<RelacionLaboral> selectedRelacionLaboralList = ejbFacade.findRelacionLaboralList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("RelacionLaboral_items", selectedRelacionLaboralList);
        }
        return "/app/relacionLaboral/index";
    }

    public boolean getIsCargaRetroactivaListEmpty() {
        return this.isCargaRetroactivaListEmpty;
    }

    private void setIsCargaRetroactivaListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isCargaRetroactivaListEmpty = ejbFacade.isCargaRetroactivaListEmpty(selected);
        } else {
            this.isCargaRetroactivaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CargaRetroactiva entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for CargaRetroactiva page
     */
    public String navigateCargaRetroactivaList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<CargaRetroactiva> selectedCargaRetroactivaList = ejbFacade.findCargaRetroactivaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CargaRetroactiva_items", selectedCargaRetroactivaList);
        }
        return "/app/cargaRetroactiva/index";
    }

    public boolean getIsCuentaBancoTrabajadorListEmpty() {
        return this.isCuentaBancoTrabajadorListEmpty;
    }

    private void setIsCuentaBancoTrabajadorListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isCuentaBancoTrabajadorListEmpty = ejbFacade.isCuentaBancoTrabajadorListEmpty(selected);
        } else {
            this.isCuentaBancoTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CuentaBancoTrabajador
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CuentaBancoTrabajador page
     */
    public String navigateCuentaBancoTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<CuentaBancoTrabajador> selectedCuentaBancoTrabajadorList = ejbFacade.findCuentaBancoTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CuentaBancoTrabajador_items", selectedCuentaBancoTrabajadorList);
        }
        return "/app/cuentaBancoTrabajador/index";
    }

    public boolean getIsLicenciaMedicaListEmpty() {
        return this.isLicenciaMedicaListEmpty;
    }

    private void setIsLicenciaMedicaListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isLicenciaMedicaListEmpty = ejbFacade.isLicenciaMedicaListEmpty(selected);
        } else {
            this.isLicenciaMedicaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of LicenciaMedica entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for LicenciaMedica page
     */
    public String navigateLicenciaMedicaList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<LicenciaMedica> selectedLicenciaMedicaList = ejbFacade.findLicenciaMedicaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LicenciaMedica_items", selectedLicenciaMedicaList);
        }
        return "/app/licenciaMedica/index";
    }

    public boolean getIsHoraExtraTrabajadorListEmpty() {
        return this.isHoraExtraTrabajadorListEmpty;
    }

    private void setIsHoraExtraTrabajadorListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isHoraExtraTrabajadorListEmpty = ejbFacade.isHoraExtraTrabajadorListEmpty(selected);
        } else {
            this.isHoraExtraTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of HoraExtraTrabajador
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for HoraExtraTrabajador page
     */
    public String navigateHoraExtraTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<HoraExtraTrabajador> selectedHoraExtraTrabajadorList = ejbFacade.findHoraExtraTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HoraExtraTrabajador_items", selectedHoraExtraTrabajadorList);
        }
        return "/app/horaExtraTrabajador/index";
    }

    public boolean getIsDescuentoTrabajadorListEmpty() {
        return this.isDescuentoTrabajadorListEmpty;
    }

    private void setIsDescuentoTrabajadorListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isDescuentoTrabajadorListEmpty = ejbFacade.isDescuentoTrabajadorListEmpty(selected);
        } else {
            this.isDescuentoTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DescuentoTrabajador
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DescuentoTrabajador page
     */
    public String navigateDescuentoTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<DescuentoTrabajador> selectedDescuentoTrabajadorList = ejbFacade.findDescuentoTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DescuentoTrabajador_items", selectedDescuentoTrabajadorList);
        }
        return "/app/descuentoTrabajador/index";
    }

    public boolean getIsGuiaListEmpty() {
        return this.isGuiaListEmpty;
    }

    private void setIsGuiaListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isGuiaListEmpty = ejbFacade.isGuiaListEmpty(selected);
        } else {
            this.isGuiaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Guia entities that are
     * retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for Guia page
     */
    public String navigateGuiaList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<Guia> selectedGuiaList = ejbFacade.findGuiaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Guia_items", selectedGuiaList);
        }
        return "/app/guia/index";
    }

    public boolean getIsFeriadoLegalListEmpty() {
        return this.isFeriadoLegalListEmpty;
    }

    private void setIsFeriadoLegalListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isFeriadoLegalListEmpty = ejbFacade.isFeriadoLegalListEmpty(selected);
        } else {
            this.isFeriadoLegalListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of FeriadoLegal entities
     * that are retrieved from Trabajador and returns the navigation outcome.
     *
     * @return navigation outcome for FeriadoLegal page
     */
    public String navigateFeriadoLegalList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<FeriadoLegal> selectedFeriadoLegalList = ejbFacade.findFeriadoLegalList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FeriadoLegal_items", selectedFeriadoLegalList);
        }
        return "/app/feriadoLegal/index";
    }

    public boolean getIsJornadaTrabajadorListEmpty() {
        return this.isJornadaTrabajadorListEmpty;
    }

    private void setIsJornadaTrabajadorListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isJornadaTrabajadorListEmpty = ejbFacade.isJornadaTrabajadorListEmpty(selected);
        } else {
            this.isJornadaTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of JornadaTrabajador
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for JornadaTrabajador page
     */
    public String navigateJornadaTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<JornadaTrabajador> selectedJornadaTrabajadorList = ejbFacade.findJornadaTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("JornadaTrabajador_items", selectedJornadaTrabajadorList);
        }
        return "/app/jornadaTrabajador/index";
    }

    public boolean getIsControlAsistenciaListEmpty() {
        return this.isControlAsistenciaListEmpty;
    }

    private void setIsControlAsistenciaListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isControlAsistenciaListEmpty = ejbFacade.isControlAsistenciaListEmpty(selected);
        } else {
            this.isControlAsistenciaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ControlAsistencia
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ControlAsistencia page
     */
    public String navigateControlAsistenciaList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<ControlAsistencia> selectedControlAsistenciaList = ejbFacade.findControlAsistenciaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ControlAsistencia_items", selectedControlAsistenciaList);
        }
        return "/app/controlAsistencia/index";
    }

    public boolean getIsObservacionTrabajadorListEmpty() {
        return this.isObservacionTrabajadorListEmpty;
    }

    private void setIsObservacionTrabajadorListEmpty() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            this.isObservacionTrabajadorListEmpty = ejbFacade.isObservacionTrabajadorListEmpty(selected);
        } else {
            this.isObservacionTrabajadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ObservacionTrabajador
     * entities that are retrieved from Trabajador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ObservacionTrabajador page
     */
    public String navigateObservacionTrabajadorList() {
        Trabajador selected = this.getSelected();
        if (selected != null) {
            TrabajadorFacade ejbFacade = (TrabajadorFacade) this.getFacade();
            List<ObservacionTrabajador> selectedObservacionTrabajadorList = ejbFacade.findObservacionTrabajadorList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ObservacionTrabajador_items", selectedObservacionTrabajadorList);
        }
        return "/app/observacionTrabajador/index";
    }

    /**
     * @return the regimenPrevisional
     */
    public int getRegimenPrevisional() {
        return regimenPrevisional;
    }

    /**
     * @param regimenPrevisional the regimenPrevisional to set
     */
    public void setRegimenPrevisional(int regimenPrevisional) {
        this.regimenPrevisional = regimenPrevisional;
    }

}
