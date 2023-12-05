/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainFXML extends Application {

    private static Scene tela1, tela2, tela3, tela4, tela5;
    private static Stage cenaInicio;

    public void exibeTelaInicial() throws IOException {
        FXMLLoader telaInicial = new FXMLLoader(MainFXML.class.getResource("/view/TelaInicialFXML.fxml"));
        Parent parentTelaUm = telaInicial.load();

        tela1 = new Scene(parentTelaUm);
        cenaInicio.setScene(tela1);
        cenaInicio.setResizable(false);
        cenaInicio.show();
    }
    @Override
    public void start(Stage stage) throws Exception {
     cenaInicio = stage;
        stage.setTitle("Aplicação Calculadora de Despesas");
        exibeTelaInicial();
    }
    public void exibeTelaCadastroUser()throws IOException {
        try{
            FXMLLoader telaDois = new FXMLLoader(MainFXML.class.getResource("/view/TelaCadastroUserFXML.fxml"));
            Parent parentTelaDois = telaDois.load();
            
            tela2 = new Scene(parentTelaDois);
            cenaInicio.setScene(tela2);
            cenaInicio.show();    
    }catch(IOException e){
        e.printStackTrace();
    }
  }
    public void exibeTelaGerenciarDespesas() throws IOException{
        FXMLLoader telaTres = new FXMLLoader(MainFXML.class.getResource("/view/TelaGerenciarDespesaFXML.fxml"));
        Parent parentTelaTres = telaTres.load();
        
        tela3 = new Scene(parentTelaTres);
        cenaInicio.setScene(tela3);
        cenaInicio.show();
    }
    public void exibeTelaCadastroCategoria() throws IOException{
        FXMLLoader telaQuatro = new FXMLLoader(MainFXML.class.getResource("/view/TelaCadastroCategoriaFXML.fxml"));
        Parent parentTelaQuatro = telaQuatro.load();
        
        tela3 = new Scene(parentTelaQuatro);
        cenaInicio.setScene(tela4);
        cenaInicio.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
