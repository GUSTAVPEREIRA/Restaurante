package estagio.controller;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.ContasPagarDAO;
import estagio.dao.FornecedorDAO;
import estagio.dao.ParcelaPagarDAO;
import estagio.model.ContasPagar;
import estagio.model.Fornecedor;
import estagio.model.ParcelaPagar;
import estagio.ui.notifications.FXNotification;
import estagio.ui.notifications.FXNotification.NotificationType;
import estagio.view.util.TextFieldFormatterHelper;
import estagio.view.util.Validadores;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ContasPagarController implements Initializable {

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private JFXButton btn_CancelarCP;

	@FXML
	private JFXButton btn_Estornar;

	@FXML
	private JFXButton btn_Filtrar;

	@FXML
	private JFXButton btn_FiltrarT;

	@FXML
	private JFXButton btn_Gerar;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private JFXButton btn_GravarCP;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private JFXButton btn_Novo1;

	@FXML
	private JFXButton btn_Remover;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private JFXButton btn_Sair1;

	@FXML
	private JFXButton btn_sairAbrir;

	@FXML
	private JFXCheckBox cb_aprazo;

	@FXML
	private JFXCheckBox cb_avista;

	@FXML
	private JFXCheckBox cb_bParcial;

	@FXML
	private JFXCheckBox cb_bTotal;

	@FXML
	private JFXCheckBox cb_fornecedor;

	@FXML
	private JFXCheckBox cb_outros;

	@FXML
	private JFXComboBox<Fornecedor> cbb_fornecedores;

	@FXML
	private JFXComboBox<Integer> cbb_parcela;

	@FXML
	private JFXComboBox<String> cbb_status;

	ContasPagar contasPagar = new ContasPagar();

	ContasPagarDAO contasPagarDAO = new ContasPagarDAO();

	String corErro = "-fx-border-color: red;";

	String corNormal = "-fx-border-color:white";

	@FXML
	private ContextMenu ctm_cbFornecedores;

	@FXML
	private ContextMenu ctm_lblBaixarConta;

	@FXML
	private ContextMenu ctm_cbParcela;

	@FXML
	private ContextMenu ctm_descricao;

	@FXML
	private ContextMenu ctm_dias;

	@FXML
	private ContextMenu ctm_status;

	@FXML
	private ContextMenu ctm_statusT;

	@FXML
	private ContextMenu ctm_total;

	@FXML
	private ContextMenu ctm_valorParcial;

	@FXML
	private ContextMenu ctm_valorTotal;

	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	int dias = 30;

	@FXML
	private JFXDatePicker dp_abertura;

	@FXML
	private JFXDatePicker dp_aberturaT;

	@FXML
	private JFXDatePicker dp_dataLancamento;

	@FXML
	private JFXDatePicker dp_dataVencimento;

	@FXML
	private JFXDatePicker dp_vencimento;

	@FXML
	private JFXDatePicker dp_vencimentoT;

	@FXML
	private HBox hbox_Buscar;

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
	@FXML
	private Label lbl_abertura;

	@FXML
	private Label lbl_BaixarConta;

	@FXML
	private Label lbl_aberturaT;

	@FXML
	private Label lbl_dataLancamento;

	@FXML
	private Label lbl_dataVencimento;

	@FXML
	private Label lbl_descricao;

	@FXML
	private Label lbl_dias;

	@FXML
	private Label lbl_listaFornecedores;

	@FXML
	private Label lbl_parcelas;

	@FXML
	private Label lbl_status;

	@FXML
	private Label lbl_TipoCondicao;

	@FXML
	private Label lbl_TipoConta;

	@FXML
	private Label lbl_total;

	@FXML
	private Label lbl_valorParcial;

	@FXML
	private Label lbl_valorTotal;

	@FXML
	private Label lbl_vencimento;

	@FXML
	private Label lbl_vencimentoT;

	List<Fornecedor> listaFornecedor;

	@FXML
	private MenuItem mi_cbFornecedores;
	@FXML
	private MenuItem mi_lblBaixarConta;

	@FXML
	private MenuItem mi_cbParcela;

	@FXML
	private MenuItem mi_descricao;

	@FXML
	private MenuItem mi_dias;

	@FXML
	private MenuItem mi_status;

	@FXML
	private MenuItem mi_statusT;

	@FXML
	private MenuItem mi_total;

	@FXML
	private MenuItem mi_valorParcial;

	@FXML
	private MenuItem mi_valorTotal;

	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	private ObservableList<Integer> obsl_parcelas;
	private ObservableList<String> obsl_status;
	private ObservableList<ParcelaPagar> obslParcelaPagar;
	private ObservableList<ContasPagar> obslContasPagar;
	private ObservableList<Fornecedor> obslFornecedor;

	ParcelaPagar parcelaPagar = new ParcelaPagar();
	ParcelaPagarDAO parcelaPagarDAO = new ParcelaPagarDAO();

	List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
	List<ContasPagar> contassPagar = new ArrayList<ContasPagar>();
	@FXML
	private StackPane sp_contasPagar;

	@FXML
	private Tab tab_baixar;
	@FXML
	private Tab tab_lancar;

	@FXML
	private TableView<ParcelaPagar> tb_baixarContas;
	@FXML
	private TableView<ContasPagar> tb_bContas;

	@FXML
	private TableView<ParcelaPagar> tb_lancarContas;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_abertura;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_aLancamento;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_aValor;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_aVencimento;

	@FXML
	private TableColumn<ContasPagar, String> tc_bAbertura;
	@FXML
	private TableColumn<ContasPagar, String> tc_bCodigo;
	@FXML
	private TableColumn<ContasPagar, String> tc_bDescricao;
	@FXML
	private TableColumn<ContasPagar, String> tc_bParcela;
	@FXML
	private TableColumn<ContasPagar, String> tc_bValor;
	@FXML
	private TableColumn<ContasPagar, String> tc_bVencimento;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_codigo;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_descricao;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_parcela;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_ValorPago;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_valor;
	@FXML
	private TableColumn<ParcelaPagar, String> tc_vencimento;

	@FXML
	private Tooltip ttp_btnBuscar;
	@FXML
	private Tooltip ttp_btnCancelar;
	@FXML
	private Tooltip ttp_btnEstornar;
	@FXML
	private Tooltip ttp_btnFiltrar;
	@FXML
	private Tooltip ttp_btnGerar;
	@FXML
	private Tooltip ttp_btnGravar;
	@FXML
	private Tooltip ttp_btnGravarCP;
	@FXML
	private Tooltip ttp_btnNovo;
	@FXML
	private Tooltip ttp_btnNovo1;
	@FXML
	private Tooltip ttp_btnRemover;
	@FXML
	private Tooltip ttp_btnSair;
	@FXML
	private Tooltip ttp_btnSair1;
	@FXML
	private Tooltip ttp_btnSairAbrir;
	@FXML
	private Tooltip ttp_lblBaixarConta;
	@FXML
	private Tooltip ttp_cbbListaFornecedores;
	@FXML
	private Tooltip ttp_cbbParcelas;
	@FXML
	private Tooltip ttp_lblClienteT;
	@FXML
	private Tooltip ttp_lblClienteT1;
	@FXML
	private Tooltip ttp_lblDataLancamento;
	@FXML
	private Tooltip ttp_lblDataVencimento;
	@FXML
	private Tooltip ttp_lblDescricao;
	@FXML
	private Tooltip ttp_lblDias;
	@FXML
	private Tooltip ttp_lblStatus;
	@FXML
	private Tooltip ttp_lblStatusT;
	@FXML
	private Tooltip ttp_lblTipoCondicao;
	@FXML
	private Tooltip ttp_lblTipoConta;
	@FXML
	private Tooltip ttp_lblValorTotal;
	@FXML
	private Tooltip ttp_lblVencimentoT;
	@FXML
	private Tooltip ttp_lblVencimentoT1;
	@FXML
	private Tooltip ttp_status;
	@FXML
	private Tooltip ttp_statusT;

	@FXML
	private JFXTextField txt_descricao;
	@FXML
	private JFXTextField txt_dias;
	@FXML
	private JFXTextField txt_total;
	@FXML
	private JFXTextField txt_valorParcial;
	@FXML
	private JFXTextField txt_valorTotal;
	Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	ButtonType btnSim = new ButtonType("Sim");
	ButtonType btnNao = new ButtonType("Não");

	public Date adicionarDias(Date data, int soma) {
		LocalDate localData = data.toLocalDate();
		localData = localData.plusDays(soma);
		return Date.valueOf(localData);
	}

	public void carregaTelaLancar() {
		tb_lancarContas.getItems().clear();
		tc_aVencimento.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
		tc_aLancamento.setCellValueFactory(new PropertyValueFactory<>("Abertura"));
		tc_aValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
		tc_aLancamento.setCellValueFactory((data) -> {
			Date temp = data.getValue().getAbertura();
			return new SimpleStringProperty(formato.format(temp));
		});
		tc_aVencimento.setCellValueFactory((data) -> {
			Date temp = data.getValue().getVencimento();
			return new SimpleStringProperty(formato.format(temp));
		});
		tc_aValor.setCellValueFactory((data) -> {
			Double temp = data.getValue().getValor();

			return new SimpleStringProperty(nf.format(temp));
		});

		obslParcelaPagar = FXCollections.observableArrayList(parcelasPagar);
		tb_lancarContas.setItems(obslParcelaPagar);
	}

	public void carregaTelaBaixar(ContasPagar contasPagar) {
		// desativaTela();
		tb_baixarContas.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_descricao.setCellValueFactory(new PropertyValueFactory<>("Status"));
		tc_abertura.setCellValueFactory(new PropertyValueFactory<>("Abertura"));
		tc_vencimento.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
		tc_parcela.setCellValueFactory(new PropertyValueFactory<>("NumeroParcela"));
		tc_ValorPago.setCellValueFactory(new PropertyValueFactory<>("ValorPgto"));
		tc_valor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
		tc_abertura.setCellValueFactory((data) -> {
			Date temp = data.getValue().getAbertura();
			return new SimpleStringProperty(formato.format(temp));
		});

		tb_baixarContas.setRowFactory((row) -> {

			return new TableRow<ParcelaPagar>() {
				@Override
				public void updateItem(ParcelaPagar item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setStyle("-fx-background-color: none;");
					} else if (item.getStatus().equals("FECHADO") == true && item.getPgto() != null) {
						setStyle("-fx-background-color: #00A279;");
					} else if (!item.getVencimento().after(Date.valueOf(LocalDate.now()))) {
						setStyle("-fx-background-color: #FF211B;");
					} else {
						setStyle("-fx-background-color: none;");
					}
				}
			};
		});
		tc_vencimento.setCellValueFactory((data) -> {

			Date temp = data.getValue().getVencimento();
			return new SimpleStringProperty(formato.format(temp));
		});
		tc_ValorPago.setCellValueFactory((data) -> {
			Double temp = data.getValue().getValorPgto();
			return new SimpleStringProperty(nf.format(temp));
		});
		tc_valor.setCellValueFactory((data) -> {
			Double temp = data.getValue().getValor();
			return new SimpleStringProperty(nf.format(temp));
		});

		String dataAbertura = "", dataVencimento = "";
		Date DataVencimentoT = null, DataAberturaT = null;
		if (dp_abertura.getValue() != null) {
			dataAbertura = dp_abertura.getValue().toString();
			DataAberturaT = Date.valueOf(dataAbertura);
		}

		if (dp_vencimento.getValue() != null) {
			dataVencimento = dp_vencimento.getValue().toString();
			DataVencimentoT = Date.valueOf(dataVencimento);
		}
		parcelasPagar = parcelaPagarDAO.listaParcelaPagar(contasPagar, DataAberturaT, DataVencimentoT,
				cbb_status.getSelectionModel().getSelectedItem());
		Double valortotal = 0.00;
		btn_Remover.setDisable(false);
		if (!parcelasPagar.isEmpty()) {
			for (ParcelaPagar pp : parcelasPagar) {
				valortotal = valortotal + (pp.getValor() - pp.getValorPgto());
				if (pp.getPgto() != null) {
					btn_Remover.setDisable(true);
				}
			}
			if (valortotal < 0) {
				valortotal = 0.0;
			}
			txt_total.setText(nf.format(valortotal));
		}
		if (!parcelasPagar.isEmpty()) {
			obslParcelaPagar = FXCollections.observableArrayList(parcelasPagar);
			tb_baixarContas.setItems(obslParcelaPagar);
		}
	}

	public void carregaTelaBuscar(Date dataAbertura, Date dataVencimento) {
		tb_bContas.getItems().clear();
		tc_bCodigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_bDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
		tc_bParcela.setCellValueFactory(new PropertyValueFactory<>("NumParcelas"));
		tc_bAbertura.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
		tc_bVencimento.setCellValueFactory(new PropertyValueFactory<>("Abertura"));
		tc_bValor.setCellValueFactory(new PropertyValueFactory<>("ValorTotal"));
		tc_bAbertura.setCellValueFactory((data) -> {
			Date temp = data.getValue().getAbertura();
			return new SimpleStringProperty(formato.format(temp));
		});
		tc_bVencimento.setCellValueFactory((data) -> {
			Date temp = data.getValue().getVencimento();
			return new SimpleStringProperty(formato.format(temp));
		});
		tc_bValor.setCellValueFactory((data) -> {
			Double temp = data.getValue().getValorTotal();

			return new SimpleStringProperty(nf.format(temp));
		});
		contassPagar = contasPagarDAO.listaContasPagar(dataAbertura, dataVencimento);
		if (!contassPagar.isEmpty()) {
			obslContasPagar = FXCollections.observableArrayList(contassPagar);
			tb_bContas.setItems(obslContasPagar);
		}

	}

	public boolean comparaData(Date lancamento, Date vencimento) {
		return vencimento.after(lancamento);
	}

	public void desativaTela() {
		if (tab_lancar.isSelected() == true) {
			// Lançar contas a pagar
			// Estilos
			lbl_TipoConta.setStyle(corNormal);
			lbl_descricao.setStyle(corNormal);
			lbl_TipoCondicao.setStyle(corNormal);
			lbl_valorTotal.setStyle(corNormal);
			lbl_dataVencimento.setStyle(corNormal);
			lbl_listaFornecedores.setStyle(corNormal);
			// TextFields
			dp_dataLancamento.setValue(LocalDate.now());
			txt_descricao.setText("");
			txt_dias.setText("");
			txt_valorTotal.setText("");
			// Data
			dp_dataVencimento.setValue(null);
			// Check box
			cb_aprazo.setSelected(false);
			cb_avista.setSelected(false);
			cb_fornecedor.setSelected(false);
			cb_outros.setSelected(false);
			// Combo box
			cbb_parcela.setDisable(true);
			cbb_fornecedores.setDisable(true);
			cbb_fornecedores.getItems().clear();
			cbb_parcela.getSelectionModel().select(0);
			// Buttons
			btn_GravarCP.setDisable(true);
			btn_CancelarCP.setDisable(true);
			// Table
			tb_lancarContas.getItems().clear();
		} else {
			// Baixar contas a pagar
			// Check box
			cbb_status.getSelectionModel().select(null);
			// Buttons
			btn_Gravar.setDisable(true);
			btn_Estornar.setDisable(true);
			btn_Remover.setDisable(true);
			btn_Cancelar.setDisable(true);
			// TextFields
			dp_abertura.setValue(null);
			dp_vencimento.setValue(null);
			txt_valorParcial.setDisable(true);
			txt_valorParcial.setText("");
			txt_total.setText("");
			// Check box
			cb_bTotal.setSelected(false);
			cb_bParcial.setSelected(false);
			// Table view
			tb_baixarContas.getItems().clear();
			// Combobox
			cbb_status.getSelectionModel().select(-1);

		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		contasPagar = new ContasPagar();

		// Lançar contas
		obsl_parcelas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbb_parcela.setItems(obsl_parcelas);
		cbb_parcela.getSelectionModel().select(0);
		txt_valorTotal.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorParcial.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_descricao.setTextFormatter(//
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_total.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_dias.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 3));

		// Baixar contas
		obsl_status = FXCollections.observableArrayList("ABERTO", "FECHADO", "AMBOS");
		cbb_status.setItems(obsl_status);
		txt_total.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));

	}

	@FXML
	void OnActionAPrazo(ActionEvent event) {
		if (cb_aprazo.isSelected() == true) {
			cbb_parcela.setDisable(false);

		} else
			cbb_parcela.setDisable(true);

		if (cb_avista.isSelected() == true) {
			cb_avista.setSelected(false);
		}
	}

	@FXML
	void OnActionAVista(ActionEvent event) {
		if (cb_avista.isSelected() == true) {
			cbb_parcela.setDisable(true);
			cbb_parcela.getSelectionModel().select(0);
		}
		if (cb_aprazo.isSelected() == true) {
			cb_aprazo.setSelected(false);
		}
	}

	@FXML
	void OnActionBuscar(ActionEvent event) {
		carregaTelaBuscar(null, null);
		hbox_Buscar.setVisible(true);
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionEstornar(ActionEvent event) {
		if (parcelaPagar.getPgto() != null || parcelaPagar.getIdRef() != null) {

			dialogoExe.setTitle("Estornar");
			dialogoExe.setHeaderText("Você deseja realmente estornar ?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
			dialogoExe.showAndWait().ifPresent(b -> {
				if (b == btnSim) {
					if (parcelaPagar != null) {
						ParcelaPagar aux = new ParcelaPagar();
						if (parcelaPagar.getIdRef() != null) {
							if (parcelaPagar.getStatus().equals("FECHADO") != true || valorRestantePagar() <= 0.01) {
								aux = parcelaPagarDAO.findById(parcelaPagar.getIdRef());
								aux.setValorPgto(0.00);
								aux.setPgto(null);
								aux.setStatus("ABERTO");
								parcelaPagarDAO.delete(parcelaPagar);
								parcelaPagarDAO.merge(aux);
							}
						} else {
							if (valorRestantePagar() <= 0.01) {
								parcelaPagar.setPgto(null);
								parcelaPagar.setStatus("ABERTO");
								parcelaPagar.setValorPgto(0.00);
								parcelaPagarDAO.merge(parcelaPagar);
							}
						}
						desativaTela();
						carregaTelaBaixar(contasPagar);
						FXNotification fxn;
						fxn = new FXNotification("Parcela Estornada.", FXNotification.NotificationType.WARNING);
						fxn.show();
					}
				}
			});
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Dados inválidos", FXNotification.NotificationType.ERROR);
			fxn.show();
		}
	}

	@FXML
	void OnActionExcluir(ActionEvent event) {

		if (contasPagar != null) {

			try {
				dialogoExe.setTitle("Remover");
				dialogoExe.setHeaderText("Você deseja realmente remover está conta ?");
				dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
				dialogoExe.showAndWait().ifPresent(b -> {
					if (b == btnSim) {
						FXNotification fxn;
						parcelaPagarDAO.deletaParcelas(contasPagar);
						contasPagarDAO.delete(contasPagar);

						fxn = new FXNotification("A Conta no valor total de: " + txt_total.getText() + " foi removida",
								NotificationType.WARNING);
						fxn.show();
						desativaTela();

					}
				});
			} catch (Exception e) {
				FXNotification fxn2;
				fxn2 = new FXNotification("Conta não pode ser removida", NotificationType.ERROR);
				fxn2.show();
			}
		}
	}

	@FXML
	void OnActionFiltrar(ActionEvent event) {
		if (!tb_baixarContas.getItems().isEmpty()) {
			carregaTelaBaixar(contasPagar);
		}

	}

	@FXML
	void OnActionFiltrarT(ActionEvent event) {
		String dataAbertura = "", dataVencimento = "";
		Date dataVencimentoT = null, DataAberturaT = null;
		if (dp_aberturaT.getValue() != null) {
			dataAbertura = dp_aberturaT.getValue().toString();
			DataAberturaT = Date.valueOf(dataAbertura);
		}

		if (dp_vencimentoT.getValue() != null) {
			dataVencimento = dp_vencimentoT.getValue().toString();
			dataVencimentoT = Date.valueOf(dataVencimento);
		}
		carregaTelaBuscar(DataAberturaT, dataVencimentoT);
	}

	@FXML
	void OnActionFornecedor(ActionEvent event) {
		if (cb_fornecedor.isSelected() == true) {
			cb_outros.setSelected(false);
			cbb_fornecedores.setDisable(false);
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			cbb_fornecedores.getItems().clear();
			obslFornecedor = FXCollections.observableArrayList(fornecedorDAO.listar(""));
			cbb_fornecedores.setItems(obslFornecedor);

		} else {
			cbb_fornecedores.setDisable(true);
		}
		if (cb_outros.isSelected() == true) {
			cb_outros.setSelected(false);
			cbb_fornecedores.setDisable(false);
		}
	}

	@FXML
	void OnActionGerar(ActionEvent event) {
		contasPagar = new ContasPagar();
		boolean erro = false;
		if (txt_descricao.getText().replace(" ", "").length() < 3) {
			erro = true;
			ctm_descricao.show(txt_descricao, Side.TOP, 10, 0);
			txt_descricao.setStyle(corErro);
		} else {
			txt_descricao.setStyle(corNormal);
			ctm_descricao.hide();
			contasPagar.setDescricao(txt_descricao.getText());
		}

		if (txt_valorTotal.getText().replace(" ", "").length() < 1
				|| Validadores.valorMonetario(txt_valorTotal.getText()) < 0) {
			erro = true;
			ctm_valorTotal.show(txt_valorTotal, Side.TOP, 10, 0);
			txt_valorTotal.setStyle(corErro);
		} else {
			txt_valorTotal.setStyle(corNormal);
			ctm_valorTotal.hide();
			contasPagar.setValorTotal(Validadores.valorMonetario(txt_valorTotal.getText()));
		}

		if (cb_aprazo.isSelected() == false && cb_avista.isSelected() == false) {
			erro = true;
			lbl_TipoCondicao.setStyle(corErro);
			// falta colocar um context menu lá no javafx
		} else {
			lbl_TipoCondicao.setStyle(corNormal);
			String condicao;
			if (cb_aprazo.isSelected() == true) {
				condicao = "A PRAZO";
			} else {
				condicao = "A VISTA";
			}
			contasPagar.setCondicaoPgto(condicao);
			contasPagar.setNumParcelas(cbb_parcela.getValue());
		}

		if (cb_fornecedor.isSelected() == false && cb_outros.isSelected() == false) {
			erro = true;
			lbl_TipoConta.setStyle(corErro);

		} else {
			if (cb_fornecedor.isSelected() == true && cbb_fornecedores.getSelectionModel().getSelectedItem() == null) {
				erro = true;
				lbl_TipoConta.setStyle(corErro);
				cbb_fornecedores.setStyle(corErro);
			} else {
				lbl_TipoConta.setStyle(corNormal);
				cbb_fornecedores.setStyle(corNormal);
				if (cb_fornecedor.isSelected() == true) {
					cbb_fornecedores.setStyle(corNormal);
					contasPagar.setFornecedor(cbb_fornecedores.getSelectionModel().getSelectedItem());
				}
			}
		}

		contasPagar.setAbertura(Date.valueOf(dp_dataLancamento.getValue()));
		if (dp_dataVencimento.getValue() == null
				|| comparaData(contasPagar.getAbertura(), Date.valueOf(dp_dataVencimento.getValue())) == false) {
			erro = true;
			dp_dataVencimento.setStyle(corErro);
		} else {
			dp_dataVencimento.setStyle(corNormal);
			contasPagar.setVencimento(Date.valueOf(dp_dataVencimento.getValue()));
		}
		if (txt_dias.getText().replace(" ", "").length() > 0) {
			dias = Integer.parseInt(txt_dias.getText());
		} else
			dias = 30;

		contasPagar.setStatus("ABERTO");
		if (erro == false) {
			parcelasPagar = new ArrayList<ParcelaPagar>();
			Double auxValor = contasPagar.getValorTotal() / contasPagar.getNumParcelas();

			for (int i = 0; i < contasPagar.getNumParcelas(); i++) {
				Date auxAbertura = contasPagar.getAbertura();
				Date auxVencimento = contasPagar.getVencimento();

				auxAbertura = adicionarDias(auxAbertura, i * dias);
				auxVencimento = adicionarDias(auxVencimento, i * dias);

				parcelaPagar = new ParcelaPagar();
				parcelaPagar.setNumeroParcela(i + 1);
				parcelaPagar.setContasPagar(contasPagar);
				parcelaPagar.setAbertura(auxAbertura);
				parcelaPagar.setVencimento(auxVencimento);
				parcelaPagar.setValor(auxValor);
				parcelaPagar.setPgto(null);
				parcelaPagar.setStatus("ABERTO");
				parcelaPagar.setValorPgto(0.00);

				parcelasPagar.add(parcelaPagar);
			}
			btn_GravarCP.setDisable(false);
			btn_CancelarCP.setDisable(false);
			carregaTelaLancar();
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Por favor, corrija os erros.", FXNotification.NotificationType.ERROR);
			fxn.show();
		}

	}

	public double valorRestantePagar() {
		if (parcelaPagar != null)
			return (parcelaPagar.getValor() - parcelaPagar.getValorPgto());
		else
			return 0;
	}

	@FXML
	void OnActionGravar(ActionEvent event) {

		Double ValorPgto = Validadores.valorMonetario(txt_valorParcial.getText());

		if (parcelaPagar.getStatus().equals("FECHADO") != true && valorRestantePagar() > 0.01
				&& ValorPgto <= valorRestantePagar() + 0.01) {
			txt_valorParcial.setStyle(corNormal);
			ctm_valorParcial.hide();

			if (cb_bTotal.isSelected() || cb_bParcial.isSelected()) {
				lbl_BaixarConta.setStyle(corNormal);
				ctm_lblBaixarConta.hide();

				parcelaPagar.setValorPgto(parcelaPagar.getValorPgto() + ValorPgto);

				parcelaPagar.setPgto(Date.valueOf(LocalDate.now()));
				parcelaPagar.setStatus("FECHADO");
				parcelaPagarDAO.merge(parcelaPagar);
				if (valorRestantePagar() > 0.01) {
					parcelaPagar.setValor(parcelaPagar.getValor() - parcelaPagar.getValorPgto());
					parcelaPagar.setValorPgto(0.0);
					parcelaPagar.setStatus("ABERTO");
					parcelaPagar.setIdRef(parcelaPagar.getId());
					parcelaPagar.setId(null);
					parcelaPagarDAO.save(parcelaPagar);

				}
				desativaTela();
				carregaTelaBaixar(contasPagar);
				FXNotification fxn;
				fxn = new FXNotification(
						"Parcela paga: " + parcelaPagar.getNumeroParcela() + " no valor de: " + nf.format(ValorPgto)
								+ " Restando: " + nf.format(valorRestantePagar()),
						FXNotification.NotificationType.INFORMATION);
				fxn.show();

			} else {
				ctm_lblBaixarConta.show(lbl_BaixarConta, Side.RIGHT, 10, 0);
				lbl_BaixarConta.setStyle(corErro);

			}
		} else {
			if (parcelaPagar.getStatus().equals("FECHADO") == true) {
				FXNotification fxn;
				fxn = new FXNotification("Parcela já paga.", FXNotification.NotificationType.ERROR);
				fxn.show();
			} else {
				ctm_valorParcial.show(txt_valorParcial, Side.RIGHT, 10, 0);
				txt_valorParcial.setStyle(corErro);
			}

		}
	}

	@FXML
	void OnActionGravarCP(ActionEvent event) {
		contasPagarDAO.save(contasPagar);
		for (ParcelaPagar pp : parcelasPagar) {
			parcelaPagarDAO.save(pp);
		}
		FXNotification fxn;
		fxn = new FXNotification("Lançamento de conta gerado no dia: " + formato.format(contasPagar.getAbertura())
				+ " no valor de: " + nf.format(contasPagar.getValorTotal()),
				FXNotification.NotificationType.INFORMATION);
		fxn.show();
		desativaTela();
	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionOutros(ActionEvent event) {
		if (cb_outros.isSelected() == true) {
			cb_fornecedor.setSelected(false);
			cbb_fornecedores.setDisable(true);

		}
		if (cb_fornecedor.isSelected() == true) {
			cb_fornecedor.setSelected(false);
			cbb_fornecedores.setDisable(true);
		}
	}

	@FXML
	void OnActionBTotal(ActionEvent event) {
		if (cb_bTotal.isSelected() == true) {
			txt_valorParcial.setDisable(true);
			txt_valorParcial.setText(nf.format(valorRestantePagar()));

		}
		if (cb_bParcial.isSelected() == true) {
			cb_bParcial.setSelected(false);
			txt_valorParcial.setDisable(true);
		}
	}

	@FXML
	void OnActionBParcial(ActionEvent event) {
		if (cb_bParcial.isSelected() == true) {
			txt_valorParcial.setDisable(false);

		} else {
			txt_valorParcial.setDisable(true);
		}
		if (cb_bTotal.isSelected() == true) {
			cb_bTotal.setSelected(false);
		}
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_contasPagar.setVisible(false);
	}

	@FXML
	void OnActionSairAbrir(ActionEvent event) {
		hbox_Buscar.setVisible(false);
	}

	@FXML
	void OnMouseClickedSelecionaConta(MouseEvent event) {
		if (tb_bContas.getSelectionModel().getSelectedItem() != null) {
			this.setConta(tb_bContas.getSelectionModel().getSelectedItem());
			hbox_Buscar.setVisible(false);
			dp_aberturaT.setValue(null);
			dp_vencimentoT.setValue(null);
			tb_bContas.getItems().clear();
		}
	}

	public void setConta(ContasPagar contasPagar) {

		this.contasPagar = contasPagar;
		parcelasPagar = new ArrayList<ParcelaPagar>();
		desativaTela();
		carregaTelaBaixar(contasPagar);

	}

	@FXML
	void OnMouseClickedCaixa(MouseEvent event) {

	}

	@FXML
	void OnMouseClickedParcela(MouseEvent event) {

	}

	@FXML
	void OnMouseClickedBaixar(MouseEvent event) {
		if (tb_baixarContas.getSelectionModel().getSelectedItem() != null) {
			this.setParcela(tb_baixarContas.getSelectionModel().getSelectedItem());
		}
	}

	public void setParcela(ParcelaPagar parcelaPagar) {
		this.parcelaPagar = new ParcelaPagar();
		this.parcelaPagar = parcelaPagar;
		txt_valorParcial.setText(nf.format(parcelaPagar.getValor() - parcelaPagar.getValorPgto()));
		if ((valorRestantePagar()) >= 0.01) {
			btn_Gravar.setDisable(false);
			btn_Estornar.setDisable(true);
		}
		if (parcelaPagar.getStatus().equals("FECHADO") == true) {
			btn_Gravar.setDisable(true);
			if (valorRestantePagar() <= 0.01) {
				btn_Estornar.setDisable(false);
			}
		} else if (parcelaPagar.getValorPgto() == 0.00 && parcelaPagar.getIdRef() != null) {
			btn_Gravar.setDisable(false);
			btn_Estornar.setDisable(false);
		}

	}

	@FXML
	void OnMouseSelectionUf(ActionEvent event) {

	}
}
