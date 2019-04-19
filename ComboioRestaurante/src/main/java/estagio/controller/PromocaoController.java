package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.model.Categoria;
import estagio.model.Produto;
import estagio.model.Promocao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class PromocaoController {

    @FXML
    private StackPane sp_promocao;

    @FXML
    private TableView<Promocao> tb_promocao;

    @FXML
    private TableColumn<Promocao, String> tc_codigo;

    @FXML
    private TableColumn<Promocao, String> tc_produto;

    @FXML
    private TableColumn<Promocao, String> tc_porcentagem;

    @FXML
    private TableColumn<Promocao, String> tc_valorPromocao;

    @FXML
    private TableColumn<Promocao, String> tc_valorNormal;

    @FXML
    private Label lbl_cbbCategoria;

    @FXML
    private JFXComboBox<Categoria> cbb_categoria;

    @FXML
    private Label lbl_cbbProduto;

    @FXML
    private JFXComboBox<Produto> cbb_Produto;

    @FXML
    private Label lbl_porcentagem;

    @FXML
    private JFXTextField txt_valorNormal;

    @FXML
    private ContextMenu ctm_ValorNormal;

    @FXML
    private JFXTextField txt_porcentagem;

    @FXML
    private ContextMenu ctm_porcentagem;

    @FXML
    private Label lbl_ValorPromocao;

    @FXML
    private JFXTextField txt_valorPromocao;

    @FXML
    private ContextMenu ctm_valorPromocao;

    @FXML
    private Label lbl_inicio;

    @FXML
    private Tooltip ttp_lblClienteT1;

    @FXML
    private JFXDatePicker dp_inicio;

    @FXML
    private Label lbl_vencimento;

    @FXML
    private Tooltip ttp_lblVencimentoT1;

    @FXML
    private JFXDatePicker dp_vencimento;

    @FXML
    private ContextMenu ctm_dataVencimento;

    @FXML
    private MenuItem mi_dataVencimento;

    @FXML
    private JFXButton btn_Novo;

    @FXML
    private Tooltip ttp_btnAdicionar;

    @FXML
    private JFXButton btn_Gravar;

    @FXML
    private Tooltip ttp_btnGravar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private Tooltip ttp_btnCancelar;

    @FXML
    private JFXButton btn_Remover;

    @FXML
    private Tooltip ttp_btnRemover;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private Tooltip ttp_btnSair;

    @FXML
    void OnActionCancelar(ActionEvent event) {

    }

    @FXML
    void OnActionGravar(ActionEvent event) {

    }

    @FXML
    void OnActionNovo(ActionEvent event) {

    }

    @FXML
    void OnActionRemover(ActionEvent event) {

    }

    @FXML
    void OnActionSair(ActionEvent event) {

    }

    @FXML
    void OnEnterPressed(KeyEvent event) {

    }

    @FXML
    void OnMouseClickedProduto(MouseEvent event) {

    }

    @FXML
    void OnMouseselectionCategoria(ActionEvent event) {

    }

    @FXML
    void OnMouseselectionProduto(ActionEvent event) {

    }

}
