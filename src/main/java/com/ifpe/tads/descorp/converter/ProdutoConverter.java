/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.converter;

import com.ifpe.tads.descorp.model.produto.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eduardo
 */
@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            Produto produtoSelecionado = (Produto) component.getAttributes().get(value);
            return produtoSelecionado;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        if (entity != null && entity instanceof Produto && ((Produto) entity).getId() != null) {
            component.getAttributes().put(((Produto) entity).getId().toString(), entity);
            return ((Produto) entity).getId().toString();
        }

        return null;
    }
}
