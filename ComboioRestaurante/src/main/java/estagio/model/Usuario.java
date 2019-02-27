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

/**
 * desativado
 * 
 * @author Pereira
 */
@Entity
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Long id;
	@Column(name = "usu_nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "usu_senha", length = 25, nullable = false)
	private String senha;
	@Column(name = "usu_tipo", length = 10, nullable = false)
	private String tipo;
	@Column(name = "usu_ativo", length = 10, nullable = false)
	private String ativo;
	@Column(name = "usu_login", length = 25, nullable = false)
	private String login;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String senha, String tipo, String ativo, String login) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.tipo = tipo;
		this.ativo = ativo;
		this.login = login;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario usu = (Usuario) obj;
			if (usu.getNome().equals(this.nome) && usu.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}
}
