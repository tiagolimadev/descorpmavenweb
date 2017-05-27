package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.model.cartao.CartaoDeCredito;
import com.ifpe.tads.descorp.model.venda.Venda;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_CLIENTE")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
public class Cliente extends Usuario implements Serializable {

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartaoDeCredito> cartoes;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas;

    public List<CartaoDeCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoDeCredito> cartoes) {
        this.cartoes = cartoes;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.usuario.Cliente[ id=" + super.getId() + ":" + super.getTipo() + " ]";
    }
    
}
