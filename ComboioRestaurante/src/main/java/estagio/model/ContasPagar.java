package estagio.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ContasPagar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cp_id")
	private Long id;

	@Column(name = "cp_abertura", nullable = false)
	private Date abertura;

	@Column(name = "cp_vencimento", nullable = false)
	private Date vencimento;

	@Column(name = "cp_parcelas", nullable = false)
	private int numParcelas;
	// Pgto = pagamento
	@Column(name = "cp_condicaoPgto", nullable = false, length = 20)
	private String condicaoPgto;

	@Column(name = "cp_descricao", nullable = false, length = 100)
	private String descricao;
	
	@ManyToOne(optional = true)
	private Compra compra;

	public String getDescricao() {
		return descricao;
	}
	
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "cp_status", nullable = false, length = 20)
	private String status;
	@Column(name = "cp_valorTotal", nullable = false, precision = 2)
	private Double valorTotal;
	@JoinColumn(nullable = true)
	@ManyToOne
	private Fornecedor fornecedor;

	public ContasPagar() {
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

	public int getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}

	public String getCondicaoPgto() {
		return condicaoPgto;
	}

	public void setCondicaoPgto(String condicaoPgto) {
		this.condicaoPgto = condicaoPgto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}
