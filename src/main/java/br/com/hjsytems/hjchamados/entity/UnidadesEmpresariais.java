/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hjsytems.hjchamados.entity;

import java.io.Serializable;
import java.util.Objects;
import javassist.SerialVersionUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heugenio
 */
@Entity
@Table(name="ÜNIDADES_EMPRESARIAIS")
public class UnidadesEmpresariais implements Serializable{
   
    private static final long SerialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UNEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "UNEM_NOME")
    @NotNull
    @Basic(optional = false)
    private String nome;
    
    @Column(name = "UNEM_SIGLA")
    @NotNull
    @Basic(optional = false)
    private String sigla;
    
    @Column(name = "UNEM_EMAIL")
    @NotNull
    @Basic(optional = false)
    private String email;
    
    @Column(name = "UNEM_RESPONSAVEL")
    @NotNull
    @Basic(optional = false)
    private String responsavel;
    
    @Column(name = "UNEM_EMAIL_RESPONSAVEL")
    private String emailResponsavel;

    public UnidadesEmpresariais() {
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final UnidadesEmpresariais other = (UnidadesEmpresariais) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

  
    
}