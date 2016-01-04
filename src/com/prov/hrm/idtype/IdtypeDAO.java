package com.prov.hrm.idtype;

import java.util.List;

public interface IdtypeDAO {

	public List<Idtype> getAllIdtype(int organizationId);

	public int addIdtype(Idtype idtype);

	public int updateIdtype(Idtype idtype);

	public int deleteIdtype(int idtypeId);

	public Idtype getIdtypeById(int idtypeId);

}
