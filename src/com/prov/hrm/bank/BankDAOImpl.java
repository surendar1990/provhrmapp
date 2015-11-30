package com.prov.hrm.bank;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

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
			List<Bank> ls=criteria.list();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
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
			
		}
	}

	// Insert a record into tblbank

	public int addBank(Bank bank) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new Timestamp(
					new java.util.Date().getTime());
			bank.setUpdateDate(date.toString());
			bank.setInsertDate(date.toString());
			session.save(bank);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Soft Delete a record from tblbank for specific bank_id
	public int deleteBank(int bankId) {
		int status = 0;
		try {
			Bank bank = getBankById(bankId);
			if (bank != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				bank.setUpdateDate(date.toString());
				bank.setUpdateBy(null);
				bank.setDeleteFlag(true);
				updateBank(bank);
				status = 1;
				return status;
			} else {
				return 0;
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
			

		}
	}

}
