package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.RecaudacionDataModel;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Named(value = "recaudacionGuiaController")
@ViewScoped
public class RecaudacionGuiaController extends AbstractController<RecaudacionGuia> {

    @Inject
    private EgresoController recaudacionGuiaIdEgresoController;
    @Inject
    private GuiaController recaudacionGuiaIdGuiaController;
    @Inject
    private RecaudacionController recaudacionGuiaIdRecaudacionController;

    private RecaudacionDataModel model;
    private ArrayList<LinkedHashMap> listOfMaps;
    private Date fecha;
    private CajaRecaudacion cajaRecaudacion;
    private List<CajaRecaudacion> cajaRecaudacionItems;
    private ArrayList<String> resultsHeader;
    private List<String> resultsTotals;
    private int totalRecaudacion;
    private RecaudacionGuiaHelper selectedItem;

    private List<Bus> busItems;
    private List<Trabajador> trabajadorItems;

    private List<Recaudacion> items;
    private List<RecaudacionGuiaHelper> selectedItems;
    private List<RecaudacionGuiaHelper> itemsRecaudacion;

    private RecaudacionGuiaHelper selectedRecaudacion;
    private final NumberFormat nf = NumberFormat.getInstance();

    private int totalAdministracion = 0;
    private int totalCovid = 0;
    private int totalBoletos = 0;
    private int totalImposiciones = 0;
    private int totalFam = 0;
    private int totalVarios = 0;

    private int cantidadBoletos = 0;
    private int guiasAnuladas = 0;

    private Map<String, Integer> boletos;
    private String cadenaBoletos = "";

    /*
        -   Buscar las últimas ventas de boletos por cada bus que compró un boleto
        -   Buscar los buses/conductores que no pagaron imposiciones
        -   Buscar total recaudación mayor a un rango promedio
        -   Mostrar inventario de boletos restantes por caja
        -   
     */
    List<RecaudacionGuia> g;

    LocalDate f;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM", new Locale("es", "PE"));

    public RecaudacionGuiaController() {
        // Inform the Abstract parent controller of the concrete RecaudacionGuia Entity
        super(RecaudacionGuia.class);
    }

    @PostConstruct
    public void init() {
        this.fecha = new Date();
        this.itemsRecaudacion = new ArrayList<>();
        this.cajaRecaudacionItems = new ICajaRecaudacionDaoImpl().findAllActive();
    }

    public void load() {
        if (this.cajaRecaudacion != null) {

            this.selectedItems = new ArrayList<>();

            this.boletos = new HashMap();

            this.totalAdministracion = 0;
            this.totalBoletos = 0;
            this.totalCovid = 0;
            this.totalVarios = 0;
            this.totalFam = 0;
            this.totalImposiciones = 0;
            this.totalRecaudacion = 0;
            this.cantidadBoletos = 0;
            this.guiasAnuladas = 0;
            this.trabajadorItems = new TrabajadorDaoImpl().findNandu();
            this.busItems = new IBusDaoImpl().findByProceso(new IProcesoRecaudacionDaoImpl().findById(2));
            this.items = new IRecaudacionDaoImpl().findByCajaFechaRecaudacion(cajaRecaudacion, fecha);
            this.itemsRecaudacion = new ArrayList<RecaudacionGuiaHelper>();
            if (!this.items.isEmpty()) {
                this.totalRecaudacion = 0;
                for (Recaudacion g : this.items) {
                    if (!g.getRecaudacionGuiaList().isEmpty()) {
                        RecaudacionGuiaHelper h = new RecaudacionGuiaHelper(g);
                        this.totalRecaudacion = this.totalRecaudacion + h.total;
                        this.itemsRecaudacion.add(h);

                        this.totalAdministracion = this.totalAdministracion + h.administracion;
                        this.totalBoletos = this.totalBoletos + h.boletos;

                        if (h.total == 0) {
                            this.guiasAnuladas++;
                        }

                        this.totalCovid = this.totalCovid + h.covid;
                        this.totalImposiciones = this.totalImposiciones + h.imposiciones;
                        this.totalFam = this.totalFam + h.fam;
                        this.totalVarios = this.totalVarios + h.varios;

                        if (!g.getRecaudacionBoletoList().isEmpty()) {
                            for (RecaudacionBoleto rb : g.getRecaudacionBoletoList()) {
                                this.cantidadBoletos++;

                                String nombreBoleto = rb.getRecaudacionBoletoIdVentaBoleto().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto().getBoletoSigla();
                                if (this.boletos.containsKey(nombreBoleto)) {
                                    this.boletos.put(nombreBoleto, this.boletos.get(nombreBoleto) + 1);
                                } else {
                                    this.boletos.put(nombreBoleto, 1);
                                }
                            }
                        }

                    }
                }

                boletos.forEach((k, v) -> this.cadenaBoletos = this.cadenaBoletos + k + ":" + v + " ");

                this.model = new RecaudacionDataModel(this.itemsRecaudacion);

                JsfUtil.addSuccessMessage("Se han encontrado " + this.itemsRecaudacion.size() + " guías");

            } else {
                JsfUtil.addErrorMessage("No se han encontrado guías ");
                this.model = new RecaudacionDataModel(new ArrayList<>());
                this.totalAdministracion = 0;
                this.totalBoletos = 0;
                this.totalCovid = 0;
                this.totalImposiciones = 0;
                this.totalRecaudacion = 0;
                this.totalVarios = 0;
                this.totalFam = 0;
            }
        }
        //this.g = this.items.stream().filter(distinctByKey)

    }

