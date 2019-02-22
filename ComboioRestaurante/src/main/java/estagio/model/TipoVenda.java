package estagio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class TipoVenda implements Serializable{

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "tipv_id")
    private Long id;
    @Column(name = "tipv_nome",length=100,nullable=false)
    private String nome;
    
	public TipoVenda() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	@Override
	public String toString() {
		return getNome();
	}
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof TipoVenda) {
    		TipoVenda tipoV = (TipoVenda)obj;
    		if (tipoV.getNome().equals(this.nome) && tipoV.getId().equals(this.id)) {
				return true;
			}
		}
    	return false;
    }
}
