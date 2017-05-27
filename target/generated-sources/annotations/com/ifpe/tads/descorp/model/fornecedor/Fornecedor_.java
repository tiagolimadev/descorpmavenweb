package com.ifpe.tads.descorp.model.fornecedor;

import com.ifpe.tads.descorp.model.endereco.Endereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T11:02:11")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile ListAttribute<Fornecedor, Endereco> enderecos;
    public static volatile SingularAttribute<Fornecedor, String> nome;
    public static volatile SingularAttribute<Fornecedor, Long> id;
    public static volatile SingularAttribute<Fornecedor, String> descricao;

}