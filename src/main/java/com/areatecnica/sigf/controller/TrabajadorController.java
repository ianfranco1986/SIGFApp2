package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Trabajador.findAllByCuenta");
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR) - 20, 1, 1, 0, 0);
        this.minFechaNacimiento = dateTime.toDate();
    }

    @Override
    public Trabajador prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.

        this.trabajadorDao = new TrabajadorDaoImpl();

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

            Trabajador auxTrabajador = new TrabajadorDaoImpl().findByTrabajadorRutAndCuenta(this.getSelected().getTrabajadorRut(), this.getUserCount());
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
