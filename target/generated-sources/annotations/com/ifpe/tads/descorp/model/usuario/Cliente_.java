package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.model.cartao.CartaoDeCredito;
import com.ifpe.tads.descorp.model.venda.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, CartaoDeCredito> cartoes;
    public static volatile ListAttribute<Cliente, Venda> vendas;

}