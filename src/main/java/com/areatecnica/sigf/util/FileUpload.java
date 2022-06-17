/*
   Copyright 2009-2021 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.areatecnica.sigf.util;

import com.areatecnica.sigf.controller.CargaMasivaController;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;

@Named(value = "fileUpload")
@RequestScoped
public class FileUpload implements java.io.Serializable {

    @Inject 
    private CargaMasivaController controller;
    
    private static final long serialVersionUID = 7466290594126927225L;

    private File doc;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded." + this.file);
            FacesContext.getCurrentInstance().addMessage(null, message);

            System.err.println("CONTENIDO:" + this.file);
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        this.file = event.getFile();
        if (file != null) {
            FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded." + this.file);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("CONTENIDO HANDLEFILEUPLOAD:" + this.file);

            try {
                this.doc = File.createTempFile("archivo", null);

                File tempFile = File.createTempFile("myfile", ".csv");
                FileUtils.copyInputStreamToFile(this.file.getInputStream(), tempFile);

                System.err.println("THIS.DOC:" + this.doc);
                System.err.println("TEMPFILE:" + tempFile);

                this.controller.setFile(tempFile);
                System.err.println("THIS.DOC2:" + this.doc);
            } catch (IOException ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JsfUtil.addErrorMessage("El archivo es nulo");
        }
    }

    public File getDoc() {
        return doc;
    }

    public void setDoc(File doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "FileUpload{" + "doc=" + doc + ", file=" + file + '}';
    }

}
