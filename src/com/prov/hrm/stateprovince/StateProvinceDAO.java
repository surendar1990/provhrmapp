package com.prov.hrm.stateprovince;

import java.util.List;

import com.prov.hrm.country.Country;

public interface StateProvinceDAO {
	public List<StateProvince> getAllState(Country country);

	public int addState(StateProvince stateprovince);

	public int updateState(StateProvince stateprovince);

	public int deleteState(int stateId);

	public StateProvince getStateById(int stateId);

}
