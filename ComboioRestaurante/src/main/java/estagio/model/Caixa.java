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

	@JoinColumn(nullable = false)
	@ManyToOne
	private Usuario aberturaAutorizado;

	@JoinColumn(nullable = true)
	@ManyToOne
	private Usuario fechamentoAutorizado;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Usuario usuario;

	public Caixa() {
		super();
	}

	public Caixa(double abertura, double fechamento, double dinheiro, double credito, double debito, double cheque,
			String status, Usuario usuario) {
		super();
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.dinheiro = dinheiro;
		this.credito = credito;
		this.debito = debito;
		this.cheque = cheque;
		this.status = status;
		this.usuario = usuario;
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

	public double getAbertura() {
		return abertura;
	}

	public Usuario getAberturaAutorizado() {
		return aberturaAutorizado;
	}

	public double getCheque() {
		return cheque;
	}

	public double getCredito() {
		return credito;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public double getDebito() {
		return debito;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public double getFechamento() {
		return fechamento;
	}

	public Usuario getFechamentoAutorizado() {
		return fechamentoAutorizado;
	}

	public Time getHoraAbertura() {
		return horaAbertura;
	}

	public Time getHoraFechamento() {
		return horaFechamento;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Object priceProperty() {
		return fechamento;
	}

	public void setAbertura(double abertura) {
		this.abertura = abertura;
	}

	public void setAberturaAutorizado(Usuario aberturaAutorizado) {
		this.aberturaAutorizado = aberturaAutorizado;
	}

	public void setCheque(double cheque) {
		this.cheque = cheque;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public void setFechamento(double fechamento) {
		this.fechamento = fechamento;
	}

	public void setFechamentoAutorizado(Usuario fechamentoAutorizado) {
		this.fechamentoAutorizado = fechamentoAutorizado;
	}

	public void setHoraAbertura(Time horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public void setHoraFechamento(Time horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double somaDinheiro() {
		return (this.cheque + this.credito + this.abertura + this.dinheiro + this.debito);
	}

}
