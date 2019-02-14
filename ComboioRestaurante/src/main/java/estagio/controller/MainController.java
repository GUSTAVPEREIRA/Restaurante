/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
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
	private void OnMouseAbreMenu(MouseEvent event) {

	}

	@FXML
	private void OnActionCadastro(ActionEvent event) throws IOException {
//        FXMLLoader cadastro = new FXMLLoader(getClass().getResource("/estagio/view/CadastroFXML.fxml"));
//        Stage stage = new Stage();
//        try {
//            Scene sc = new Scene(cadastro.load());
//            stage.setScene(sc);   
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.showAndWait();  
//        } catch (IOException ex) {
//            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
		Node node;
		node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/CadastroFXML.fxml"));
		ap_menu.getChildren().setAll(node);
	}

}
