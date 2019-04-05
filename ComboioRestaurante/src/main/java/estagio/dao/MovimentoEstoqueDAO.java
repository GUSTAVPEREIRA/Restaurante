package estagio.dao;

import estagio.model.MovimentoEstoque;

public class MovimentoEstoqueDAO extends GenericDAO<MovimentoEstoque> {

	public MovimentoEstoqueDAO() {
		super(MovimentoEstoque.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(MovimentoEstoque obj) {
		return obj.getId();
	}
}
