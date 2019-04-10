package estagio.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ContasReceber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cr_id")
	private Long id;

	@Column(name = "cr_abertura", nullable = false)
	private Date abertura;

	@Column(name = "cr_vencimento", nullable = false)
	private Date vencimento;

	@Column(name = "cr_parcelas", nullable = false)
	private int numParcelas;
	// Pgto = pagamento
	@Column(name = "cr_condicaoPgto", nullable = false, length = 20)
	private String condicaoPgto;

	@Column(name = "cr_descricao", nullable = false, length = 100)
	private String descricao;
	
	@ManyToOne(optional = true)
	private Venda venda;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
}
