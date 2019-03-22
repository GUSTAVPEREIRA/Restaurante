package estagio.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "comp_id")
	private Long id;
	@Column(name = "comp_data", nullable = false)
	private Date data;
	@Column(name = "comp_valorTotal", nullable = false,length=15,precision=2)
	private Double valorTotal;
	@ManyToOne
	private Fornecedor fornecedor; 
	@OneToMany(fetch= FetchType.LAZY, mappedBy="compra")
	private List<ItensCompra> listaItensCompra;
	
	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List<ItensCompra> getListaItensCompra() {
		return listaItensCompra;
	}
	public void setListaItensCompra(List<ItensCompra> listaItensCompra) {
		this.listaItensCompra = listaItensCompra;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
