package com.prov.hrm.bank;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.country.Country;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.utility.SessionFactoryUtil;

public class BankDAOImpl implements BankDAO {

	// Return all record from tblbank
	public List<Bank> getAllBank(int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.asc("bankName"));
			List<Bank> ls=criteria.list();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	// Return a record from tblbank for specific bank_id
	@Override
	public Bank getBankById(int bankId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Bank bank = (Bank) session.get(Bank.class, bankId);
			if (bank != null) {
				if (!bank.getDeleteFlag()) {
					return bank;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	
	
	// Insert a record into tblbank

		public int addBank(Bank bank) throws HibernateException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				Criteria criteria=session.createCriteria(Bank.class);
				criteria.add(Restrictions.eq("bankName",bank.getBankName()));
				criteria.add(Restrictions.eq("organizationId",bank.getOrganizationId()));
				if(criteria.list().size()!=0){
					List<Bank> bank1=new ArrayList<Bank>();
					bank1=criteria.list();
					Iterator<Bank> ite= bank1.iterator();
					while(ite.hasNext()){
						Bank bank2=ite.next();
						if(bank2.getDeleteFlag()==false){
							status=2;
						}else{
							bank2.setDeleteFlag(false);
							bank2.setUpdateDate(date.toString());
							bank2.setInsertDate(date.toString());
							bank2.setInsertBy(bank.getInsertBy());
							bank2.setUpdateBy(bank.getUpdateBy());
							session.update(bank2);
				status = 1;
				}}
				}
				else{
					bank.setUpdateDate(date.toString());
					bank.setInsertDate(date.toString());
					session.save(bank);
					status = 1;
				}
				
				return status;
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
			}
		}



		// Update a record in tblbank for specific bank_id
		@Override
		public int updateBank(Bank bank) throws HibernateException,
				ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				Criteria criteria=session.createCriteria(Bank.class);
				criteria.add(Restrictions.eq("bankName",bank.getBankName()));
				criteria.add(Restrictions.eq("organizationId", bank.getOrganizationId()));
				criteria.add(Restrictions.eq("deleteFlag",false));
				criteria.add(Restrictions.ne("bankId", bank.getBankId()));
				if(criteria.list().size()==0)
				{ System.out.println("size");
					session.clear();
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					bank.setUpdateDate(date.toString());
					Bank bank1 = getBankById(bank.getBankId());
					if (bank1 != null) {
						bank.setInsertBy(bank1.getInsertBy());
						bank.setInsertDate(bank1.getInsertDate());
						session.update(bank);
						status = 1;
						return status;
					} else {
						return status;
					}
				}
				
				else{

					return 5;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
				
			}
		}




	// Soft Delete a record from tblbank for specific bank_id
		public int deleteBank(int bankId) {
			int status = 0;
			
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				
				Bank bank = getBankById(bankId);
				Criteria criteria = session.createCriteria(EmployeeBank.class);
				criteria.add(Restrictions.eq("organizationId", bank.getOrganizationId()));
				criteria.add(Restrictions.eq("deleteFlag", false));
				criteria.add(Restrictions.eq("bank", new Bank(bank.getBankId())));
				if(criteria.list().size()!=0)
				{
					
					return 2;
				}
				if (bank != null &&  criteria.list().size()==0) {
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					bank.setUpdateDate(date.toString());
					bank.setUpdateBy(null);
					bank.setDeleteFlag(true);
					updateBank(bank);
					status = 1;
					return status;
					
				}
				else {
					return status;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			}
		}


	// Hard Delete a record from tblbank for specific bank_id
	public int deleteBankHard(int bankId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Bank bank = getBankById(bankId);
			session.delete(bank);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}

}
