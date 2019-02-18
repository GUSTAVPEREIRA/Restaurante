/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Pereira
 */
@SuppressWarnings("serial")
@Entity
public class Estado implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="est_id")
    private Long id;
    @Column(name="est_nome",length = 100,nullable = false)
    private String nome;
    @Column(name="est_uf",length = 2,nullable = false)
    private String uf;
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    @Override
    public String toString() {
        return getNome();      
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Estado) {
			Estado est = (Estado)obj;
    		if (est.getNome().equals(this.nome) && est.getId().equals(this.id)) {
				return true;
			}
		}
    	return false;
    }
    
    
}
