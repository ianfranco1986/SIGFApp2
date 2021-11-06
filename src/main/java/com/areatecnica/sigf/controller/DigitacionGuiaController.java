/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.dao.*;
import com.areatecnica.sigf.dao.impl.*;
import com.areatecnica.sigf.entities.*;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionGuia;
import com.areatecnica.sigf.models.RecaudacionDataModel;
import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IBusDaoImpl;
import com.areatecnica.sigf.dao.impl.IRecaudacionDaoImpl;
import com.areatecnica.sigf.models.DigitacionDataModel; 
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import javax.inject.Named;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ian
 */
@Named(value = "digitacionGuiaController")
@ViewScoped
public class DigitacionGuiaController extends AbstractController<Recaudacion> {

    
    
    private List<Recaudacion> recaudacionItems;
    private List<RecaudacionGuia> recaudacionGuiaList;
    private List<Egreso> egresosList;
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    private List<CajaRecaudacion> cajaRecaudacionList;
//    private List<EgresoResumenRecaudacion> egresosResumenList;
    private List<Bus> busesList;
    private List<PetroleoHelper> ventaCombustibleList;
    private List<MinutosHelper> registroMinutoList;
    private ArrayList<LinkedHashMap> listOfMaps;
    private LinkedHashMap guiaLink;
    private LinkedHashMap totals;
    private List<String> resultsHeader;
    private List<String> resultsTotals;
    private Map folios;
    private Recaudacion recaudacionPrinter;
    private ProcesoRecaudacion procesoRecaudacion;
    private ResumenRecaudacion resumenRecaudacion;
    private CajaRecaudacion cajaRecaudacion;
    private DigitacionDataModel model;
    private MinutosHelper registroMinuto;
    private PetroleoHelper ventaCombustible;
    private String minutos;
    private String petroleo;
    private Date fechaRecaudacion;
    private Boolean permitirEgresoFlota;
    private Boolean permitirEgresoBus;
    private Boolean permitirEgresoProceso;
    private Boolean estadoProceso;
    private int resumenTotal;
    private String resumenTotalFormat;
    private IRecaudacionDao recaudacionDao;
    private IProcesoRecaudacionDao procesoDao;
    private ICajaRecaudacionDao cajaRecaudacionDao;
    private IBusDao busDao;
    private IResumenRecaudacionDao resumenRecaudacionDao;
    private IRegistroMinutoDao registroMinutoDao;
    private IVentaCombustibleDao ventaCombustibleDao;
    private static String pattern = "###,###.###";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private ServletOutputStream servletOutputStream;

    /**
     * Initialize the concrete Region controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    public void init() {
        this.cajaRecaudacionDao = new ICajaRecaudacionDaoImpl();
        this.setCajaRecaudacionList((List<CajaRecaudacion>) this.cajaRecaudacionDao.findAll());

        if (this.getCajaRecaudacionList().size() == 1) {
            this.setCajaRecaudacion(this.getCajaRecaudacionList().get(0));

            handleCajaRecaudacionChange(null);
        }

        this.setPermitirEgresoBus(Boolean.TRUE);
        this.setPermitirEgresoFlota(Boolean.TRUE);
        this.setPermitirEgresoProceso(Boolean.TRUE);
        this.setResumenRecaudacion(new ResumenRecaudacion());
        this.getResumenRecaudacion().setResumenRecaudacionCerrado(Boolean.FALSE);
        this.setEstadoProceso(Boolean.FALSE);
        this.ventaCombustibleDao = new IVentaCombustibleDaoImpl();
        this.registroMinutoDao = new IRegistroMinutoDaoImpl();
    }

    public DigitacionGuiaController() {
        // Inform the Abstract parent controller of the concrete Region Entity
        super(Recaudacion.class);
        this.fechaRecaudacion = new Date();
    }

    /**
     * @return the recaudacionItems
     */
    public List<Recaudacion> getRecaudacionItems() {
        return recaudacionItems;
    }

    /**
     * @param recaudacionItems the recaudacionItems to set
     */
    public void setRecaudacionItems(List<Recaudacion> recaudacionItems) {
        this.recaudacionItems = recaudacionItems;
    }

    /**
     * @return the egresosList
     */
    public List<Egreso> getEgresosList() {
        return egresosList;
    }

    /**
     * @param egresosList the egresosList to set
     */
    public void setEgresosList(List<Egreso> egresosList) {
        this.egresosList = egresosList;
    }

    /**
     * @return the procesoRecaudacionList
     */
    public List<ProcesoRecaudacion> getProcesoRecaudacionList() {
        return procesoRecaudacionList;
    }

    /**
     * @param procesoRecaudacionList the procesoRecaudacionList to set
     */
    public void setProcesoRecaudacionList(List<ProcesoRecaudacion> procesoRecaudacionList) {
        this.procesoRecaudacionList = procesoRecaudacionList;
    }

