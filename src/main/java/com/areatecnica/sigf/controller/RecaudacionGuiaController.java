package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.dto.RecaudacionGuiaDTO;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.models.RecaudacionDataModel;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    private RecaudacionGuiaDTO selectedItem;

    private List<Bus> busItems;
    private List<Trabajador> trabajadorItems;

    private List<Recaudacion> items;
    private List<RecaudacionGuiaDTO> selectedItems;
    private List<RecaudacionGuiaDTO> itemsRecaudacion;

    private RecaudacionGuiaDTO selectedRecaudacion;
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
        this.cajaRecaudacionItems = new CajaRecaudacionDaoImpl().findAllActive();
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

            this.items = new RecaudacionDaoImpl().findByCajaFechaRecaudacion(cajaRecaudacion, fecha);
            this.itemsRecaudacion = new ArrayList<RecaudacionGuiaDTO>();
            if (!this.items.isEmpty()) {
                this.totalRecaudacion = 0;
                for (Recaudacion g : this.items) {
                    if (!g.getRecaudacionGuiaList().isEmpty()) {
                        RecaudacionGuiaDTO h = new RecaudacionGuiaDTO(g);
                        this.totalRecaudacion = this.totalRecaudacion + h.getTotal();
                        this.itemsRecaudacion.add(h);

                        this.totalAdministracion = this.totalAdministracion + h.getAdministracion();
                        this.totalBoletos = this.totalBoletos + h.getBoletos();

                        if (h.getTotal() == 0) {
                            this.guiasAnuladas++;
                        }

                        this.totalCovid = this.totalCovid + h.getCovid();
                        this.totalImposiciones = this.totalImposiciones + h.getImposiciones();
                        this.totalFam = this.totalFam + h.getFam();
                        this.totalVarios = this.totalVarios + h.getVarios();

//                        if (!g.getRecaudacionBoletoList().isEmpty()) {
//                            for (RecaudacionBoleto rb : g.getRecaudacionBoletoList()) {
//                                this.cantidadBoletos++;
//
//                                String nombreBoleto = rb.getRecaudacionBoletoIdVentaBoleto().getVentaBoletoIdInventarioCaja().getInventarioCajaIdInventarioInterno().getInventarioInternoIdBoleto().getBoletoSigla();
//                                if (this.boletos.containsKey(nombreBoleto)) {
//                                    this.boletos.put(nombreBoleto, this.boletos.get(nombreBoleto) + 1);
//                                } else {
//                                    this.boletos.put(nombreBoleto, 1);
//                                }
//                            }
//                        }

                    }
                }

//                boletos.forEach((k, v) -> this.cadenaBoletos = this.cadenaBoletos + k + ":" + v + " ");

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

    public void prepareToEdit() {
        if (this.selectedItem != null) {
//            this.trabajadorItems = new TrabajadorDaoImpl().findNandu();
//            this.busItems = new BusDaoImpl().findByProceso(new ProcesoRecaudacionDaoImpl().findById(2));
        }
    }

    public List<RecaudacionGuiaDTO> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<RecaudacionGuiaDTO> selectedItems) {
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

    public void setSelectedRecaudacion(RecaudacionGuiaDTO selectedRecaudacion) {
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

    public RecaudacionGuiaDTO getSelectedRecaudacion() {
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

    public RecaudacionGuiaDTO getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RecaudacionGuiaDTO selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getFechaCompleta() {
        LocalDate date = LocalDate.from(this.fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return date.format(formatter);
    }

    public void delete(ActionEvent event) {
        if (this.selectedItem != null) {
            Recaudacion g = selectedItem.getRecaudacion();
            for (RecaudacionGuia rg : g.getRecaudacionGuiaList()) {
                rg.setRecaudacionGuiaMonto(0);
            }

            this.totalAdministracion = this.totalAdministracion - selectedItem.getAdministracion();
            this.totalBoletos = this.totalBoletos - selectedItem.getBoletos();
            this.totalImposiciones = this.totalImposiciones - selectedItem.getImposiciones();
            this.totalFam = this.totalFam - selectedItem.getFam();
            this.totalVarios = this.totalVarios - selectedItem.getVarios();
            this.totalCovid = this.totalCovid - selectedItem.getCovid();

            selectedItem.setAdministracion(0);
            selectedItem.setBoletos(0);
            selectedItem.setImposiciones(0);
            selectedItem.setCovid(0);
            selectedItem.setFam(0);
            selectedItem.setVarios(0);
            this.totalRecaudacion = this.totalRecaudacion - selectedItem.getTotal();

            selectedItem.setTotal(0);

            Recaudacion rr = new RecaudacionDaoImpl().update(g);
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
                for (RecaudacionGuia g : selectedItem.getRecaudacion().getRecaudacionGuiaList()) {
                    int diferencia = 0;
                    switch (g.getRecaudacionGuiaIdEgreso().getEgresoId()) {
                        case 1:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getAdministracion();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalAdministracion -= diferencia;

                            g.setRecaudacionGuiaMonto(selectedItem.getAdministracion());
                            System.err.println("SE ACTUALIZÓ ADMIN:" + this.totalAdministracion);
                            break;
                        case 2:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getCovid();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalCovid -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.getCovid());
                            System.err.println("SE ACTUALIZÓ COVID:" + this.totalCovid);

                            break;
                        case 3:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getImposiciones();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalImposiciones -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.getImposiciones());
                            System.err.println("SE ACTUALIZÓ IMPOS.:" + this.totalImposiciones);

                            break;
                        case 4:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getBoletos();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalBoletos -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.getBoletos());
                            System.err.println("SE ACTUALIZÓ BOLETOS:" + this.totalBoletos);

                            break;
                        case 5:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getFam();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalFam -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.getFam());
                            System.err.println("SE ACTUALIZÓ FAM:" + this.totalFam);

                            break;
                        case 6:
                            diferencia = g.getRecaudacionGuiaMonto() - selectedItem.getVarios();

                            if (diferencia == 0) {
                                break;
                            }
                            this.totalVarios -= diferencia;
                            g.setRecaudacionGuiaMonto(selectedItem.getVarios());
                            System.err.println("SE ACTUALIZÓ VARIOS:" + this.totalVarios);

                            break;
                    }
                    total += g.getRecaudacionGuiaMonto();
                }
                this.setTotal();
                this.selectedItem.setTotal(total);
                new RecaudacionDaoImpl().update(selectedItem.getRecaudacion());
                new GuiaDaoImpl().update(selectedItem.getGuia());
                JsfUtil.addSuccessMessage("Se ha actualizado la Recaudación: " + selectedItem.getRecaudacion().getRecaudacionId());

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
            for (RecaudacionGuiaDTO r : this.selectedItems) {

                if (r.getTotal() != 0) {
                    for (RecaudacionGuia rg : r.getRecaudacion().getRecaudacionGuiaList()) {
                        rg.setRecaudacionGuiaMonto(0);
                    }

                    this.totalAdministracion = this.totalAdministracion - r.getAdministracion();
                    this.totalBoletos = this.totalBoletos - r.getBoletos();
                    this.totalImposiciones = this.totalImposiciones - r.getImposiciones();
                    this.totalFam = this.totalFam - r.getFam();
                    this.totalVarios = this.totalVarios - r.getVarios();
                    this.totalCovid = this.totalCovid - r.getCovid();

                    r.setAdministracion(0);
                    r.setBoletos(0);
                    r.setImposiciones(0);
                    r.setCovid(0);
                    r.setFam(0);
                    r.setVarios(0);
                    this.totalRecaudacion = this.totalRecaudacion - r.getTotal();

                    r.setTotal(0);

                    Recaudacion rr = new RecaudacionDaoImpl().update(r.getRecaudacion());
                    r.setTotal(0);
                    JsfUtil.addSuccessMessage("Se ha cancelado la recaudacion #" + rr.getRecaudacionId());
                } else {
                    JsfUtil.addWarningMessage("La recaudacion # " + r.getRecaudacion().getRecaudacionId() + " ya se encuentra anulada");
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

    public void setItemsRecaudacion(List<RecaudacionGuiaDTO> itemsRecaudacion) {
        this.itemsRecaudacion = itemsRecaudacion;
    }

    public List<RecaudacionGuiaDTO> getItemsRecaudacion() {
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

}
