package estagio.dao;

import estagio.model.ItensCompra;

public class ItensCompraDAO extends GenericDAO<ItensCompra> {

	public ItensCompraDAO() {
		super(ItensCompra.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ItensCompra obj) {
		return obj.getId();
	}

}
