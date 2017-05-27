package com.ifpe.tads.descorp.model.produto;

import com.ifpe.tads.descorp.model.compra.ItemCompra;
import com.ifpe.tads.descorp.model.produto.Categoria;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, BigDecimal> preco;
    public static volatile ListAttribute<Produto, ItemCompra> itensCompras;
    public static volatile SingularAttribute<Produto, String> codigo;
    public static volatile ListAttribute<Produto, ItemCompra> itensVendas;
    public static volatile ListAttribute<Produto, Categoria> categorias;
    public static volatile SingularAttribute<Produto, String> nome;
    public static volatile SingularAttribute<Produto, Long> id;
    public static volatile SingularAttribute<Produto, Long> qtdeDisponivel;
    public static volatile SingularAttribute<Produto, String> descricao;

}