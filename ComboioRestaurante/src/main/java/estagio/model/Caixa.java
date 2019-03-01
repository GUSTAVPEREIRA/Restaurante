package estagio.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Caixa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "caixa_id")
	private Long id;
	@Column(name = "caixa_dataAbertura", nullable = false)
	Date dataAbertura;
	@Column(name = "caixa_horaAbertura", nullable = false)
	Time horaAbertura;
	@Column(name = "caixa_dataFechamento", nullable = true)
	Date dataFechamento;
	@Column(name = "caixa_horaFechamento", nullable = true)
	Time horaFechamento;
	@Column(name = "caixa_valorAbertura", nullable = false, precision = 2, scale = 2)
	private double abertura;
	@Column(name = "caixa_valorFechamento", nullable = true, precision = 2, scale = 2)
	private double fechamento;
	@Column(name = "caixa_valorDinheiro", nullable = false, precision = 2, scale = 2)
	private double dinheiro;
	@Column(name = "caixa_valorCredito", nullable = false, precision = 2, scale = 2)
	private double credito;
	@Column(name = "caixa_valorDebito", nullable = false, precision = 2, scale = 2)
	private double debito;
	@Column(name = "caixa_valorCheque", nullable = false, precision = 2, scale = 2)
	private double cheque;
	@Column(name = "caixa_status", nullable = false, length = 8)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JoinColumn(nullable = false)
	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Caixa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Time getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Time horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Time getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Time horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public double getAbertura() {
		return abertura;
	}

	public void setAbertura(double abertura) {
		this.abertura = abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public void setFechamento(double fechamento) {
		this.fechamento = fechamento;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public double getDebito() {
		return debito;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}

	public double getCheque() {
		return cheque;
	}

	public void setCheque(double cheque) {
		this.cheque = cheque;
	}

	public Double somaDinheiro() {
		return (this.cheque + this.credito + this.abertura + this.dinheiro + this.debito);
	}

	public Object priceProperty() {
		return fechamento;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Caixa) {
			Caixa caixa = (Caixa) obj;
			if (caixa.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

}
