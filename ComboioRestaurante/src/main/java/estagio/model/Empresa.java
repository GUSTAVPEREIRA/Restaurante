package estagio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
	@Column(name = "emp_nome", nullable = false, length = 100)
	private String nome;
	@Column(name = "emp_cnpj", nullable = false, length = 20,unique=true)
	private String cnpj;
	@Column(name = "emp_ie", nullable = false, length = 40)
	private String ie;
	@Column(name = "emp_img", nullable = false, length = 500)
	private String caminho_imgem;
	@Column(name = "emp_cep", nullable = false, length = 20)
	private String cep;
	@Column(name = "emp_telefone", nullable = false, length = 20)
	private String telefone;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@JoinColumn(nullable = false)
	@ManyToOne
	private Cidade cidade;

	public void setCaminho_imgem(String caminho_imgem) {
		this.caminho_imgem = caminho_imgem;
	}

	public String getCaminho_imgem() {
		return caminho_imgem;
	}

	public Empresa() {
		super();
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
