/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CidadeDAO;
import estagio.dao.ClienteDAO;
import estagio.dao.EstadoDAO;
import estagio.model.Cidade;
import estagio.model.Cliente;
import estagio.model.Estado;
import estagio.model.Fornecedor;
import estagio.view.util.TextFieldFormatterHelper;
import estagio.view.util.Validadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class ClienteController implements Initializable {

	@FXML
	private AnchorPane ap_cliente;

	@FXML
	private Label lbl_codigo;

	@FXML
	private Tooltip ttp_lblCodigo;

	@FXML
	private JFXTextField txt_codigo;

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private Tooltip ttp_btnBuscar;

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
	private Label lbl_cep;

	@FXML
	private Tooltip ttp_lblCep;

	@FXML
	private JFXTextField txt_cep;

	@FXML
	private ContextMenu ctm_cep;

	@FXML
	private MenuItem mi_cep;

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
	private TableView<Cliente> tb_pessoa;

	@FXML
	private TableColumn<Cliente, String> tc_codigo;

	@FXML
	private TableColumn<Cliente, String> tc_cpf;

	@FXML
	private TableColumn<Cliente, String> tc_cnp;

	@FXML
	private TableColumn<Cliente, String> tc_cidade;

	@FXML
	private TableColumn<Cliente, String> tc_telefone;

	@FXML
	private JFXButton btn_voltar;

	@FXML
	private Label lbl_UF1;

	@FXML
	private Tooltip ttp_lblCidade11;

	@FXML
	private AnchorPane ap_pessoaFisica;

	@FXML
	private Label lbl_cpf;

	@FXML
	private Tooltip ttp_lblCpf;

	@FXML
	private JFXTextField txt_cpf;

	@FXML
	private ContextMenu ctm_cpf;

	@FXML
	private MenuItem mi_cpf;

	@FXML
	private Label lbl_rg;

	@FXML
	private Tooltip ttp_lblRg;

	@FXML
	private JFXTextField txt_rg;

	@FXML
	private ContextMenu ctm_rg;

	@FXML
	private MenuItem mi_rg;

	@FXML
	private Label lbl_estadoCivil;

	@FXML
	private Tooltip ttp_lblEstadoCivil;

	@FXML
	private JFXComboBox<Cliente> cbb_estadoCivil;

	@FXML
	private Tooltip ttp_estadoCivil;

	@FXML
	private ContextMenu ctm_estadoCivil;

	@FXML
	private MenuItem mi_estadoCivil;

	@FXML
	private Label lbl_dataNasc;

	@FXML
	private Tooltip ttp_lblDataNasc;

	@FXML
	private ContextMenu ctm_dataNasc;

	@FXML
	private MenuItem mi_dataNasc;

	@FXML
	private AnchorPane ap_pessoaJuridica;

	@FXML
	private Label lbl_nomeFantasia;

	@FXML
	private Tooltip ttp_lblNomeFantasia;

	@FXML
	private JFXTextField txt_nomeFantasia;

	@FXML
	private ContextMenu ctm_nomeFantasia;

	@FXML
	private MenuItem mi_nomeFantasia;

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
	private JFXCheckBox cb_fisica;

	@FXML
	private JFXCheckBox cb_juridica;
	@FXML
	private JFXDatePicker txt_dataNasc;

	private List<Estado> listaEstado;
	private ObservableList<Estado> obslEstado;
	private EstadoDAO estadoDAO = new EstadoDAO();
	private List<Cidade> listaCidade;
	private ObservableList<Cidade> obslCidade;
	private CidadeDAO cidadeDAO = new CidadeDAO();
	private Validadores val = new Validadores();
	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private String corErro = "-fx-border-color: red;";
	private String corNormal = "-fx-border-color:white";
	private ObservableList<Fornecedor> obslFornecedor;
	private List<Fornecedor> listaFornecedor;
	private TextFieldFormatterHelper tffh;

	@FXML
	void Limitetxt_Nome(KeyEvent event) {
		if (txt_nome.getText().length() == 100) {
			event.consume();
		}
	}

	@FXML
	void Limitetxt_filtro(KeyEvent event) {
		if (txt_filtro.getText().length() == 100) {
			event.consume();
		}
	}

	@FXML
	void OnActionBuscar(ActionEvent event) {

	}

	@FXML
	void OnActionCancelar(ActionEvent event) {

	}

	@FXML
	void OnActionExcluir(ActionEvent event) {

	}

	@FXML
	void OnActionFiltro(ActionEvent event) {

	}

	@FXML
	void OnActionGravar(ActionEvent event) {

	}

	@FXML
	void OnActionNovo(ActionEvent event) {

	}

	@FXML
	void OnActionSair(ActionEvent event) {
		ap_cliente.setVisible(false);
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		ap_busca.setVisible(false);
	}

	@FXML
	void OnKeyPressedEnter(KeyEvent event) {

	}

	@FXML
	void OnMouseClickedCliente(MouseEvent event) {

	}

	@FXML
	void OnMouseSelectionEstadoCivil(ActionEvent event) {

	}

	@FXML
	void OnMouseSelectionUf(ActionEvent event) {
		if (cbb_est.getSelectionModel().getSelectedIndex() != -1) {
			listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
			obslCidade = FXCollections.observableArrayList(listaCidade);
			cbb_cidade.setItems(obslCidade);
			cbb_cidade.setDisable(false);
		}
	}

	@FXML
	void onActionPessoaFisica(ActionEvent event) {
		if (cb_juridica.isSelected() == true) {
			cb_juridica.setSelected(false);
			ap_pessoaJuridica.setVisible(false);
		}
		if (cb_fisica.isSelected() == true) {
			ap_pessoaFisica.setVisible(true);
		} else {
			ap_pessoaFisica.setVisible(false);
		}
	}

	@FXML
	void onActionPessoaJuridica(ActionEvent event) {
		if (cb_fisica.isSelected() == true) {
			cb_fisica.setSelected(false);
			ap_pessoaFisica.setVisible(false);
		}
		if (cb_juridica.isSelected() == true) {
			ap_pessoaJuridica.setVisible(true);
		} else {
			ap_pessoaJuridica.setVisible(false);
		}
	}

	@FXML
	void Limitetxt_ie(KeyEvent event) {

	}

	/**
	 * Initializes the controller class.
	 */
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_est.setItems(obslEstado);
		txt_nome.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_nomeFantasia.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_telefone.setTextFormatter(tffh.getTextFieldPhoneDDDAndNumberFormatter());
		txt_cep.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "#####-###"));
		txt_cnpj.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "##.###.###/####-##"));
		txt_ie.setTextFormatter(tffh.getTextFieldToUpperFormatter("[0-9]", 30));
	}

}
