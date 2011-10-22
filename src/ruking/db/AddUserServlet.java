package ruking.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		//DBconnect
		DBManage dbm=new DBManage();
		String sql="insert into users(username,address) values('"+username+"','"+address+"')";
		dbm.renewData(sql);
		/*try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection conn=DriverManager.getConnection("jdbc:mysql://10.10.6.44:3306/utf80?useUnicode=true&characterEncoding=utf-8","david", "123456");
			Connection conn=DriverManager.getConnection("jdbc:mysql://zkc1e1.chinaw3.com:3306/zkc1e1_db?useUnicode=true&characterEncoding=utf-8","zkc1e1", "p7k5g5r2");
			Statement stmt=conn.createStatement();
			
			//String sql="insert into users(username,address) values('"+username+"','"+address+"')";
		    
			
			stmt.executeUpdate(sql);
		    stmt.close();
		    conn.close();
			/*
			ResultSet res = stmt.executeQuery("select * from user");
			
			while (res.next())
		      {
		    	String f2=res.getString(2);
		    	String f3=res.getString(3);
		    	System.out.println("username:"+f2+"    address:"+f3);
		      }
		      
		      res.close();*/
			
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    //response.setCharacterEncoding("utf-8");
	    response.sendRedirect("showResult.jsp");
	    
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
