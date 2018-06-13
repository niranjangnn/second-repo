package com.controller.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.controller.dao.UserDao;
import com.controller.dto.AccountDto;
import com.controller.dto.TransactionDto;

@WebServlet("/transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Transaction Controller");
		HttpSession session = request.getSession(false);
		AccountDto accountDto = (AccountDto) session.getAttribute("accinfo");
		String toa = request.getParameter("toa");
		UserDao dao = new UserDao();
		
		JSONObject obj = dao.getTransaction(toa, accountDto);
		AccountDto dto=(AccountDto) obj.get("accountdto") ;
		List<TransactionDto> dtos=(List<TransactionDto>) obj.get("transactionlist");
//		List<TransactionDto> dtos = dto.getTransdto();
		
		request.setAttribute("transactionlist", dtos);
		request.setAttribute("accNo", dto.getId());
		RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
		rd.forward(request, response);
		for (int i = 0; i < dtos.size(); i++) {
			System.out.println(dtos.get(i));

		}

		for (TransactionDto transactionDto : dtos) {
			System.out.println(transactionDto.getBalance());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
