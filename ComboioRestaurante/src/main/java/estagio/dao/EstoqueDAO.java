package estagio.dao;

import estagio.model.Estoque;

public class EstoqueDAO extends GenericDAO<Estoque> {

	public EstoqueDAO() {
		super(Estoque.class);
	}

	@Override
	public Long getID(Estoque obj) {
		return obj.getId();
	}
}
