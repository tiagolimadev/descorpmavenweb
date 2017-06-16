/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.converter;

import com.ifpe.tads.descorp.model.usuario.Operador;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tiago Lima
 */
@FacesConverter(forClass = Operador.class)
public class DataNascConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value == null || value.isEmpty() ? null : Operador.class;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null ? null : formataDataNasc((Date)value);
    }
    
    public static String formataDataNasc(Date dataNasc) {
        if(dataNasc == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(dataNasc);
        }
    }
}
