/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pereira
 */
public class MainController implements Initializable {

	HamburgerNextArrowBasicTransition task;
	@FXML
	private JFXButton btn_Cadastro;
	@FXML
	private JFXButton btn_vendas;
	@FXML
	private JFXButton btn_financeiro;
	@FXML
	private JFXButton btn_relatorio;
	@FXML
	private JFXButton btn_sair;
	@FXML
	private JFXButton btn_texto;
	@FXML
	private VBox VB_Menu;
	@FXML
	private AnchorPane ap_menu;
	@FXML
	private Label lbl_login;

	@FXML
	private void OnActionCadastro(ActionEvent event) throws IOException {

		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CadastroFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {

		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/LoginFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void onActionVendas(ActionEvent event) throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/VendasFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	void onActionFinanceiro(ActionEvent event) throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/FinanceiroFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (LoginController.logado.getTipo().equals("ADMIN") == true) {
			btn_financeiro.setDisable(false);
			btn_relatorio.setDisable(false);
		}
		btn_texto.setText("Olá,\n" + LoginController.logado.getNome());
	}

	@FXML
	void onActionRelatorio(ActionEvent event) throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatoriosFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

}
