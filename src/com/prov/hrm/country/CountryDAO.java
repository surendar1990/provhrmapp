package com.prov.hrm.country;

import java.util.List;


public interface CountryDAO {

	public List<Country> getAllCountry();

	public int addCountry(Country country);

	public int updateCountry(Country country);

	public int deleteCountry(int countryId);

	public Country getCountryById(int countryId);

}