    /**
     * @return the cajaRecaudacionList
     */
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    /**
     * @param cajaRecaudacionList the cajaRecaudacionList to set
     */
    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

//    /**
//     * @return the egresosResumenList
//     */
//    public List<EgresoResumenRecaudacion> getEgresosResumenList() {
//        return egresosResumenList;
//    }
//
//    /**
//     * @param egresosResumenList the egresosResumenList to set
//     */
//    public void setEgresosResumenList(List<EgresoResumenRecaudacion> egresosResumenList) {
//        this.egresosResumenList = egresosResumenList;
//    }

    /**
     * @return the busesList
     */
    public List<Bus> getBusesList() {
        return busesList;
    }

    /**
     * @param busesList the busesList to set
     */
    public void setBusesList(List<Bus> busesList) {
        this.busesList = busesList;
    }

    /**
     * @return the listOfMaps
     */
    public ArrayList<LinkedHashMap> getListOfMaps() {
        return listOfMaps;
    }

    /**
     * @param listOfMaps the listOfMaps to set
     */
    public void setListOfMaps(ArrayList<LinkedHashMap> listOfMaps) {
        this.listOfMaps = listOfMaps;
    }

    /**
     * @return the guiaLink
     */
    public LinkedHashMap getGuiaLink() {
        return guiaLink;
    }

    /**
     * @param guiaLink the guiaLink to set
     */
    public void setGuiaLink(LinkedHashMap guiaLink) {
        this.guiaLink = guiaLink;
    }

    
    public void setTotal(){
        
    }
    
    /**
     * @return the totals
     */
    public LinkedHashMap getTotals() {
        return totals;
    }

    /**
     * @param totals the totals to set
     */
    public void setTotals(LinkedHashMap totals) {
        this.totals = totals;
    }

    /**
     * @return the resultsHeader
     */
    public List<String> getResultsHeader() {
        return resultsHeader;
    }

    /**
     * @param resultsHeader the resultsHeader to set
     */
    public void setResultsHeader(List<String> resultsHeader) {
        this.resultsHeader = resultsHeader;
    }

    /**
     * @return the resultsTotals
     */
    public List<String> getResultsTotals() {
        return resultsTotals;
    }

    /**
     * @param resultsTotals the resultsTotals to set
     */
    public void setResultsTotals(List<String> resultsTotals) {
        this.resultsTotals = resultsTotals;
    }

    /**
     * @return the folios
     */
    public Map getFolios() {
        return folios;
    }

    /**
     * @param folios the folios to set
     */
    public void setFolios(Map folios) {
        this.folios = folios;
    }

    /**
     * @return the procesoRecaudacion
     */
    public ProcesoRecaudacion getProcesoRecaudacion() {
        return procesoRecaudacion;
    }

    /**
     * @param procesoRecaudacion the procesoRecaudacion to set
     */
    public void setProcesoRecaudacion(ProcesoRecaudacion procesoRecaudacion) {
        this.procesoRecaudacion = procesoRecaudacion;
    }

    /**
     * @return the resumenRecaudacion
     */
    public ResumenRecaudacion getResumenRecaudacion() {
        return resumenRecaudacion;
    }

    /**
     * @param resumenRecaudacion the resumenRecaudacion to set
     */
    public void setResumenRecaudacion(ResumenRecaudacion resumenRecaudacion) {
        this.resumenRecaudacion = resumenRecaudacion;
    }

    /**
     * @return the cajaRecaudacion
     */
    public CajaRecaudacion getCajaRecaudacion() {
        return cajaRecaudacion;
    }

