package estagio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CategoriaDAO;
import estagio.dao.TipoVendaDAO;
import estagio.model.Categoria;
import estagio.model.TipoVenda;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TipoVendaController {

	@FXML
	private JFXTextField txt_codigo;

	@FXML
	private Label lbl_codigo;

	@FXML
	private Tooltip ttp_lblCodigo;

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private Tooltip ttp_btnBuscar;

	@FXML
	private Label lbl_nome;

	@FXML
	private Tooltip ttp_Nome;

	@FXML
	private JFXTextField txt_nome;

	@FXML
	private ContextMenu ctm_nome;

	@FXML
	private MenuItem mi_nome;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private Tooltip ttp_btnNovo;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private JFXButton btn_Excluir;

	@FXML
	private Tooltip ttp_btnExcluir;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnCancelar;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	private AnchorPane ap_busca;

	@FXML
	private JFXButton btn_filtro;

	@FXML
	private JFXTextField txt_filtro;

	@FXML
	private TableView<TipoVenda> tb_tipoVenda;

	@FXML
	private TableColumn<TipoVenda, String> tc_codigo;

	@FXML
	private TableColumn<TipoVenda, String> tc_nome;

	@FXML
	private JFXButton btn_voltar;

	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	private TextFieldFormatterHelper tffh = new TextFieldFormatterHelper();
	private TipoVenda tipoVenda;
	private TipoVendaDAO tipoVendaDAO;
	private ObservableList<TipoVenda> obslTipoVenda;
	private List<TipoVenda> listaTipoVenda;

	@SuppressWarnings("static-access")
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		tipoVenda = new TipoVenda();
		tipoVendaDAO = new TipoVendaDAO();
		mi_nome.setText("Por favor, insira o nome corretamente.");
		ctm_nome.getItems().add(mi_nome);
		txt_nome.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
		txt_filtro.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
		listaTipoVenda = new ArrayList<>();

	}

	public void desativaTela() {
		txt_nome.setText("");
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		tipoVenda = new TipoVenda();
		tipoVendaDAO = new TipoVendaDAO();
	}

	@FXML
	void Limitetxt_filtro(KeyEvent event) {
		if (txt_filtro.getText().length() == 100) {
			event.consume();
		}
	}

	@FXML
	void Limitetxt_nome(KeyEvent event) {
		if (txt_nome.getText().length() == 100) {
			event.consume();
		}
	}

	@FXML
	void OnActionBuscar(ActionEvent event) {
		ap_busca.setVisible(true);
		carregaTela(txt_filtro.getText());
	}

	public void carregaTela(String palavra) {
		listaTipoVenda.clear();
		tb_tipoVenda.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		// listaTipoVenda = tipoVendaDAO.listar(palavra.toUpperCase());
		if (!listaTipoVenda.isEmpty()) {
			obslTipoVenda = FXCollections.observableArrayList(listaTipoVenda);
			tb_tipoVenda.setItems(obslTipoVenda);
		}
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionExcluir(ActionEvent event) {

	}

	@FXML
	void OnActionFiltro(ActionEvent event) {
		carregaTela(txt_filtro.getText());
	}

	@FXML
	void OnActionGravar(ActionEvent event) {

	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionSair(ActionEvent event) {

	}

	@FXML
	void OnActionVoltar(ActionEvent event) {

	}

	@FXML
	void OnKeyPressedEnter(KeyEvent event) {

	}

	public void setTipoVenda(TipoVenda tipoVenda) {
		this.tipoVenda = tipoVenda;
		txt_nome.setText(tipoVenda.getNome());
		txt_codigo.setText("" + tipoVenda.getId());
		limpaBuscas();
		ativaTela();
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void limpaBuscas() {
		listaTipoVenda.clear();
		tb_tipoVenda.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

	@FXML
	void OnMouseClickedTipoVenda(MouseEvent event) {
		if (tb_tipoVenda.getSelectionModel().getSelectedItem() != null) {
			this.setTipoVenda(tb_tipoVenda.getSelectionModel().getSelectedItem());
		}
	}

}
