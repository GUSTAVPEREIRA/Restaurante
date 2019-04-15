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
    private JFXButton Promocao;

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
		btn_texto.setText("Olá,\n"+LoginController.logado.getNome());
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				FXNotificationFactory.initialize(stack_vendas);
			}
		});
	}

	@FXML
	void OnActionCaixa(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CaixaFXML.fxml"));
		ap_vendas.getChildren().setAll(node);
	}

	@FXML
	void OnActionPromocao(ActionEvent event) {

	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionVendas(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/VendaFXML.fxml"));
		ap_vendas.getChildren().setAll(node);
	}

}
