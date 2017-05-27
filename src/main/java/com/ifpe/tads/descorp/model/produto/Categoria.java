package com.ifpe.tads.descorp.model.produto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 *
 * @author Tiago Lima
 */
@Entity
@Table(name = "TB_CATEGORIA")
@NamedQueries(
    {
        @NamedQuery(
            name = "Categoria.PorNome",
            query = "SELECT c FROM Categoria c WHERE c.nome LIKE :nome ORDER BY c.id"
        )
    }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Categoria.PorNomeSQL",
                    query = "SELECT ID, TXT_NOME FROM TB_CATEGORIA WHERE TXT_NOME LIKE ? ORDER BY ID",
                    resultClass = Categoria.class
            ),
            @NamedNativeQuery(
                    name = "Categoria.QuantidadeProdutosSQL",
                    query = "SELECT count(prodcat.ID_PRODUTO) as TOTAL_PRODUTOS from TB_CATEGORIA cat, TB_PRODUTOS_CATEGORIAS AS prodcat "
                            + "WHERE cat.TXT_NOME LIKE ? AND cat.ID = prodcat.ID_CATEGORIA GROUP BY cat.ID",
                    resultSetMapping = "Categoria.QuantidadeProdutos"
            )
        }
)
@SqlResultSetMapping(
        name = "Categoria.QuantidadeProdutos",
        columns = {
            @ColumnResult(name = "TOTAL_PRODUTOS", type = Long.class)}
)
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @org.hibernate.validator.constraints.NotBlank
    @Size(max = 20)
    @Column(name = "TXT_NOME")
    private String nome;
    
    @Valid
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.produto.Categoria[ id=" + id + ":" + nome + " ]";
    }
    
}
