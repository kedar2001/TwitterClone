package com.example.demo.controller.normal.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet
public class RegistrationServlet extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		out.print("this is the reg serv");
	}
}
