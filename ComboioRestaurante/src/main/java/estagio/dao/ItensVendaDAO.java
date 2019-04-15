package estagio.dao;

import estagio.model.ItensVenda;

public class ItensVendaDAO extends GenericDAO<ItensVenda> {

	public ItensVendaDAO() {
		super(ItensVenda.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ItensVenda obj) {
		return obj.getId();
	}

}
