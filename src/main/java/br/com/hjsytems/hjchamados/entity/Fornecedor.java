/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heugenio
 */
@Entity
@Table(name="FORNECEDORES")
public class Fornecedor implements Serializable{
    
private static final long SerialVersionUID = 1L;
    @Id
    @Column(name = "FRNC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    @Column(name = "FRNC_NOME")
    @NotNull
    @Basic(optional = false)
    private String nome;
    
    @Column(name = "FRNC_CNPJCPF")
    private String cnpjCpf;

    @Column(name = "FRNC_EMAIL")
    @NotNull
    @Basic(optional = false)
    private String email;
    
    @Column(name = "FRNC_TELEFONE")
    @NotNull
    @Basic(optional = false)
    private String telefone;    
    
    @Column(name = "FRNC_CELULAR")
    @NotNull
    @Basic(optional = false)
    private String celular;       

    @Column(name = "FRNC_EMAIL_AUX")
    private String emailAux;
    
    
    @ManyToMany
    @JoinTable(name = "FORNECEDORES_TIPOS_OCORRENCIA",
    joinColumns = @JoinColumn(name = "FRNC_ID"),
    inverseJoinColumns = @JoinColumn(name = "TPOC_ID"))
    private List<TiposOcorrencia> listTiposOcorrencias;
    
    public Fornecedor() {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmailAux() {
        return emailAux;
    }

    public void setEmailAux(String emailAux) {
        this.emailAux = emailAux;
    }

    public List<TiposOcorrencia> getListTiposOcorrencias() {
        return listTiposOcorrencias;
    }

    public void setListTiposOcorrencias(List<TiposOcorrencia> listTiposOcorrencias) {
        this.listTiposOcorrencias = listTiposOcorrencias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
