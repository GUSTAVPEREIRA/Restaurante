package estagio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RelatoriosController implements Initializable {

	@FXML
	private AnchorPane ap_menu;

	@FXML
	private JFXDrawer draw_menu;

	@FXML
	private VBox VB_Menu;

	@FXML
	private JFXButton Caixa;

	@FXML
	private JFXButton Compras;

	@FXML
	private JFXButton Estoque;

	@FXML
	private JFXButton Pagar;

	@FXML
	private JFXButton Receber;

	@FXML
	private JFXButton Vendas;

	@FXML
	private JFXButton Sair;

	@FXML
	private JFXButton btn_texto;

	@FXML
	private StackPane stack_vendas;

	@FXML
	private AnchorPane ap_relatorios;
	private Node node;

	@FXML
	void OnActionCaixa(ActionEvent event) {

	}

	@FXML
	void OnActionCompras(ActionEvent event) {

	}

	@FXML
	void OnActionEstoque(ActionEvent event) {

	}

	@FXML
	void OnActionPagar(ActionEvent event) {

	}

	@FXML
	void OnActionReceber(ActionEvent event) {

	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionVendas(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
