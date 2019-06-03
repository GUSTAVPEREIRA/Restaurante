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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
	private JFXButton btn_backup;
	@FXML
	private JFXButton btn_texto;
	@FXML
	private VBox VB_Menu;
	@FXML
	private AnchorPane ap_menu;
	@FXML
	private JFXButton btn_manual;
	@FXML
	private Label lbl_login;
	@FXML
	private StackPane sp_sub;

	@FXML
	private AnchorPane ap_cadastros;

	public void carregaCadastro() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CadastroFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	public void carregaSair() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/LoginFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	public void carregaVendas() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/VendasFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	public void carregaFinanceiro() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/FinanceiroFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	public void carregaRelatorio() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/RelatoriosFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

	@FXML
	private void OnActionCadastroEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaCadastro();
		}
	}

	@FXML
	private void OnActionBackupEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaCadastro();
		}
	}
	
	@FXML
	void OnActionSairEnter(KeyEvent event) throws IOException {

		if (event.getCode() == KeyCode.ENTER) {
			carregaSair();
		}
	}

	@FXML
	void onActionVendasEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaVendas();
		}
	}

	@FXML
	void onActionFinanceiroEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaFinanceiro();
		}
	}

	@FXML
	private void OnActionCadastro(ActionEvent event) throws IOException {

		carregaCadastro();
	}

	@FXML
	void OnActionSair(ActionEvent event) throws IOException {

		carregaSair();
	}

	@FXML
	void onActionVendas(ActionEvent event) throws IOException {
		carregaVendas();
	}

	@FXML
	void onActionFinanceiro(ActionEvent event) throws IOException {
		carregaFinanceiro();
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
	void onActionRelatorioEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaRelatorio();
		}
	}

	@FXML
	void onActionRelatorio(ActionEvent event) throws IOException {
		carregaRelatorio();
	}

	@FXML
	void onActionManual(ActionEvent event) throws IOException {
		carregaManual();
	}

	@FXML
	void onActionBackup(ActionEvent event) throws IOException {
		carregaBackup();
	}
	
	public void carregaBackup() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/BackupFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}
	
	public void carregaManual() throws IOException {
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/ManualUsuarioFXML.fxml"));
		ap_cadastros.getChildren().setAll(node);
	}

	@FXML
	void onActionManualEnter(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			carregaManual();
		}
	}

}
