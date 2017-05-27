package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.model.endereco.Endereco;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Tiago Lima <tiagolimadev@outlook.com>
 */
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "U",
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = "Usuario.PorCPF",
                    query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"
            ),
            @NamedQuery(
                    name = "Usuario.PorNome",
                    query = "SELECT u FROM Usuario u WHERE u.nome = :nome"
            ),
            @NamedQuery(
                    name = "Usuario.PorEmail",
                    query = "SELECT u FROM Usuario u WHERE u.email = :email"
            ),
            @NamedQuery(
                    name = "Usuario.fazerLogin",
                    query = "SELECT u FROM Usuario u WHERE u.login = :login and u.senha = :senha"
            ),
            @NamedQuery(
                    name = "Usuario.PorTipo",
                    query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo"
            )
        }
)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "TXT_NOME")
    private String nome;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "TXT_EMAIL")
    private String email;

    @NotBlank
    @Size(min = 4, max = 12)
    @Column(name = "TXT_LOGIN", unique = true)
    private String login;

    @NotBlank
    @Size(min = 8, max = 16)
    @Column(name = "TXT_SENHA")
    private String senha;

    @CPF
    @NotBlank
    @Column(name = "TXT_CPF", unique = true)
    private String cpf;

    @Past
    @NotNull
    @Column(name = "DT_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToMany(mappedBy = "usuarios")
    private List<Endereco> enderecos;

    @Transient
    private Integer idade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TP_USUARIO")
    private TipoUsuario tipo;
    
    private void calcularIdade() {
        Calendar nascimento = new GregorianCalendar();
        nascimento.setTime(this.dataNascimento);
        Calendar hoje = Calendar.getInstance();
        Integer idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        nascimento.add(Calendar.YEAR, idade);

        if (hoje.before(nascimento)) {
            idade--;
        }
        this.idade = idade;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Integer getIdade() {
        this.calcularIdade();
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "com.ifpe.tads.descorp.model.usuario.Usuario[ id=" + id + ":" + tipo + " ]";
    }

}
