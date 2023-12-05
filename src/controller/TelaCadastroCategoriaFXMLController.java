/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoCategoria;
import dao.DaoUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.MainFXML;
import model.Categoria;
import model.Despesa;
import model.Despesa_Categoria;

/**
 * FXML Controller class
 *
 * @author rhani
 */
public class TelaCadastroCategoriaFXMLController implements Initializable {
    private static DaoUsuario daoUsuario = new DaoUsuario();
    private static DaoCategoria daoCategoria = new DaoCategoria();

    private static MainFXML mainFXML = new MainFXML();
    private static Despesa_Categoria despesa_categprioa = new Despesa_Categoria();
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void actionVoltarTelaInicial(ActionEvent event) throws IOException {
        mainFXML.exibeTelaInicial();
    }

    @FXML
    public void actionCadastrar(ActionEvent event) {
        Categoria categoriaOBJ = new Categoria(txtNome.getText(),(txtTipo.getText()));

        try {

            boolean resultado = daoCategoria.salvar(categoriaOBJ);

            if (resultado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro de Categoria");
                alert.setHeaderText("Cadastro de Categoria");
                alert.setContentText("Cadastro de Categoria Realizado com sucesso!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Categoria");
                alert.setHeaderText("Cadastro de Categoria");
                alert.setContentText("Não foi possível realizar o Cadastro da categoria, tente novamente!");
                alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroCategoriaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
