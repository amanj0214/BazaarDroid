package com.myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Registration_Server
 */
public class Registration_Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    Connection con;
    public Registration_Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
    	Public_Info.DoSQLInit();
		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
//	        String connectionUrl = "jdbc:mysql://localhost:3306/mysql";
	        con = Public_Info.con;

	    } catch (ClassNotFoundException cE) {
	        System.out.println("Class Not Found Exception: "+ cE.toString());                 
	    }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = "";
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			if (sb.length() != 0) {
				text = sb.toString();
			}
		} catch (Exception e) {
		} 

		
		
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
	//	text = "123123t3127682*jsgd637gduyb7632t72ehbxn8732yh3gg";
		if (!text.equals("")) {
			Add_Registration(text);
			response.getWriter().write("Registration Successful!");
		} else {
			response.getWriter().write("Registration Failed!");
		}	}
	
	
	
	private void Add_Registration(String text) {
		// TODO Auto-generated method stub
		 try {
	            Statement stmt = null;	
	            
	            String[] txt = text.split("\\*");	            
	            System.out.println(" ::" + txt[0] + txt[1]+text);

	            String strSQL = "insert into reg_devices values('" + txt[0] + 
	            	"','" + txt[1] + "')";
	            stmt = (Statement) con.createStatement();
	            int rowsEffected = stmt.executeUpdate(strSQL);
	            System.out.println(rowsEffected + " rows effected" + txt[0] + txt[1]);

	            System.out.println("done"); 
	            text = "done";

	        } catch (SQLException e) {
	            System.out.println("SQL Exception: "+ e.toString());

	        }
	}
}
