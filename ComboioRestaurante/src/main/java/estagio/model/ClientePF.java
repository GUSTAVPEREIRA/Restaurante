package estagio.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class ClientePF extends Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "clif_cpf", nullable = false, length = 20)
	private String cpf;
	@Column(name = "clif_rg", nullable = false, length = 20)
	private String rg;
	@Column(name = "clif_estadoCivil", nullable = false, length = 20)
	private String estadoCivil;
	@Column(name = "clif_dataNasc", nullable = false)
	private Date dataNasc;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public ClientePF() {
		super();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClientePF) {
			ClientePF cli = (ClientePF) obj;
			if (cli.getEstadoCivil().equals(this.estadoCivil) && cli.getCpf().equals(this.getCpf())) {
				return true;
			}
		}
		return false;
	}
	

}
