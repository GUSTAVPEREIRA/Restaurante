package estagio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import estagio.ui.notifications.FXNotificationFactory;
import javafx.application.Platform;
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
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				FXNotificationFactory.initialize(stack_financeiro);
			}
		});
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
	private StackPane stack_financeiro;

	@FXML
	private AnchorPane ap_financeiro;

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


}