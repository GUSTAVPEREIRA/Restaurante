package estagio.dao;

import estagio.model.ContasPagar;

public class ContasPagarDAO extends GenericDAO<ContasPagar> {

	public ContasPagarDAO() {
		super(ContasPagar.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ContasPagar obj) {
		return obj.getId();
	}

}
