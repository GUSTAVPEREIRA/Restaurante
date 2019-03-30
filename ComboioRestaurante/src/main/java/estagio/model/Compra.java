package estagio.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "comp_valorTotal", nullable = false, length = 15, precision = 2)
	private Double valorTotal;
	@ManyToOne(optional = true)
	private Fornecedor fornecedor;
	@OneToMany(mappedBy = "compra")
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

	/**
	   * Add new itemcompra to the compra. The method keeps 
	   * relationships consistency:
	   * * this compra is set as the itemcompra compra
	   */
	public void addItemCompra(ItensCompra account) {
		// prevent endless loop
		if (listaItensCompra.contains(account))
			return;
		// add new account
		listaItensCompra.add(account);
		// set myself into the twitter account
		account.setCompra(this);
	}

	/**
	 * Removes the itemcompra from the compra. The method keeps relationships
	 * consistency: * the itemcompra will no longer reference this compra as its owner
	 */
	public void removeItemCompra(ItensCompra account) {
		// prevent endless loop
		if (!listaItensCompra.contains(account))
			return;
		// remove the account
		listaItensCompra.remove(account);
		// remove myself from the twitter account
		account.setCompra(null);
	}

}
