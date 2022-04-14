package com.areatecnica.sigf.converter;

import com.areatecnica.sigf.entities.TipoCotizacionTrabajador;
import com.areatecnica.sigf.facade.TipoCotizacionTrabajadorFacade;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "tipoCotizacionTrabajadorConverter")
public class TipoCotizacionTrabajadorConverter implements Converter {

    private TipoCotizacionTrabajadorFacade ejbFacade;

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
        if (object instanceof TipoCotizacionTrabajador) {
            TipoCotizacionTrabajador o = (TipoCotizacionTrabajador) object;
            return getStringKey(o.getTipoCotizacionTrabajadorId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoCotizacionTrabajador.class.getName()});
            return null;
        }
    }

    private TipoCotizacionTrabajadorFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(TipoCotizacionTrabajadorFacade.class).get();
        return this.ejbFacade;
    }
}
