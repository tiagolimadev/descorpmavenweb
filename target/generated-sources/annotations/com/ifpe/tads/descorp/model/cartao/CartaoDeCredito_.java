package com.ifpe.tads.descorp.model.cartao;

import com.ifpe.tads.descorp.model.cartao.Bandeira;
import com.ifpe.tads.descorp.model.usuario.Cliente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(CartaoDeCredito.class)
public class CartaoDeCredito_ { 

    public static volatile SingularAttribute<CartaoDeCredito, Cliente> cliente;
    public static volatile SingularAttribute<CartaoDeCredito, String> numero;
    public static volatile SingularAttribute<CartaoDeCredito, Long> id;
    public static volatile SingularAttribute<CartaoDeCredito, String> nomeImpresso;
    public static volatile SingularAttribute<CartaoDeCredito, Bandeira> bandeira;
    public static volatile SingularAttribute<CartaoDeCredito, Date> validade;

}