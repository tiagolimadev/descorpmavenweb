package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.model.entrega.Entrega;
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
@Table(name = "TB_ENTREGADOR")
@DiscriminatorValue(value = "E")
@PrimaryKeyJoinColumn(name = "ID_ENTREGADOR", referencedColumnName = "ID")
public class Entregador extends Usuario implements Serializable {
    
    @OneToMany(mappedBy = "entregador", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrega> entregas;

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.usuario.Entregador[ id=" + super.getId() + ":" + super.getTipo() + " ]";
    }
    
}
