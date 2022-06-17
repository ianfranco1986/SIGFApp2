/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.areatecnica.sigf.dto.services;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dto.CompraDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroCompraService {

    private CompraDTO dTO;
    private List<CompraDTO> list;
    private File file;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yy hh:mm");

    public RegistroCompraService(File file) {
        this.file = file;
        try {
            load();
        } catch (IOException ex) {
            Logger.getLogger(RegistroCompraService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() throws IOException {
        if (this.file != null) {

            this.list = Files.lines(this.file.toPath())
                    .skip(1)
                    .map(RegistroCompraService::getCompra)
                    .collect(Collectors.toList());

            if (this.list == null) {
                this.list = new ArrayList<>();
            }

        } else {
            JsfUtil.addErrorMessage("Error al leer el archivo");
        }
    }

    private void load2() {
        if (this.file != null) {
            CSVReader reader = null;

            try {
                reader = new CSVReader(new FileReader(file));

                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    for (String token : nextLine) {
                        System.err.println("token:" + token);
                    }
                    System.out.print("\n");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RegistroCompraService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | CsvValidationException ex) {
                Logger.getLogger(RegistroCompraService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static CompraDTO getCompra(String line) {

        int nro;
        int tipoDoc;
        String tipoCompra;
        String rutProveedor;
        String razonSocial;
        int folio;
        Date fechaDocto = null;
        Date fechaRecepcion = null;
        Date fechaAcuse = null;
        int montoExento;
        int montoNeto;
        int montoIVARecuperable;
        int montoIvaNoRecuperable;
        int codigoIVANoRec;
        int montoTotal;
        int montoNetoActivoFijo;
        int ivaActivoFijo;
        int ivaUsoComun;
        int imptoSinDerechoACredito;
        int ivaNoRetenido;
        int tabacosPuros;
        int tabacosCigarrillos;
        int tabacosElaborados;
        int NCEoNDE;
        int codigoOtroImpuesto = 0;
        int valorOtroImpuesto = 0;
        int tasaOtroImpuesto = 0;

        CompraDTO dto = null;

        String[] fields = line.split(";");
//        System.err.println("Contenido de Line:" + line);
//        System.err.println(
//                "lineas:" + fields.length);
//        System.err.println(
//                "contenido:" + fields);

        nro = Integer.valueOf(fields[0]);

        tipoDoc = Integer.valueOf(fields[1]);

        tipoCompra = fields[2];

        rutProveedor = fields[3];

        razonSocial = fields[4];

        folio = Integer.valueOf(fields[5]);

        try {
            System.err.println("fecha dcto:" + fields[6]);
            fechaDocto = sdf.parse(fields[6]);

        } catch (ParseException e) {
            System.err.println("error 1");

        }
        try {
            System.err.println("fecha dcto:" + fields[7]);

            fechaRecepcion = sdf2.parse(fields[7]);
        } catch (ParseException e) {
            System.err.println("error 2");

        }
        try {
            System.err.println("fecha dcto:" + fields[8]);

            fechaAcuse = sdf.parse(fields[8]);

        } catch (ParseException e) {
            fechaAcuse = fechaRecepcion;
                        System.err.println("error 3");

        }

        montoExento = getIntValue(fields[9]);

        montoNeto = getIntValue(fields[10]);

        montoIVARecuperable = getIntValue(fields[11]);

        montoIvaNoRecuperable = getIntValue(fields[12]);

        codigoIVANoRec = getIntValue(fields[13]);

        montoTotal = getIntValue(fields[14]);

        montoNetoActivoFijo = getIntValue(fields[15]);

        ivaActivoFijo = getIntValue(fields[16]);

        ivaUsoComun = getIntValue(fields[17]);

        imptoSinDerechoACredito = getIntValue(fields[18]);

        ivaNoRetenido = getIntValue(fields[19]);

        tabacosPuros = getIntValue(fields[20]);

        tabacosCigarrillos = getIntValue(fields[21]);

        tabacosElaborados = getIntValue(fields[22]);

        NCEoNDE = getIntValue(fields[23]);

        if (fields.length == 27) {

            codigoOtroImpuesto = getIntValue(fields[24]);

            valorOtroImpuesto = getIntValue(fields[25]);

            tasaOtroImpuesto = getIntValue(fields[26]);
        }
//        if (fields.length != 27) {
//            throw new RuntimeException("Error al procesar el archivo");
//        }
        dto = new CompraDTO(nro, tipoDoc, tipoCompra, rutProveedor, razonSocial, folio, fechaDocto, fechaRecepcion, fechaAcuse, montoExento, montoNeto, montoIVARecuperable, montoIvaNoRecuperable, codigoIVANoRec, montoTotal, montoNetoActivoFijo, ivaActivoFijo, ivaUsoComun, imptoSinDerechoACredito, ivaNoRetenido, tabacosPuros, tabacosCigarrillos, tabacosElaborados, NCEoNDE, codigoOtroImpuesto, valorOtroImpuesto, tasaOtroImpuesto);

        System.err.println(dto);

        return dto;
    }

    public List<CompraDTO> getList() {
        return list;
    }

    public static int getIntValue(String val) {
        int aux = 0;
        if (val.equals("")) {
            return aux;
        }
        try {
            aux = Integer.parseInt(val);
        } catch (NumberFormatException ee) {
            return 0;
        }
        return aux;
    }
}
