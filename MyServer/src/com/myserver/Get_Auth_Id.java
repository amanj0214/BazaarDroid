package com.myserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Get_Auth_Id
 */
public class Get_Auth_Id extends HttpServlet {
	ServletRequest _request;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get_Auth_Id() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		HttpURLConnection connection = null;
		try {
		    URL url = null;
		    try {
		        url = new URL("https://www.google.com/accounts/ClientLogin");
		    } catch (MalformedURLException e) {
		        throw new ServletException(e.getCause());
		    }
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setDoOutput(true);
		    connection.setUseCaches(false);
		    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		} catch (Exception e) {
		    // Exception handling
		}
		
		
		
		
		StringBuilder sb = new StringBuilder();
		 
		addEncodedParameter(sb, "accountType", "GOOGLE");
		addEncodedParameter(sb, "Email", _request.getParameter("pict.c2dm@gmail.com"));
		addEncodedParameter(sb, "Passwd", _request.getParameter("imaginecup"));
		addEncodedParameter(sb, "service", "ac2dm");
		addEncodedParameter(sb, "source", "myCompany-demoApp-1.0.0");
		String data = sb.toString();
		 
		DataOutputStream stream = null;
		try {
			stream = new DataOutputStream(connection.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stream.writeBytes(data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String line;
		String tokenIdentifier = "Auth=";
		String errorIdentifier = "Error=";
		String token = null;
		StringBuilder errors = new StringBuilder();
		try {
			while ((line = reader.readLine()) != null) {
			    if (line.startsWith(tokenIdentifier)) {
			        token = line.substring(tokenIdentifier.length());
			    } else if (line.startsWith(errorIdentifier)) {
			        String error = line.substring(errorIdentifier.length());
			        errors.append(error + System.getProperty("line.separator"));
			    }
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.print(tokenIdentifier);
		
	}
	
	
	public void addEncodedParameter(StringBuilder sb, String name, String value) {
		 
	    if (sb.length() > 0) {
	        sb.append("&");
	    }
	 
	    try {
	        sb.append(URLEncoder.encode(name, "UTF-8"));
	        sb.append("=");
	        sb.append(URLEncoder.encode(value, "UTF-8"));
	    } catch (UnsupportedEncodingException e) {
	    }
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
		_request = request;
	}

}
