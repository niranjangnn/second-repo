package com.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TransactionDto")
public class TransactionDto {
	@Id
	@GenericGenerator(name = "Transaction", strategy = "increment")
	@GeneratedValue(generator = "Transaction")
	@Column(name = "tid")
	private int transid;
	private String toa;
	private String ttype;


	private int amount;
	private int balance;
	private String tdate;
	@ManyToOne
	@JoinColumn(name = "aid")
	private AccountDto account;
	
	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public int getTransid() {
		return transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public String getToa() {
		return toa;
	}

	public void setToa(String toa) {
		this.toa = toa;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "TransactionDto [transid=" + transid + ", toa=" + toa + ", ttype=" + ttype + ", amount=" + amount
				+ ", balance=" + balance + ", tdate=" + tdate + ", account=" + account + "]";
	}

	

}
