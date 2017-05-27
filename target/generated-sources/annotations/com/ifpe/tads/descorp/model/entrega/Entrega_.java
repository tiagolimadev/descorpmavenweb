package com.ifpe.tads.descorp.model.entrega;

import com.ifpe.tads.descorp.model.entrega.StatusEntrega;
import com.ifpe.tads.descorp.model.usuario.Entregador;
import com.ifpe.tads.descorp.model.venda.Venda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile SingularAttribute<Entrega, Venda> venda;
    public static volatile SingularAttribute<Entrega, StatusEntrega> statusEntrega;
    public static volatile SingularAttribute<Entrega, Date> dataEntrega;
    public static volatile SingularAttribute<Entrega, Long> id;
    public static volatile SingularAttribute<Entrega, Entregador> entregador;

}