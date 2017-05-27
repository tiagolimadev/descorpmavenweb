package com.ifpe.tads.descorp.model.entrega;

import com.ifpe.tads.descorp.model.usuario.Entregador;
import com.ifpe.tads.descorp.model.venda.Venda;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_ENTREGA")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Entrega.PorDataEntrega",
                    query = "SELECT e FROM Entrega e WHERE e.dataEntrega = :dataEntrega"
            ),
            @NamedQuery(
                    name = "Entrega.PorStatusEntrega",
                    query = "SELECT e FROM Entrega e WHERE e.statusEntrega = :statusEntrega"
            ),
        }
)
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_STATUS_ENTREGA")
    private StatusEntrega statusEntrega;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_DATA_ENTREGA", nullable = false)
    private Date dataEntrega;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_VENDA", referencedColumnName = "ID")
    private Venda venda;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_ENTREGADOR", referencedColumnName = "ID")
    private Entregador entregador;
    
    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return false;
    }
    
    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.entrega.Entrega[ id=" + id + ":" + statusEntrega + " ]";
    }
}
