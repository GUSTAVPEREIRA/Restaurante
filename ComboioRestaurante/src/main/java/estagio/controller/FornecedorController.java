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
import estagio.dao.FornecedorDAO;
import estagio.model.Cidade;
import estagio.model.Estado;
import estagio.model.Fornecedor;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.TextFieldFormatterHelper;
import estagio.view.util.Validadores;
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
public class FornecedorController implements Initializable {

	@FXML
	private JFXTextField txt_codigo;
	@FXML
	private JFXButton btn_Buscar;
	@FXML
	private JFXTextField txt_nome;
	@FXML
	private JFXTextField txt_cnpj;
	@FXML
	private JFXTextField txt_ie;
	@FXML
	private JFXTextField txt_telefone;
	@FXML
	private JFXTextField txt_cep;
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
	private Tooltip ttp_btnNovo;
	@FXML
	private Tooltip ttp_btnGravar;
	@FXML
	private Tooltip ttp_btnExcluir;
	@FXML
	private Tooltip ttp_btnCancelar;
	@FXML
	private Tooltip ttp_btnSair;
	@FXML
	private Tooltip ttp_btnBuscar;
	@FXML
	private Label lbl_codigo;
	@FXML
	private Tooltip ttp_lblCodigo;
	@FXML
	private Label lbl_nome;
	@FXML
	private Tooltip ttp_lblNome;
	@FXML
	private Label lbl_cnpj;
	@FXML
	private Tooltip ttp_lblCnpj;
	@FXML
	private Label lbl_ie;
	@FXML
	private Tooltip ttp_lblIe;
	@FXML
	private Label lbl_telefone;
	@FXML
	private Tooltip ttp_lblTelefone;
	@FXML
	private Label lbl_cep;
	@FXML
	private Tooltip ttp_lblCep;
	@FXML
	private Label lbl_cidade;
	@FXML
	private Tooltip ttp_lblCidade;
	@FXML
	private Tooltip ttp_cidade;
	@FXML
	private JFXComboBox<Cidade> cbb_cidade;
	@FXML
	private Label lbl_UF;
	@FXML
	private Tooltip ttp_lblCidade1;
	@FXML
	private JFXComboBox<Estado> cbb_est;
	@FXML
	private Tooltip ttp_estado;
	@FXML
	private ContextMenu ctm_nome;
	@FXML
	private MenuItem mi_nome;
	@FXML
	private ContextMenu ctm_cnpj;
	@FXML
	private MenuItem mi_cnpj;
	@FXML
	private ContextMenu ctm_ie;
	@FXML
	private MenuItem mi_ie;
	@FXML
	private ContextMenu ctm_telefone;
	@FXML
	private MenuItem mi_telefone;
	@FXML
	private ContextMenu ctm_cep;
	@FXML
	private MenuItem mi_cep;
	@FXML
	private ContextMenu ctm_uf;
	@FXML
	private MenuItem mi_uf;
	@FXML
	private ContextMenu ctm_cidade;
	@FXML
	private MenuItem mi_cidade;
	@FXML
	private AnchorPane ap_busca;
	@FXML
	private JFXButton btn_filtro;
	@FXML
	private JFXTextField txt_filtro;
	@FXML
	private TableView<Fornecedor> tb_fornecedor;
	@FXML
	private TableColumn<Fornecedor, String> tc_codigo;
	@FXML
	private TableColumn<Fornecedor, String> tc_fornecedor;
	@FXML
	private TableColumn<Fornecedor, String> tc_cidade;
	@FXML
	private TableColumn<Fornecedor, String> tc_telefone;
	@FXML
	private TableColumn<Fornecedor, String> tc_cnpj;
	@FXML
	private JFXButton btn_voltar;
	private List<Estado> listaEstado;
	private ObservableList<Estado> obslEstado;
	private EstadoDAO estadoDAO = new EstadoDAO();
	private List<Cidade> listaCidade;
	private ObservableList<Cidade> obslCidade;
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private Validadores val = new Validadores();
	private Fornecedor fornecedor;
	private FornecedorDAO fornecedorDAO;
	private String corErro = "-fx-border-color: red;";
	private String corNormal = "-fx-border-color:white";
	private ObservableList<Fornecedor> obslFornecedor;
	private List<Fornecedor> listaFornecedor;
	private TextFieldFormatterHelper tffh;
	JFXAutoCompletePopup<Estado> autoCompletePopupEst = new JFXAutoCompletePopup<Estado>();
	JFXAutoCompletePopup<Cidade> autoCompletePopupCid = new JFXAutoCompletePopup<Cidade>();
	@FXML
	private AnchorPane ap_fornecedor;

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_est.setItems(obslEstado);
		InitComboBoxEst();

