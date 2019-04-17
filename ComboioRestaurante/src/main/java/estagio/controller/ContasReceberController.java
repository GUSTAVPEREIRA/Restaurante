package estagio.controller;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CaixaDAO;
import estagio.dao.ContasReceberDAO;
import estagio.dao.ParcelaReceberDAO;
import estagio.model.Caixa;
import estagio.model.ContasReceber;
import estagio.model.ParcelaReceber;
import estagio.model.Usuario;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ContasReceberController implements Initializable {

	@FXML
	private Label lbl_abertura;

	@FXML
	private Tooltip ttp_lblClienteT1;

	@FXML
	private JFXDatePicker dp_abertura;

	@FXML
	private Label lbl_vencimento;

	@FXML
	private Tooltip ttp_lblVencimentoT1;

	@FXML
	private JFXDatePicker dp_vencimento;

	@FXML
	private Label lbl_status;

	@FXML
	private Tooltip ttp_lblStatus;

	@FXML
	private JFXComboBox<String> cbb_status;

	@FXML
	private Tooltip ttp_status;

	@FXML
	private ContextMenu ctm_status;

	@FXML
	private MenuItem mi_status;

	@FXML
	private JFXButton btn_Filtrar;

	@FXML
	private Tooltip ttp_btnFiltrar;

	@FXML
	private JFXButton btn_Buscar;

	@FXML
	private Tooltip ttp_btnBuscar;

	@FXML
	private TableView<ParcelaReceber> tb_baixarContas;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_codigo;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_descricao;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_abertura;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_vencimento;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_parcela;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_ValorPago;

	@FXML
	private TableColumn<ParcelaReceber, String> tc_valor;

	@FXML
	private Label lbl_BaixarConta;

	@FXML
	private Tooltip ttp_lblBaixarConta;

	@FXML
	private ContextMenu ctm_lblBaixarConta;

	@FXML
	private MenuItem mi_lblBaixarConta;

	@FXML
	private Label lbl_total;

	@FXML
	private JFXTextField txt_total;

	@FXML
	private ContextMenu ctm_total;

	@FXML
	private MenuItem mi_total;

	@FXML
	private Label lbl_BaixarConta1;

	@FXML
	private Tooltip ttp_lblBaixarConta1;

	@FXML
	private ContextMenu ctm_lblBaixarConta1;

	@FXML
	private MenuItem mi_lblBaixarConta1;

	@FXML
	private JFXCheckBox cb_bTotal;

	@FXML
	private JFXCheckBox cb_bParcial;

	@FXML
	private Label lbl_valorParcial;

	@FXML
	private JFXTextField txt_valorParcial;

	@FXML
	private ContextMenu ctm_valorParcial;

	@FXML
	private MenuItem mi_valorParcial;

	@FXML
	private JFXCheckBox cb_bCredito;

	@FXML
	private JFXCheckBox cb_bDebito;

	@FXML
	private JFXCheckBox cb_bDinheiro;

	@FXML
	private JFXCheckBox cb_bCheque;

	@FXML
	private JFXButton btn_Novo1;

	@FXML
	private Tooltip ttp_btnNovo1;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private JFXButton btn_Estornar;

	@FXML
	private Tooltip ttp_btnEstornar;

	@FXML
	private JFXButton btn_Remover;

	@FXML
	private Tooltip ttp_btnRemover;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnCancelar;

	@FXML
	private StackPane sp_contasReceber;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	private HBox hbox_Buscar;

	@FXML
	private Label lbl_aberturaT;

	@FXML
	private Tooltip ttp_lblClienteT;

	@FXML
	private JFXDatePicker dp_aberturaT;

	@FXML
	private Label lbl_vencimentoT;

	@FXML
	private Tooltip ttp_lblVencimentoT;

	@FXML
	private JFXDatePicker dp_vencimentoT;

	@FXML
	private JFXButton btn_FiltrarT;

	@FXML
	private TableView<ContasReceber> tb_bContas;

	@FXML
	private TableColumn<ContasReceber, String> tc_bCodigo;

	@FXML
	private TableColumn<ContasReceber, String> tc_bDescricao;

	@FXML
	private TableColumn<ContasReceber, String> tc_bParcela;

	@FXML
	private TableColumn<ContasReceber, String> tc_bAbertura;

	@FXML
	private TableColumn<ContasReceber, String> tc_bVencimento;

	@FXML
	private TableColumn<ContasReceber, String> tc_bValor;

	@FXML
	private JFXButton btn_sairAbrir;

	@FXML
	private Tooltip ttp_btnSairAbrir;
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	private ObservableList<String> obsl_status;
	private ObservableList<ParcelaReceber> obslParcelaReceber;
	private ObservableList<ContasReceber> obslContasReceber;
	// private ObservableList<Cliente> obslCliente;
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
	ParcelaReceber parcelaReceber = new ParcelaReceber();
	ContasReceber contasReceber = new ContasReceber();
	ParcelaReceberDAO parcelaReceberDAO = new ParcelaReceberDAO();
	ContasReceberDAO contasReceberDAO = new ContasReceberDAO();
	List<ParcelaReceber> parcelasReceber = new ArrayList<ParcelaReceber>();
	List<ContasReceber> contassReceber = new ArrayList<ContasReceber>();
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	ButtonType btnSim = new ButtonType("Sim");
	ButtonType btnNao = new ButtonType("Não");

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

	public double valorRestantePagar() {
		if (parcelaReceber != null)
			return (parcelaReceber.getValor() - parcelaReceber.getValorPgto());
		else
			return 0;
	}

	@FXML
	void OnActionBuscar(ActionEvent event) {
		carregaTelaBuscar(null, null);
		hbox_Buscar.setVisible(true);
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
		contassReceber = contasReceberDAO.listaContasReceber(dataAbertura, dataVencimento);
		if (!contassReceber.isEmpty()) {
			obslContasReceber = FXCollections.observableArrayList(contassReceber);
			tb_bContas.setItems(obslContasReceber);
		}

	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		desativaTela();
	}

	public void desativaTela() {

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

	@FXML
	void OnActionEstornar(ActionEvent event) {
		if (parcelaReceber.getPgto() != null || parcelaReceber.getIdRef() != null) {

			dialogoExe.setTitle("Estornar");
			dialogoExe.setHeaderText("Você deseja realmente estornar ?");
			dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
			dialogoExe.showAndWait().ifPresent(b -> {
				if (b == btnSim) {
					if (parcelaReceber != null) {
						ParcelaReceber aux = new ParcelaReceber();
						CaixaDAO caixaDAO = new CaixaDAO();
						Usuario usuario = LoginController.logado;
						Caixa caixa = caixaDAO.listaCaixasVenda(usuario);
						if (parcelaReceber.getCondicao().equals("CREDITO")) {
							caixa.setCredito(caixa.getCredito()-parcelaReceber.getValorPgto());
						}
						else if(parcelaReceber.getCondicao().equals("DEBITO"))
						{
							caixa.setDebito(caixa.getDebito()-parcelaReceber.getValorPgto());
						}
						else if(parcelaReceber.getCondicao().equals("DINHEIRO"))
						{
							caixa.setDinheiro(caixa.getDinheiro()-parcelaReceber.getValorPgto());
						}
						else
						{
							caixa.setCheque(caixa.getCheque()-parcelaReceber.getValorPgto());
						}
						caixaDAO.merge(caixa);
						if (parcelaReceber.getIdRef() != null) {
							if (parcelaReceber.getStatus().equals("FECHADO") != true || valorRestantePagar() <= 0.01) {
								aux = parcelaReceberDAO.findById(parcelaReceber.getIdRef());
							
								aux.setValorPgto(0.00);
								aux.setPgto(null);
								aux.setStatus("ABERTO");
								parcelaReceberDAO.delete(parcelaReceber);
								parcelaReceberDAO.merge(aux);
							}
						} else {
							if (valorRestantePagar() <= 0.01) {
								parcelaReceber.setPgto(null);
								parcelaReceber.setStatus("ABERTO");
								parcelaReceber.setValorPgto(0.00);
								parcelaReceberDAO.merge(parcelaReceber);
							}
						}
						desativaTela();
						carregaTelaBaixar(contasReceber);
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
		if (contasReceber != null) {

			try {
				dialogoExe.setTitle("Remover");
				dialogoExe.setHeaderText("Você deseja realmente remover está conta ?");
				dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
				dialogoExe.showAndWait().ifPresent(b -> {
					if (b == btnSim) {
						FXNotification fxn;
						parcelaReceberDAO.deletaParcelas(contasReceber);
						contasReceberDAO.delete(contasReceber);

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
			carregaTelaBaixar(contasReceber);
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
	void OnActionGravar(ActionEvent event) {

		Double ValorPgto = Validadores.valorMonetario(txt_valorParcial.getText());

		if (parcelaReceber.getStatus().equals("FECHADO") != true && valorRestantePagar() > 0.01
				&& ValorPgto <= valorRestantePagar() + 0.01) {
			txt_valorParcial.setStyle(corNormal);
			ctm_valorParcial.hide();

			if (cb_bTotal.isSelected() || cb_bParcial.isSelected()) {
				lbl_BaixarConta.setStyle(corNormal);
				ctm_lblBaixarConta.hide();

				if (cb_bCredito.isSelected() || cb_bCheque.isSelected() || cb_bDebito.isSelected()
						|| cb_bDinheiro.isSelected()) {
					Usuario usuario = LoginController.logado;
					CaixaDAO caixaDAO = new CaixaDAO();
					Caixa caixa = caixaDAO.listaCaixasVenda(usuario);
					lbl_BaixarConta1.setStyle(corNormal);

					if (cb_bCredito.isSelected()) {
						cb_bCredito.setSelected(false);
						parcelaReceber.setCondicao("CREDITO");
						caixa.setCredito(caixa.getCredito()+ValorPgto);
					} else if (cb_bDebito.isSelected()) {
						cb_bDebito.setSelected(false);
						caixa.setDebito(caixa.getDebito()+ValorPgto);
						parcelaReceber.setCondicao("DEBITO");
					} else if (cb_bDinheiro.isSelected()) {
						cb_bDinheiro.setSelected(false);
						parcelaReceber.setCondicao("DINHEIRO");
						caixa.setDinheiro(caixa.getDinheiro()+ValorPgto);
					} else {
						cb_bCheque.setSelected(false);
						parcelaReceber.setCondicao("CHEQUE");
						caixa.setCheque(caixa.getCheque()+ValorPgto);
					}
					caixaDAO.merge(caixa);
					parcelaReceber.setValorPgto(parcelaReceber.getValorPgto() + ValorPgto);
					parcelaReceber.setPgto(Date.valueOf(LocalDate.now()));
					parcelaReceber.setStatus("FECHADO");
					parcelaReceberDAO.merge(parcelaReceber);
					if (valorRestantePagar() > 0.01) {
						parcelaReceber.setValor(parcelaReceber.getValor() - parcelaReceber.getValorPgto());
						parcelaReceber.setValorPgto(0.0);
						parcelaReceber.setStatus("ABERTO");
						parcelaReceber.setIdRef(parcelaReceber.getId());
						parcelaReceber.setId(null);
						parcelaReceberDAO.save(parcelaReceber);

					}
					desativaTela();
					carregaTelaBaixar(contasReceber);
					FXNotification fxn;
					fxn = new FXNotification(
							"Parcela paga: " + parcelaReceber.getNumeroParcela() + " no valor de: "
									+ nf.format(ValorPgto) + " Restando: " + nf.format(valorRestantePagar()),
							FXNotification.NotificationType.INFORMATION);
					fxn.show();
				} else {
					lbl_BaixarConta1.setStyle(corErro);
					FXNotification fxn;
					fxn = new FXNotification("Informe os dados corretamente.", FXNotification.NotificationType.ERROR);
					fxn.show();
				}

			} else {
				ctm_lblBaixarConta.show(lbl_BaixarConta, Side.RIGHT, 10, 0);
				lbl_BaixarConta.setStyle(corErro);

			}
		} else {
			if (parcelaReceber.getStatus().equals("FECHADO") == true) {
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
	void OnActionNovo(ActionEvent event) {
		desativaTela();
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_contasReceber.setVisible(false);
	}

	@FXML
	void OnActionSairAbrir(ActionEvent event) {
		hbox_Buscar.setVisible(false);
	}

	@FXML
	void OnMouseClickedBaixar(MouseEvent event) {
		if (tb_baixarContas.getSelectionModel().getSelectedItem() != null) {
			this.setParcela(tb_baixarContas.getSelectionModel().getSelectedItem());
		}
	}

	public void setParcela(ParcelaReceber parcelaReceber) {
		this.parcelaReceber = new ParcelaReceber();
		this.parcelaReceber = parcelaReceber;
		txt_valorParcial.setText(nf.format(parcelaReceber.getValor() - parcelaReceber.getValorPgto()));
		if ((valorRestantePagar()) >= 0.01) {
			btn_Gravar.setDisable(false);
			btn_Estornar.setDisable(true);
		}
		if (parcelaReceber.getStatus().equals("FECHADO") == true) {
			btn_Gravar.setDisable(true);
			if (valorRestantePagar() <= 0.01) {
				btn_Estornar.setDisable(false);
			}
		} else if (parcelaReceber.getValorPgto() == 0.00 && parcelaReceber.getIdRef() != null) {
			btn_Gravar.setDisable(false);
			btn_Estornar.setDisable(false);
		}

	}

	public void setConta(ContasReceber contasReceber) {

		this.contasReceber = contasReceber;
		parcelasReceber = new ArrayList<ParcelaReceber>();
		desativaTela();
		carregaTelaBaixar(contasReceber);

	}

	public void carregaTelaBaixar(ContasReceber contasReceber) {
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

			return new TableRow<ParcelaReceber>() {
				@Override
				public void updateItem(ParcelaReceber item, boolean empty) {
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
		parcelasReceber = parcelaReceberDAO.listaParcelaReceber(contasReceber, DataAberturaT, DataVencimentoT,
				cbb_status.getSelectionModel().getSelectedItem());
		Double valortotal = 0.00;
		btn_Remover.setDisable(false);
		if (!parcelasReceber.isEmpty()) {
			for (ParcelaReceber pp : parcelasReceber) {
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
		if (!parcelasReceber.isEmpty()) {
			obslParcelaReceber = FXCollections.observableArrayList(parcelasReceber);
			tb_baixarContas.setItems(obslParcelaReceber);
		}
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

	@FXML
	void OnMouseSelectionUf(ActionEvent event) {

	}

	@FXML
	void OnActionCheque(ActionEvent event) {

		if (cb_bCheque.isSelected() == true) {
			cb_bCredito.setSelected(false);
			cb_bDebito.setSelected(false);
			cb_bDinheiro.setSelected(false);
		}
	}

	@FXML
	void OnActionCredito(ActionEvent event) {
		if (cb_bCredito.isSelected() == true) {
			cb_bCheque.setSelected(false);
			cb_bDebito.setSelected(false);
			cb_bDinheiro.setSelected(false);
		}
	}

	@FXML
	void OnActionDebito(ActionEvent event) {
		if (cb_bDebito.isSelected() == true) {
			cb_bCredito.setSelected(false);
			cb_bCheque.setSelected(false);
			cb_bDinheiro.setSelected(false);
		}
	}

	@FXML
	void OnActionDinheiro(ActionEvent event) {
		if (cb_bDinheiro.isSelected() == true) {
			cb_bCredito.setSelected(false);
			cb_bDebito.setSelected(false);
			cb_bCheque.setSelected(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		desativaTela();
		contasReceber = new ContasReceber();
		// Lançar contas
		txt_valorParcial.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_total.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		// Baixar contas
		obsl_status = FXCollections.observableArrayList("ABERTO", "FECHADO", "AMBOS");
		cbb_status.setItems(obsl_status);
		txt_total.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));

	}

}
