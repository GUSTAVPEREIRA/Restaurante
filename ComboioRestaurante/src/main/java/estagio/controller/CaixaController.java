package estagio.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.Banco;
import estagio.dao.CaixaDAO;
import estagio.dao.Conexao;
import estagio.dao.UsuarioDAO;
import estagio.model.Caixa;
import estagio.model.Usuario;
import estagio.ui.notifications.FXNotification;
import estagio.ui.notifications.FXNotification.NotificationType;
import estagio.view.util.TextFieldFormatterHelper;
import estagio.view.util.Validadores;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class CaixaController implements Initializable {

	@FXML
	private VBox vb_caixaInicial;

	@FXML
	private TableView<Caixa> tb_caixas;

	@FXML
	private TableColumn<Caixa, String> tc_codigo;

	@FXML
	private TableColumn<Caixa, String> tc_nome;

	@FXML
	private TableColumn<Caixa, String> tc_status;

	@FXML
	private TableColumn<Caixa, String> tc_valor;

	@FXML
	private PieChart PC_saldoCaixas;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private Tooltip ttp_btnNovo;
	@FXML
	private ImageView img_viewImage;
	@FXML
	private JFXButton btn_Editar;

	@FXML
	private Tooltip ttp_btnNovo1;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnExcluir1;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	private HBox hb_gerenciamentoCaixa;

	@FXML
	private VBox ap_caixa;

	@FXML
	private Label lbl_valorAtual;

	@FXML
	private StackPane sp_caixa;

	@FXML
	private Tooltip ttp_lblValorAtual;

	@FXML
	private JFXTextField txt_valorAtual;

	@FXML
	private ContextMenu ctm_valorAtual;

	@FXML
	private MenuItem mi_valorAtual;

	@FXML
	private Label lbl_dinheiro;

	@FXML
	private Tooltip ttp_lblDinheiro;

	@FXML
	private JFXTextField txt_dinheiro;

	@FXML
	private ContextMenu ctm_dinheiro;

	@FXML
	private MenuItem mi_dinheiro;

	@FXML
	private Label lbl_cheque;

	@FXML
	private Tooltip ttp_lblCheque;

	@FXML
	private JFXTextField txt_cheque;

	@FXML
	private ContextMenu ctm_cheque;

	@FXML
	private MenuItem mi_cheque;

	@FXML
	private Label lbl_cartaoDebito;

	@FXML
	private Tooltip ttp_lblCartaoDebito;

	@FXML
	private JFXTextField txt_cartaoDebito;

	@FXML
	private ContextMenu ctm_cartaoDebito;

	@FXML
	private MenuItem mi_cartaoDebito;

	@FXML
	private Label lbl_cartaoCredito;

	@FXML
	private Tooltip ttp_lblCartaoCredito;

	@FXML
	private JFXTextField txt_cartaoCredito;

	@FXML
	private ContextMenu ctm_cartaoCredito;

	@FXML
	private MenuItem mi_cartaoCredito;

	@FXML
	private Label lbl_dataAbertura;

	@FXML
	private Tooltip ttp_lblDataAbertura;

	@FXML
	private JFXDatePicker txt_dataAbertura;

	@FXML
	private ContextMenu ctm_dataAbertura;

	@FXML
	private MenuItem mi_dataAbertura;

	@FXML
	private Label lbl_horaAbertura;

	@FXML
	private Tooltip ttp_lblHoraAbertura;

	@FXML
	private JFXTextField txt_horaAbertura;

	@FXML
	private ContextMenu ctm_horaAbertura;

	@FXML
	private MenuItem mi_horaAbertura;

	@FXML
	private Label lbl_dataFechamento;

	@FXML
	private Tooltip ttp_lblDataAbertura1;

	@FXML
	private JFXDatePicker txt_dataFechamento;

	@FXML
	private ContextMenu ctm_dataAbertura1;

	@FXML
	private MenuItem mi_dataAbertura1;

	@FXML
	private Label lbl_horaFechamento;

	@FXML
	private Tooltip ttp_lblHoraAbertura1;

	@FXML
	private JFXTextField txt_horaFechamento;

	@FXML
	private ContextMenu ctm_horaAbertura1;

	@FXML
	private MenuItem mi_horaAbertura1;

	@FXML
	private Label lbl_valorAbertura;

	@FXML
	private Tooltip ttp_lblValorAbertura;

	@FXML
	private JFXTextField txt_valorAbertura;

	@FXML
	private ContextMenu ctm_valorAbertura;

	@FXML
	private MenuItem mi_valorAbertura;
	@FXML
	private VBox vboxRelatorio;

	@FXML
	private HBox hboxJasperMaldito;

	@FXML
	private JFXButton btn_VoltarOnHell;

	@FXML
	private Tooltip ttp_btnVoltar1;
	@FXML
	private JFXButton btn_abrirCaixa;

	@FXML
	private Tooltip ttp_btnAbrirCaixa;

	@FXML
	private JFXButton btn_fecharCaixa;

	@FXML
	private Tooltip ttp_btnFecharCaixa;

	@FXML
	private JFXButton btn_sairAbrir;

	@FXML
	private Tooltip ttp_btnSairAbrir;
	@FXML
	private JFXTextField txt_login;

	@FXML
	private JFXPasswordField txt_senha;

	@FXML
	private JFXButton btn_Confirmar;

	@FXML
	private Tooltip ttp_btnConfirmar;

	@FXML
	private JFXButton btn_Voltar;

	@FXML
	private Tooltip ttp_btnVoltar;

	@FXML
	private HBox hbox_login;
	SwingNode sn = new SwingNode();
	Usuario abertoAutorizado;
	Usuario fechadoAutorizado;
	private CaixaDAO caixaDAO;
	private Caixa caixa;
	String corErro = "-fx-border-color:red;";
	String corNormal = "-fx-border-color:white";
	DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private ObservableList<Caixa> obslCaixa;
	private List<Caixa> listaCaixa;
	private Usuario usuarioAutorizou;
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	@FXML
	void OnActionConfirmar(ActionEvent event) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioAutorizou = usuarioDAO.login(txt_login.getText(), txt_senha.getText());
		if (usuarioAutorizou != null && usuarioAutorizou.getTipo().equals("ADMIN") == true) {
			hbox_login.setVisible(false);
			hb_gerenciamentoCaixa.setVisible(true);
			FXNotification fxn = new FXNotification("Login efetuado", NotificationType.INFORMATION);
			fxn.show();
		} else {
			FXNotification fxn = new FXNotification("Login inválido", NotificationType.ERROR);
			fxn.show();
		}

		txt_senha.setText("");
		txt_login.setText("");
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		hbox_login.setVisible(false);
		ativaTela();
		txt_senha.setText("");
		txt_login.setText("");
		btn_Cancelar.setDisable(true);
		btn_Editar.setDisable(true);
		btn_Sair.setDisable(false);
		btn_Novo.setDisable(false);
		carregaTela();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		abertoAutorizado = new Usuario();
		fechadoAutorizado = new Usuario();
		caixaDAO = new CaixaDAO();
		caixa = new Caixa();
		listaCaixa = new ArrayList<Caixa>();
		carregaTela();
		txt_cartaoCredito.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_cartaoDebito.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_cheque.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_dinheiro.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorAtual.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorAbertura.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_login.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_senha.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));

	}

	public void verificaCaixaAberto() {
		caixaDAO = new CaixaDAO();
		if (caixa != null) {
			txt_cartaoCredito.setText(nf.format(caixa.getCredito()));
			txt_cartaoDebito.setText(nf.format(caixa.getDebito()));
			txt_cheque.setText(nf.format(caixa.getCheque()));
			txt_dinheiro.setText(nf.format(caixa.getDinheiro()));
			txt_valorAtual.setText(nf.format(caixa.somaDinheiro()));
			txt_valorAbertura.setText(nf.format(caixa.getAbertura()));
			txt_dataAbertura.setValue(caixa.getDataAbertura().toLocalDate());
			txt_horaAbertura.setText(caixa.getHoraAbertura().toString());
			if (caixa.getStatus().equals("ABERTO") == true) {
				txt_valorAbertura.setDisable(true);
				btn_abrirCaixa.setDisable(true);
				btn_fecharCaixa.setDisable(false);
			} else {
				txt_valorAbertura.setDisable(false);
				btn_abrirCaixa.setDisable(false);
				btn_fecharCaixa.setDisable(true);
			}

		} else {
			txt_valorAbertura.setDisable(true);
		}
	}

	@FXML
	void OnActionAbrir(ActionEvent event) {
		FXNotification fxn;
		boolean erro = false;
		caixa = new Caixa();
		Double valorAbertura = -1.00;
		if (txt_valorAbertura.getText().equals("") != true) {
			valorAbertura = Validadores.valorMonetario(txt_valorAbertura.getText());
		}
		if (txt_valorAbertura.getText().equals("") == true || valorAbertura < 0) {
			erro = true;
			ctm_valorAbertura.show(txt_valorAbertura, Side.RIGHT, 10, 0);
			txt_valorAbertura.setStyle(corErro);
		} else {
			ctm_valorAbertura.hide();
			txt_valorAbertura.setStyle(corNormal);
		}
		if (erro != true) {

			ctm_valorAbertura.hide();
			erro = false;

			txt_dataAbertura.setValue(LocalDate.now());
			txt_horaAbertura.setText(LocalDateTime.now().format(horaFormat));
			txt_valorAbertura.setStyle(corNormal);
			caixa.setAbertura(valorAbertura);
			caixa.setFechamento(valorAbertura);
			caixa.setCheque(0.00);
			caixa.setCredito(0.00);
			caixa.setDebito(0.00);
			caixa.setDinheiro(0.00);
			caixa.setStatus("ABERTO");
			caixa.setDataAbertura(Date.valueOf(txt_dataAbertura.getValue()));
			caixa.setHoraAbertura(Time.valueOf(txt_horaAbertura.getText()));
			caixa.setUsuario(LoginController.logado);
			caixa.setAberturaAutorizado(usuarioAutorizou);
			btn_abrirCaixa.setDisable(true);
			btn_fecharCaixa.setDisable(false);
			txt_cartaoCredito.setText(nf.format(caixa.getCredito()));
			txt_cartaoDebito.setText(nf.format(caixa.getDebito()));
			txt_cheque.setText(nf.format(caixa.getCheque()));
			txt_dinheiro.setText(nf.format(caixa.getDinheiro()));
			txt_valorAtual.setText((nf.format(caixa.somaDinheiro())));
			txt_valorAbertura.setText(nf.format(caixa.getAbertura()));
			txt_valorAbertura.setDisable(true);

			fxn = new FXNotification(
					"Caixa aberto no dia " + String.valueOf(caixa.getDataAbertura().toLocalDate().format(dataFormat))
							+ "" + " as " + String.valueOf(caixa.getHoraAbertura()),
					FXNotification.NotificationType.INFORMATION);
			fxn.show();
			caixa.setUsuario(LoginController.logado);
			caixaDAO.persist(caixa);
		} else {
			fxn = new FXNotification("Informe os dados corretamente", FXNotification.NotificationType.ERROR);
			fxn.show();
		}
	}

	public void carregaTela() {
		listaCaixa.clear();
		tb_caixas.getItems().clear();

		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tc_nome.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
		tc_valor.setCellValueFactory(new PropertyValueFactory<>("Fechamento"));
		tc_status.setCellValueFactory(new PropertyValueFactory<>("Status"));
		tc_valor.setCellValueFactory((data) -> {
			Double temp = data.getValue().getAbertura() + data.getValue().getCheque() + data.getValue().getCredito()
					+ data.getValue().getDebito() + data.getValue().getDinheiro();
			return new SimpleStringProperty(nf.format(temp));
		});
		listaCaixa = caixaDAO.listaCaixas();
		double dinheiro = 0;
		double cheque = 0;
		double credito = 0;
		double debito = 0;
		double aberto = 0;
		if (!listaCaixa.isEmpty()) {
			obslCaixa = FXCollections.observableArrayList(listaCaixa);
			tb_caixas.setItems(obslCaixa);
			for (int i = 0; i < listaCaixa.size(); i++) {
				dinheiro = dinheiro + listaCaixa.get(i).getDinheiro();
				cheque = cheque + listaCaixa.get(i).getCheque();
				credito = credito + listaCaixa.get(i).getCredito();
				debito = debito + listaCaixa.get(i).getDebito();
				aberto = aberto + listaCaixa.get(i).getAbertura();
			}

			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("Dinheiro:  R$" + String.format("%.2f", dinheiro), dinheiro),
					new PieChart.Data("Cheque  :  R$" + String.format("%.2f", cheque), cheque),
					new PieChart.Data("Credito  :  R$" + String.format("%.2f", credito), credito),
					new PieChart.Data("Debito   :  R$" + String.format("%.2f", debito), debito),
					new PieChart.Data("Abertura:  R$" + String.format("%.2f", aberto), aberto));
			PC_saldoCaixas.setData(pieChartData);
			PC_saldoCaixas.setTitle(
					"Valor total : R$" + String.format("%.2f", (aberto + dinheiro + cheque + credito + debito)));
		}

	}

	@FXML
	void onMousePressedPIE(MouseEvent event) {

	}

	public void ativaTela() {
		carregaTela();
		btn_Editar.setDisable(false);
		btn_Novo.setDisable(false);
		btn_Sair.setDisable(false);
		vb_caixaInicial.setDisable(false);
	}

	public void desativaTela() {

		txt_cartaoCredito.setText("");
		txt_cartaoDebito.setText("");
		txt_cheque.setText("");
		txt_dinheiro.setText("");
		txt_valorAtual.setText("");
		txt_valorAbertura.setText("R$ 0.00");
		txt_valorAbertura.setDisable(false);
		txt_dataAbertura.setValue(null);
		txt_horaAbertura.setText("");
		txt_dataFechamento.setValue(null);
		txt_horaFechamento.setText("");
		btn_Cancelar.setDisable(true);
		btn_Editar.setDisable(true);
	}

	@FXML
	void OnActionFechar(ActionEvent event) {
		txt_cartaoCredito.setText("");
		txt_cartaoDebito.setText("");
		txt_cheque.setText("");
		txt_dinheiro.setText("");
		txt_valorAtual.setText("");
		txt_valorAbertura.setText("R$ 0.00");
		txt_valorAbertura.setDisable(false);
		txt_dataAbertura.setValue(null);
		txt_horaAbertura.setText("");
		FXNotification fxn;
		caixa.setDataFechamento(Date.valueOf(LocalDate.now()));
		caixa.setHoraFechamento(Time.valueOf(LocalDateTime.now().format(horaFormat)));
		caixa.setStatus("FECHADO");
		caixa.setFechamentoAutorizado(usuarioAutorizou);
		fxn = new FXNotification("Caixa fechado no dia "
				+ String.valueOf(caixa.getDataFechamento().toLocalDate().format(dataFormat)) + "" + " as "
				+ String.valueOf(
						caixa.getHoraFechamento() + "\n Com o valor total de:" + nf.format(caixa.somaDinheiro())),
				FXNotification.NotificationType.INFORMATION);
		fxn.show();
		caixaDAO.merge(caixa);
		btn_fecharCaixa.setDisable(true);
		btn_abrirCaixa.setDisable(false);

	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		// hb_gerenciamentoCaixa.setVisible(true);
		hbox_login.setVisible(true);
		vb_caixaInicial.setDisable(true);
		txt_valorAbertura.setDisable(false);
		btn_abrirCaixa.setDisable(false);
		btn_fecharCaixa.setDisable(true);
		desativaTela();
	}

	@FXML
	void OnActionSairAbrir(ActionEvent event) {
		hb_gerenciamentoCaixa.setVisible(false);
		vb_caixaInicial.setDisable(false);
		carregaTela();
		btn_Cancelar.setDisable(true);
		btn_Editar.setDisable(true);
	}

	@FXML
	void OnMouseClickedCaixa(MouseEvent event) {
		if (tb_caixas.getSelectionModel().getSelectedItem() != null) {
			this.setCaixa(tb_caixas.getSelectionModel().getSelectedItem());
		}
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
		txt_dataAbertura.setValue(caixa.getDataAbertura().toLocalDate());
		txt_horaAbertura.setText(caixa.getHoraAbertura().toString());
		if (caixa.getStatus().equals("fechado") == true) {
			txt_dataFechamento.setValue(caixa.getDataFechamento().toLocalDate());
			txt_horaFechamento.setText(caixa.getHoraFechamento().toString());
			txt_valorAbertura.setDisable(false);
		} else {
			txt_valorAbertura.setDisable(true);
		}
		txt_valorAbertura.setStyle(corNormal);
		txt_cartaoCredito.setText(nf.format(caixa.getCredito()));
		txt_cartaoDebito.setText(nf.format(caixa.getDebito()));
		txt_cheque.setText(nf.format(caixa.getCheque()));
		txt_dinheiro.setText(nf.format(caixa.getDinheiro()));
		txt_valorAtual.setText(nf.format(caixa.getFechamento()));
		txt_valorAbertura.setText(nf.format(caixa.getAbertura()));
		btn_Editar.setDisable(false);
		btn_Cancelar.setDisable(false);
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_caixa.setVisible(false);
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		btn_Cancelar.setDisable(true);
		btn_Editar.setDisable(true);
	}

	@FXML
	void OnActionEditar(ActionEvent event) {
		btn_Editar.setDisable(true);
		vb_caixaInicial.setDisable(true);
		hbox_login.setVisible(true);
		verificaCaixaAberto();

	}


	@FXML
	void onActionClickViewCaixa(MouseEvent event) throws JRException {

		new FXNotification("Gerando relatório, aguarde alguns segundos.", FXNotification.NotificationType.INFORMATION)
				.show();

		Task<JRViewer> task = new Task<JRViewer>() {
			@Override
			public JRViewer call() {
				try {
					Banco.getCon();
					Connection con = Conexao.abre();
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("id", caixa.getId());
					InputStream jasper1 = getClass().getResourceAsStream("/estagio/relatorios/CaixaReports.jasper");
					JasperPrint jp = JasperFillManager.fillReport(jasper1, parameters, con);
					JRViewer jr = new JRViewer(jp);

					return jr;
				} catch (Exception e) {
					Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Warning: an error ocurred when ",
							e);

				}
				return null;
			}
		};
		
		task.setOnSucceeded((event1) -> {
			Platform.runLater(() -> {
				try {
					vboxRelatorio.setVisible(true);
					HBox.setHgrow(sn, Priority.ALWAYS);
					sn.setContent(task.get());
					hboxJasperMaldito.getChildren().add(sn);
				} catch (InterruptedException | ExecutionException e) {
					Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error: failed to load ", e);
				}
			});
		});

		new Thread(task).start();

	}

	@FXML
	void OnActionVoltarOnHell(ActionEvent event) {
		vboxRelatorio.setVisible(false);
		hboxJasperMaldito.getChildren().remove(sn);
	}
}