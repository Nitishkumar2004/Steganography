package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.util;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		          
		String id=request.getParameter("id");
		String n=request.getParameter("name");  
		String e=request.getParameter("email");  
		String p=request.getParameter("number");  
		String pa=request.getParameter("pas"); 
		String a=request.getParameter("enroll");
		String s="ACTIVE";
		
		try{  
			
			  
			PreparedStatement ps=util.getconnection().prepareStatement("insert into register values(?,?,?,?,?,?,?)");
			ps.setString(1,id);  
			ps.setString(2,n);  
			ps.setString(3,e);  
			ps.setString(4,p);  
			ps.setString(5,pa);  
			ps.setString(6,a);
			ps.setString(7,s);
		
			          
			int i=ps.executeUpdate();  
			if(i>0) 
			{
				out.print("<html><body><script>alert('Registered Successfully');</script></body></html>");
			RequestDispatcher rs= request.getRequestDispatcher("Login.jsp");
			rs.include(request,response);
			}
			          
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
			  
			}  