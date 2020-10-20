package com.areatecnica.sigf.controller;

import com.areatecnica.sigf.entities.Log;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "logController")
@ViewScoped
public class LogController extends AbstractController<Log> {

    public LogController() {
        // Inform the Abstract parent controller of the concrete Log Entity
        super(Log.class);
    }

}
