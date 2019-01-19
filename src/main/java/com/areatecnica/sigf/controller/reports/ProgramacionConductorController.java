/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controller.reports;

import com.areatecnica.sigf.entities.Trabajador;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "programacionConductorController")
@ViewScoped
public class ProgramacionConductorController implements Serializable{

    private List<Trabajador> items;
    private Trabajador selected;
    
    
    /**
     * Creates a new instance of ProgramacionConductorController
     */
    public ProgramacionConductorController() {
    }
    
}
