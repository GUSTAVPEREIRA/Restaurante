/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;


import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 *
 * @author Pereira
 */
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
public class SideMenuController {

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
    
    
    
    public void initialize(URL location, ResourceBundle resources) {
      
       
    }

    @FXML
    private void OnActionCadastro(ActionEvent event) {
        FXMLLoader cadastro = new FXMLLoader(getClass().getResource("/estagio/view/CadastroFXML.fxml"));
        Stage stage = new Stage();
        try {
            Scene sc = new Scene(cadastro.load());
            stage.setScene(sc);   
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();  
        } catch (IOException ex) {
            Logger.getLogger(SideMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
