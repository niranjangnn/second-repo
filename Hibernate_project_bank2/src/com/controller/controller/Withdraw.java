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

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession(false);
		AccountDto accountDto=(AccountDto) session.getAttribute("accinfo");
		
	int amount=Integer.parseInt(request.getParameter("amount"));
	String toa=request.getParameter("toa");
	UserDao dao=new UserDao();
	int x=dao.withdraw(toa,amount,accountDto);
	if(x==1)
	{
		System.out.println("sucessfully Withdrawn!!");
		PrintWriter out=response.getWriter();
		out.println("Sucessfull Withdraw!!");
		RequestDispatcher rd=request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}
	else
	{
		System.out.println("Else loop Withdraw");
		response.sendRedirect("withdraw.jsp");
	}
		
		
		doGet(request, response);
	}


}
