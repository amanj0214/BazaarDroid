package com.myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Notification
 */
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notification() {
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
		if (text.equals("request_judge")) {
			
//			response.getWriter().write("Following group Id : IM-245, C0-243 , C0-212  Your Judge is on his way please be ready with your respective projects");
			response.getWriter().write("New Seminar in Auditorium. Title : Using C2DM in Android. Speakers : Mr.Joshi , Mr.Bharat");
			
			
			System.out.print(text);

		}
		else if (text.equals("request_seminar")) {
			
			response.getWriter().write("New Seminar in Auditorium. Title : Using C2DM in Android. Speakers : Mr.Joshi , Mr.Bharat");
			System.out.print(text);

		} 
		else {
			response.getWriter().write("Ratingksjdf Failed!");
		}
	}

}
