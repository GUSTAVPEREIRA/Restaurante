/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pereira
 */
public class MainController {

	HamburgerNextArrowBasicTransition task;
	@FXML
	private JFXButton btn_Cadastro;
	@FXML
	private JFXButton btn_vendas;
	@FXML
	private JFXButton btn_consultas;
	@FXML
	private JFXButton btn_financeiro;
	@FXML
	private JFXButton btn_relatorio;
	@FXML
	private JFXButton btn_sair;
	@FXML
	private VBox VB_Menu;
	@FXML
	private AnchorPane ap_menu;

	public void inicialization() {

	}

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

}
