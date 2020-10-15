/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.validators;

import com.areatecnica.sigf.controller.util.JsfUtil;
import com.areatecnica.sigf.dao.impl.IClienteDaoImpl;
import com.areatecnica.sigf.entities.Cliente;
import com.areatecnica.sigf.entities.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ianfr
 */
@FacesValidator("rutValidatorCliente")
public class RutValidatorCliente implements Validator {

    private FacesMessage msg;
    private Usuario usuario;

    public RutValidatorCliente() {
        this.msg = new FacesMessage("Error de validación", "Error de validación");
        this.msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            String rut = (String) value;

            if (rut != null && rut.length() > 0) {
                rut = rut.toUpperCase();
                rut = rut.replace(".", "");
                rut = rut.replace("-", "");
                int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

                char dv = rut.charAt(rut.length() - 1);

                int m = 0, s = 1;
                for (; rutAux != 0; rutAux /= 10) {
                    s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                }
                if (dv != (char) (s != 0 ? s + 47 : 75)) {
                    throw new ValidatorException(msg);
                }

                Cliente cliente = new IClienteDaoImpl().findByRut(rut);

                if (cliente != null) {
                    FacesMessage msg2 = new FacesMessage("Rut ya registrado", "Error de validación");
                    msg2.setSeverity(FacesMessage.SEVERITY_ERROR);

                    JsfUtil.addErrorMessage("El rut ya se encuentra registrado");

                    throw new ValidatorException(msg2);
                }
            } else {
                throw new ValidatorException(msg);
            }

        } catch (java.lang.NumberFormatException | javax.el.PropertyNotFoundException | NullPointerException | javax.faces.FacesException e) {
            throw new ValidatorException(msg);
        }
    }

}
