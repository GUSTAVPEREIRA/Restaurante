package estagio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class ClientePJ extends Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "clij_cnpj", nullable = false, length = 20, unique = true)
	private String cnpj;
	@Column(name = "clij_ie", nullable = false, length = 40)
	private String ie;
	@Column(name = "clij_nome", nullable = false, length = 200)
	private String nomeFantasia;

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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nome) {
		this.nomeFantasia = nome;
	}

	public ClientePJ() {
		super();
	}

}
