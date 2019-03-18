/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Pereira
 */

@Entity
public class Cidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid_id")
	private Long id;
	@Column(name = "cid_nome")
	private String nome;
	@JoinColumn(nullable = false)
	@ManyToOne
	private Estado estado;
	@OneToMany(mappedBy = "cidade")
	private List<Fornecedor> fornecedor;

	public Cidade() {
	}

	public Cidade(Long id, String nome, Estado estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return getNome();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cidade) {
			Cidade cid = (Cidade) obj;
			if (cid.getEstado().equals(this.estado) && cid.getNome().equals(this.nome) && cid.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

}
