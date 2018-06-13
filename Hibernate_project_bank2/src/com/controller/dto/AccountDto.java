package com.controller.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AccountEx")
public class AccountDto implements Serializable {
	@Id
	@GenericGenerator(name = "account", strategy = "increment")
	@GeneratedValue(generator = "account")
	@Column(name = "account")
	private int id;
	private String name;
	private String email;
	private String password;
	private String toa;
	private int balance;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<TransactionDto> transdto;

	public String getToa() {
		return toa;
	}

	public void setToa(String toa) {
		this.toa = toa;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TransactionDto> getTransdto() {
		return transdto;
	}

	public void setTransdto(List<TransactionDto> transdto) {
		this.transdto = transdto;
	}

	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", toa="
				+ toa + ", balance=" + balance + "]";
	}

}
