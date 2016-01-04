package com.prov.hrm.bank;

import java.util.List;


public interface BankDAO {
	public List<Bank> getAllBank(int organizationId);

	public int addBank(Bank bank);

	public int updateBank(Bank bank);

	public int deleteBank(int bankId);

	public Bank getBankById(int bankId);
}
