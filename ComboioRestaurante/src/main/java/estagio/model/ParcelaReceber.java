package estagio.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParcelaReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpr_id")
	@Id
	private Long id;
	@Column(name = "cpr_abertura", nullable = false)
	private Date abertura;
	@Column(name = "cpr_vencimento", nullable = false)
	private Date vencimento;
	@Column(name = "cpr_pgto", nullable = true)
	private Date pgto;
	@Column(name = "cpr_valor", nullable = false, scale = 2)
	private Double valor;
	@Column(name = "cpr_valorPgto", nullable = false, scale = 2)
	private Double valorPgto;
	@Column(name = "cpr_status", length = 20, nullable = true)
	private String status;
	@Column(name = "cpr_tipoPagamento", length = 20, nullable = true)
	private String condicao;
	@Column(name = "cpr_numeroParcela", nullable = false)
	private int numeroParcela;
	@Column(name = "cpr_hashReferencia", nullable = true)
	private Long idRef;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Long getIdRef() {
		return idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
