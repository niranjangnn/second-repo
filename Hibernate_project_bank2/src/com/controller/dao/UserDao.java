package com.controller.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONObject;

import com.controller.dto.AccountDto;
import com.controller.dto.TransactionDto;
import com.controller.service.HibernzateUtil;

import jdk.nashorn.api.scripting.JSObject;

public class UserDao {
	SessionFactory fac = HibernzateUtil.getFactory();
	

	public boolean signup(AccountDto accountDto) {
		System.out.println("userdao dao" + accountDto);
		Session sess = fac.openSession();
		Transaction tr = sess.beginTransaction();
		sess.save(accountDto);
		tr.commit();
		sess.close();
		return true;

	}

	public AccountDto login(String name, String password) {
		System.out.println("UserDao.login()");
		Session sess = fac.openSession();
//			String str = "select a from AccountDto a where a.name=:name and a.password=:password and rownum =1";
//			Query qry = sess.createQuery(str);
			String str="select * from AccountEx where name=:name and password=:password and rownum=1";
			SQLQuery qry = sess.createSQLQuery(str);
		
			qry.setParameter("name", name);
			qry.setParameter("password", password);
			qry.addEntity(AccountDto.class);
			AccountDto accountDto =(AccountDto) qry.uniqueResult();
			
			System.out.println(accountDto);
			
			
			return accountDto;
		
		
	}

	public int deposit(String toa, int amount, AccountDto accountDto) {
		Session sess = fac.openSession();
		AccountDto accountDto2=getAccountDto(accountDto,toa);
		System.out.println(accountDto2);
		
		int newBalance=accountDto2.getBalance()+amount;
		accountDto2.setBalance(newBalance);
		
		TransactionDto transactiondto= new TransactionDto();
		transactiondto.setAmount(amount);
		transactiondto.setBalance(newBalance);
		transactiondto.setTdate(LocalDateTime.now().toString());
		transactiondto.setToa(toa);
		transactiondto.setTtype("credited");
	
		transactiondto.setAccount(accountDto2);
		
		List<TransactionDto> dtos=accountDto.getTransdto();
		dtos.add(transactiondto);
		
		accountDto2.setTransdto(dtos);
		
		Transaction tr = sess.beginTransaction();
		
		//System.out.println("!!!!!!!!!!!!!!!!!!!!");
		sess.merge(accountDto2);	
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@");
		tr.commit();
		sess.close();
		
		return 1;
	}

	private AccountDto getAccountDto(AccountDto accountDto, String toa) {
		Session sess = fac.openSession();
		String hql="select a from AccountDto a where a.email=:email and a.toa=:toa";
		Query qry=sess.createQuery(hql);
		qry.setParameter("email", accountDto.getEmail());
		qry.setParameter("toa", toa);
		AccountDto accountDto2=(AccountDto) qry.uniqueResult();
		
		return accountDto2;
	}

	public int withdraw(String toa, int amount, AccountDto accountDto) {
		
		Session sess = fac.openSession();
		AccountDto accountDto2=getAccountDto(accountDto,toa);
		System.out.println(accountDto2);
		
		int newBalance=accountDto2.getBalance()-amount;
		accountDto2.setBalance(newBalance);
		
		TransactionDto transactiondto= new TransactionDto();
		transactiondto.setAmount(amount);
		transactiondto.setBalance(newBalance);
		transactiondto.setTdate(LocalDateTime.now().toString());
		transactiondto.setToa(toa);
		transactiondto.setTtype("debited");
	
		transactiondto.setAccount(accountDto2);
		
		List<TransactionDto> dtos=accountDto.getTransdto();
		dtos.add(transactiondto);
		
		accountDto2.setTransdto(dtos);
		Transaction tr = sess.beginTransaction();
		sess.merge(accountDto2);	
		tr.commit();
		sess.close();
		
		return 1;

	}

	public JSONObject getTransaction(String toa, AccountDto accountDto) {
		JSONObject obj=new JSONObject();
		Session sess = fac.openSession();
		AccountDto dto=getAccountDto(accountDto, toa);
		
		Criteria cr = sess.createCriteria(TransactionDto.class);
		//cr.addOrder(Order.desc("transactionId"));
		cr.setFirstResult(0);
        cr.setMaxResults(5);
		obj.put("accountdto", dto);
		System.out.println(dto);
		cr.add(Restrictions.eq("account", dto));
		List<TransactionDto> lst=(List<TransactionDto>)cr.list();
		
		obj.put("transactionlist", lst);
		
		//List<TransactionDto> dtos=dto.getTransdto();
		//List<TransactionDto> transactionDtos=getAccountDto(accountDto, toa).getTransdto();
		return obj;
	}

}
