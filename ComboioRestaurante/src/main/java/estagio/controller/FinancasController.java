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

public class FinancasController implements Initializable {

	private Node node;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btn_texto.setText("Olá,\n" + LoginController.logado.getNome());

	}

	@FXML
	private AnchorPane ap_menu;

	@FXML
	private JFXDrawer draw_menu;

	@FXML
	private VBox VB_Menu;

	@FXML
	private JFXButton Pagar;

	@FXML
	private JFXButton Receber;

	@FXML
	private JFXButton Sair;

	@FXML
	private JFXButton Comprar;

	@FXML
	private JFXButton Estoque;
	@FXML
	private StackPane stack_financeiro;

	@FXML
	private AnchorPane ap_financeiro;
	@FXML
	private JFXButton btn_texto;

	@FXML
	void OnActionPagar(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/ContasPagarFXML.fxml"));
		ap_financeiro.getChildren().setAll(node);
	}

	@FXML
	void OnActionReceber(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/ContasReceberFXML.fxml"));
		ap_financeiro.getChildren().setAll(node);
	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionComprar(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CompraFXML.fxml"));
		ap_financeiro.getChildren().setAll(node);
	}

	@FXML
	void OnActionEstoque(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/AtualizarEstoqueFXML.fxml"));
		ap_financeiro.getChildren().setAll(node);
	}

}
