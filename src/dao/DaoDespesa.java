/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Despesa;
import model.Usuario;
/**
 *
 * @author rhani
 */
public class DaoDespesa {
    //CRUD
    
       public boolean salvar(Despesa despesa) throws SQLException{
           Connection conexao = ConnectionFactory.getConexao();

           String sql = "INSERT INTO DESPESA (descricao, valor, usuario) VALUES (?, ?, ?, ?)";
           java.sql.PreparedStatement ps = conexao.prepareStatement(sql,java.sql.PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, despesa.getDescricao());
		ps.setDouble(2, despesa.getValor());
		ps.setObject(3, despesa.getUsuario());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			despesa.setId(id);
		}
		
		return (linhasAfetadas == 1);
          
       }
       public boolean atualizar(Despesa despesa) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "update despesa set descricao = ?, valor = ? where id = ?";
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, despesa.getDescricao());
		ps.setDouble(2, despesa.getValor());
		ps.setInt(3, despesa.getId());
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "delete from despesa where id = ?";
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Despesa buscar(int idBuscado) throws SQLException {
		
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from despesa where id = ?";
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Despesa despesa = null;
		
		if( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int valor = result.getInt("valor");
			int idUsuario = result.getInt("usuario_id");
			
			DaoUsuario daoUser = new DaoUsuario();
			Usuario u = daoUser.buscarPorId(idUsuario);
			
			despesa = new Despesa(id, descricao, valor, u);
		}
		
		return despesa;
	}

	public List<Despesa> buscarTodas() throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from despesa";
		
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		DaoUsuario daoUser = new DaoUsuario();

		while( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int valor = result.getInt("valor");
			int idUsuario = result.getInt("usuario_id");
			
			Usuario u = daoUser.buscarPorId(idUsuario);
			
			Despesa t = new Despesa(id, descricao, valor, u);
	
			despesas.add(t);
		}
		
		return despesas;
	}

	
	public List<Despesa> despesaPorUsuario(String nome) throws SQLException {
		Connection con = ConnectionFactory.getConexao();
		
		String sql = "select * from despesa left join usuario on despesa.usuario_id = usuario.id where usuario.nome = ?;";
		
		java.sql.PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, nome);
		
		ResultSet result = ps.executeQuery();
		
		List<Despesa> despesas = new ArrayList<>();
		
		
		if( result.next() ) {			
			int idUser = result.getInt("usuario_id");
			String senha = result.getString("senha");
			
			Usuario usuario = new Usuario(idUser, nome, senha);
			
			do {
				int id = result.getInt("id");
				String descricao = result.getString("descricao");
				double valor = result.getInt("valor");
				
				Despesa d = new Despesa(id, descricao, valor, usuario);
		
				despesas.add(d);
			}while(result.next());
		}
		
		return despesas;
	}
}
