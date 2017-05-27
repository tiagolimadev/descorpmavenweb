package com.ifpe.tads.descorp.model.compra;

import com.ifpe.tads.descorp.model.compra.Compra;
import com.ifpe.tads.descorp.model.produto.Produto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(ItemCompra.class)
public class ItemCompra_ { 

    public static volatile SingularAttribute<ItemCompra, BigDecimal> precoUnitario;
    public static volatile SingularAttribute<ItemCompra, Compra> compra;
    public static volatile SingularAttribute<ItemCompra, Produto> produto;
    public static volatile SingularAttribute<ItemCompra, Long> id;
    public static volatile SingularAttribute<ItemCompra, Integer> quantidade;

}