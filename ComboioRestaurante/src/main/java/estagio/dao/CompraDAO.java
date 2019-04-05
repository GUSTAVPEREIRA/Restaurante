package estagio.dao;

import estagio.model.Compra;

public class CompraDAO extends GenericDAO<Compra>{


	public CompraDAO() {
		super(Compra.class);
	}

	@Override
	public Long getID(Compra obj) {
		return obj.getId();
	}
	
}
