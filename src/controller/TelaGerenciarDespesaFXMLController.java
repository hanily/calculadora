/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoDespesa;
import dao.DaoUsuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFXML;
import model.Despesa;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author rhani
 */
public class TelaGerenciarDespesaFXMLController implements Initializable {
 

    @FXML
    private TableView<?> tvDespesas;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Label lblNome;
    @FXML
    private TableColumn<Despesa, Integer> colunaID;
    @FXML
    private TableColumn<Despesa, String> colunaDescricao;
    @FXML
    private TableColumn<Despesa, Integer> colunaValor;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtValor;
    @FXML
    private Usuario usuarioSessao = new Usuario();
    private Despesa despesaSelecionada = null;
    private DaoUsuario daoUsuario = new DaoUsuario();

    //inserimos o observable por causa da exibição no FX
    private ObservableList<Despesa> obsListaDados = FXCollections.observableArrayList();
    private ArrayList<Despesa> listaDados = new ArrayList<>();

    private static DaoDespesa daoDespesas = new DaoDespesa();
    private static MainFXML mainFXML = new MainFXML();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Listener acionado diante de quaisquer alterações na seleção de itens do TableView
        tvDespesas.getSelectionModel().selectedItemProperty().addListener(
                (observavble, oldValue, newValue) -> selecionarItemTableViewDespesas((Despesa) newValue));
     
    }
    @FXML
    public void actionCadastrar(ActionEvent event) {
        Despesa despesaOBJ = new Despesa(txtDescricao.getText(), Double.parseDouble(txtValor.getText()), usuarioSessao);

        try {

            boolean resultado = daoDespesas.salvar(despesaOBJ);

            if (resultado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro de Despesas");
                alert.setHeaderText("Cadastro de Despesas");
                alert.setContentText("Cadastro de Despesa Realizado com sucesso!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Despesas");
                alert.setHeaderText("Cadastro de Despesas");
                alert.setContentText("Não foi possível realizar o Cadastro da despesa, tente novamente!");
                alert.show();
            }
            limparCampos();
            atualizarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(TelaGerenciarDespesaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void actionAtualizar(ActionEvent event) throws SQLException {

        if (despesaSelecionada != null) {
            despesaSelecionada.setDescricao(txtDescricao.getText());
            despesaSelecionada.setValor(Integer.parseInt(txtValor.getText()));

            daoDespesas.atualizar(despesaSelecionada);
            atualizarTabela();

        }
        limparCampos();

    }

    @FXML
    public void actionRemover(ActionEvent event) {
        try {
            daoDespesas.excluir(despesaSelecionada.getId());
            atualizarTabela();
            limparCampos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaGerenciarDespesaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarDados() throws SQLException {
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        listaDados = (ArrayList<Despesa>) daoDespesas.despesaPorUsuario(usuarioSessao.getNome());

        obsListaDados = FXCollections.observableArrayList(listaDados);

        FilteredList<Despesa> listaFiltrada = new FilteredList<>(obsListaDados, b -> true);

        txtBuscar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            listaFiltrada.setPredicate((Despesa despesa) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String conversaoMinusculoFiltro = newValue.toLowerCase();

                if (despesa.getDescricao().toLowerCase().contains(conversaoMinusculoFiltro) != false) {
                    return true;
                } else if (String.valueOf(despesa.getId()).contains(conversaoMinusculoFiltro) != false) {
                    return true;
                } else if (String.valueOf(despesa.getValor()).contains(conversaoMinusculoFiltro) != false);
                return true;

            });
        });
    }

    public void atualizarTabela() {

        //limpamos todos os campos e recarregamos a tabela
        try {            
            limparCampos();
            obsListaDados.clear();
            carregarDados();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtBuscar.setText("");
        txtDescricao.setText("");
        txtID.setText("");
        txtValor.setText("");
        despesaSelecionada = null;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
        lblNome.setText(this.usuarioSessao.getNome());
    }

    //recebe a tarefa que o usuário clicou na listview
    public void selecionarItemTableViewDespesas(Despesa despesaOBJ) {
        if (despesaOBJ != null) {
            despesaSelecionada = despesaOBJ;
            txtID.setText(String.valueOf(despesaSelecionada.getId()));
            txtDescricao.setText(despesaSelecionada.getDescricao());
            txtValor.setText(String.valueOf(despesaSelecionada.getValor()));
        }
    }

    @FXML
    public void actionVoltarTelaInicial(ActionEvent event) throws IOException {
        mainFXML.exibeTelaInicial();
    }

}
