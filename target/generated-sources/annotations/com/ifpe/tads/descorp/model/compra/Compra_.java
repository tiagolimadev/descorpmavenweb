package com.ifpe.tads.descorp.model.compra;

import com.ifpe.tads.descorp.model.compra.ItemCompra;
import com.ifpe.tads.descorp.model.fornecedor.Fornecedor;
import com.ifpe.tads.descorp.model.usuario.Administrador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Administrador> administrador;
    public static volatile SingularAttribute<Compra, Boolean> cancelada;
    public static volatile SingularAttribute<Compra, Long> id;
    public static volatile SingularAttribute<Compra, Fornecedor> fornecedor;
    public static volatile ListAttribute<Compra, ItemCompra> itensCompra;
    public static volatile SingularAttribute<Compra, Date> dataCompra;

}