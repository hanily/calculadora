/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ConnectionFactory.getConexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rhani
 */

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/calculadora";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exemplo de método para obter todas as despesas de um usuário
    public ResultSet obterDespesasDoUsuario(int idUsuario) {
        try {
            String query = "SELECT * FROM despesa WHERE id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void testarConexao() {
        try {
            Connection testeConexao = getConexao();
            if (testeConexao != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Não foi possível estabelecer a conexão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao testar a conexão: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
       testarConexao();
    }
}
