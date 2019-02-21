/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Pereira
 */
public class CadastroController implements Initializable {
//xml jobson ensinando a bugar

	@FXML
	private AnchorPane ap_cadastros;
	@FXML
	private JFXButton Categoria;
	@FXML
	private JFXButton Cliente;
	@FXML
	private JFXButton Cidade;
	@FXML
	private JFXButton Estado;
	@FXML
	private JFXButton Empresa;
	@FXML
	private JFXButton Produto;
	@FXML
	private JFXButton Usuario;
	@FXML
	private VBox VB_Menu;
	@FXML
	private JFXButton Fornecedor;
	@FXML
	private JFXButton TipoVenda;
	@FXML
	private JFXDrawer draw_menu;
	@FXML
	private JFXButton Sair;
	private Node node;
	@FXML
	private AnchorPane ap_menu;	
    @FXML
    private StackPane stack_cadastros;

    @Override
    	public void initialize(URL url, ResourceBundle rb) {
    		Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					FXNotificationFactory.initialize(stack_cadastros);					
				}
			});
    	}

	@FXML
	private void OnActionCategoria(ActionEvent event) throws IOException {

//		FXNotification fxn = new FXNotification("Gravado com sucesso");
//		fxn.show();
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CategoriaFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionCliente(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/ClienteFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionCidade(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CidadeFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionEstado(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/EstadoFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionEmpresa(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/EmpresaFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionProduto(ActionEvent event) throws IOException {
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/ProdutoFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionUsuario(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/UsuarioFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionFornecedor(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/FornecedorFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	private void OnActionSair(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionTipoVenda(ActionEvent event) throws IOException {

		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/TipoVendaFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

}
