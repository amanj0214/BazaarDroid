package com.myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class MyServer
 */
public class MyServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HELLO_STRING = "Server's response : ";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
    Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
        	String text=null;
        	
        	Public_Info.DoSQLInit();
		
    		try {
    	        Class.forName("com.mysql.jdbc.Driver");
//    	        String connectionUrl = "jdbc:mysql://localhost:3306/mysql";
    	        con = Public_Info.con;

    	    } catch (ClassNotFoundException cE) {
    	        System.out.println("Class Not Found Exception: "+ cE.toString());                 
    	    }	
        	
        	
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
//	            String connectionUrl = "jdbc:mysql://localhost:3306/mysql";
	    //        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","amandeep","some_pass");
	            con = Public_Info.con;


	            Statement stmt = null;
	            ResultSet rs = null;
	            //SQL query command
	            String SQL = "SELECT * FROM reg_devices";
	            stmt = (Statement) con.createStatement();
	            rs = stmt.executeQuery(SQL);
	            while (rs.next()) {
	                System.out.println(rs.getString("imei") + " : " + rs.getString("reg_id"));
	            }



	
	                     //   Statement stmt = null;
	          //  PreparedStatement stmt = null;
	            String strSQL = "insert into reg_devices values('123133123','3ytuygq3tehuwhdugw63teyh')";
//	            stmt = con.prepareStatement(strSQL);
	            int rowsEffected = stmt.executeUpdate(strSQL);
	            System.out.println(rowsEffected + " rows effected");

	            System.out.println("done"); 
	            text = "done";

	        } catch (SQLException e) {
	            System.out.println("SQL Exception: "+ e.toString());
	  //                      text = "done";

	        } catch (ClassNotFoundException cE) {
	            System.out.println("Class Not Found Exception: "+ cE.toString());
	                        text = "EROR:done";

	        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
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

		if (!text.equals("")) {
			response.getWriter().write(HELLO_STRING + text);
		} else {
			response.getWriter().write(HELLO_STRING);
		}

	}

}
