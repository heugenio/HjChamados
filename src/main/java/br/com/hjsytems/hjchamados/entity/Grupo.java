
package br.com.hjsytems.hjchamados.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Hallef
 */
@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {
    
    @Id
    @Column(name = "GRPO_CODIGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @Column(name = "GPRO_NOME")
    private String nome;
    
    @Column(name = "GPRO_PERMISSOES")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "GRUPO_PERMISSAO", joinColumns = @JoinColumn(name = "CODI_GRUPO"), 
                                  inverseJoinColumns = @JoinColumn(name = "CODI_PERMISSAO"))
    private List<Permissao> permissoes;
    
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.codigo);
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
        final Grupo other = (Grupo) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}
