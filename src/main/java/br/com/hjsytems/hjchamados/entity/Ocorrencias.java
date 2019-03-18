
package br.com.hjsytems.hjchamados.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Heugenio
 */
@Entity
@Table (name = "OCORRENCIAS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ocorrencias implements Serializable{
    
    public static final long SerialVersionUID = 1L;
    
    @Id
    @Column(name = "OCRC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(name = "OCRC_DESCRICAO")
    @NotNull
    @Size(max = 5000)
    private String descricao;
    
    @Column(name = "OCRC_STATUS")
    //@NotNull
    private String status;
    
    @Column(name = "OCRC_DT_ABERTURA")
    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura;   

    @Column(name = "OCRC_DT_FECHAMENTO", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFechamento;  
    
    @JoinColumn(name = "TPOC_ID")
    @ManyToOne
    private TiposOcorrencia tiposOcorrencia;
    
    @JoinColumn(name = "FRNC_ID")
    @ManyToOne
    private Fornecedor fornecedor;
    
    @JoinColumn(name = "UNEM_ID")
    @ManyToOne
    private UnidadesEmpresariais unidadeEmpresarial;
    
    @JoinColumn(name = "USRS_ID")
    @ManyToOne
    private Usuarios usuario;

    public Ocorrencias() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public TiposOcorrencia getTiposOcorrencia() {
        return tiposOcorrencia;
    }

    public void setTiposOcorrencia(TiposOcorrencia tiposOcorrencia) {
        this.tiposOcorrencia = tiposOcorrencia;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public UnidadesEmpresariais getUnidadeEmpresarial() {
        return unidadeEmpresarial;
    }

    public void setUnidadeEmpresarial(UnidadesEmpresariais unidadeEmpresarial) {
        this.unidadeEmpresarial = unidadeEmpresarial;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Ocorrencias other = (Ocorrencias) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
         
    
}
