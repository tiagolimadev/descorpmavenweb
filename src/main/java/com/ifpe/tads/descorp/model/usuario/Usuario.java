package com.ifpe.tads.descorp.model.usuario;

import com.ifpe.tads.descorp.acesso.Grupo;
import com.ifpe.tads.descorp.model.endereco.Endereco;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
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
                    name = "Usuario.PorLogin",
                    query = "SELECT u FROM Usuario u WHERE u.login = :login"
            ),
            @NamedQuery(
                    name = "Usuario.PorTipo",
                    query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo"
            ),
            @NamedQuery(
                    name = "Usuario.ListarTodos",
                    query = "SELECT u FROM Usuario u ORDER BY u.nome"
            )
        }
)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Pattern(regexp = "^[A-Z].+", message = "O nome deve começar com uma letra maiuscula.")
    @NotBlank(message = "{usuario.nome.obrigatorio}")
    @Size(max = 100)
    @Column(name = "TXT_NOME")
    private String nome;

    @Email(message = "{usuario.email.invalido}")
    @NotBlank(message = "{usuario.email.obrigatorio}")
    @Size(max = 100)
    @Column(name = "TXT_EMAIL", unique = true)
    private String email;

    @NotBlank(message = "{usuario.login.obrigatorio}")
    @Size(min = 4, max = 12)
    @Column(name = "TXT_LOGIN", unique = true)
    private String login;
    
    @Size(max = 45)
    @Column(name = "TXT_SAL")
    private String sal;

    @NotBlank(message = "{usuario.senha.obrigatorio}")
    @Size(min = 8, max = 16)
    @Column(name = "TXT_SENHA")
    private String senha;
    

    @CPF
    @NotBlank(message = "{usuario.cpf.obrigatorio}")
    @Column(name = "TXT_CPF", unique = true)
    private String cpf;

    @Past(message = "ususario.dtnasc.passado")
    @NotNull(message = "{usuario.dtnasc.obrigatorio}")
    @Column(name = "DT_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToMany(mappedBy = "usuarios")
    private List<Endereco> enderecos;

    @Transient
    private Integer idade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_TIPO_USUARIO")
    private TipoUsuario tipo;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_USUARIO_GRUPO", joinColumns = {
        @JoinColumn(name = "ID_USUARIO")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_GRUPO")})
    private List<Grupo> grupos;
    
    @PrePersist
    public void gerarHash() {
        try {
            gerarSal();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            setSenha(sal + senha);
            digest.update(senha.getBytes(Charset.forName("UTF-8")));
            setSenha(Base64.getEncoder().encodeToString(digest.digest()));
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void gerarSal() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        setSal(Base64.getEncoder().encodeToString(randomBytes));
    }
    
    private void calcularIdade() {
        Calendar nascimento = new GregorianCalendar();
        nascimento.setTime(this.dataNascimento);
        Calendar hoje = Calendar.getInstance();
        Integer calcIdade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        nascimento.add(Calendar.YEAR, calcIdade);

        if (hoje.before(nascimento)) {
            calcIdade--;
        }
        
        this.idade = calcIdade;
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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
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
