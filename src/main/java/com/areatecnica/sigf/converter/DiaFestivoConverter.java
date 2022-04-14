package com.areatecnica.sigf.converter;

import com.areatecnica.sigf.entities.DiaFestivo;
import com.areatecnica.sigf.facade.DiaFestivoFacade;
import com.areatecnica.sigf.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "diaFestivoConverter")
public class DiaFestivoConverter implements Converter {

    private DiaFestivoFacade ejbFacade;

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
        if (object instanceof DiaFestivo) {
            DiaFestivo o = (DiaFestivo) object;
            return getStringKey(o.getDiaFestivoId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DiaFestivo.class.getName()});
            return null;
        }
    }

    private DiaFestivoFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(DiaFestivoFacade.class).get();
        return this.ejbFacade;
    }
}
