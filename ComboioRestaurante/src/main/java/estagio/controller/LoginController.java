/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CidadeDAO;
import estagio.dao.EmpresaDAO;
import estagio.dao.UsuarioDAO;
import estagio.model.Cidade;
import estagio.model.Empresa;
import estagio.model.Usuario;
import estagio.ui.notifications.FXNotification;
import estagio.ui.notifications.FXNotificationFactory;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Pereira
 */
public class LoginController implements Initializable {

	@FXML
	private AnchorPane ap_login;
	@FXML
	private JFXTextField txt_login;
	@FXML
	private JFXPasswordField txt_senha;
	@FXML
	private JFXButton btn_login;
	private UsuarioDAO usuarioDAO;
	public static Usuario logado = new Usuario();
	public final Usuario loginEfetuado = new Usuario();
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

	@FXML
	private StackPane sp_login;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ProcessBuilder pb2 = new ProcessBuilder("psql", "--host=localhost", "--port=5432", "--username=postgres",
					"--command=CREATE DATABASE restaurante");
			pb2.environment().put("PGPASSWORD", "postgres123");
			final Process process2 = pb2.start();

			final BufferedReader r2 = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
			String line = r2.readLine();
			while (line != null) {
				System.err.println(line);
				line = r2.readLine();
			}
			r2.close();

			process2.waitFor();
			process2.destroy();
		} catch (Exception e) {
			// TODO: handle exception
		}
		txt_login.setText("");
		txt_senha.setText("");
		new Usuario();
		txt_login.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_senha.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				FXNotificationFactory.initialize(sp_login);
			}
		});
		
		
		
		usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.count_tipo("ADMIN") == 0) {
			Usuario usuario = new Usuario(null,  "ADMIN", "ADMIN","ADMIN","ATIVADO","ADMIN");
			usuarioDAO = new UsuarioDAO();
			usuarioDAO.inserir(usuario);
			EmpresaDAO empresaDAO = new EmpresaDAO(); 
			Empresa empresa = new Empresa();
			empresa.setCep("19210-000");
			empresa.setIe("488094250362");
			empresa.setCnpj("44.860.740/0001-73");
			empresa.setNome("RESTAURANTE COMBOIO");
			empresa.setTelefone("(18) 32891501");
			InputStream imageStream = this.getClass().getResourceAsStream("/estagio/view/resources/logoPosto2.png");
			empresa.setCaminho_imgem(imageStream.toString());
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidade = new Cidade();
			cidade.setId((long) 3553906);
			cidade = cidadeDAO.listar(cidade.getId());
			empresa.setCidade(cidade);
			empresa.setId(null);
			empresaDAO.inserir(empresa);
		}

	}

	@FXML
	private void ActionLogin(ActionEvent event) throws IOException {
		login();

	}

	public void login() throws IOException {
		usuarioDAO = new UsuarioDAO();
		logado = new Usuario();
		String login, senha;
		login = "";
		senha = "";
		Boolean erro = false;
		if (txt_login.getText().equals("")) {
			erro = true;
			txt_login.setStyle(corErro);
		} else {
			login = txt_login.getText();
			txt_login.setStyle(corNormal);
			ctm_login.hide();
		}
		if (txt_senha.getText().equals("")) {
			erro = true;
			txt_senha.setStyle(corErro);
		} else {
			senha = txt_senha.getText();
			txt_senha.setStyle(corNormal);
			ctm_senha.hide();
		}
		if (erro == false) {
			senha = senha.toUpperCase();
			login = login.toUpperCase();
			logado = usuarioDAO.login(login.toUpperCase(), senha.toUpperCase());
			if (logado != null && !logado.getTipo().equals("") && logado.getAtivo().equals("ATIVADO") == true) {
				logado.setLogin(login);
				logado.setSenha(senha);
				try {

					Thread.sleep(0, 1 * 1000);
					Node node;
					node = (Node) FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
					ap_login.getChildren().setAll(node);
				} catch (InterruptedException ex) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				 FXNotification fxn = new FXNotification("Login inválido.",
				 FXNotification.NotificationType.ERROR);
				 fxn.show();
				// ctm_btnLogin.show(btn_login, Side.LEFT, 20, 0);
			}
		} else {
			 FXNotification fxn = new FXNotification("Login inválido.",
			 FXNotification.NotificationType.ERROR);
			 fxn.show();
			// ctm_btnLogin.show(btn_login, Side.LEFT, 20, 0);
		}
	}

	@FXML
	void OnKeyPressedEnter(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			login();
		}
	}

	@FXML
	void OnKeyPressedSair(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			sair();
		}
	}

	
	public void sair() {
		Stage stage = (Stage) btn_sair.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void ActionSair(ActionEvent event) {
		sair();
	}

}
