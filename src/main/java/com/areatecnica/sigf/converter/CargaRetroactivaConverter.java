package com.areatecnica.sigf.converter;

import com.areatecnica.sigf.entities.CargaRetroactiva;
import com.areatecnica.sigf.facade.CargaRetroactivaFacade;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "cargaRetroactivaConverter")
public class CargaRetroactivaConverter implements Converter {

    private CargaRetroactivaFacade ejbFacade;

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
        if (object instanceof CargaRetroactiva) {
            CargaRetroactiva o = (CargaRetroactiva) object;
            return getStringKey(o.getCargaRetroactivaId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CargaRetroactiva.class.getName()});
            return null;
        }
    }

    private CargaRetroactivaFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(CargaRetroactivaFacade.class).get();
        return this.ejbFacade;
    }
}
