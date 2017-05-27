package com.ifpe.tads.descorp.model.produto;

import com.ifpe.tads.descorp.model.compra.ItemCompra;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_PRODUTO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Produto.PorNome",
                    query = "SELECT p FROM Produto p WHERE p.nome LIKE :nome"
            ),
            @NamedQuery(
                    name = "Produto.PorCodigo",
                    query = "SELECT p FROM Produto p WHERE p.codigo LIKE :codigo"
            ),
        }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Produto.PorMenorPrecoSQL",
                    query = "SELECT ID, TXT_CODIGO, TXT_NOME, TXT_DESCRICAO, "
                            + "NUM_PRECO, NUM_QTDE_DISPONIVEL FROM TB_PRODUTO "
                            + "WHERE NUM_PRECO = (SELECT MIN(NUM_PRECO) FROM TB_PRODUTO)",
                    resultClass = Produto.class
            ),
            @NamedNativeQuery(
                    name = "Produto.PorMaiorPrecoSQL",
                    query = "SELECT ID, TXT_CODIGO, TXT_NOME, TXT_DESCRICAO, "
                            + "NUM_PRECO, NUM_QTDE_DISPONIVEL FROM TB_PRODUTO "
                            + "WHERE NUM_PRECO = (SELECT MAX(NUM_PRECO) FROM TB_PRODUTO)",
                    resultClass = Produto.class
            ),
        }
)
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "TXT_CODIGO")
    private String codigo;

    @NotBlank
    @Size(max = 50)
    @Column(name = "TXT_NOME")
    private String nome;

    @NotBlank
    @Size(max = 500)
    @Column(name = "TXT_DESCRICAO")
    private String descricao;

    @DecimalMin("0.1")
    @NotNull
    @Column(name = "NUM_PRECO")
    private BigDecimal preco;

    @Min(0)
    @Max(30000)
    @NotNull
    @Column(name = "NUM_QTDE_DISPONIVEL")
    private Long qtdeDisponivel;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_PRODUTOS_CATEGORIAS", joinColumns = {
        @JoinColumn(name = "ID_PRODUTO")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_CATEGORIA")
            })
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itensCompras;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itensVendas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<ItemCompra> getItensCompras() {
        return itensCompras;
    }

    public void setItensCompras(List<ItemCompra> itensCompras) {
        this.itensCompras = itensCompras;
    }

    public List<ItemCompra> getItensVendas() {
        return itensVendas;
    }

    public void setItensVendas(List<ItemCompra> itensVendas) {
        this.itensVendas = itensVendas;
    }

    public Long getQtdeDisponivel() {
        return qtdeDisponivel;
    }

    public void setQtdeDisponivel(Long qtdeDisponivel) {
        this.qtdeDisponivel = qtdeDisponivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return false;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.produto.Produto[ id=" + id + ":" + nome + " ]";
    }

}
