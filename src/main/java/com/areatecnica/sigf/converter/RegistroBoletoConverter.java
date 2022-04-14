package com.areatecnica.sigf.converter;

import com.areatecnica.sigf.entities.RegistroBoleto;
import com.areatecnica.sigf.facade.RegistroBoletoFacade;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "registroBoletoConverter")
public class RegistroBoletoConverter implements Converter {

    private RegistroBoletoFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof RegistroBoleto) {
            RegistroBoleto o = (RegistroBoleto) object;
            return getStringKey(o.getRegistroBoletoId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RegistroBoleto.class.getName()});
            return null;
        }
    }

    private RegistroBoletoFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(RegistroBoletoFacade.class).get();
        return this.ejbFacade;
    }
}
