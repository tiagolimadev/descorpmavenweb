/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.jpa.validator;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Eduardo
 */
public class ValidadorHoje implements ConstraintValidator<ValidaHoje, Date> {
    private Date data;
    
    @Override
    public void initialize(ValidaHoje validaHoje) {
    
    }

    @Override
    public boolean isValid(Date valor, ConstraintValidatorContext context) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        
//        return valor == null ? false : (formatter.format(new Date()).equals(formatter.format(valor)));
        return (formatter.format(new Date())).compareTo(formatter.format(valor)) <= 0 ? true : false;
    }
    
}
