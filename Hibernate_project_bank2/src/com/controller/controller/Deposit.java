package com.controller.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.dao.UserDao;
import com.controller.dto.AccountDto;

@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		AccountDto accountDto=(AccountDto) session.getAttribute("accinfo");
		
	int amount=Integer.parseInt(request.getParameter("amount"));
	String toa=request.getParameter("toa");
	UserDao dao=new UserDao();
	int x=dao.deposit(toa,amount,accountDto);
	if(x==1)
	{
		System.out.println("sucessfully deposited!!");
		PrintWriter out=response.getWriter();
		out.println("Sucessfully Deposied!!");
		RequestDispatcher rd=request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}
	else
	{
		System.out.println("Else loop deposit");
		response.sendRedirect("deposit.jsp");
	}
		
		
		doGet(request, response);
	}

}
