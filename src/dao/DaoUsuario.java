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
import javafx.scene.control.PasswordField;
import model.Despesa;
import model.Usuario;

/**
 *
 * @author rhani
 */
public class DaoUsuario {
    public boolean salvar(Usuario usuario) throws SQLException{
        Connection conexao = ConnectionFactory.getConexao();

        String sql = "insert into usuario (nome, senha) values(? , ?);";
        PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql, java.sql.PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getSenha());

        int linhasAfetadas = ps.executeUpdate();

                try (ResultSet r = ps.getGeneratedKeys()) {
                    if (r.next()) {
                        int id = r.getInt(1);
                        usuario.setId(id);
                    }
                }
                return linhasAfetadas == 1;
          
       }
    public Usuario buscarPorId(int idBuscado) throws SQLException {
        Connection con = ConnectionFactory.getConexao();

        String sql = "select * from usuario where id = ?";
        java.sql.PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idBuscado);

        ResultSet result = ps.executeQuery();

        Usuario usuario = null;

        if (result.next()) {
            int id = result.getInt("id");
            String nome = result.getString("nome");
            String senha = result.getString("senha");

            usuario = new Usuario(id, nome, senha);
        }

        return usuario;
    }
    
     public Usuario verificarLog(String nome, String senha) throws SQLException {
        Connection con = ConnectionFactory.getConexao();

        String sql = "select * from usuario where nome = ? AND senha = ?";

        java.sql.PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, senha);

        ResultSet result = ps.executeQuery();

        Usuario user = null;

        if (result.next()) {
            int idRecebido = result.getInt("id");
            String nomeRecebido = result.getString("nome");
            String senhaRecebida = result.getString("senha");
            user = new Usuario(idRecebido, nomeRecebido, senhaRecebida);
        }

        return user;
    }

}
