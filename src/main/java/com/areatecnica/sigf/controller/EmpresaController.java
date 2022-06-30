package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.IEmpresaDao;
import com.areatecnica.sigf.dao.impl.EmpresaDaoImpl;
import com.areatecnica.sigf.entities.Empresa;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> {

    @Inject
    private CajaCompensacionController empresaIdCajaCompensacionController;
    @Inject
    private CuentaController empresaIdCuentaController;
    @Inject
    private MutualController empresaIdMutualController;

    private List<Empresa> items;

    private final IEmpresaDao dao;

    public EmpresaController() {
        // Inform the Abstract parent controller of the concrete Empresa Entity
        super(Empresa.class);
        this.setLimitedByCuenta(Boolean.TRUE);
        this.setNamedQuery("Empresa.findAllByCuenta");
        this.dao = new EmpresaDaoImpl();
        this.items = this.dao.findByCuenta(this.getUserCount());
    }

    @Override
    public Empresa prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.getSelected().setEmpresaIdCuenta(this.getUserCount());
        return getSelected();
    }

    public List<Empresa> getItems() {
        return items;
    }

    public void setItems(List<Empresa> items) {
        this.items = items;
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.saveNew(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void save(ActionEvent event) {
        if (this.getSelected() != null) {
            Empresa e = new EmpresaDaoImpl().update(this.getSelected());

            if (e != null) {
                JsfUtil.addSuccessMessage("Se han registrado los cambios ");
            } else {
                JsfUtil.addErrorMessage("Error al guardar los cambios");
            }
        }
    }

}
