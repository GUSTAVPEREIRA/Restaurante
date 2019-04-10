package estagio.dao;

import estagio.model.ParcelaReceber;

public class ParcelaReceberDAO extends GenericDAO<ParcelaReceber> {
	public ParcelaReceberDAO() {
		super(ParcelaReceber.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getID(ParcelaReceber obj) {
		return obj.getId();
	}
}
