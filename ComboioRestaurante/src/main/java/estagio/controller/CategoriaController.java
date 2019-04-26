/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CategoriaDAO;
import estagio.model.Categoria;
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
public class CategoriaController implements Initializable {

	@FXML
	private JFXTextField txt_codigo;
	@FXML
	private JFXTextField txt_nome;
	@FXML
	private JFXButton btn_Buscar;
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
	@FXML
	private ContextMenu ctm_nome;
	@FXML
	private MenuItem mi_nome;
	@FXML
	private Tooltip ttp_Nome;
	@FXML
	private Tooltip ttp_btnBuscar;
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
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	@FXML
	private Label lbl_codigo;
	@FXML
	private Tooltip ttp_lblCodigo;
	@FXML
	private Label lbl_nome;
	@FXML
	private AnchorPane ap_busca;
	@FXML
	private JFXButton btn_filtro;
	@FXML
	private JFXTextField txt_filtro;
	@FXML
	private TableView<Categoria> tb_categoria;
	@FXML
	private TableColumn<Categoria, String> tc_codigo;
	@FXML
	private TableColumn<Categoria, String> tc_nome;
	@FXML
	private JFXButton btn_voltar;
	private Categoria categoria;
	private CategoriaDAO categoriaDAO;
	private ObservableList<Categoria> obslCategoria;
	private List<Categoria> listaCategoria;
	@FXML
	private AnchorPane ap_categoria;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		categoria = new Categoria();
		categoriaDAO = new CategoriaDAO();
		// \\u00C0-\\u00FF São os caracteres unicodes latinos que podem ser encontrados
		// http://www.fileformat.info/info/unicode/block/latin_supplement/list.htm
		txt_nome.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_filtro.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		listaCategoria = new ArrayList<>();
		ap_categoria.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.ESCAPE)) {
				if (ap_busca.isVisible() == true) {
					limpaBuscas();
					txt_nome.setFocusTraversable(true);
				} else {
					ap_categoria.setVisible(false);
				}

			}
			if (event.getCode().equals(KeyCode.F1) && ap_busca.isVisible() == false) {
				desativaTela();
			}
			if (event.getCode().equals(KeyCode.F2) && ap_busca.isVisible() == false) {
				grava();
			}
			if (event.getCode().equals(KeyCode.F3) && ap_busca.isVisible() == false
					&& btn_Excluir.isDisable() == false) {
				excluir();
			}
			if (event.getCode().equals(KeyCode.F4) && ap_busca.isVisible() == false
					&& btn_Cancelar.isDisable() == false) {
				desativaTela();
			}

		});
		txt_nome.setFocusTraversable(true);
	}

	public void desativaTela() {
		txt_nome.setText("");
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		categoria = new Categoria();
		categoriaDAO = new CategoriaDAO();
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void grava() {
		Boolean erro = false;

		if (txt_nome.getText().equals("") == true || txt_nome.getText().length() < 4) {
			erro = true;
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
			txt_nome.setStyle(corErro);
		} else {
			categoria.setNome(txt_nome.getText());
			txt_nome.setStyle(corNormal);
			ctm_nome.hide();
		}

		if (txt_codigo.getText().equals("") == true) {
			erro = true;
		} else {
			categoria.setId(Long.parseLong(txt_codigo.getText()));
		}

		if (erro != true) {
			FXNotification fxn;
			categoriaDAO = new CategoriaDAO();
			if (categoria.getId() == 0) {
				categoria.setId(null);
				categoriaDAO.inserir(categoria);
				fxn = new FXNotification("Categoria " + categoria.getNome() + " foi inserida com sucesso.",
						FXNotification.NotificationType.INFORMATION);
			} else {
				categoriaDAO.alterar(categoria);
				fxn = new FXNotification("Categoria " + categoria.getNome() + " foi alterada com sucesso.",
						FXNotification.NotificationType.INFORMATION);
			}
			fxn.show();
			desativaTela();
		}
	}

	@FXML
	private void OnActionGravar(ActionEvent event) {
		grava();
	}

	public void excluir() {

		categoriaDAO = new CategoriaDAO();
		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("");
		dialogoExe.setHeaderText("Você deseja realmente excluir " + categoria.getNome() + "?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
		dialogoExe.showAndWait().ifPresent(b -> {
			if (b == btnSim) {
				if (txt_codigo.getText().equals("") != true && !txt_codigo.getText().isEmpty()) {
					categoria.setId(Long.parseLong(txt_codigo.getText()));
					Boolean catExcluida = categoriaDAO.Deletar(categoria);
					if (catExcluida == true) {
						FXNotification fxn;
						fxn = new FXNotification("Categoria de " + categoria.getNome() + " foi excluida.",
								FXNotification.NotificationType.INFORMATION);
						fxn.show();
						desativaTela();
					} else {
						FXNotification fxn;
						fxn = new FXNotification(
								"Não foi possível excluir, pois a categoria pertence a produtos cadastrados.",
								FXNotification.NotificationType.WARNING);
						fxn.show();
					}

				}

			}
		});

	}

	@FXML
	private void OnActionExcluir(ActionEvent event) {
		excluir();
	}

	@FXML
	private void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	private void OnActionSair(ActionEvent event) throws IOException {
		ap_categoria.setVisible(false);
	}

	@FXML
	private void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
		txt_nome.setText(categoria.getNome());
		txt_codigo.setText("" + categoria.getId());
		limpaBuscas();
		ativaTela();
		txt_nome.setFocusTraversable(true);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@FXML
	private void OnActionBuscar(ActionEvent event) {
		ap_busca.setVisible(true);
		carregaTela(txt_filtro.getText());
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

	public void carregaTela(String palavra) {
		listaCategoria.clear();
		tb_categoria.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		listaCategoria = categoriaDAO.listar(palavra.toUpperCase());
		if (!listaCategoria.isEmpty()) {
			obslCategoria = FXCollections.observableArrayList(listaCategoria);
			tb_categoria.setItems(obslCategoria);
		}

	}

	@FXML
	private void OnMouseClickedCategoria(MouseEvent event) {
		if (tb_categoria.getSelectionModel().getSelectedItem() != null) {
			this.setCategoria(tb_categoria.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	private void OnActionVoltar(ActionEvent event) {
		limpaBuscas();
		txt_nome.setFocusTraversable(true);
	}

	public void limpaBuscas() {
		listaCategoria.clear();
		tb_categoria.getItems().clear();
		ap_busca.setVisible(false);
		ap_busca.setFocusTraversable(false);
		txt_filtro.setText("");
	}
}
