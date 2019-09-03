/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.reports;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ianfr
 */
@Named(value = "reportController")
@SessionScoped
public class ReportController implements Serializable {

    /**
     * Creates a new instance of reportController
     */
    public ReportController() {
    }

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    private StreamedContent media;
    private ByteArrayOutputStream outputStream;
    private Map<String, Object> map;
    private String path;

    public void generateReport(String path, Map<String, Object> map) {
        try {
            this.path = path; 
            outputStream = JasperReportUtil.getOutputStreamFromReport(map, getPathFileJasper());
            media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", getNameFilePdf());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            log.error(e.getMessage(), e);
        }
    }

    public String getPathFileJasper() {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/" + path + ".jasper");
        return reportPath;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getNameFilePdf() {
        return path + ".pdf";
    }
    
    public String getNameFileXls() {
        return path + ".xls";
    }
    
    public void downloadFile(String path, Map<String, Object> map) {
        try {
            this.path = path; 
            outputStream = JasperReportUtil.getOutputStreamFromReport(map, getPathFileJasper());
            //media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", getNameFilePdf());
            
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + getNameFilePdf());

            
            
            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void downloadFileXls(String path, Map<String, Object> map) {
        try {
            this.path = path; 
            outputStream = JasperReportUtil.getOutputStreamFromReportXls(map, getPathFileJasper());
            //media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", getNameFilePdf());
            
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + getNameFileXls());

            
            
            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void downloadFile() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + getNameFilePdf());

            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public void setReport(String path, Map<String, Object> map) {
        this.path = path;
        this.map = map;
    }

}
