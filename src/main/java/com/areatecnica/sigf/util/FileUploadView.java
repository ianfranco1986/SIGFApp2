/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author ianfr
 */
@Named(value = "fileUploadView")
@ManagedBean
public class FileUploadView {

    /**
     * Creates a new instance of FileUploadView
     */
    public FileUploadView() {
    }
    
//    private UploadedFile file;
// 
//    public UploadedFile getFile() {
//        return file;
//    }
 
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
//     
//    public void upload() {
//        if(file != null) {
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
    
}
