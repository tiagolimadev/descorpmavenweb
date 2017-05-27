package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.model.compra.Compra;
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
@Table(name = "TB_ADMINISTRADOR")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID")
public class Administrador extends Usuario implements Serializable {
    
    @OneToMany(mappedBy = "administrador", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras;

    public Administrador() {
        super.setTipo(TipoUsuario.ADMINISTRADOR);
    }
    
    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.usuario.Administrador[ id=" + super.getId() + ":" + super.getTipo() + " ]";
    }
    
}
