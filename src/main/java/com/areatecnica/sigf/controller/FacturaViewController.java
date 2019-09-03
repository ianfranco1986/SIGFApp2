package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IFacturaDaoImpl;
import com.areatecnica.sigf.entities.Factura;
import com.areatecnica.sigf.models.FacturaDataModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.joda.time.DateTime;
import org.primefaces.event.RowEditEvent;

@Named(value = "facturaViewController")
@ViewScoped
public class FacturaViewController extends AbstractController<Factura> {

    private List<Factura> items;
    private FacturaDataModel model;

    private int mes;
    private int anio;
    private int total = 0;
    private int neto = 0;
    private int iva = 0;
    private int folio = 0;
    private Date desde;
    private Date hasta;
    private Date fechaMovimiento;
    private Date fechaLiquidacion;
    private DateTime dateTime;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    private NumberFormat nf = NumberFormat.getInstance();

    public FacturaViewController() {
        // Inform the Abstract parent controller of the concrete CargoLiquidacion Entity
        super(Factura.class);
    }

    @PostConstruct
    @Override
    public void initParams() {
        super.initParams(); //To change body of generated methods, choose Tools | Templates.

        Calendar cal = Calendar.getInstance();
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.anio = cal.get(Calendar.YEAR);
        this.fechaMovimiento = new Date();
        this.fechaLiquidacion = new Date();

        this.desde = new Date();
        setFecha();

        this.items = new ArrayList<>();
        this.model = new FacturaDataModel(items);
    }

    public void loadDocumento() {
        if (this.folio > 0) {
            Factura factura = new IFacturaDaoImpl().findByFolio(folio);
            if (factura != null) {
                this.items = new ArrayList<>();
                this.items.add(factura);
                this.model = new FacturaDataModel(items);

            } else {
                this.items = new ArrayList<>();
                this.model = new FacturaDataModel(items);
                JsfUtil.addWarningMessage("No se ha encontrado una factura con el folio: " + this.folio);
            }
        }
    }

    public void load() {
        setFecha();
        if (this.desde != null) {

            this.total = 0;
            this.neto = 0;
            this.iva = 0;

            this.items = new IFacturaDaoImpl().findBetweenDates(this.desde, this.hasta);
            this.model = new FacturaDataModel(items);

            if (!this.items.isEmpty()) {
                for (Factura f : this.items) {
                    this.total = this.total + f.getFacturaTotal();
                    this.neto = this.neto + f.getFacturaNeto();
                    this.iva = this.iva + f.getFacturaIva();
                }
                Factura factura = this.items.get(this.items.size() - 1);
                this.folio = factura.getFacturaFolio();

                this.getSelected().setFacturaFolio(folio + 1);
                JsfUtil.addSuccessMessage("Se han encontrado " + this.items.size() + " registros");

            } else {
                JsfUtil.addWarningMessage("No se han encontrado registros");
            }
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        if (this.getSelected() != null) {
            int folio = this.getSelected().getFacturaFolio();
            this.getSelected().setFacturaFecha(this.desde);
            this.items.add(this.getSelected());
            super.saveNew(event); //To change body of generated methods, choose Tools | Templates.

            this.total = this.total + this.getSelected().getFacturaTotal();
            this.neto = this.neto + this.getSelected().getFacturaNeto();
            this.iva = this.iva + this.getSelected().getFacturaIva();

            JsfUtil.addSuccessMessage("Se ha registrado una Factura");

            this.setSelected(prepareCreate(event));
            this.getSelected().setFacturaFolio(folio + 1);

        }
    }

    public void onRowEdit(RowEditEvent event) {
        Factura temp = (Factura) event.getObject();

        try {

            temp.setFacturaTotal(temp.getFacturaIva() + temp.getFacturaNeto());

            this.total = 0;
            this.neto = 0;
            this.iva = 0;

            for (Factura f : this.items) {
                this.total = this.total + f.getFacturaTotal();
                this.neto = this.neto + f.getFacturaNeto();
                this.iva = this.iva + f.getFacturaIva();
            }

            new IFacturaDaoImpl().update(temp);
            JsfUtil.addSuccessMessage("Se ha actualizado el registro");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ha ocurrido un error al guardar los cambios");
        }
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getFolio() {
        return folio;
    }

    public int getNeto() {
        return neto;
    }

    public void setNeto(int neto) {
        this.neto = neto;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public void setModel(FacturaDataModel model) {
        this.model = model;
    }

    public FacturaDataModel getModel() {
        return model;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void setItems(List<Factura> items) {
        this.items = items;
    }

    public List<Factura> getItems() {
        return items;
    }

    public void setFecha() {
        try {
            System.err.println("NUEVA FECHA:");
            this.desde = this.sdf.parse("01/" + this.mes + "/" + this.anio);
            this.dateTime = new DateTime(this.desde);
            this.hasta = this.dateTime.dayOfMonth().withMaximumValue().toDate();
        } catch (ParseException ex) {
        }
    }

}
