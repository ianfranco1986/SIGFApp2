/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ianfr
 */
public class JasperReportUtil {

    public static ByteArrayOutputStream getOutputStreamFromReport(List list, Map map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, dataSource);

        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    public static ByteArrayOutputStream getOutputStreamFromReport(Map map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://www.areatecnica.cl:3306/sigfdb", "root", "NintendO64");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, connection);

        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    public static ByteArrayOutputStream getOutputStreamFromReportXls(Map map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://www.areatecnica.cl:3306/sigfdb", "root", "NintendO64");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, connection);

        JRXlsExporter exporterXLS = new JRXlsExporter();

        exporterXLS.setExporterInput(new SimpleExporterInput(jp));
        exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(os));

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        configuration.setWhitePageBackground(false);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        exporterXLS.setConfiguration(configuration);
        
        exporterXLS.exportReport();
        
        os.flush();
        os.close();
        return os;
    }

    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, contentType, nameFile);
        return file;
    }

    public static StreamedContent getStreamContentReport(List list, Map map, String pathJasper, String nameFilePdf) throws Exception {
        StreamedContent pdf = null;
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, dataSource);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();

        InputStream is = new ByteArrayInputStream(os.toByteArray());
        pdf = new DefaultStreamedContent(is, "application/pdf", nameFilePdf);
        return pdf;
    }

}
