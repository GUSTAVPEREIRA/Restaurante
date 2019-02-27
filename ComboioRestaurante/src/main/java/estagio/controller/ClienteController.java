/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import estagio.model.ClientePF;
import estagio.model.ClientePJ;
import estagio.model.Estado;
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
	private TableColumn<Cliente, String> tc_cnpj;

	@FXML
	private TableColumn<Cliente, String> tc_cidade;

	@FXML
	private TableColumn<Cliente, String> tc_telefone;

	@FXML
	private TableColumn<Cliente, String> tc_nome;

	@FXML
	private Tooltip ttp_lblTipoP;

	@FXML
	private ContextMenu ctm_tipoP;

	@FXML
	private MenuItem mi_tipoP;

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
	private JFXComboBox<String> cbb_estadoCivil;

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

	@FXML
	private JFXCheckBox cb_fisicaBusca;

	@FXML
	private Label lbl_tipoP;

	@FXML
	private JFXCheckBox cb_juridicaBusca;
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
	private ObservableList<Cliente> obslCliente;
	private List<Cliente> listaCliente;
	private TextFieldFormatterHelper tffh;
	private ObservableList<String> obsl_estadoCivil;
	private String tipo;

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
		ap_busca.setVisible(true);
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionExcluir(ActionEvent event) {
		Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
		dialogoExe.setTitle("");
		dialogoExe.setHeaderText("Você deseja realmente excluir " + cliente.getNome() + "?");
		dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
		dialogoExe.showAndWait().ifPresent(b -> {
			if (b == btnSim) {
				clienteDAO = new ClienteDAO();
				if (txt_codigo.getText().equals("0") != true && !txt_codigo.getText().isEmpty()) {
					cliente.setId(Long.parseLong(txt_codigo.getText()));
					clienteDAO.delete(cliente);
					FXNotification fxn;
					fxn = new FXNotification("Cliente: " + cliente.getNome() + " foi Excluido",
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
		boolean erro = false;

		if (cb_fisica.isSelected() == false && cb_juridica.isSelected() == false) {
			ctm_tipoP.show(lbl_tipoP, Side.RIGHT, 10, 0);
			lbl_tipoP.setStyle(corErro);
		} else {
			ctm_tipoP.hide();
			lbl_tipoP.setStyle(corNormal);
		}

		if (cb_fisica.isSelected() == true) {
			cliente = new ClientePF();

			if (txt_dataNasc.getValue() == null) {
				ctm_dataNasc.show(txt_dataNasc, Side.RIGHT, 10, 0);
				txt_dataNasc.setStyle(corErro);
			} else {
				ctm_dataNasc.hide();
				txt_dataNasc.setStyle(corNormal);
				((ClientePF) cliente).setDataNasc(Date.valueOf(txt_dataNasc.getValue()));
			}
			if (txt_rg.getText().length() < 4) {
				ctm_rg.show(txt_rg, Side.RIGHT, 10, 0);
				txt_rg.setStyle(corErro);
			} else {
				ctm_rg.hide();
				txt_rg.setStyle(corNormal);
				((ClientePF) cliente).setRg(txt_rg.getText());
			}
			if (cbb_estadoCivil.getValue() == null) {
				erro = true;
				cbb_estadoCivil.setStyle(corErro);
				ctm_estadoCivil.show(cbb_estadoCivil, Side.RIGHT, 10, 0);
			} else {
				((ClientePF) cliente).setEstadoCivil(cbb_estadoCivil.getSelectionModel().getSelectedItem());
				cbb_estadoCivil.setStyle(corNormal);
				ctm_estadoCivil.hide();
			}
			if (txt_cpf.getText().equals("")) {
				erro = true;
				txt_cpf.setStyle(corErro);
				ctm_cpf.show(txt_cpf, Side.RIGHT, 10, 0);
			} else {
				if (!val.isCPF(txt_cpf.getText())) {
					txt_cpf.setStyle(corErro);
					erro = true;
					ctm_cpf.show(txt_cpf, Side.RIGHT, 10, 0);
				} else {
					((ClientePF) cliente).setCpf(txt_cpf.getText());
					txt_cpf.setStyle(corNormal);
					ctm_cpf.hide();
				}

			}
			// PESSOA JURIDICA
		} else if (cb_juridica.isSelected() == true) {
			cliente = new ClientePJ();
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
					((ClientePJ) cliente).setCnpj(txt_cnpj.getText());
					txt_cnpj.setStyle(corNormal);
					ctm_cnpj.hide();
				}

			}
			if (txt_ie.getText().equals("")) {
				erro = true;
				txt_ie.setStyle(corErro);
				ctm_ie.show(txt_ie, Side.RIGHT, 10, 0);

			} else {
				((ClientePJ) cliente).setIe(txt_ie.getText());
				txt_ie.setStyle(corNormal);
				ctm_ie.hide();
			}

			if (txt_nomeFantasia.getText().equals("") || txt_nomeFantasia.getText().length() < 3) {
				erro = true;
				txt_nomeFantasia.setStyle(corErro);
				ctm_nome.show(txt_nomeFantasia, Side.RIGHT, 10, 0);
			} else {
				((ClientePJ) cliente).setNomeFantasia(txt_nomeFantasia.getText());
				txt_nomeFantasia.setStyle(corNormal);
				ctm_nome.hide();
			}

		}
		if (txt_codigo.getText().equals("")) {
			erro = true;
		} else {
			cliente.setId(Long.parseLong(txt_codigo.getText()));
		}
		if (cbb_cidade.getValue() == null) {
			erro = true;
			cbb_cidade.setStyle(corErro);
			ctm_cidade.show(cbb_cidade, Side.RIGHT, 10, 0);
		} else {
			cliente.setCidade(cbb_cidade.getSelectionModel().getSelectedItem());
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

		if (txt_cep.getText().equals("") == true || !val.isCEP(txt_cep.getText())) {
			erro = true;
			txt_cep.setStyle(corErro);
			ctm_cep.show(txt_cep, Side.RIGHT, 10, 0);
		} else {
			cliente.setCep(txt_cep.getText());
			txt_cep.setStyle(corNormal);
			ctm_cep.hide();
		}

		if (txt_telefone.getText().equals("") || !val.isTELEFONE(txt_telefone.getText())) {
			erro = true;
			txt_telefone.setStyle(corErro);
			ctm_telefone.show(txt_telefone, Side.RIGHT, 10, 0);
		} else {
			cliente.setTelefone(txt_telefone.getText());
			txt_telefone.setStyle(corNormal);
			ctm_telefone.hide();
		}
		if (txt_nome.getText().equals("") || txt_nome.getText().length() < 3) {
			erro = true;
			txt_nome.setStyle(corErro);
			ctm_nome.show(txt_nome, Side.RIGHT, 10, 0);
		} else {
			cliente.setNome(txt_nome.getText());
			txt_nome.setStyle(corNormal);
			ctm_nome.hide();
		}

		if (erro != true) {
			FXNotification fxn;

			if (cliente.getId() == 0) {
				cliente.setId(null);
				new ClienteDAO().save(cliente);
				fxn = new FXNotification("Cliente: " + cliente.getNome() + " foi inserido",
						FXNotification.NotificationType.INFORMATION);
			} else {
				clienteDAO.merge(cliente);
				fxn = new FXNotification("Cliente: " + cliente.getNome() + " foi alterado",
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
		ap_cliente.setVisible(false);
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
	void OnMouseClickedCliente(MouseEvent event) {

		if (tb_pessoa.getSelectionModel().getSelectedItem() != null) {
			this.setCliente(tb_pessoa.getSelectionModel().getSelectedItem());
			limpaBuscas();
		}
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
	void onActionPessoaFisicaBusca(ActionEvent event) {
		if (cb_juridicaBusca.isSelected() == true) {
			tb_pessoa.getItems().clear();
			cb_juridicaBusca.setSelected(false);
			ap_pessoaJuridica.setVisible(false);
			tc_cnpj.setVisible(false);
		}
		if (cb_fisicaBusca.isSelected() == true) {
			tb_pessoa.getItems().clear();
			tc_cpf.setVisible(true);
			ap_pessoaFisica.setVisible(true);
		} else {
			tb_pessoa.getItems().clear();
			tc_cpf.setVisible(false);
			ap_pessoaFisica.setVisible(false);
		}
	}

	@FXML
	void onActionPessoaJuridicaBusca(ActionEvent event) {
		if (cb_fisicaBusca.isSelected() == true) {
			tb_pessoa.getItems().clear();
			tc_cpf.setVisible(false);
			cb_fisicaBusca.setSelected(false);
			ap_pessoaFisica.setVisible(false);
		}
		if (cb_juridicaBusca.isSelected() == true) {
			tb_pessoa.getItems().clear();
			tc_cnpj.setVisible(true);
			ap_pessoaJuridica.setVisible(true);
		} else {
			tb_pessoa.getItems().clear();
			cb_juridicaBusca.setSelected(false);
			ap_pessoaJuridica.setVisible(false);
		}
	}

	@FXML
	void Limitetxt_ie(KeyEvent event) {

	}

	public void desativaTela() {
		cliente = new Cliente();
		txt_cpf.setText("");
		txt_dataNasc.setValue(null);
		txt_rg.setText("");
		txt_nomeFantasia.setText("");
		txt_nome.setText("");
		txt_cep.setText("");
		txt_telefone.setText("");
		txt_cnpj.setText("");
		txt_ie.setText("");
		txt_rg.setStyle(corNormal);
		txt_cpf.setStyle(corNormal);
		txt_nomeFantasia.setStyle(corNormal);
		txt_nome.setStyle(corNormal);
		txt_cep.setStyle(corNormal);
		txt_telefone.setStyle(corNormal);
		txt_cnpj.setStyle(corNormal);
		txt_ie.setStyle(corNormal);
		cbb_est.setStyle(corNormal);
		cbb_cidade.setStyle(corNormal);
		txt_codigo.setText("0");
		btn_Excluir.setDisable(true);
		btn_Cancelar.setDisable(true);
		cbb_estadoCivil.getSelectionModel().select(null);
		cbb_cidade.getSelectionModel().select(null);
		cbb_cidade.getItems().clear();
		cbb_est.getSelectionModel().select(null);
		cbb_cidade.setDisable(true);
	}

	public void ativaTela() {
		btn_Excluir.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	public void setCliente(Cliente cliente) {
		ativaTela();
		desativaTela();
		this.cliente = cliente;

		txt_nome.setText(cliente.getNome());
		txt_cep.setText(cliente.getCep());
		if (tipo.equals("FISICA") != true) {
			cb_fisica.setSelected(false);
			ap_pessoaFisica.setVisible(false);
			cb_juridica.setSelected(true);
			txt_cnpj.setText(((ClientePJ) cliente).getCnpj());
			txt_ie.setText(((ClientePJ) cliente).getIe());
			txt_nomeFantasia.setText(((ClientePJ) cliente).getNomeFantasia());
		} else {
			cb_juridica.setSelected(false);
			ap_pessoaJuridica.setVisible(false);
			cb_fisica.setSelected(true);
			txt_cpf.setText(((ClientePF) cliente).getCpf());
			txt_rg.setText(((ClientePF) cliente).getRg());
			txt_dataNasc.setValue((((ClientePF) cliente).getDataNasc().toLocalDate()));
			cbb_estadoCivil.getSelectionModel().select(((ClientePF) cliente).getEstadoCivil());
		}
		txt_codigo.setText("" + cliente.getId());
		txt_telefone.setText(cliente.getTelefone());
		cbb_est.getSelectionModel().select(cliente.getCidade().getEstado());
		listaCidade = cidadeDAO.listCidadesPEstado(cbb_est.getSelectionModel().selectedItemProperty().getValue());
		obslCidade = FXCollections.observableArrayList(listaCidade);
		cbb_cidade.setDisable(false);
		cbb_cidade.setItems(obslCidade);
		cbb_cidade.getSelectionModel().select(cliente.getCidade());
		ativaTela();
	}

	public void carregaTela(String palavra) {
		if (cb_juridicaBusca.isSelected() == true)
			tipo = "JURIDICA";
		else
			tipo = "FISICA";

		clienteDAO = new ClienteDAO();
		listaCliente.clear();
		tb_pessoa.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tc_cidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
		tc_telefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		tc_cnpj.setCellValueFactory(new PropertyValueFactory<>("Cnpj"));
		tc_cpf.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
		listaCliente = clienteDAO.listar(palavra, tipo);
		if (!listaCliente.isEmpty()) {
			obslCliente = FXCollections.observableArrayList(listaCliente);
			tb_pessoa.setItems(obslCliente);
		}

	}

	/**
	 * Initializes the controller class.
	 */
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		cliente = new Cliente();
		listaCliente = new ArrayList<>();
		obsl_estadoCivil = FXCollections.observableArrayList("SOLTEIRO(A)", "CASADO(A)");
		cbb_estadoCivil.setItems(obsl_estadoCivil);
		listaEstado = estadoDAO.listar("");
		obslEstado = FXCollections.observableArrayList(listaEstado);
		cbb_est.setItems(obslEstado);
		txt_nome.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_nomeFantasia.setTextFormatter(tffh.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_telefone.setTextFormatter(tffh.getTextFieldPhoneDDDAndNumberFormatter());
		txt_cep.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "#####-###"));
		txt_cnpj.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "##.###.###/####-##"));
		txt_cpf.setTextFormatter(tffh.getTextFieldMaskFormatter("[0-9]", "###.###.###-##"));
		txt_rg.setTextFormatter(tffh.getTextFieldToUpperFormatter("[0-9]+", 30));
		txt_ie.setTextFormatter(tffh.getTextFieldToUpperFormatter("[0-9]+", 30));
	}

	public void limpaBuscas() {
		listaCliente.clear();
		tb_pessoa.getItems().clear();
		ap_busca.setVisible(false);
		txt_filtro.setText("");
	}

}
