package com.ifpe.tads.descorp.model.cartao;

import com.ifpe.tads.descorp.model.usuario.Cliente;
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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_CARTAO_CREDITO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "CartaoDeCredito.PorNomeImpresso",
                    query = "SELECT C FROM CartaoDeCredito C WHERE C.nomeImpresso = :nomeImpresso"
            ),
            @NamedQuery(
                    name = "CartaoDeCredito.PorNumero",
                    query = "SELECT C FROM CartaoDeCredito C WHERE C.numero = :numero"
            ),
            @NamedQuery(
                    name = "CartaoDeCredito.PorCliente",
                    query = "SELECT C FROM CartaoDeCredito C WHERE C.cliente.id = :clienteId"
            ),
            @NamedQuery(
                    name = "CartaoDeCredito.PorClienteBandeira",
                    query = "SELECT C FROM CartaoDeCredito C WHERE C.bandeira = :bandeira AND c.cliente.id = :clienteId"
            )
        }
)
public class CartaoDeCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CreditCardNumber
    @Column(name = "TXT_NUMERO")
    private String numero;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(name = "TXT_NOME_IMPRESSO")
    private String nomeImpresso;

    @Future
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_VALIDADE")
    private Date validade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_BANDEIRA")
    private Bandeira bandeira;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CartaoDeCredito)) {
            return false;
        }
        CartaoDeCredito other = (CartaoDeCredito) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.cartao.CartaoDeCredito[ id=" + id + ":" + bandeira + " ]";
    }

}
