package it.polito.tdp.dizionario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=";
	

	public static Connection getConnection() {

			Connection connection;
		try {
			
				connection = DriverManager.getConnection(jdbcURL);
				return connection;
			
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new RuntimeException("Errore nella connessione", null);
		}
	}

}