    /**
     * @param cajaRecaudacion the cajaRecaudacion to set
     */
    public void setCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        this.cajaRecaudacion = cajaRecaudacion;
    }

    /**
     * @return the model
     */
    public DigitacionDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DigitacionDataModel model) {
        this.model = model;
    }

    /**
     * @return the fechaRecaudacion
     */
    public Date getFechaRecaudacion() {
        return fechaRecaudacion;
    }

    /**
     * @param fechaRecaudacion the fechaRecaudacion to set
     */
    public void setFechaRecaudacion(Date fechaRecaudacion) {
        this.fechaRecaudacion = fechaRecaudacion;
    }

    /**
     * @return the permitirEgresoFlota
     */
    public Boolean getPermitirEgresoFlota() {
        return permitirEgresoFlota;
    }

    /**
     * @param permitirEgresoFlota the permitirEgresoFlota to set
     */
    public void setPermitirEgresoFlota(Boolean permitirEgresoFlota) {
        this.permitirEgresoFlota = permitirEgresoFlota;
    }

    /**
     * @return the permitirEgresoBus
     */
    public Boolean getPermitirEgresoBus() {
        return permitirEgresoBus;
    }

    /**
     * @param permitirEgresoBus the permitirEgresoBus to set
     */
    public void setPermitirEgresoBus(Boolean permitirEgresoBus) {
        this.permitirEgresoBus = permitirEgresoBus;
    }

    /**
     * @return the permitirEgresoProceso
     */
    public Boolean getPermitirEgresoProceso() {
        return permitirEgresoProceso;
    }

    /**
     * @param permitirEgresoProceso the permitirEgresoProceso to set
     */
    public void setPermitirEgresoProceso(Boolean permitirEgresoProceso) {
        this.permitirEgresoProceso = permitirEgresoProceso;
    }

    /**
     * @return the estadoProceso
     */
    public Boolean getEstadoProceso() {
        return estadoProceso;
    }

    /**
     * @param estadoProceso the estadoProceso to set
     */
    public void setEstadoProceso(Boolean estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    /**
     * @return the resumenTotal
     */
    public int getResumenTotal() {
        return resumenTotal;
    }

    /**
     * @param resumenTotal the resumenTotal to set
     */
    public void setResumenTotal(int resumenTotal) {
        this.resumenTotal = resumenTotal;
    }

    /**
     * @return the resumenTotalFormat
     */
    public String getResumenTotalFormat() {
        return resumenTotalFormat;
    }

    /**
     * @param resumenTotalFormat the resumenTotalFormat to set
     */
    public void setResumenTotalFormat(String resumenTotalFormat) {
        this.resumenTotalFormat = resumenTotalFormat;
    }

    /**
     * @return the recaudacionEgresoList
     */
    public List<RecaudacionGuia> getRecaudacionGuiaList() {
        return recaudacionGuiaList;
    }

    /**
     * @param recaudacionEgresoList the recaudacionEgresoList to set
     */
    public void setRecaudacionGuiaList(List<RecaudacionGuia> recaudacionGuiaList) {
        this.recaudacionGuiaList = recaudacionGuiaList;
    }

    /**
     * @return the pattern
     */
    public static String getPattern() {
        return pattern;
    }

    /**
     * @param aPattern the pattern to set
     */
    public static void setPattern(String aPattern) {
        pattern = aPattern;
    }

    /**
     * @return the decimalFormat
     */
    public static DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @param aDecimalFormat the decimalFormat to set
     */
    public static void setDecimalFormat(DecimalFormat aDecimalFormat) {
        decimalFormat = aDecimalFormat;
    }

    /**
     * @return the ventaCombustibleList
     */
    public List<PetroleoHelper> getVentaCombustibleList() {
        return ventaCombustibleList;
    }

    /**
     * @param ventaCombustibleList the ventaCombustibleList to set
     */
    public void setVentaCombustibleList(List<PetroleoHelper> ventaCombustibleList) {
        this.ventaCombustibleList = ventaCombustibleList;
    }

    /**
     * @return the registroMinutoList
     */
    public List<MinutosHelper> getRegistroMinutoList() {
        return registroMinutoList;
    }

    /**
     * @param registroMinutoList the registroMinutoList to set
     */
    public void setRegistroMinutoList(List<MinutosHelper> registroMinutoList) {
        this.registroMinutoList = registroMinutoList;
    }

    /**
     * @return the registroMinuto
     */
    public MinutosHelper getRegistroMinuto() {
        return registroMinuto;
    }

    /**
     * @param registroMinuto the registroMinuto to set
     */
    public void setRegistroMinuto(MinutosHelper registroMinuto) {
        this.registroMinuto = registroMinuto;
    }

    /**
     * @return the ventaCombustible
     */
    public PetroleoHelper getVentaCombustible() {
        return ventaCombustible;
    }

    /**
     * @param ventaCombustible the ventaCombustible to set
     */
    public void setVentaCombustible(PetroleoHelper ventaCombustible) {
        this.ventaCombustible = ventaCombustible;
    }

    public void setPetroleo(String petroleo) {
        this.petroleo = petroleo;
    }

    public String getPetroleo() {
        return petroleo;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setRecaudacionPrinter(Recaudacion recaudacionPrinter) {
        this.recaudacionPrinter = recaudacionPrinter;
    }

    public Recaudacion getRecaudacionPrinter() {
        return recaudacionPrinter;
    }


    public Recaudacion prepareCreate(ActionEvent event) {
        super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
        this.resumenTotalFormat = "$ 0";
        this.setRecaudacionGuiaList(egresosRecaudacion(this.getSelected()));
        this.getSelected().setRecaudacionFecha(fechaRecaudacion);
        this.getSelected().setRecaudacionIdCaja(cajaRecaudacion);
        return this.getSelected();
    }

    private List<RecaudacionGuia> egresosRecaudacion(Recaudacion recaudacion) {
        List<RecaudacionGuia> egresoGuiaAuxList = new ArrayList<>();

        int i = 0;

        for (Egreso e : this.egresosList) {

            if (e.getEgresoActivo()) {
                RecaudacionGuia egresoGuia = new RecaudacionGuia();
                egresoGuia.setRecaudacionGuiaIdRecaudacion(recaudacion);
                egresoGuia.setRecaudacionGuiaIdEgreso(e);
                egresoGuia.setRecaudacionGuiaMonto(e.getEgresoValorDefecto());

                egresoGuiaAuxList.add(egresoGuia);
            }

            i++;
        }

        return egresoGuiaAuxList;
    }

    @Override
    public void save(ActionEvent event) {

        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setRecaudacionHora(new Date());
        this.getSelected().setRecaudacionGuiaList(recaudacionGuiaList);

        if (this.resumenRecaudacion.getResumenRecaudacionCerrado()) {
            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
            // CORRERGIR this.resumenRecaudacionFacade.edit(resumenRecaudacion);

        }

        if (this.ventaCombustible != null) {
            this.ventaCombustible.getVentaCombustible().setVentaCombustibleRecaudado(true);
            this.ventaCombustibleDao.update(this.ventaCombustible.getVentaCombustible());

            this.ventaCombustible = null;
            this.ventaCombustibleList = null;
        } else {
            System.err.println("Si es nula la venta de combustible");
        }

        if (this.registroMinuto != null) {
            if (this.registroMinuto.todos) {
                System.err.println("estan seleccionados todos los REGISTROs DE MINUTOs");
                this.registroMinutoList.stream().map((m) -> m.getRegistro()).map((registro) -> {
                    registro.setRegistroMinutoRecaudado(Boolean.TRUE);
                    return registro;
                }).forEachOrdered((registro) -> {
                    this.registroMinutoDao.update(registro);
                });

            } else {
                System.err.println("esta seleccionado sólo 1 REGISTRO DE MINUTO");

                RegistroMinuto registro = this.registroMinuto.getRegistro();
                registro.setRegistroMinutoRecaudado(Boolean.TRUE);
                this.registroMinutoDao.update(registro);

            }
            this.registroMinuto = null;
            this.registroMinutoList = null;
        } else {
            System.err.println("SI ES NULO EL REGISTRO DE MINUTO");
        }

        /*
         * Agrega nueva fila a la tabla
         *
         */
        LinkedHashMap auxLink = new LinkedHashMap();
        for (RecaudacionGuia eg : this.getSelected().getRecaudacionGuiaList()) {
            if (eg.getRecaudacionGuiaIdEgreso().getEgresoObligatorio()) {
                String key = eg.getRecaudacionGuiaIdEgreso().getEgresoNombre();

                if (totals.containsKey(key)) {
                    int aux = (int) totals.get(key);
                    aux += eg.getRecaudacionGuiaMonto();
                    totals.put(key, aux);
                } else {
                    totals.put(key, eg.getRecaudacionGuiaMonto());
                }
                auxLink.put(eg.getRecaudacionGuiaIdEgreso().getEgresoNombre(), eg.getRecaudacionGuiaMonto());
            }
        }

        this.getSelected().setLink(auxLink);
        this.getListOfMaps().add(this.getSelected().getLink());

        this.resultsTotals = new ArrayList<>();

        totals.values().stream().forEach((i) -> {
            this.resultsTotals.add(decimalFormat.format((int) i));
        });

//        //dudas acá
//        this.egresosResumenList.stream().forEach((eg) -> {
//            if (totals.containsKey(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre())) {
//                eg.setEgresoResumenRecaudacionTotal((int) totals.get(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre()));
//            }
//        });

        this.recaudacionItems.add(this.getSelected());

        //notificationController.pushNotify("/notify", "Se ha ingresado la Guia N°" + this.getSelected().getRecaudacionIdentificador());
        JsfUtil.addSuccessMessage("Se ha ingresado la Guia N°" + this.getSelected().getRecaudacionIdentificador());

        this.recaudacionPrinter = this.getSelected();
        this.setSelected(prepareCreate(event));
        this.setResumenTotalFormat(decimalFormat.format(getResumenTotal()));

    }

    public void load() {
        this.egresosList = new ArrayList<>();
        this.setListOfMaps(new ArrayList<>());
        this.setFolios(new HashMap<Integer, Integer>());
        this.setResultsHeader(new ArrayList<>());
        this.resultsTotals = new ArrayList<>();
        this.resumenRecaudacion = new ResumenRecaudacion();
        this.totals = new LinkedHashMap();

        /*NOMBRE DE LAS COLUMNAS*/
        if (this.permitirEgresoProceso) {
            for (EgresoProcesoRecaudacion epr : this.procesoRecaudacion.getEgresoProcesoRecaudacionList()) {

                if (epr.getEgresoProcesoRecaudacionIdEgreso().getEgresoObligatorio()) {
                    Egreso egreso = epr.getEgresoProcesoRecaudacionIdEgreso();
                    egreso.setEgresoValorDefecto(epr.getEgresoProcesoRecaudacionValorDefecto());
                    egreso.setEgresoPorcentaje(epr.getEgresoProcesoRecaudacionPorcentaje());
                    this.egresosList.add(egreso);
                }

            }
        } else {
            for (EgresoCajaRecaudacion ecr : this.cajaRecaudacion.getEgresoCajaRecaudacionList()) {
                Egreso egreso = ecr.getEgresoCajaRecaudacionIdEgreso();
                egreso.setEgresoValorDefecto(ecr.getEgresoCajaRecaudacionValorDefecto());
                egreso.setEgresoPorcentaje(ecr.getEgresoCajaRecaudacionPorcentaje());
                this.egresosList.add(egreso);
            }
        }

        this.recaudacionDao = new IRecaudacionDaoImpl();
        this.recaudacionItems = this.recaudacionDao.findByProcesoCajaFechaRecaudacion(procesoRecaudacion, cajaRecaudacion, fechaRecaudacion);
        JsfUtil.addSuccessMessage("Cantidad de Guías Registradas: " + this.recaudacionItems.size());

        /*Si la lista no está vacía*/
        if (!this.recaudacionItems.isEmpty()) {
            /*Proceso de carga de egresos a partir de cada guía*/
            for (Recaudacion g : this.recaudacionItems) {
                List<RecaudacionGuia> auxEgresosGuia = g.getRecaudacionGuiaList();
                LinkedHashMap auxLink = new LinkedHashMap();

                for (RecaudacionGuia eg : auxEgresosGuia) {
                    if (eg.getRecaudacionGuiaIdEgreso().getEgresoObligatorio()) {
                        String key = eg.getRecaudacionGuiaIdEgreso().getEgresoNombre();
                        this.getResultsHeader().add(key);
                        if (totals.containsKey(key)) {
                            int aux = (int) totals.get(key);
                            aux += eg.getRecaudacionGuiaMonto();
                            totals.put(key, aux);
                        } else {
                            totals.put(key, eg.getRecaudacionGuiaMonto());
                        }
                        auxLink.put(eg.getRecaudacionGuiaIdEgreso().getEgresoNombre(), eg.getRecaudacionGuiaMonto());
                    }
                }
                g.setLink(auxLink);
                this.getListOfMaps().add(g.getLink());
            }

            for (Object i : totals.values()) {
                resultsTotals.add(decimalFormat.format((int) i));
            }

        } else {
            LinkedHashMap auxLink = new LinkedHashMap();

            for (Egreso eg : this.egresosList) {
                if (eg.getEgresoObligatorio()) {
                    this.totals.put(eg.getEgresoNombre(), 0);
                    this.resultsTotals.add("0");
                    this.resultsHeader.add(eg.getEgresoNombre());
                    auxLink.put(eg.getEgresoNombre(), 0);
                }
            }

            this.listOfMaps.add(auxLink);
        }

        this.setModel(new DigitacionDataModel(recaudacionItems));

        this.setBusesList(busesProceso(this.procesoRecaudacion));
        JsfUtil.addSuccessMessage("N° de Buses en el Proceso: " + this.getBusesList().size());

        this.resumenRecaudacionDao = new IResumenRecaudacionDaoImpl();
        this.resumenRecaudacion = this.resumenRecaudacionDao.findByCajaProcesoDate(cajaRecaudacion, procesoRecaudacion, fechaRecaudacion);

//        if (this.resumenRecaudacion == null) {
//
//            this.resumenRecaudacion = new ResumenRecaudacion();
//            this.resumenRecaudacion.setResumenRecaudacionFechaIngreso(new Date());
//            this.resumenRecaudacion.setResumenRecaudacionFecha(fechaRecaudacion);
//            this.resumenRecaudacion.setResumenRecaudacionIdCaja(cajaRecaudacion);
//            this.resumenRecaudacion.setResumenRecaudacionCerrado(Boolean.FALSE);
//            this.resumenRecaudacion.setResumenRecaudacionIdProceso(procesoRecaudacion);
//            this.resumenRecaudacion.setResumenRecaudacionTotal(0);
//
//            this.egresosResumenList = new ArrayList<>();
//            this.egresosList.stream().map((eg) -> {
//                EgresoResumenRecaudacion egresoRecaudacion = new EgresoResumenRecaudacion();
//                egresoRecaudacion.setEgresoResumenRecaudacionIdResumen(resumenRecaudacion);
//                egresoRecaudacion.setEgresoResumenRecaudacionIdEgreso(eg);
//                return egresoRecaudacion;
//            }).map((egresoRecaudacion) -> {
//                egresoRecaudacion.setEgresoResumenRecaudacionTotal(0);
//                return egresoRecaudacion;
//            }).forEach((egresoRecaudacion) -> {
//                this.egresosResumenList.add(egresoRecaudacion);
//            });
//            //prueba
//            this.listOfMaps.add(new LinkedHashMap());
//            this.resumenRecaudacion.setEgresoResumenRecaudacionList(this.egresosResumenList);
//            this.resumenRecaudacionFacade.create(this.resumenRecaudacion);
//
//        } else {
//            this.egresosResumenList = this.resumenRecaudacion.getEgresoResumenRecaudacionList();
//
//            this.egresosResumenList.stream().forEach((EgresoResumenRecaudacion eg) -> {
//                int val = (int) totals.get(eg.getEgresoResumenRecaudacionIdEgreso().getEgresoNombre());
//                System.err.println(":" + val);
//                eg.setEgresoResumenRecaudacionTotal(val);
//            });
//            this.resumenRecaudacion.setEgresoResumenRecaudacionList(this.egresosResumenList);
//
//        }

        this.setResumenTotalFormat(decimalFormat.format(getResumenTotal()));
    }

    private List<Bus> busesProceso(ProcesoRecaudacion procesoRecaudacion) {
        this.busDao = new IBusDaoImpl();
        return this.busDao.findByProceso(procesoRecaudacion);
    }

    public void loadGuia() {
        if (this.getSelected() != null) {
            this.guiaLink = this.getSelected().getLink();
            this.recaudacionGuiaList = this.getSelected().getRecaudacionGuiaList();
            //this.porcentajesList = new ArrayList<PorcentajeHelper>();

            for (RecaudacionGuia eg : this.recaudacionGuiaList) {
                if (this.totals.containsKey(eg.getRecaudacionGuiaIdEgreso().getEgresoNombre())) {
                    int val = (int) this.totals.get(eg.getRecaudacionGuiaIdEgreso().getEgresoNombre());
                    val -= eg.getRecaudacionGuiaMonto();
                    this.totals.put(eg.getRecaudacionGuiaIdEgreso().getEgresoNombre(), val);
                }
            }
        }
    }

    public int calculaTotal() {
        int total = 0;

        for (RecaudacionGuia r : recaudacionGuiaList) {
            total = total + r.getRecaudacionGuiaMonto();
        }
        if (this.ventaCombustible != null) {
            total = total + ventaCombustible.getVentaCombustible().getVentaCombustibleTotal();
            if (this.ventaCombustible.getVentaCombustible().getVentaCombustibleIdBus() == null) {

            }
        }
        if (this.registroMinuto != null) {
            total = total + registroMinuto.getRegistro().getRegistroMinutoMonto();
            if (this.registroMinuto.getRegistro().getRegistroMinutoDesdeIdBus() == null) {

            }
        }
        this.getSelected().setRecaudacionTotal(total);
        this.resumenTotalFormat = decimalFormat.format(total);
        return total;
    }

    public int calculaTotalPetroleo() {
        int total = calculaTotal();

        if (this.ventaCombustible != null) {
            if (this.ventaCombustible.getVentaCombustible().getVentaCombustibleIdBus() == null) {
                this.petroleo = "Sin deuda";
            } else {
                total = total + ventaCombustible.getVentaCombustible().getVentaCombustibleTotal();
            }
        }

        this.getSelected().setRecaudacionTotal(total);

        return total;
    }

    public int calculaTotalMinutos() {
        int total = calculaTotal();

        if (this.registroMinuto != null) {
            if (this.registroMinuto.getRegistro().getRegistroMinutoDesdeIdBus() == null) {
                this.minutos = "Sin deuda";
            } else {
                total = total + registroMinuto.getRegistro().getRegistroMinutoMonto();
            }
        }

        this.getSelected().setRecaudacionTotal(total);

        return total;
    }

    public void handleCajaRecaudacionChange(ActionEvent event) {
        if (this.getCajaRecaudacion() != null) {
            this.setProcesoRecaudacionList(new ArrayList<ProcesoRecaudacion>());

            List<CajaProceso> cajaProcesoList = this.getCajaRecaudacion().getCajaProcesoList();

            for (CajaProceso cp : cajaProcesoList) {
                if (cp.getCajaProcesoActivo()) {
                    this.getProcesoRecaudacionList().add(cp.getCajaProcesoIdProceso());
                }
            }
        }
    }

    public void handleBusChange(ActionEvent event) {
//        if (this.getSelected().getRecaudacionIdBus() != null) {
//            this.registroMinutoList = null;
//            this.ventaCombustibleList = null;
//            this.minutos = "";
//            this.petroleo = "";
//            this.ventaCombustible = null;
//            this.registroMinuto = null;
//
//            List<RegistroMinuto> minutosList = this.registroMinutoDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());
//
//            if (minutosList.isEmpty()) {
//                //deshabilito el combobox
//                this.registroMinuto = null;
//            } else {
//                this.registroMinutoList = new ArrayList<>();
//                //Si tiene más de una deuda de minutos, busco el total 
//                if (minutosList.size() > 1) {
//
//                    int total = 0;
//                    for (RegistroMinuto m : minutosList) {
//                        total = total + m.getRegistroMinutoMonto();
//                        MinutosHelper minuto = new MinutosHelper();
//                        minuto.setRegistro(m);
//                        minuto.setObservacion("$ " + decimalFormat.format(m.getRegistroMinutoMonto()) + "   N° Bus: " + m.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + sdf.format(m.getRegistroMinutoFechaMinuto()));
//                        this.registroMinutoList.add(minuto);
//                    }
//                    //Creo un nuevo minutohelper con la suma de todos los minutos 
//                    RegistroMinuto r = new RegistroMinuto();
//                    r.setRegistroMinutoMonto(total);
//                    MinutosHelper totalMinutos = new MinutosHelper();
//                    totalMinutos.setObservacion("$ " + decimalFormat.format(total) + " Todos");
//                    totalMinutos.setRegistro(r);
//                    totalMinutos.setTodos(Boolean.TRUE);
//                    this.registroMinutoList.add(0, totalMinutos);
//                    this.registroMinuto = totalMinutos;
//                    calculaTotal();
//
//                } else {
//                    RegistroMinuto r = minutosList.get(0);
//                    MinutosHelper minuto = new MinutosHelper();
//                    minuto.setRegistro(r);
//                    minuto.setObservacion("$ " + decimalFormat.format(r.getRegistroMinutoMonto()) + "   N° Bus: " + r.getRegistroMinutoHastaIdBus().getBusNumero() + " - " + sdf.format(r.getRegistroMinutoFechaMinuto()));
//                    minuto.setTodos(Boolean.FALSE);
//                    this.registroMinutoList.add(minuto);
//                    this.registroMinuto = minuto;
//                    calculaTotal();
//                }
//
//            }
//
//            List<VentaCombustible> combustibleList = this.ventaCombustibleDao.findByBusSinRecaudar(this.getSelected().getRecaudacionIdBus());
//
//            if (combustibleList.isEmpty()) {
//                this.ventaCombustible = null;
//            } else {
//                this.ventaCombustibleList = new ArrayList<>();
//
//                for (VentaCombustible v : combustibleList) {
//                    PetroleoHelper vPetroleo = new PetroleoHelper();
//                    vPetroleo.setObservacion("$ " + decimalFormat.format(v.getVentaCombustibleTotal()) + " -  " + sdf.format(v.getVentaCombustibleFecha()));
//                    vPetroleo.setVentaCombustible(v);
//                    this.ventaCombustibleList.add(vPetroleo);
//                }
//
//                this.ventaCombustible = this.ventaCombustibleList.get(0);
//                calculaTotal();
//            }
//
//        }
    }

//    public void exportPdf2(ActionEvent event) {
//
//        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "inline; filename=VentasCombustible.pdf");
//
//        try {
//            servletOutputStream = httpServletResponse.getOutputStream();
//            AdhocStyle style = new AdhocStyle();
//
//            style.setHorizontalAlignment(AdhocHorizontalAlignment.RIGHT);
//
//            AdhocConfiguration configuration = new AdhocConfiguration();
//            AdhocReport report = new AdhocReport();
//            report.setColumnTitleStyle(style);
//            configuration.setReport(report);
//
//            //DRDataSource dataSource = new DRDataSource("numero", "identificador", "hora", "boleta", "bus", "ppu", "operador", "cantidad", "total");
//            AdhocSubtotal subtotal = new AdhocSubtotal();
//
//            AdhocColumn column = new AdhocColumn();
//            column.setName("numero");
//            report.addColumn(column);
//
//            column = new AdhocColumn();
//            column.setName("folio");
//            column.setWidth(70);
//            report.addColumn(column);
//
//            column = new AdhocColumn();
//            column.setName("bus");
//            report.addColumn(column);
//
//            column = new AdhocColumn();
//            column.setName("conductor");
//            column.setWidth(110);
//            report.addColumn(column);
//
//            AdhocFont font = new AdhocFont();
//
//            font.setBold(Boolean.TRUE);
//            style.setFont(font);
//            for (Egreso r : this.egresosList) {
//                column = new AdhocColumn();
//                column.setName(r.getEgresoNombre());
//                report.addColumn(column);
//
//                subtotal = new AdhocSubtotal();
//                subtotal.setCalculation(AdhocCalculation.SUM);
//                subtotal.setName(r.getEgresoNombre());
//                subtotal.setStyle(style);
//                report.addSubtotal(subtotal);
//            }
//
//            column = new AdhocColumn();
//            column.setName("liquido");
//            report.addColumn(column);
//
//            subtotal = new AdhocSubtotal();
//            subtotal.setCalculation(AdhocCalculation.SUM);
//            subtotal.setName("liquido");
//            subtotal.setStyle(style);
//            report.addSubtotal(subtotal);
//
//            JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(), new ReportCustomizer());
//            reportBuilder.setDataSource(createDataSource());
//            reportBuilder.toPdf(servletOutputStream);
//
//        } catch (IOException | DRException ex) {
//            Logger.getLogger(RegistroMinutoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        FacesContext.getCurrentInstance().responseComplete();
//
//        servletOutputStream = null;
//    }

//    private DRDataSource createDataSource() {
//
//        ArrayList<String> list = new ArrayList<>(resultsHeader);
//        list.add(0, "liquido");
//        list.add(0, "conductor");
//        list.add(0, "bus");
//        list.add(0, "folio");
//        list.add(0, "numero");
//
//        String[] array = new String[list.size()];
//
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (String) list.get(i);
//        }
//
//        DRDataSource dataSource = new DRDataSource(array);
//
//        int i = 0;
//        for (Recaudacion r : this.recaudacionItems) {
//
//            List a = new ArrayList();
//
//            a.add(i + 1);
//            a.add(r.getRecaudacionIdentificador());
//            a.add(r.getRecaudacionIdBus().getBusNumero());
//            a.add(r.getRecaudacionIdTrabajador().getTrabajadorApellidoPaterno());
//            a.add(r.getRecaudacionTotal());
//            a.addAll(this.listOfMaps.get(i).values());
//
//            dataSource.add(a.toArray());
//            i++;
//        }
//        return dataSource;
//    }

//    private class ReportCustomizer extends DefaultAdhocReportCustomizer {
//
//        /**
//         *
//         * If you want to add some fixed content to a report that is not needed
//         * to store in the xml file.
//         *
//         * For example you can add default page header, footer, default
//         * fonts,...
//         *
//         */
//        @Override
//
//        public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {
//            super.customize(report, adhocReport);
//            //default report values
//            report.setTemplate(Templates.reportTemplate);
//            report.title(Templates.createTitleComponent(cajaRecaudacion.getCajaRecaudacionNombre() + " Recaudación al :" + sdf.format(fechaRecaudacion)));
//            //a fixed page footer that user cannot change, this customization is not stored in the xml file
//            //report.pageFooter(Templates.footerComponent);
//            report.pageFooter(Templates.footerComponent);
//        }
//
//        /**
//         *
//         * This method returns a field type from a given field name.
//         *
//         */
//        @Override
//        protected DRIDataType<?, ?> getFieldType(String name) {
//            if (name.equals("numero") || name.equals("boleta") || name.equals("bus") || name.equals("total")) {
//                return type.integerType();
//            }
//
//            if (name.equals("conductor") || name.equals("ppu") || name.equals("operador")) {
//                return type.stringType();
//            }
//
//            if (name.equals("hora")) {
//                return type.timeHourToMinuteType();
//            }
//
//            return type.integerType();
//        }
//
//        /**
//         *
//         * If a user doesn’t specify a column title, getColumnTitle is evaluated
//         * and the return value is used as a column title.
//         *
//         */
//        @Override
//////DRDataSource("numero", "identificador", "hora", "boleta", "bus", "ppu", "operador", "cantidad", "total");
//        protected String getFieldLabel(String name) {
//            if (name.equals("numero")) {
//                return "#";
//            }
//            if (name.equals("liquido")) {
//                return "Líquido";
//            }
//            if (name.equals("folio")) {
//                return "Folio";
//            }
//            if (name.equals("bus")) {
//                return "N° Bus";
//            }
//            if (name.equals("conductor")) {
//                return "Conductor";
//            }
//            if (name.equals("operador")) {
//                return "Operador";
//            }
//            if (name.equals("cantidad")) {
//                return "N° Litros";
//            }
//            if (name.equals("total")) {
//                return "Total";
//            }
//            return name;
//        }
//
//    }

    public class MinutosHelper {

        private String observacion;
        private RegistroMinuto registro;
        private Boolean todos;

        public MinutosHelper(String observacion, RegistroMinuto registro, Boolean todos) {
            this.observacion = observacion;
            this.registro = registro;
            this.todos = todos;
        }

        public MinutosHelper() {
        }

        public String getObservacion() {
            return observacion;
        }

        public void setObservacion(String observacion) {
            this.observacion = observacion;
        }

        public RegistroMinuto getRegistro() {
            return registro;
        }

        public void setRegistro(RegistroMinuto registro) {
            this.registro = registro;
        }

        public void setTodos(Boolean todos) {
            this.todos = todos;
        }

        public Boolean getTodos() {
            return todos;
        }

    }

    public class PetroleoHelper {

        private String observacion;
        private VentaCombustible ventaCombustible;

        public PetroleoHelper() {
        }

        public PetroleoHelper(String observacion, VentaCombustible ventaCombustible) {
            this.observacion = observacion;
            this.ventaCombustible = ventaCombustible;
        }

        public VentaCombustible getVentaCombustible() {
            return ventaCombustible;
        }

        public void setVentaCombustible(VentaCombustible ventaCombustible) {
            this.ventaCombustible = ventaCombustible;
        }

        public String getObservacion() {
            return observacion;
        }

        public void setObservacion(String observacion) {
            this.observacion = observacion;
        }

    }
    
}
