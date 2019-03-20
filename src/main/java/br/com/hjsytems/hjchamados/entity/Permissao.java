
package br.com.hjsytems.hjchamados.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Hallef
 */
@Entity
@Table(name = "PERMISSAO")
public class Permissao implements Serializable {

    @Id
    @Column(name = "PERM_CODIGO")
    private Long codigo;
    
    @Column(name = "PERM_NOME")
    private String nome;

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
}
