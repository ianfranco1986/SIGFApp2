package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.sigf.entities.Trabajador;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private List<Trabajador> items;
    private Trabajador selected;

    private Date minFechaNacimiento;
    private Date maxFechaNacimiento;
    private int regimenPrevisional;
    private boolean errorTrabajador;


    public TrabajadorController() {
        // Inform the Abstract parent controller of the concrete Trabajador Entity
        super(Trabajador.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Trabajador.findAllByCuenta");
        Calendar calendar = Calendar.getInstance();
//        DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR) - 20, 1, 1, 0, 0);
//        this.minFechaNacimiento = dateTime.toDate();

        this.items = new TrabajadorDaoImpl().findAll();
    }

    @Override
    public Trabajador prepareCreate(ActionEvent event) {
        this.selected = new Trabajador();
        this.selected.setTrabajadorCodigo(new TrabajadorDaoImpl().findMaxCodigoCuenta(this.getUserCount()));
        this.selected.setTrabajadorIdCuenta(this.getUserCount());
        this.selected.setTrabajadorNacionalidad(Boolean.TRUE);
        this.selected.setTrabajadorSexo(Boolean.TRUE);
        this.selected.setTrabajadorSubsidioJoven(Boolean.FALSE);
        this.selected.setTrabajadorIdEstadoCivil(this.trabajadorIdEstadoCivilController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdAsignacionFamiliar(this.trabajadorIdAsignacionFamiliarController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdCentroCosto(this.trabajadorIdCentroCostoController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdComuna(this.trabajadorIdComunaController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdSindicato(this.trabajadorIdSindicatoController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdTipoCotizacionTrabajador(this.trabajadorIdTipoCotizacionTrabajadorController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdInstitucionSalud(this.trabajadorIdInstitucionSaludController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorFechaNacimiento(this.minFechaNacimiento);
        this.selected.setTrabajadorIdInstitucionApv(this.trabajadorIdInstitucionApvController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
        this.selected.setTrabajadorFormaPagoApv(Boolean.TRUE);
        this.selected.setTrabajadorMontoApv(0);
        this.selected.setTrabajadorFonasa(Boolean.TRUE);
        this.selected.setTrabajadorPoseeApv(Boolean.FALSE);
        this.selected.setTrabajadorPoseeCargas(Boolean.FALSE);
        this.selected.setTrabajadorPoseeCuentaBanco(Boolean.FALSE);
        this.selected.setTrabajadorJubilado(Boolean.FALSE);
        this.selected.setTrabajadorIps(Boolean.FALSE);
        this.selected.setTrabajadorContratado(Boolean.FALSE);
        this.setRegimenPrevisional(0);
        return this.selected;
    }

    public void saveNew(ActionEvent event) {

        if (!errorTrabajador) {
            switch (this.regimenPrevisional) {
                case 0:
                    break;
                case 1:
                    this.selected.setTrabajadorJubilado(Boolean.TRUE);
                    this.selected.setTrabajadorIps(Boolean.FALSE);
                    this.selected.setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                case 2:
                    this.selected.setTrabajadorIps(Boolean.TRUE);
                    this.selected.setTrabajadorJubilado(Boolean.FALSE);
                    this.selected.setTrabajadorIdInstitucionPrevision(this.trabajadorIdInstitucionPrevisionController.getItems().stream().findFirst().get());
                    break;
                default:
                    break;
            }
            Trabajador t = new TrabajadorDaoImpl().create(selected);

            if (t != null) {
                this.items.add(t);
                JsfUtil.addSuccessMessage("Se ha registrado el trabajador código: " + t.getTrabajadorCodigo());
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
            }

        } else {
            JsfUtil.addErrorMessage("El rut se enncuentra registrado");
        }
    }

    public void save(ActionEvent event) {
        if (this.selected != null) {
            Trabajador t = new TrabajadorDaoImpl().update(selected);
            if (t != null) {
                JsfUtil.addSuccessMessage("Se ha actualizado el trabajador código: " + t.getTrabajadorCodigo());
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
            }
        }

    }

    public void delete(ActionEvent event) {
        if (this.selected != null) {

        }
    }

    public void findTrabajador() {
        if (!this.selected.getTrabajadorRut().equals("")) {
            FacesMessage msg = new FacesMessage("Rut ya registrado", "Error de validación");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            Trabajador auxTrabajador = new TrabajadorDaoImpl().findByTrabajadorRutAndCuenta(this.selected.getTrabajadorRut(), this.getUserCount());
            if (auxTrabajador != null) {
                this.errorTrabajador = Boolean.TRUE;
                JsfUtil.addErrorMessage("Rut ya registrado");
            } else {
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

    public void setItems(List<Trabajador> items) {
        this.items = items;
    }

    public List<Trabajador> getItems() {
        return items;
    }

    public void setSelected(Trabajador selected) {
        this.selected = selected;
    }

    public Trabajador getSelected() {
        return selected;
    }

}
