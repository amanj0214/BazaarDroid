package com.myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Get_Rating
 */
public class Get_Rating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
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
     * @see HttpServlet#HttpServlet()
     */
    Connection con;
    public Get_Rating() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		if (text.equals("get_rating")) {
			response.getWriter().write("2.0");
		} else if (!text.equals("")){			
			Add_Rating(text);
			response.getWriter().write("Rating Posted Successfully!");
			System.out.print(text);
		}else {
			response.getWriter().write("Rating Failed!");
		}
	}

	private void Add_Rating(String text) {
		// TODO Auto-generated method stub
		 try {
	            Statement stmt = null;	
	            
	            String[] txt = text.split("\\*");	            
	            System.out.println(" ::" + txt[0] +" "+ txt[1]+" "+ txt[2]);
	            
	            String strSQL1 = "select * from rating where imei='"+txt[0]+"' and ProjectId='"+txt[1]+"'";
	         
            stmt = (Statement) con.createStatement();
            stmt.executeQuery(strSQL1);
            if(stmt.getResultSet() == null){
	            System.out.println("You already rated for this project");
            }
            
	            
	            
	            String strSQL = "insert into rating values('" + txt[0] + 
	            	"','" + txt[1] + "','" + txt[2] + "')";
	            stmt = (Statement) con.createStatement();
	            int rowsEffected = stmt.executeUpdate(strSQL);
	            System.out.println(rowsEffected + " rows effected" + txt[1] + txt[2]);

	            System.out.println("done"); 
	            text = "done";
	            
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: "+ e.toString());

	        }
	}

}
