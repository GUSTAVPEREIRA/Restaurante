package estagio.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.TipoVendaDAO;
import estagio.model.TipoVenda;
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

public class TipoVendaController implements Initializable {

	@FXML
	private AnchorPane ap_busca;

	@FXML
	private AnchorPane ap_tipoVenda;

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private JFXButton btn_Excluir;

	@FXML
	private JFXButton btn_filtro;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private JFXButton btn_voltar;

	String corErro = "-fx-border-color: red;";

	String corNormal = "-fx-border-color:white";

	@FXML
	private ContextMenu ctm_nome;

	@FXML
	private Label lbl_codigo;

	@FXML
	private Label lbl_nome;

	private List<TipoVenda> listaTipoVenda;

	@FXML
	private MenuItem mi_nome;

	private ObservableList<TipoVenda> obslTipoVenda;

	@FXML
	private TableView<TipoVenda> tb_tipoVenda;

	@FXML
	private TableColumn<TipoVenda, String> tc_codigo;

	@FXML
	private TableColumn<TipoVenda, String> tc_nome;

	private TipoVenda tipoVenda;

	private TipoVendaDAO tipoVendaDAO;

	@FXML
	private Tooltip ttp_btnBuscar;

	@FXML
	private Tooltip ttp_btnCancelar;

	@FXML
	private Tooltip ttp_btnExcluir;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private Tooltip ttp_btnNovo;
	@FXML
	private Tooltip ttp_btnSair;
	@FXML
	private Tooltip ttp_lblCodigo;
	@FXML
	private Tooltip ttp_Nome;
	@FXML
	private JFXTextField txt_codigo;
	@FXML
	private JFXTextField txt_filtro;
	@FXML
	private JFXTextField txt_nome;

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void carregaTela(String palavra) {

		listaTipoVenda.clear();
		tb_tipoVenda.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		listaTipoVenda = tipoVendaDAO.listar(palavra.toUpperCase());
		if (!listaTipoVenda.isEmpty()) {
			obslTipoVenda = FXCollections.observableArrayList(listaTipoVenda);
			tb_tipoVenda.setItems(obslTipoVenda);
		}
	}

	public void desativaTela() {
		txt_nome.setText("");
		txt_nome.setStyle(corNormal);
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		tipoVenda = new TipoVenda();
		tipoVendaDAO = new TipoVendaDAO();
	}

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rbad
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		tipoVenda = new TipoVenda();
		tipoVendaDAO = new TipoVendaDAO();
		txt_filtro.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_nome.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		listaTipoVenda = new ArrayList<>();
		tipoVenda = new TipoVenda();

	}

	public void limpaBuscas() {
		listaTipoVenda.clear();
		tb_tipoVenda.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

	@FXML
	void OnActionBuscar(ActionEvent event) {
		carregaTela(txt_filtro.getText());
		ap_busca.setVisible(true);

	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionExcluir(ActionEvent event) {
		resetaDAO();
		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("");
		dialogoExe.setHeaderText("Você deseja realmente excluir " + tipoVenda.getNome() + "?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
		dialogoExe.showAndWait().ifPresent(b -> {
			if (b == btnSim) {
				FXNotification fxn;
				if (txt_codigo.getText().equals("") != true && !txt_codigo.getText().isEmpty()) {
					tipoVenda.setId(Long.parseLong(txt_codigo.getText()));
					tipoVendaDAO.Deletar(tipoVenda);
					fxn = new FXNotification("Novo produto: " + tipoVenda.getNome() + " foi excluido.",
							FXNotification.NotificationType.INFORMATION);
					fxn.show();
				}
				desativaTela();
			}
		});
	}

	@FXML
	void OnActionFiltro(ActionEvent event) {
		carregaTela(txt_filtro.getText());
	}

	@FXML
	void OnActionGravar(ActionEvent event) {
		resetaDAO();
		Boolean erro = false;
		tipoVenda = new TipoVenda();
		if (txt_nome.getText().equals("") == true || txt_nome.getText().length() < 4) {
			erro = true;
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
			txt_nome.setStyle(corErro);
		} else {
			tipoVenda.setNome(txt_nome.getText());
			txt_nome.setStyle(corNormal);
			ctm_nome.hide();
		}

		if (txt_codigo.getText().equals("") == true) {
			erro = true;
		} else {
			tipoVenda.setId(Long.parseLong(txt_codigo.getText()));
		}

		if (erro != true) {
			tipoVendaDAO = new TipoVendaDAO();
			FXNotification fxn;
			if (tipoVenda.getId() == 0) {
				tipoVenda.setId(null);
				tipoVendaDAO.inserir(tipoVenda);
				fxn = new FXNotification("Tipo de venda " + tipoVenda.getNome() + " Inserida",
						FXNotification.NotificationType.INFORMATION);
			} else {
				tipoVendaDAO.alterar(tipoVenda);
				fxn = new FXNotification("Tipo de venda " + tipoVenda.getNome() + " Alterada",
						FXNotification.NotificationType.INFORMATION);
			}
			fxn.show();
			desativaTela();
		}

	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		ap_tipoVenda.setVisible(false);
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		ap_busca.setVisible(false);
		limpaBuscas();

	}

	@FXML
	void OnKeyPressedEnter(KeyEvent event) {
		txt_filtro.setOnKeyPressed((KeyEvent event1) -> {
			if (event1.getCode() == KeyCode.ENTER) {
				carregaTela(txt_filtro.getText());
			}
		});
	}

	@FXML
	void OnMouseClickedTipoVenda(MouseEvent event) {
		if (tb_tipoVenda.getSelectionModel().getSelectedItem() != null) {
			this.setTipoVenda(tb_tipoVenda.getSelectionModel().getSelectedItem());
		}
	}

	public void resetaDAO() {
		tipoVendaDAO = new TipoVendaDAO();
	}

	public void setTipoVenda(TipoVenda tipoVenda) {
		this.tipoVenda = tipoVenda;
		txt_nome.setText(tipoVenda.getNome());
		txt_codigo.setText("" + tipoVenda.getId());
		limpaBuscas();
		ativaTela();
	}

}
