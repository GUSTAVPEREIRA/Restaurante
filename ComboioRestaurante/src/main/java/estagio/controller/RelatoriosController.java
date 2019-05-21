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

	public void caixaCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioCaixaFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void comprasCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioComprasFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void estoqueCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioMovimentoEstoqueFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void pagarCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioContasPagarFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void receberCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioContasReceberFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void vendasCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatorioVendaFXML.fxml"));
		ap_relatorios.getChildren().setAll(node);
	}

	public void sairCarrega() throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionCaixaEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			caixaCarrega();
		}
	}

	@FXML
	void OnActionComprasEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			comprasCarrega();
		}

	}

	@FXML
	void OnActionEstoqueEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			estoqueCarrega();
		}
	}

	@FXML
	void OnActionPagarEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			pagarCarrega();
		}
	}

	@FXML
	void OnActionReceberEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			receberCarrega();
		}
	}

	@FXML
	void OnActionSairEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			sairCarrega();
		}
	}

	@FXML
	void OnActionVendasEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			vendasCarrega();
		}
	}

	@FXML
	void OnActionCaixa(ActionEvent event) throws IOException {
		caixaCarrega();
	}

	@FXML
	void OnActionCompras(ActionEvent event) throws IOException {
		comprasCarrega();
	}

	@FXML
	void OnActionEstoque(ActionEvent event) throws IOException {
		estoqueCarrega();
	}

	@FXML
	void OnActionPagar(ActionEvent event) throws IOException {
		pagarCarrega();
	}

	@FXML
	void OnActionReceber(ActionEvent event) throws IOException {
		receberCarrega();
	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {
		sairCarrega();
	}

	@FXML
	void OnActionVendas(ActionEvent event) throws IOException {
		vendasCarrega();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_texto.setText("Olá,\n" + LoginController.logado.getNome());

	}

}
