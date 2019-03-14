package estagio.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParcelaPagar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpp_id")
	@Id
	private Long id;
	@Column(name = "cpp_abertura", nullable = false)
	private Date abertura;
	@Column(name = "cpp_vencimento", nullable = false)
	private Date vencimento;
	@Column(name = "cpp_pgto", nullable = true)
	private Date pgto;
	@Column(name = "cpp_valor", nullable = false, precision = 2)
	private Double valor;
	@Column(name = "cpp_valorPgto", nullable = false, precision = 2)
	private Double valorPgto;
	@Column(name = "cpp_status", length = 20, nullable = true)
	private String status;
	@Column(name = "cpp_numeroParcela",nullable = false)
	private int numeroParcela;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	@JoinColumn(nullable = false)
	@ManyToOne
	private ContasPagar contasPagar;
	
	
	public ContasPagar getContasPagar() {
		return contasPagar;
	}

	public void setContasPagar(ContasPagar contasPagar) {
		this.contasPagar = contasPagar;
	}

	public ParcelaPagar() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getPgto() {
		return pgto;
	}

	public void setPgto(Date pgto) {
		this.pgto = pgto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorPgto() {
		return valorPgto;
	}

	public void setValorPgto(Double valorPgto) {
		this.valorPgto = valorPgto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
