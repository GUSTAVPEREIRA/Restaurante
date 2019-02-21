/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CidadeDAO;
import estagio.dao.EstadoDAO;
import estagio.model.Cidade;
import estagio.model.Estado;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class CidadeController implements Initializable {

	@FXML
	private JFXTextField txt_codigo;
	@FXML
	private JFXButton btn_Buscar;
	@FXML
	private JFXTextField txt_nome;
	@FXML
	private JFXComboBox<Estado> cbb_estId;
	@FXML
	private JFXButton btn_Novo;
	@FXML
	private JFXButton btn_Gravar;
	@FXML
	private JFXButton btn_Excluir;
	@FXML
	private JFXButton btn_Cancelar;
	@FXML
	private JFXButton btn_Sair;
	private List<Estado> listaEstado;
	private ObservableList<Estado> obslEstado;
	private EstadoDAO estadoDAO = new EstadoDAO();
	@SuppressWarnings("unused")
	private Estado estado = new Estado();
	private Cidade cidade = new Cidade();
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private ObservableList<Cidade> obslCidade;
	public CidadeController cidadeController;
	private List<Cidade> listaCidade;
	@FXML
	private Label lbl_codigo;
	@FXML
	private Tooltip ttp_lblCodigo;
	@FXML
	private Tooltip ttp_btnBuscar;
	@FXML
	private Label lbl_nome;
	@FXML
	private Tooltip ttp_lblNome;
	@FXML
	private Label lbl_estado;
	@FXML
	private Tooltip ttp_lblEstado;
	@FXML
	private Tooltip ttp_btnNovo;
	@FXML
	private Tooltip ttp_btnGravar;
	@FXML
	private Tooltip ttp_btnExcluir;
	@FXML
	private Tooltip ttp_btnCancelar;
	@FXML
	private Tooltip ttp_btnSair;
	private String corErro = "-fx-border-color: red;";
	private String corNormal = "-fx-border-color:white";
	@FXML
	private ContextMenu ctm_nome;
	@FXML
	private MenuItem mi_nome;
	@FXML
	private ContextMenu ctm_estado;
	@FXML
	private MenuItem mi_estado;
	private TextFieldFormatterHelper tffh;
	@FXML
	private AnchorPane ap_busca;
	@FXML
	private JFXButton btn_filtro;
	@FXML
	private JFXTextField txt_filtro;
	@FXML
	private TableView<Cidade> tb_cidade;
	@FXML
	private TableColumn<Cidade, String> tc_codigo;
	@FXML
	private TableColumn<Cidade, String> tc_cidade;
	@FXML
	private TableColumn<Cidade, String> tc_estado;
	@FXML
	private JFXButton btn_voltar;
	@FXML
	private AnchorPane ap_cidade;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_estId.setItems(obslEstado);
		txt_nome.setText("");
		txt_codigo.setText("0");
		cidade = new Cidade();
		estado = new Estado();
		cidadeDAO = new CidadeDAO();
		txt_filtro.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
		txt_nome.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
		listaCidade = new ArrayList();
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void desativaTela() {
		txt_nome.setText("");
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		cbb_estId.getSelectionModel().select(null);
		cidadeDAO = new CidadeDAO();
		estado = new Estado();
		cidade = new Cidade();
	}

	@FXML
	private void OnActionBuscar(ActionEvent event) {
		ap_busca.setVisible(true);
		carregaTela(txt_filtro.getText());
	}

	@FXML
	private void OnActionNovo(ActionEvent event) {

		desativaTela();
	}

	@FXML
	private void OnActionGravar(ActionEvent event) {
		Boolean erro = false;
		cidade = new Cidade();
		cidadeDAO = new CidadeDAO();
		String nome = txt_nome.getText();
		nome = nome.replaceAll("\\s", "");
		if (txt_nome.getText().compareTo("") == 0 || nome.compareTo("") == 0 || nome.length() < 3) {
			erro = true;
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
			txt_nome.setStyle(corErro);
		} else {
			cidade.setNome(txt_nome.getText());
			ctm_nome.hide();
			txt_nome.setStyle(corNormal);

		}

		if (cbb_estId.getSelectionModel().getSelectedItem() == null) {
			erro = true;
			ctm_estado.show(cbb_estId, Side.RIGHT, 10, 0);
			cbb_estId.setStyle(corErro);
		} else {
			cidade.setEstado(cbb_estId.getSelectionModel().getSelectedItem());
			ctm_estado.hide();
			cbb_estId.setStyle(corNormal);
		}
		if (txt_codigo.getText().equals("") == true)
			erro = true;
		else
			cidade.setId(Long.parseLong(txt_codigo.getText()));

		if (erro != true) {
			FXNotification fxn;
			if (cidade.getId() == 0) {
				cidade.setId(null);
				cidadeDAO.inserir(cidade);

				fxn = new FXNotification("Nova cidade: " + cidade.getNome() + " inserida.",
						FXNotification.NotificationType.INFORMATION);

			} else {
				cidadeDAO.alterar(cidade);
				fxn = new FXNotification("Cidade: " + cidade.getNome() + " alterada.",
						FXNotification.NotificationType.INFORMATION);
			}
			fxn.show();

			desativaTela();
		}
	}

	@FXML
	private void OnActionExcluir(ActionEvent event) {
		cidadeDAO = new CidadeDAO();
		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("");
		dialogoExe.setHeaderText("Você deseja realmente excluir " + cidade.getNome() + "?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
		dialogoExe.showAndWait().ifPresent(b -> {
			if (b == btnSim) {
				if (txt_codigo.getText().equals("0") != true && !txt_codigo.getText().isEmpty()) {
					cidade.setId(Long.parseLong(txt_codigo.getText()));
					Boolean deletado = cidadeDAO.Deletar(cidade);
					FXNotification fxn;
					if (deletado == true) {

						fxn = new FXNotification("Cidade de " + cidade.getNome() + " foi excluida.",
								FXNotification.NotificationType.INFORMATION);
						fxn.show();
						desativaTela();
					} else {
						fxn = new FXNotification("Cidade de " + cidade.getNome() + " não pode ser excluida.",
								FXNotification.NotificationType.INFORMATION);
						fxn.show();
						desativaTela();
					}
				}
			}
		});
	}

	@FXML
	private void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	private void OnActionSair(ActionEvent event) {
		ap_cidade.setVisible(false);
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
		txt_nome.setText(cidade.getNome());
		txt_codigo.setText("" + cidade.getId());
		cbb_estId.getSelectionModel().select(1);
		cbb_estId.getSelectionModel().select(cidade.getEstado());
		limpaBuscas();
		ativaTela();
	}

	public Cidade getCidade() {
		return cidade;
	}

	@FXML
	private void OnActionFiltro(ActionEvent event) {
		carregaTela(txt_filtro.getText());
	}

	@FXML
	private void OnKeyPressedEnter(KeyEvent event) {
		txt_filtro.setOnKeyPressed((KeyEvent event1) -> {
			if (event1.getCode() == KeyCode.ENTER) {
				carregaTela(txt_filtro.getText());
			}
		});
	}

	@FXML
	private void Limitetxt_filtro(KeyEvent event) {
		if (txt_filtro.getText().length() == 100)
			event.consume();
	}

	@FXML
	private void OnMouseClickedCidade(MouseEvent event) {
		if (tb_cidade.getSelectionModel().getSelectedItem() != null) {
			this.setCidade(tb_cidade.getSelectionModel().getSelectedItem());
		}
	}

	public void carregaTela(String palavra) {
		listaCidade.clear();
		tb_cidade.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_cidade.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tc_estado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
		listaCidade = cidadeDAO.listar(palavra.toUpperCase());
		if (!listaCidade.isEmpty()) {
			obslCidade = FXCollections.observableArrayList(listaCidade);
			tb_cidade.setItems(obslCidade);
		}

	}

	@FXML
	private void OnActionVoltar(ActionEvent event) {
		limpaBuscas();
	}

	public void limpaBuscas() {
		listaCidade.clear();
		tb_cidade.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

	@FXML
	private void Limitetxt_Nome(KeyEvent event) {
		if (txt_nome.getText().length() == 100)
			event.consume();
	}
}
