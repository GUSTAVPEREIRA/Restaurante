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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VendasController implements Initializable {

	@FXML
	private AnchorPane ap_menu;

	@FXML
	private JFXDrawer draw_menu;

	@FXML
	private VBox VB_Menu;

	@FXML
	private JFXButton Caixa;

	@FXML
	private JFXButton Vendas;

	@FXML
	private JFXButton Sair;

	@FXML
	private StackPane stack_vendas;

	@FXML
	private AnchorPane ap_vendas;
	@FXML
	private JFXButton btn_texto;

	private Node node;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btn_texto.setText("Olá,\n" + LoginController.logado.getNome());
	}

	@FXML
	void OnActionCaixaEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaCaixa();
		}
	}

	@FXML
	void OnActionSairEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaSair();
		}
	}

	@FXML
	void OnActionVendasEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaVendas();
		}
	}

	public void carregaVendas() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/VendaFXML.fxml"));
		ap_vendas.getChildren().setAll(node);
	}

	public void carregaCaixa() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CaixaFXML.fxml"));
		ap_vendas.getChildren().setAll(node);
	}

	public void carregaSair() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionCaixa(ActionEvent event) throws IOException {
		carregaCaixa();
	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {
		carregaSair();
	}

	@FXML
	void OnActionVendas(ActionEvent event) throws IOException {
		carregaVendas();
	}

}
