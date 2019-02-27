/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Entity
public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "for_id")
	private Long id;
	@Column(name = "for_nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "for_cnpj", length = 20, unique = true, nullable = false)
	private String cnpj;
	@Column(name = "for_ie", length = 30, nullable = false)
	private String ie;
	@Column(name = "for_telefone", nullable = false, length = 20)
	private String telefone;
	@Column(name = "for_cep", nullable = false, length = 10)
	private String cep;
	@JoinColumn(nullable = false)
	@ManyToOne(cascade=CascadeType.ALL)
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fornecedor) {
			Fornecedor forne = (Fornecedor) obj;
			if (forne.getNome().equals(this.nome) && forne.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

}
