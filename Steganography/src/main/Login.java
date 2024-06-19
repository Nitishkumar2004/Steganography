package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Data.util;

@WebServlet("/Login1")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String em = request.getParameter("em");
		String pa = request.getParameter("pw");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();

		try {
			
			if (em.equalsIgnoreCase("ADMIN@gmail.com") && pa.equalsIgnoreCase("ADMIN@123")) {
			
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);
			}
			else {
				
				String qry = "SELECT * FROM register WHERE email='"+em+"' AND password='"+pa+"'";
				ResultSet rs = util.getconnection().createStatement().executeQuery(qry);	
				
				if(rs.next()) {
				
				String Role = rs.getString("enroll");
				System.out.println(Role);
				String email=rs.getString("email");

				if(Role.equals("LAB")) {
					session.setAttribute("mail", email);
					session.setAttribute("Role", 6);
					RequestDispatcher rd = request.getRequestDispatcher("Lab.jsp");
					rd.forward(request, response);
				}else if (Role.equals("DOCTOR")){
					session.setAttribute("mail", email);
					session.setAttribute("Role", Role);
		            session.setAttribute("id", rs.getString(1));
		            session.setAttribute("name", rs.getString(2));
					RequestDispatcher rd = request.getRequestDispatcher("doctor.jsp");
					rd.forward(request, response);
				}else if (Role.equals("STAFF")){
					session.setAttribute("mail", email);
					session.setAttribute("Role", Role);
		            session.setAttribute("id", rs.getString(1));
		            session.setAttribute("name", rs.getString(2));
					/*session.setAttribute("Role", 4);
					session.setAttribute("cid", rs.getString(8));*/
					RequestDispatcher rd = request.getRequestDispatcher("staff.jsp");
					rd.forward(request, response);
				}
				
			}else {
				out.print("<html><body><script>alert('Usermail or Password are incorrect')</script></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.include(request, response);
			}
		}
				
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
