package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Serie;

public class SerieDAO extends DAO {
	
	public SerieDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Serie serie) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO serie ( titulo, descricao, genero) "
				       + "VALUES ( '" + serie.getTitulo() + "', '"  
				       + serie.getDescricao() + "', '" + serie.getGenero() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Serie get(int codigo) {
		Serie serie = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM serie WHERE codigo=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 serie = new Serie(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("descricao"), rs.getString("genero"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return serie;
	}
	
	
	public List<Serie> get() {
		return get("");
	}

	
	public List<Serie> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Serie> getOrderByTitulo() {
		return get("titulo");		
	}
	
	
	public List<Serie> getOrderByGenero() {
		return get("genero");		
	}
	
	
	private List<Serie> get(String orderBy) {	
	
		List<Serie> serie = new ArrayList<Serie>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM serie" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Serie u = new Serie(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("descricao"), rs.getString("genero"));
	            serie.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return serie;
	}

	
	
	public boolean update(Serie serie) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE serie SET titulo = '" + serie.getTitulo() + "', descricao = '"  
				       + serie.getDescricao() + "', genero = '" + serie.getGenero() + "'"
					   + " WHERE codigo = " + serie.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM serie WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
	
	