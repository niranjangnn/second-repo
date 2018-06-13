package com.controller.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.dao.UserDao;
import com.controller.dto.AccountDto;
import com.controller.dto.TransactionDto;
import com.oracle.jrockit.jfr.RequestableEvent;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int deposit = 500;
		System.out.println("Signup.doPost()");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String toa = request.getParameter("toa");
		AccountDto accountDto = new AccountDto();
		accountDto.setName(name);
		accountDto.setEmail(email);
		accountDto.setPassword(password);
		accountDto.setToa(toa);
		if (toa.equalsIgnoreCase("current")) {
			deposit = 5000;
			accountDto.setBalance(deposit);
		} else {
			accountDto.setBalance(deposit);
		}
		
		TransactionDto dto=new TransactionDto();
		dto.setAmount(deposit);
		dto.setBalance(deposit);
		dto.setTdate(LocalDateTime.now().toString());
		dto.setToa(toa);
		dto.setTtype("credited");
		
		List<TransactionDto> dtos=new ArrayList<>();
		dtos.add(dto);
		accountDto.setTransdto(dtos);
		dto.setAccount(accountDto);
		
		UserDao dao = new UserDao();
		boolean x = dao.signup(accountDto);
		if (x) {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.include(request, response);
		}

	}

}
