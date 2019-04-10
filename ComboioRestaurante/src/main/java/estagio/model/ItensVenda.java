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
public class ItensVenda implements Serializable{
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iv_id")
	private Long id;
	@Column(name = "iv_quant",nullable=false)
	private int quantidade;
	@Column(name = "iv_valor",nullable=false)
	private Double valor;
	// Assim que se faz um relacionamento bidirecional de classe de associação
	@ManyToOne(cascade = CascadeType.MERGE)
	private Produto produto;
	@ManyToOne
	private Venda venda;

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
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
	    //prevent endless loop
	    if (sameAsFormer(venda))
	      return ;
	    //set new venda
	    Venda oldVenda = this.venda;
	    this.venda = venda;
	    //remove from the old venda
	    if (oldVenda!=null)
	    	oldVenda.removeItemVenda(this);
	    //set myself into new venda
	    if (venda!=null)
	    	venda.addItemVenda(this);
	  }
	
	  private boolean sameAsFormer(Venda newVenda) {
		    return venda==null? newVenda == null : venda.equals(newVenda);
		  }
	
}
