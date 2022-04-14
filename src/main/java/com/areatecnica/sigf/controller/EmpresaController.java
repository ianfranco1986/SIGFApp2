package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.dao.impl.IEmpresaDaoImpl;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import com.areatecnica.sigf.entities.RepresentanteEmpresa;
import com.areatecnica.sigf.entities.LiquidacionSueldo;
import com.areatecnica.sigf.entities.ReconocimientoDeuda;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RelacionLaboral;
import java.util.List;
import com.areatecnica.sigf.facade.EmpresaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> {

    @Inject
    private CajaCompensacionController empresaIdCajaCompensacionController;
    @Inject
    private CuentaController empresaIdCuentaController;
    @Inject
    private MutualController empresaIdMutualController;

    private IEmpresaDao dao;

    public EmpresaController() {
        // Inform the Abstract parent controller of the concrete Empresa Entity
        super(Empresa.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Empresa.findAllByCuenta");
        this.dao = new IEmpresaDaoImpl();
        this.setItems(this.dao.findByCuenta(this.getUserCount()));
    }

    @Override
    public Empresa prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEmpresaIdCuenta(this.getUserCount());
        return getSelected();
    }

}
