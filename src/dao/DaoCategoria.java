/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author rhani
 */

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Despesa;

public class DaoCategoria {
    //CRUD
    
       public boolean salvar(Categoria categoria) throws SQLException{
           Connection conexao = ConnectionFactory.getConexao();

           String sql = "INSERT INTO CATEGORIA (nome, tipo) VALUES ( ?, ?)";
           java.sql.PreparedStatement ps = conexao.prepareStatement(sql,java.sql.PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, categoria.getNome());
		ps.setString(2, categoria.getTipo());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			categoria.setId(id);
		}
		
		return (linhasAfetadas == 1);
          
       }
       public boolean atualizar(Categoria categoria) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "update despesa set nome = ?, tipo = ? where id = ?";
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, categoria.getNome());
		ps.setString(2, categoria.getTipo());
		ps.setInt(3, categoria.getId());
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public List<Categoria> buscarTodas() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from categoria";
		
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String nome = result.getString("nome");
			String tipo = result.getString("tipo");			
			Categoria c = new Categoria(id, nome, tipo);
	
			categorias.add(c);
		}
		
		return categorias;
	}
}