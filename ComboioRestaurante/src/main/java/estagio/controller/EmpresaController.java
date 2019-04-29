package estagio.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CidadeDAO;
import estagio.dao.EmpresaDAO;
import estagio.dao.EstadoDAO;
import estagio.model.Cidade;
import estagio.model.Empresa;
import estagio.model.Estado;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.Regex;
import estagio.view.util.TextFieldFormatterHelper;
import estagio.view.util.Validadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EmpresaController implements Initializable {

	@FXML
	private AnchorPane ap_empresa;

	@FXML
	private ImageView img_empresa;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	private Label lbl_nome;

	@FXML
	private Tooltip ttp_lblNome;

	@FXML
	private JFXTextField txt_nome;

	@FXML
	private ContextMenu ctm_nome;

	@FXML
	private MenuItem mi_nome;

	@FXML
	private Label lbl_cnpj;

	@FXML
	private Tooltip ttp_lblCnpj;

	@FXML
	private JFXTextField txt_cnpj;

	@FXML
	private ContextMenu ctm_cnpj;

	@FXML
	private MenuItem mi_cnpj;

	@FXML
	private Label lbl_ie;

	@FXML
	private Tooltip ttp_lblIe;

	@FXML
	private JFXTextField txt_ie;

	@FXML
	private ContextMenu ctm_ie;

	@FXML
	private MenuItem mi_ie;

	@FXML
	private Label lbl_telefone;

	@FXML
	private Tooltip ttp_lblTelefone;

	@FXML
	private JFXTextField txt_telefone;

	@FXML
	private ContextMenu ctm_telefone;

	@FXML
	private MenuItem mi_telefone;

	@FXML
	private JFXTextField txt_cep;

	@FXML
	private ContextMenu ctm_cep;

	@FXML
	private MenuItem mi_cep;

	@FXML
	private Label lbl_cep;

	@FXML
	private Tooltip ttp_lblCep;

	@FXML
	private Label lbl_UF;

	@FXML
	private Tooltip ttp_lblCidade1;

	@FXML
	private JFXComboBox<Estado> cbb_est;

	@FXML
	private Tooltip ttp_estado;

	@FXML
	private ContextMenu ctm_uf;

	@FXML
	private MenuItem mi_uf;

	@FXML
	private Label lbl_cidade;

	@FXML
	private Tooltip ttp_lblCidade;

	@FXML
	private JFXComboBox<Cidade> cbb_cidade;

	@FXML
	private Tooltip ttp_cidade;

	@FXML
	private ContextMenu ctm_cidade;

	@FXML
	private MenuItem mi_cidade;

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private Tooltip ttp_btnBuscar;

	JFXAutoCompletePopup<Estado> autoCompletePopupEst = new JFXAutoCompletePopup<Estado>();
	JFXAutoCompletePopup<Cidade> autoCompletePopupCid = new JFXAutoCompletePopup<Cidade>();
	private List<Estado> listaEstado;
	private ObservableList<Estado> obslEstado;
	private EstadoDAO estadoDAO = new EstadoDAO();
	private List<Cidade> listaCidade;
	private ObservableList<Cidade> obslCidade;
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private Validadores val = new Validadores();
	private TextFieldFormatterHelper tffh;
	private EmpresaDAO empresaDAO;
	private Empresa empresa;
	private Regex regex = new Regex();
	private String corErro = "-fx-border-color: red;";
	private String corNormal = "-fx-border-color:white";

	@FXML
	void Limitetxt_Nome(KeyEvent event) {

	}

	@FXML
	void OnActionBuscar(ActionEvent event) {
		selecionaFoto();
	}

	public void gravar() {
		boolean erro = false;
		resetaDAO();

		if (txt_nome.getText().equals("") == true || txt_nome.getText().replace("", " ").length() < 4) {
			erro = true;
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
			txt_nome.setStyle(corErro);
		} else {
			txt_nome.setStyle(corNormal);
			ctm_nome.hide();
			empresa.setNome(txt_nome.getText());
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
				empresa.setCnpj(txt_cnpj.getText());
				txt_cnpj.setStyle(corNormal);
				ctm_cnpj.hide();
			}
		}

		if (txt_cep.getText().equals("") == true || !val.isCEP(txt_cep.getText())) {
			erro = true;
			txt_cep.setStyle(corErro);
			ctm_cep.show(txt_cep, Side.RIGHT, 10, 0);
		} else {
			empresa.setCep(txt_cep.getText());
			txt_cep.setStyle(corNormal);
			ctm_cep.hide();
		}

		if (txt_telefone.getText().equals("") || !val.isTELEFONE(txt_telefone.getText())) {
			erro = true;
			txt_telefone.setStyle(corErro);
			ctm_telefone.show(txt_telefone, Side.RIGHT, 10, 0);
		} else {
			empresa.setTelefone(txt_telefone.getText());
			txt_telefone.setStyle(corNormal);
			ctm_telefone.hide();
		}
		if (txt_ie.getText().equals("")) {
			erro = true;
			txt_ie.setStyle(corErro);
			ctm_ie.show(txt_ie, Side.RIGHT, 10, 0);

		} else {
			empresa.setIe(txt_ie.getText().toString());
			txt_ie.setStyle(corNormal);
			ctm_ie.hide();
		}

		if (cbb_cidade.getValue() == null) {
			erro = true;
			cbb_cidade.setStyle(corErro);
			ctm_cidade.show(cbb_cidade, Side.RIGHT, 10, 0);
		} else {
			empresa.setCidade(cbb_cidade.getValue());
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
			if (empresa.getId() == 0) {
				empresa.setId(null);
				empresaDAO.inserir(empresa);
				fxn = new FXNotification("Empresa : " + empresa.getNome() + " foi cadastrada.",
						FXNotification.NotificationType.INFORMATION);

			} else {
				empresaDAO.alterar(empresa);
				fxn = new FXNotification("Empresa : " + empresa.getNome() + " foi alterada.",
						FXNotification.NotificationType.INFORMATION);
			}
			fxn.show();
		}
	}

	@FXML
	void OnActionGravar(ActionEvent event) {
		gravar();
	}

	public void resetaDAO() {
		empresaDAO = new EmpresaDAO();
		estadoDAO = new EstadoDAO();
		cidadeDAO = new CidadeDAO();
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		ap_empresa.setVisible(false);
	}

	@FXML
	void OnMouseSelectionUf(ActionEvent event) {
		if (cbb_est.getSelectionModel().getSelectedIndex() != -1) {
			listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
			obslCidade = FXCollections.observableArrayList(listaCidade);
			cbb_cidade.setItems(obslCidade);
			cbb_cidade.setDisable(false);
			InitComboBoxCid();
		}
	}

	@FXML
	void txtCepOnKeyRelease(KeyEvent event) {

	}

	@FXML
	void txtCnpjOnKeyRelease(KeyEvent event) {

	}

	@FXML
	void txtIeOnKeyRelease(KeyEvent event) {

	}

	@FXML
	void txtTelefoneOnKeyRelease(KeyEvent event) {

	}

	public void selecionaFoto() {
		FileChooser f = new FileChooser();
		f.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
		File file = f.showOpenDialog(new Stage());
		if (file != null) {
			img_empresa.setImage(new Image("file:///" + file.getAbsolutePath()));
			empresa.setCaminho_imgem(file.getAbsolutePath());
		}
	}

	public void setaFoto(String caminho) {
		img_empresa.setImage(new Image("file:///" + empresa.getCaminho_imgem()));
	}

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		tffh = new TextFieldFormatterHelper();
		txt_nome.setTextFormatter(tffh.getTextFieldToUpperFormatter(regex.getDescricao(), 100));
		txt_telefone.setTextFormatter(tffh.getTextFieldPhoneDDDAndNumberFormatter());
		txt_cep.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "#####-###"));
		txt_cnpj.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "##.###.###/####-##"));
		txt_ie.setTextFormatter(tffh.getTextFieldFormatter("[0-9]+", 30));
		resetaDAO();
		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_est.setItems(obslEstado);
		empresa = new Empresa();
		empresa = empresaDAO.listar();
		InitComboBoxEst();
		InitComboBoxCid();
		autoCompletePopupEst.hide();
		autoCompletePopupCid.hide();
		if (empresa != null) {
			setEmpresa(empresa);
		} else {
			empresa = new Empresa();
			empresa.setId(Long.parseLong("0"));
		}
		ap_empresa.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.ESCAPE)) {
				ap_empresa.setVisible(false);
			}
			if (event.getCode().equals(KeyCode.F2)) {
				gravar();
			}
		});
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

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		img_empresa.setImage(new Image("file:///" + empresa.getCaminho_imgem()));
		txt_nome.setText(empresa.getNome());
		txt_cep.setText(empresa.getCep());
		txt_cnpj.setText(empresa.getCnpj());
		txt_telefone.setText(empresa.getTelefone());
		txt_ie.setText(empresa.getIe());
		cbb_est.getSelectionModel().select(empresa.getCidade().getEstado());
		listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
		obslCidade = FXCollections.observableArrayList(listaCidade);
		cbb_cidade.setDisable(false);
		cbb_cidade.setItems(obslCidade);
		cbb_cidade.getSelectionModel().select(empresa.getCidade());
	}

}
