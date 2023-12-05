/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoCategoria;
import dao.DaoUsuario;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import main.MainFXML;
import model.Categoria;
import model.Despesa;
import model.Usuario;


public class TelaInicialFXMLController implements Initializable {
    
    private static DaoCategoria daoCategoria = new DaoCategoria();
    private static MainFXML mainFXML = new MainFXML();
    private static Categoria categoriaSelecionada = null;
    private static DaoUsuario daoUsuario = new DaoUsuario();
    private ObservableList<Despesa> obsListaDados = FXCollections.observableArrayList();


    
    @FXML
    private TextField txtNome;
    @FXML
    private PasswordField txtSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void actionCadastrar(ActionEvent event) throws IOException {
        mainFXML.exibeTelaCadastroUser();
    }

    @FXML
    public void actionAcessar(ActionEvent event) throws SQLException {
        try {
            Usuario usuarioObj = daoUsuario.verificarLog(txtNome.getText(), txtSenha.getText());

            if (usuarioObj != null) {
                mainFXML.exibeTelaCadastroCategoria();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Não foi possível realizar o Login");
                alert.setHeaderText("Login de Usuário inválido");
                alert.setContentText("Não foi possível realizar o login, por favor tente novamente");
                alert.show();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void actionAtualizar(ActionEvent event) throws SQLException {
        
        if (categoriaSelecionada != null) {
            categoriaSelecionada.setNome(txtNome.getText());

            daoCategoria.atualizar(categoriaSelecionada);
            atualizarTabela();

        }
    }
    public void atualizarTabela() {
        obsListaDados.clear();
    }
    
    @FXML
    public void actionListar(ActionEvent event) {
    }
    
}
