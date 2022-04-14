package com.areatecnica.sigf.converter;

import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import com.areatecnica.sigf.facade.RecaudacionDescuentoExtraFacade;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "recaudacionDescuentoExtraConverter")
public class RecaudacionDescuentoExtraConverter implements Converter {

    private RecaudacionDescuentoExtraFacade ejbFacade;

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
        if (object instanceof RecaudacionDescuentoExtra) {
            RecaudacionDescuentoExtra o = (RecaudacionDescuentoExtra) object;
            return getStringKey(o.getRecaudacionDescuentoExtraId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RecaudacionDescuentoExtra.class.getName()});
            return null;
        }
    }

    private RecaudacionDescuentoExtraFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(RecaudacionDescuentoExtraFacade.class).get();
        return this.ejbFacade;
    }
}
