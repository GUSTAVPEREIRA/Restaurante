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

import com.jfoenix.controls.JFXAutoCompletePopup;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

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
	JFXAutoCompletePopup<Estado> autoCompletePopupEst = new JFXAutoCompletePopup<Estado>();

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_estId.setItems(obslEstado);
		InitComboBoxEst();
		txt_nome.setText("");
		txt_codigo.setText("0");
		cidade = new Cidade();
		estado = new Estado();
		cidadeDAO = new CidadeDAO();
		txt_filtro.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_nome.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		listaCidade = new ArrayList<Cidade>();
		ap_cidade.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.ESCAPE)) {
				if (ap_busca.isVisible() == true) {
					limpaBuscas();
					txt_nome.setFocusTraversable(true);
				} else {
					ap_cidade.setVisible(false);
				}

			}
			if (event.getCode().equals(KeyCode.F1) && ap_busca.isVisible() == false) {
				desativaTela();
			}
			if (event.getCode().equals(KeyCode.F2) && ap_busca.isVisible() == false) {
				gravar();
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
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
		autoCompletePopupEst.hide();
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
		autoCompletePopupEst.hide();
	}

	@FXML
	private void OnActionBuscar(ActionEvent event) {
		ap_busca.setVisible(true);
		carregaTela(txt_filtro.getText());
	}

	@FXML
	private void OnActionBuscarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			ap_busca.setVisible(true);
			carregaTela(txt_filtro.getText());
		}
	}

	@FXML
	private void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	@FXML
	private void OnActionNovoEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			desativaTela();
		}
	}

	public void gravar() {
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
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Corrija os erros destacados em vermelho.", FXNotification.NotificationType.ERROR);
			fxn.show();
		}
	}

	@FXML
	private void OnActionGravar(ActionEvent event) {
		gravar();
	}

	@FXML
	private void OnActionGravarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			gravar();
		}
	}

	private void InitComboBoxEst() {
		autoCompletePopupEst.getSuggestions().addAll(cbb_estId.getItems());

		autoCompletePopupEst.setSelectionHandler(eventt -> {
			cbb_estId.setValue(eventt.getObject());
			cbb_estId.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupEst.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_estId.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupEst.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupEst.setHideOnEscape(false);
			autoCompletePopupEst.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupEst.getFilteredSuggestions().isEmpty() || cbb_estId.showingProperty().get()
					|| cbb_estId.getEditor().isFocused() == false) {
				autoCompletePopupEst.hide();
			} else {
				cbb_estId.hide();
				autoCompletePopupEst.show(editor);

			}
		});
		cbb_estId.setConverter(new StringConverter<Estado>() {

			@Override
			public String toString(Estado provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Estado fromString(String string) {
				try {
					int index = cbb_estId.getSelectionModel().getSelectedIndex();
					return cbb_estId.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	public void excluir() {
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
								FXNotification.NotificationType.WARNING);
						fxn.show();
						desativaTela();
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
	private void OnActionExcluirEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			excluir();
		}
	}

	@FXML
	private void OnActionCancelarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			desativaTela();
		}
	}

	@FXML
	private void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	private void OnActionSairEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			ap_cidade.setVisible(false);
		}
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
		txt_nome.setFocusTraversable(true);
	}

	public Cidade getCidade() {
		return cidade;
	}

	@FXML
	private void OnActionFiltroEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			carregaTela(txt_filtro.getText());
		}
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
	private void OnActionVoltarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			limpaBuscas();
			txt_nome.setFocusTraversable(true);
		}
	}

	@FXML
	private void OnActionVoltar(ActionEvent event) {
		limpaBuscas();
		txt_nome.setFocusTraversable(true);
	}

	public void limpaBuscas() {
		listaCidade.clear();
		tb_cidade.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

}