		listaFornecedor = new ArrayList<>();
		txt_filtro.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_nome.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_telefone.setTextFormatter(tffh.getTextFieldPhoneDDDAndNumberFormatter());
		txt_cep.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "#####-###"));
		txt_cnpj.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "##.###.###/####-##"));
		txt_ie.setTextFormatter(tffh.getTextFieldFormatter("[0-9]+", 30));
		desativaTela();

		ap_fornecedor.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.ESCAPE)) {
				if (ap_busca.isVisible() == true) {
					limpaBuscas();
					txt_nome.setFocusTraversable(true);
				} else {
					ap_fornecedor.setVisible(false);
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		ativaTela();
		txt_nome.setText(fornecedor.getNome());
		txt_cep.setText(fornecedor.getCep());
		txt_cnpj.setText(fornecedor.getCnpj());
		txt_codigo.setText("" + fornecedor.getId());
		txt_telefone.setText(fornecedor.getTelefone());
		txt_ie.setText(fornecedor.getIe());
		cbb_est.getSelectionModel().select(fornecedor.getCidade().getEstado());
		listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
		obslCidade = FXCollections.observableArrayList(listaCidade);
		cbb_cidade.setDisable(false);
		cbb_cidade.setItems(obslCidade);
		cbb_cidade.getSelectionModel().select(fornecedor.getCidade());
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void desativaTela() {
		fornecedor = new Fornecedor();
		fornecedorDAO = new FornecedorDAO();
		txt_nome.setText("");
		txt_cep.setText("");
		txt_telefone.setText("");
		txt_cnpj.setText("");
		txt_ie.setText("");
		txt_nome.setStyle(corNormal);
		txt_cep.setStyle(corNormal);
		txt_telefone.setStyle(corNormal);
		txt_cnpj.setStyle(corNormal);
		txt_ie.setStyle(corNormal);
		ctm_cep.hide();
		ctm_cidade.hide();
		ctm_cnpj.hide();
		ctm_ie.hide();
		ctm_nome.hide();
		ctm_telefone.hide();
		ctm_uf.hide();
		cbb_est.setStyle(corNormal);
		cbb_cidade.setStyle(corNormal);
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		cbb_cidade.getSelectionModel().select(null);
		cbb_cidade.getItems().clear();
		cbb_est.getSelectionModel().select(null);
		cbb_cidade.setDisable(true);
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
		fornecedorDAO = new FornecedorDAO();
		fornecedor = new Fornecedor();
		if (txt_nome.getText().equals("") || txt_nome.getText().length() < 3) {
			erro = true;
			txt_nome.setStyle(corErro);
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
		} else {
			fornecedor.setNome(txt_nome.getText());
			txt_nome.setStyle(corNormal);
			ctm_nome.hide();
		}

		if (txt_cnpj.getText().equals("")) {
			erro = true;
			txt_cnpj.setStyle(corErro);
			ctm_cnpj.show(txt_cnpj, Side.RIGHT, 10, 0);
		} else {
			if (!val.isCNPJ(txt_cnpj.getText())) {
				txt_cnpj.setStyle(corErro);
				erro = true;
				ctm_cnpj.show(txt_cnpj, Side.RIGHT, 10, 0);
			} else {
				fornecedor.setCnpj(txt_cnpj.getText());
				txt_cnpj.setStyle(corNormal);
				ctm_cnpj.hide();
			}

		}

		if (txt_cep.getText().equals("") == true || !val.isCEP(txt_cep.getText())) {
			erro = true;
			txt_cep.setStyle(corErro);
			ctm_cep.show(txt_cep, Side.RIGHT, 10, 0);
		} else {
			fornecedor.setCep(txt_cep.getText());
			txt_cep.setStyle(corNormal);
			ctm_cep.hide();
		}

		if (txt_telefone.getText().equals("") || !val.isTELEFONE(txt_telefone.getText())) {
			erro = true;
			txt_telefone.setStyle(corErro);
			ctm_telefone.show(txt_telefone, Side.RIGHT, 10, 0);
		} else {
			fornecedor.setTelefone(txt_telefone.getText());
			txt_telefone.setStyle(corNormal);
			ctm_telefone.hide();
		}

		if (txt_ie.getText().equals("")) {
			erro = true;
			txt_ie.setStyle(corErro);
			ctm_ie.show(txt_ie, Side.RIGHT, 10, 0);

		} else {
			fornecedor.setIe(txt_ie.getText().toString());
			txt_ie.setStyle(corNormal);
			ctm_ie.hide();
		}

		if (txt_codigo.getText().equals("")) {
			erro = true;
		} else {
			fornecedor.setId(Long.parseLong(txt_codigo.getText()));
		}
		if (cbb_cidade.getValue() == null) {
			erro = true;
			cbb_cidade.setStyle(corErro);
			ctm_cidade.show(cbb_cidade, Side.RIGHT, 10, 0);
		} else {
			fornecedor.setCidade(cbb_cidade.getValue());
			cbb_cidade.setStyle(corNormal);
			ctm_cidade.hide();
		}
		if (cbb_est.getValue() == null) {
			erro = true;
			cbb_est.setStyle(corErro);
			ctm_uf.show(cbb_est, Side.RIGHT, 10, 0);
		} else {
			cbb_est.setStyle(corNormal);
			ctm_uf.hide();
		}
		if (erro != true) {
			FXNotification fxn;
			Boolean inserido = true;

			if (fornecedor.getId() == 0) {
				fornecedor.setId(null);
				inserido = fornecedorDAO.inserir(fornecedor);
				fxn = new FXNotification("Fornecedor: " + fornecedor.getNome() + " foi inserido",
						FXNotification.NotificationType.INFORMATION);
			} else {
				inserido = fornecedorDAO.alterar(fornecedor);
				fxn = new FXNotification("Fornecedor: " + fornecedor.getNome() + " foi alterado",
						FXNotification.NotificationType.INFORMATION);
			}

			if (inserido == false) {
				fxn = new FXNotification("Fornecedores já possuem o mesmo CNPJ.",
						FXNotification.NotificationType.WARNING);
				txt_cnpj.setStyle(corErro);
			} else {
				desativaTela();
			}
			fxn.show();

		} else {
			FXNotification fxn;
			fxn = new FXNotification("Corrija os erros em vermelho.", FXNotification.NotificationType.ERROR);
			fxn.show();
		}
	}

	@FXML
	private void OnActionGravar(ActionEvent event) {
		gravar();
	}

	@FXML
	void OnActionGravarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			gravar();
		}
	}

	@FXML
	private void OnActionExcluirEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			excluir();
		}
	}

	@FXML
	private void OnActionExcluir(ActionEvent event) {
		excluir();
	}

	public void excluir() {
		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("");
		dialogoExe.setHeaderText("Você deseja realmente excluir " + fornecedor.getNome() + "?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
		dialogoExe.showAndWait().ifPresent(b -> {
			if (b == btnSim) {
				Boolean deletado = false;
				fornecedorDAO = new FornecedorDAO();
				if (txt_codigo.getText().equals("0") != true && !txt_codigo.getText().isEmpty()) {
					try {

						fornecedor.setId(Long.parseLong(txt_codigo.getText()));
						deletado = fornecedorDAO.Deletar(fornecedor);
						if (deletado == true) {
							FXNotification fxn;
							fxn = new FXNotification("Fornecedor: " + fornecedor.getNome() + " foi Excluido",
									FXNotification.NotificationType.INFORMATION);
							fxn.show();
							desativaTela();
						} else {
							FXNotification fxn;
							fxn = new FXNotification("Fornecedor não pode ser deletado.",
									FXNotification.NotificationType.WARNING);
							fxn.show();
						}
					} catch (Exception e) {

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
	private void OnActionCancelarEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			desativaTela();
		}
	}

	@FXML
	private void OnActionSair(ActionEvent event) {
		ap_fornecedor.setVisible(false);
	}

	@FXML
	private void OnActionSairEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			ap_fornecedor.setVisible(false);
		}
	}

	@FXML
	private void OnMouseSelectionUf(ActionEvent event) {
		if (cbb_est.getSelectionModel().getSelectedIndex() != -1) {
			listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
			obslCidade = FXCollections.observableArrayList(listaCidade);
			cbb_cidade.setItems(obslCidade);
			cbb_cidade.setDisable(false);
			InitComboBoxCid();
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

	public void carregaTela(String palavra) {
		listaFornecedor.clear();
		tb_fornecedor.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_fornecedor.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tc_cidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
		tc_telefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		tc_cnpj.setCellValueFactory(new PropertyValueFactory<>("Cnpj"));
		listaFornecedor = fornecedorDAO.listar(palavra.toUpperCase());
		if (!listaFornecedor.isEmpty()) {
			obslFornecedor = FXCollections.observableArrayList(listaFornecedor);
			tb_fornecedor.setItems(obslFornecedor);
		}

	}

	private void InitComboBoxCid() {
		autoCompletePopupCid.getSuggestions().clear();
		autoCompletePopupCid.getSuggestions().addAll(cbb_cidade.getItems());

		autoCompletePopupCid.setSelectionHandler(eventt -> {
			cbb_cidade.setValue(eventt.getObject());
			cbb_cidade.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupCid.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_cidade.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupCid.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupCid.setHideOnEscape(false);
			autoCompletePopupCid.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupCid.getFilteredSuggestions().isEmpty() || cbb_cidade.showingProperty().get()
					|| cbb_cidade.getEditor().isFocused() == false) {
				autoCompletePopupCid.hide();
			} else {
				autoCompletePopupCid.show(editor);
			}
		});
		cbb_cidade.setConverter(new StringConverter<Cidade>() {

			@Override
			public String toString(Cidade provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Cidade fromString(String string) {
				try {
					int index = cbb_cidade.getSelectionModel().getSelectedIndex();
					return cbb_cidade.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	private void InitComboBoxEst() {
		autoCompletePopupEst.getSuggestions().addAll(cbb_est.getItems());

		autoCompletePopupEst.setSelectionHandler(eventt -> {
			cbb_est.setValue(eventt.getObject());
			cbb_est.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupEst.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_est.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupEst.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupEst.setHideOnEscape(false);
			autoCompletePopupEst.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupEst.getFilteredSuggestions().isEmpty() || cbb_est.showingProperty().get()
					|| cbb_est.getEditor().isFocused() == false) {
				autoCompletePopupEst.hide();
			} else {
				autoCompletePopupEst.show(editor);
			}
		});
		cbb_est.setConverter(new StringConverter<Estado>() {

			@Override
			public String toString(Estado provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Estado fromString(String string) {
				try {
					int index = cbb_est.getSelectionModel().getSelectedIndex();
					return cbb_est.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	@FXML
	private void OnActionVoltar(ActionEvent event) {
		limpaBuscas();
	}

	public void limpaBuscas() {
		listaFornecedor.clear();
		tb_fornecedor.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

	@FXML
	private void OnMouseClickedFornecedor(MouseEvent event) {

		if (tb_fornecedor.getSelectionModel().getSelectedItem() != null) {
			this.setFornecedor(tb_fornecedor.getSelectionModel().getSelectedItem());
			limpaBuscas();
		}
	}
}
