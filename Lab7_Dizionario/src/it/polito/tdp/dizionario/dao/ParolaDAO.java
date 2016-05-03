package it.polito.tdp.dizionario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParolaDAO {

	public List<String> getListaParole(int length) {

		List<String> res = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		String sql = "SELECT nome FROM parola WHERE length(nome) = ?;";

		try {

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, length);

			ResultSet ress = st.executeQuery();

			while (ress.next())
				res.add(ress.getString("nome"));

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public List<String> paroleSimili(String s) {
		List<String> res = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			
			paroleSimiliInPosizione(s,i);

		}

		return res;
	}

	private List<String> paroleSimiliInPosizione(String s, int pos) {

		List<String> res = new LinkedList<>();

		String sql = "SELECT nome FROM parola WHERE nome LIKE ?";
		
		String p = s.substring(0,pos);
		
		p += "_";
		
		p += s.substring(pos+1);
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p);
			
			ResultSet ress = st.executeQuery();
			
			while (ress.next())
				res.add(ress.getString("nome"));
			
			conn.close();
			
			System.out.println("Ho trovato le parole vicine per " + s +"\n");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		return res;
	}

}
