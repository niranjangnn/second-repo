package com.controller.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.dao.UserDao;
import com.controller.dto.AccountDto;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserDao dao = new UserDao();
		AccountDto accountDto = dao.login(name, password);
		if (accountDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", accountDto.getName());
			session.setAttribute("accinfo", accountDto);
			session.setMaxInactiveInterval(60);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.include(request, response);

		} else {
			System.out.println("else loop login controller");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);

		}

	}

}
