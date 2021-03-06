package com.ifpe.tads.descorp.model.venda;

import com.ifpe.tads.descorp.model.produto.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_ITEM_VENDA")
@NamedQueries(
    {
        @NamedQuery(
            name = "ItemVenda.PorIdVenda",
            query = "SELECT i FROM ItemVenda i WHERE i.venda.id = :idVenda"
        )
    }
)
public class ItemVenda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Range(min = 1, max = 1000)
    @NotNull(message = "{itemvenda.quantidade.obrigatorio}")
    @Column(name = "NUM_QUANTIDADE", nullable = false)
    private Integer quantidade;
    
    @DecimalMin("0.1")
    @NotNull(message = "{itemvenda.preco.obrigatorio}")
    @Column(name = "NUM_PRECO_UNITARIO", nullable = false, scale = 2, precision = 15)
    private BigDecimal precoUnitario;
    
    @Transient
    private BigDecimal subTotal;
    
    @NotNull(message = "{itemvenda.produto.obrigatorio}")
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    private Produto produto;
    
    @NotNull(message = "{itemvenda.venda.obrigatorio}")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VENDA", referencedColumnName = "ID")
    private Venda venda;

    private void calcularSubTotal(){
        this.subTotal = this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
    
    public void copiarPreco(){
        this.precoUnitario = this.produto.getPreco();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubTotal() {
        this.calcularSubTotal();
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.venda.ItemVenda[ id=" + id + ":" + precoUnitario + " ]";
    }
    
}
