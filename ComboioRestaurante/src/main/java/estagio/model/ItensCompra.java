package estagio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItensCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ic_id")
	private Long id;
	@Column(name = "ic_quant",nullable=false)
	private int quantidade;
	@Column(name = "ic_valor",nullable=false)
	private Double valor;
	// Assim que se faz um relacionamento bidirecional de classe de associaço
	@ManyToOne(cascade = CascadeType.MERGE)
	private Produto produto;
	@ManyToOne
	private Compra compra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
	    //prevent endless loop
	    if (sameAsFormer(compra))
	      return ;
	    //set new compra
	    Compra oldCompra = this.compra;
	    this.compra = compra;
	    //remove from the old compra
	    if (oldCompra!=null)
	      oldCompra.removeItemCompra(this);
	    //set myself into new compra
	    if (compra!=null)
	      compra.addItemCompra(this);
	  }

	  private boolean sameAsFormer(Compra newCompra) {
	    return compra==null? newCompra == null : compra.equals(newCompra);
	  }

}
