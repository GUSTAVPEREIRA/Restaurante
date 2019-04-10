package estagio.dao;

import estagio.model.ContasReceber;

public class ContasReceberDAO extends GenericDAO<ContasReceber>  {
	public ContasReceberDAO() {
		super(ContasReceber.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ContasReceber obj) {
		return obj.getId();
	}
}
