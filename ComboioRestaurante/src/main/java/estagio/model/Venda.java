/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Pereira
 */
@Entity
public class Venda implements Serializable{
    
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "vend_id")
	private Long id;
	@Column(name = "vend_data", nullable = false)
	private Date data;
	@Column(name = "vend_valorTotal", nullable = false, length = 15, precision = 2)
	private Double valorTotal;
	@ManyToOne(optional = true)
	private Fornecedor fornecedor;
	@OneToMany(mappedBy = "venda")
	private List<ItensVenda> listaItensVenda;
	
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItensVenda> getListaItensVenda() {
		return listaItensVenda;
	}

	public void setListaItensVenda(List<ItensVenda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}

	public void addItemVenda(ItensVenda account) {
		// prevent endless loop
		if (listaItensVenda.contains(account))
			return;
		// add new account
		listaItensVenda.add(account);
		// set myself into the twitter account
		//account.setCompra(this);
	}

	public void removeItemVenda(ItensVenda account) {
		// prevent endless loop
		if (!listaItensVenda.contains(account))
			return;
		// remove the account
		listaItensVenda.remove(account);
		// remove myself from the twitter account
		account.setVenda(null);
	}
}
