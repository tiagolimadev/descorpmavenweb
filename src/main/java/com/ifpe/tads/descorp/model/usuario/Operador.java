package com.ifpe.tads.descorp.model.usuario;

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
@Table(name = "TB_OPERADOR")
@DiscriminatorValue(value = "O")
@PrimaryKeyJoinColumn(name = "ID_OPERADOR", referencedColumnName = "ID")
public class Operador extends Usuario implements Serializable {

    @OneToMany(mappedBy = "operador", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas;

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.usuario.Operador[ id=" + super.getId() + ":" + super.getTipo() + " ]";
    }
    
}
