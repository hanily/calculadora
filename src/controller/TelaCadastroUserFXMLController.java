/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.MainFXML;
import model.Usuario;


/**
 * FXML Controller class
 *
 * @author rhani
 */
public class TelaCadastroUserFXMLController implements Initializable {
    @FXML
    private TextField txtNome;
    @FXML
    private PasswordField txtSenha;
    
    private static DaoUsuario daoUsuario = new DaoUsuario();
    private static MainFXML mainFXML = new MainFXML();
 
    /**
     * Initializes the controller class.
     */   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void actionCadastrar(ActionEvent event) throws SQLException, IOException {
        Usuario usuario = new Usuario(txtNome.getText(), txtSenha.getText());

        boolean resultado = daoUsuario.salvar(usuario);

        System.out.println(resultado ? "Cadastrou com sucesso!" : "Falha do cadastro...");
        
        if (resultado) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuario cadastrado com sucesso!!");
            alert.setHeaderText("Cadastro Realizado:");
            alert.setContentText("Usuario cadastrado sob o id" + usuario.getId());
            alert.show();
            
            mainFXML.exibeTelaInicial();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Não foi possível realizar o Cadastro");
            alert.setHeaderText("Cadastro de Usuário inválido");
            alert.setContentText("Não foi possível realizar o cadastro do usuário, por favor tente novamente");
            alert.show();
        }

    }

    @FXML
    public void exibeTelaInicial(ActionEvent event) throws IOException {
        mainFXML.exibeTelaInicial();
    }
    
    
}
