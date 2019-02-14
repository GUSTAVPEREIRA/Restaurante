/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.UsuarioDAO;
import estagio.model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import estagio.view.util.TextFieldFormatterHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Pereira
 */
public class LoginController implements Initializable{

    @FXML
    private JFXTextField txt_login;
    @FXML
    private JFXPasswordField txt_senha;
    @FXML
    private JFXButton btn_login;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    public static Usuario logado = new Usuario(); 
    private TextFieldFormatterHelper tffh;
    private String corErro = "-fx-border-color: red;";
    private String corNormal = "-fx-border-color:white";
    @FXML
    private JFXButton btn_sair;
    @FXML
    private ContextMenu ctm_login;
    @FXML
    private MenuItem mi_login;
    @FXML
    private ContextMenu ctm_senha;
    @FXML
    private MenuItem mi_senha;
    @FXML
    private ContextMenu ctm_btnLogin;
    @FXML
    private MenuItem mi_btnLogin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       txt_login.setText("");
       txt_senha.setText("");
       usuario = new Usuario();
       txt_login.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
       txt_senha.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
    }   
    
    @FXML
    private void ActionLogin(ActionEvent event) throws IOException {
        usuarioDAO = new UsuarioDAO();
        logado = new Usuario();
        String login,senha;
        login = "";
        senha = "";
        Boolean erro = false;
        if(txt_login.getText().equals(""))
        {
            erro = true;
            txt_login.setStyle(corErro);
            ctm_login.show(txt_login,Side.RIGHT,10,0);
        }
        else
        {
           login = txt_login.getText(); 
           txt_login.setStyle(corNormal);
           ctm_login.hide();
        }
        if(txt_senha.getText().equals(""))
        {
            erro = true;
            txt_senha.setStyle(corErro);
            ctm_senha.show(txt_senha,Side.RIGHT,10,0);
        }
        else
        {
            senha = txt_senha.getText(); 
            txt_senha.setStyle(corNormal);
            ctm_senha.hide();
        }
        if(erro == false)
        {                             
            senha = senha.toUpperCase();
            login = login.toUpperCase();
            logado = usuarioDAO.login(login.toUpperCase(),senha.toUpperCase());             
            if(logado != null && !logado.getTipo().equals("") && logado.getAtivo().equals("ATIVADO") == true)
            {
               logado.setLogin(login);
               logado.setSenha(senha);
               Stage stage = (Stage) btn_login.getScene().getWindow();
               stage.close();
               try 
               {
                   Thread.sleep(0,1 * 1000);
                   Parent root;    
                   root = FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
                   Scene scene = new Scene(root);
                   stage = new Stage();            
                   stage.setTitle("Menu");
                   stage.setResizable(false);
                   stage.setScene(scene);
                   stage.show();
               } 
               catch (InterruptedException ex) 
               {
                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
               }                              
            }
            else
            {
               ctm_btnLogin.show(btn_login,Side.LEFT,20,0);
            }
        }
        else
        {
            ctm_btnLogin.show(btn_login,Side.LEFT,20,0);
        }
    }

    @FXML
    private void Limitetxt_Login(KeyEvent event) {
        if (txt_login.getText().length() == 26) {
            event.consume();
        }
    }

    @FXML
    private void Limitetxt_Senha(KeyEvent event) {
        if (txt_senha.getText().length() == 26) {
            event.consume();
        }         
    }
}
