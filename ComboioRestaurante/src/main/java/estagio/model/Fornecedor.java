/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * @author Pereira
 */
@SuppressWarnings("serial")
@Entity
public class Fornecedor implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "for_id")
    private Long id;
    @Column(name = "for_nome")
    private String nome;
    @Column(name = "for_cnpj")
    private String cnpj;
    @Column(name = "for_ie")
    private String ie;
    @Column(name = "for_telefone")
    private String telefone;
    @Column(name = "for_cep")
    private String cep;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Cidade cidade;

    public Fornecedor() {

    }

    public Fornecedor(Long id, String nome, String cnpj, String ie, String telefone, String cep, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.telefone = telefone;
        this.cep = cep;
        this.cidade = cidade;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
    

}