    public List<RecaudacionGuiaHelper> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<RecaudacionGuiaHelper> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void setBoletos(Map boletos) {
        this.boletos = boletos;
    }

    public Map getBoletos() {
        return boletos;
    }

    public void setCadenaBoletos(String cadenaBoletos) {
        this.cadenaBoletos = cadenaBoletos;
    }

    public String getCadenaBoletos() {
        return cadenaBoletos;
    }

    public void setSelectedRecaudacion(RecaudacionGuiaHelper selectedRecaudacion) {
        this.selectedRecaudacion = selectedRecaudacion;
    }

    public void setBusItems(List<Bus> busItems) {
        this.busItems = busItems;
    }

    public List<Bus> getBusItems() {
        return busItems;
    }

    public void setTrabajadorItems(List<Trabajador> trabajadorItems) {
        this.trabajadorItems = trabajadorItems;
    }

    public List<Trabajador> getTrabajadorItems() {
        return trabajadorItems;
    }

    public RecaudacionGuiaHelper getSelectedRecaudacion() {
        return selectedRecaudacion;
    }

    public int getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public int getTotalImposiciones() {
        return totalImposiciones;
    }

    public int getTotalCovid() {
        return totalCovid;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public int getTotalAdministracion() {
        return totalAdministracion;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public void setTotalVarios(int totalVarios) {
        this.totalVarios = totalVarios;
    }

    public int getTotalVarios() {
        return totalVarios;
    }

    public void setTotalFam(int totalFam) {
        this.totalFam = totalFam;
    }

    public int getTotalFam() {
        return totalFam;
    }

    public int getGuiasAnuladas() {
        return guiasAnuladas;
    }

    public void setGuiasAnuladas(int guiasAnuladas) {
        this.guiasAnuladas = guiasAnuladas;
    }

    public RecaudacionGuiaHelper getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RecaudacionGuiaHelper selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getFechaCompleta() {
        LocalDate date = LocalDate.from(this.fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return date.format(formatter);
    }

    public void delete(ActionEvent event) {
        if (this.selectedItem != null) {
            Recaudacion g = selectedItem.recaudacion;
            for (RecaudacionGuia rg : g.getRecaudacionGuiaList()) {
                rg.setRecaudacionGuiaMonto(0);
            }

            this.totalAdministracion = this.totalAdministracion - selectedItem.administracion;
            this.totalBoletos = this.totalBoletos - selectedItem.boletos;
            this.totalImposiciones = this.totalImposiciones - selectedItem.imposiciones;
            this.totalFam = this.totalFam - selectedItem.fam;
            this.totalVarios = this.totalVarios - selectedItem.varios;
            this.totalCovid = this.totalCovid - selectedItem.covid;

            selectedItem.administracion = 0;
            selectedItem.boletos = 0;
            selectedItem.imposiciones = 0;
            selectedItem.covid = 0;
            selectedItem.fam = 0;
            selectedItem.varios = 0;
            this.totalRecaudacion = this.totalRecaudacion - selectedItem.total;

            selectedItem.total = 0;

            Recaudacion rr = new IRecaudacionDaoImpl().update(g);
            g.setRecaudacionTotal(0);
            if (rr != null) {
                JsfUtil.addSuccessMessage("Se ha cancelado la recaudacion #" + rr.getRecaudacionId());
            } else {
                JsfUtil.addErrorMessage("Ha ocurrido un error");
            }

            this.selectedItem = null;
        } else {
            JsfUtil.addErrorMessage("Debe seleccionar la recaudación");
            this.selectedItem = null;
        }
    }

    public void saveRecaudacion() {
        if (this.selectedItem != null) {
            try {
                int total = 0;
                for (RecaudacionGuia g : selectedItem.recaudacion.getRecaudacionGuiaList()) {
                    int diferencia = 0;
                    switch (g.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                        case 1:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.administracion;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalAdministracion -= diferencia;

                            g.setRecaudacionGuiaMonto(selectedItem.administracion);
                            System.err.println("SE ACTUALIZÓ ADMIN:" + this.totalAdministracion);
                            break;
                        case 2:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.covid;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalCovid -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.covid);
                            System.err.println("SE ACTUALIZÓ COVID:" + this.totalCovid);

                            break;
                        case 3:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.imposiciones;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalImposiciones -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.imposiciones);
                            System.err.println("SE ACTUALIZÓ IMPOS.:" + this.totalImposiciones);

                            break;
                        case 4:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.boletos;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalBoletos -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.boletos);
                            System.err.println("SE ACTUALIZÓ BOLETOS:" + this.totalBoletos);

                            break;
                        case 5:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.fam;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalFam -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.fam);
                            System.err.println("SE ACTUALIZÓ FAM:" + this.totalFam);

                            break;
                        case 6:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.varios;

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalVarios -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.varios);
                            System.err.println("SE ACTUALIZÓ VARIOS:" + this.totalVarios);

                            break;
                    }
                    total += g.getRecaudacionGuiaMonto();
                }
                this.setTotal();
                this.selectedItem.setTotal(total);
                new IRecaudacionDaoImpl().update(selectedItem.recaudacion);
                new IGuiaDaoImpl().update(selectedItem.guia);
                JsfUtil.addSuccessMessage("Se ha actualizado la Recaudación: " + selectedItem.recaudacion.getRecaudacionId());

            } catch (Exception e) {
                JsfUtil.addErrorMessage("Ha ocurrido un error al registrar los cambios");
            }
        }
    }

    public void setTotal() {
        this.totalRecaudacion = this.totalAdministracion + this.totalBoletos + this.totalCovid + this.totalFam + this.totalImposiciones + this.totalVarios;
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedGuias()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " recaudaciones seleccionadas" : "1 recaudación seleccionada";
        }

        return "Eliminar";
    }

    public boolean hasSelectedGuias() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void deleteSelectedGuias() {
        if (hasSelectedGuias()) {
            for (RecaudacionGuiaHelper r : this.selectedItems) {

                if (r.total != 0) {
                    for (RecaudacionGuia rg : r.recaudacion.getRecaudacionGuiaList()) {
                        rg.setRecaudacionGuiaMonto(0);
                    }

                    this.totalAdministracion = this.totalAdministracion - r.administracion;
                    this.totalBoletos = this.totalBoletos - r.boletos;
                    this.totalImposiciones = this.totalImposiciones - r.imposiciones;
                    this.totalFam = this.totalFam - r.fam;
                    this.totalVarios = this.totalVarios - r.varios;
                    this.totalCovid = this.totalCovid - r.covid;

                    r.administracion = 0;
                    r.boletos = 0;
                    r.imposiciones = 0;
                    r.covid = 0;
                    r.fam = 0;
                    r.varios = 0;
                    this.totalRecaudacion = this.totalRecaudacion - r.total;

                    r.total = 0;

                    Recaudacion rr = new IRecaudacionDaoImpl().update(r.recaudacion);
                    r.setTotal(0);
                    JsfUtil.addSuccessMessage("Se ha cancelado la recaudacion #" + rr.getRecaudacionId());
                } else {
                    JsfUtil.addWarningMessage("La recaudacion # " + r.recaudacion.getRecaudacionId() + " ya se encuentra anulada");
                }
            }
            this.selectedItems = new ArrayList<>();
        }
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        recaudacionGuiaIdEgresoController.setSelected(null);
        recaudacionGuiaIdGuiaController.setSelected(null);
        recaudacionGuiaIdRecaudacionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Egreso controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionGuiaIdEgreso(ActionEvent event) {
        RecaudacionGuia selected = this.getSelected();
        if (selected != null && recaudacionGuiaIdEgresoController.getSelected() == null) {
            recaudacionGuiaIdEgresoController.setSelected(selected.getRecaudacionGuiaIdEgreso());
        }
    }

    /**
     * Sets the "selected" attribute of the Guia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionGuiaIdGuia(ActionEvent event) {
        RecaudacionGuia selected = this.getSelected();
        if (selected != null && recaudacionGuiaIdGuiaController.getSelected() == null) {
            recaudacionGuiaIdGuiaController.setSelected(selected.getRecaudacionGuiaIdGuia());
        }
    }

    /**
     * Sets the "selected" attribute of the Recaudacion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRecaudacionGuiaIdRecaudacion(ActionEvent event) {
        RecaudacionGuia selected = this.getSelected();
        if (selected != null && recaudacionGuiaIdRecaudacionController.getSelected() == null) {
            recaudacionGuiaIdRecaudacionController.setSelected(selected.getRecaudacionGuiaIdRecaudacion());
        }
    }

    public void setModel(RecaudacionDataModel model) {
        this.model = model;
    }

    public RecaudacionDataModel getModel() {
        return model;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    public List<CajaRecaudacion> getCajaRecaudacionItems() {
        return cajaRecaudacionItems;
    }

    public void setCajaRecaudacionItems(List<CajaRecaudacion> cajaRecaudacionItems) {
        this.cajaRecaudacionItems = cajaRecaudacionItems;
    }

    public ArrayList<String> getResultsHeader() {
        return resultsHeader;
    }

    public void setResultsHeader(ArrayList<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    public void setItemsRecaudacion(List<RecaudacionGuiaHelper> itemsRecaudacion) {
        this.itemsRecaudacion = itemsRecaudacion;
    }

    public List<RecaudacionGuiaHelper> getItemsRecaudacion() {
        return itemsRecaudacion;
    }

    public String getFormatValue(int val) {
        return nf.format(val);
    }

    public void loadGuia() {

    }

    public void findFolio() {

    }

    public void handleBusChange() {

    }

    public static class RecaudacionGuiaHelper implements Serializable {

        private Integer id;

        private int administracion;
        private int covid;
        private int boletos;
        private int imposiciones;
        private int fam;
        private int varios;
        private int total;

        private Recaudacion recaudacion;

        private Guia guia;

        public RecaudacionGuiaHelper() {
        }

        public RecaudacionGuiaHelper(Recaudacion recaudacion) {
            this.id = recaudacion.getRecaudacionId();
            this.recaudacion = recaudacion;
            for (RecaudacionGuia g : recaudacion.getRecaudacionGuiaList()) {

                if (guia != g.getRecaudacionGuiaIdGuia()) {
                    guia = g.getRecaudacionGuiaIdGuia();
                }

                switch (g.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                    case 1:
                        this.administracion = g.getRecaudacionGuiaMonto();
                        break;
                    case 2:
                        this.covid = g.getRecaudacionGuiaMonto();
                        break;
                    case 3:
                        this.imposiciones = g.getRecaudacionGuiaMonto();
                        break;
                    case 4:
                        this.boletos = g.getRecaudacionGuiaMonto();
                        break;

                    case 5:
                        this.fam = g.getRecaudacionGuiaMonto();
                        break;
                    case 6:
                        this.varios = g.getRecaudacionGuiaMonto();
                        break;
                }

            }
            this.total = this.administracion + this.covid + this.imposiciones + this.boletos + this.fam + this.varios;
        }

        public Guia getGuia() {
            return guia;
        }

        public void setGuia(Guia guia) {
            this.guia = guia;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public int getAdministracion() {
            return administracion;
        }

        public void setAdministracion(int administracion) {
            this.administracion = administracion;
        }

        public int getCovid() {
            return covid;
        }

        public void setCovid(int covid) {
            this.covid = covid;
        }

        public int getBoletos() {
            return boletos;
        }

        public void setBoletos(int boletos) {
            this.boletos = boletos;
        }

        public int getImposiciones() {
            return imposiciones;
        }

        public void setImposiciones(int imposiciones) {
            this.imposiciones = imposiciones;
        }

        public void setFam(int fam) {
            this.fam = fam;
        }

        public int getFam() {
            return fam;
        }

        public void setVarios(int varios) {
            this.varios = varios;
        }

        public int getVarios() {
            return varios;
        }

        public Recaudacion getRecaudacion() {
            return recaudacion;
        }

        public void setRecaudacion(Recaudacion Recaudacion) {
            this.recaudacion = Recaudacion;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

    }

}
