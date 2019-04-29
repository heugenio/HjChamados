
package br.com.hjsytems.hjchamados.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heugenio
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable{
  

    @Id
    @Column(name = "USRS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(name = "USRS_NOME")
    @NotNull
    @Basic(optional = false)
    private String nome;  
    
    @Column(name = "USRS_LOGIN")
    @NotNull
    @Basic(optional = false)
    private String login;      
    
    @Column(name = "USRS_EMAIL")
    @NotNull
    @Basic(optional = false)
    private String email;      
    
    @Column(name = "USRS_SENHA")
    @NotNull
    @Basic(optional = false)
    private String senha;  
    
    @JoinColumn(name = "UNEM_ID")
    @ManyToOne
    private UnidadesEmpresariais unidadeEmpresarial;
    
    
    @ManyToMany
    @JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "CODI_USUARIO"), inverseJoinColumns = @JoinColumn(name = "CODI_GRUPO"))
    private List<Grupo> grupos;
    
    public Usuarios() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UnidadesEmpresariais getUnidadeEmpresarial() {
        return unidadeEmpresarial;
    }

    public void setUnidadeEmpresarial(UnidadesEmpresariais unidadeEmpresarial) {
        this.unidadeEmpresarial = unidadeEmpresarial;
    }
    
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuarios other = (Usuarios) obj;
        return Objects.equals(this.id, other.id);
    }
}
