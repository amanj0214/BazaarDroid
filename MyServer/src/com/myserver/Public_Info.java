package com.myserver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Public_Info {
	static Connection con;
	static void DoSQLInit(){
	try {
        Class.forName("com.mysql.jdbc.Driver");
//        String connectionUrl = "jdbc:mysql://localhost:3306/mysql";
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","amandeep","some_pass");

    } catch (SQLException e) {
        System.out.println("SQL Exception: "+ e.toString());
//                      text = "done";

    } catch (ClassNotFoundException cE) {
        System.out.println("Class Not Found Exception: "+ cE.toString());                 

    }
	}
}
