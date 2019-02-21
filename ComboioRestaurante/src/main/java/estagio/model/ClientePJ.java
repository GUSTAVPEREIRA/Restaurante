package estagio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class ClientePJ extends Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "clij_cnpj", nullable = false, length = 20)
	private String cnpj;
	@Column(name = "clij_ie", nullable = false, length = 40)
	private String ie;
	@Column(name = "clij_nome", nullable = false, length = 200)
	private String nome;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ClientePJ() {
		super();
	}

}
