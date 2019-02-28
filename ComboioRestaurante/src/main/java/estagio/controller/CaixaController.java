package estagio.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CaixaDAO;
import estagio.model.Caixa;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class CaixaController implements Initializable {

	@FXML
	private AnchorPane ap_caixa;

	@FXML
	private Label lbl_valorAtual;

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
	private Tooltip ttp_lblDataFechamento;

	@FXML
	private JFXDatePicker txt_dataFechamento;

	@FXML
	private ContextMenu ctm_dataFechamento;

	@FXML
	private MenuItem mi_dataFechamento;

	@FXML
	private Label lbl_horaFechamento;

	@FXML
	private Tooltip ttp_lblHoraFechamento;

	@FXML
	private JFXTextField txt_horaFechamento;

	@FXML
	private ContextMenu ctm_horaFechamento;

	@FXML
	private MenuItem mi_horaFechamento;

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
	private JFXButton btn_abrirCaixa;

	@FXML
	private Tooltip ttp_btnAbrirCaixa;

	@FXML
	private JFXButton btn_fecharCaixa;

	@FXML
	private Tooltip ttp_btnFecharCaixa;

	private CaixaDAO caixaDAO;
	private Caixa caixa;
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		caixaDAO = new CaixaDAO();
		caixa = new Caixa();
		verificaCaixaAberto();
		txt_cartaoCredito.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_cartaoDebito.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_cheque.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_dinheiro.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorAtual.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorAbertura.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		verificaCaixaAberto();

	}

	public void verificaCaixaAberto() {
		caixaDAO = new CaixaDAO();
		caixa = caixaDAO.caixa_aberto("aberto");
		if (caixa != null) {
			txt_cartaoCredito.setText(String.valueOf(caixa.getCredito()));
			txt_cartaoDebito.setText(String.valueOf(caixa.getDebito()));
			txt_cheque.setText(String.valueOf(caixa.getCheque()));
			txt_dinheiro.setText(String.valueOf(caixa.getDinheiro()));
			txt_valorAtual.setText((String.valueOf(caixa.somaDinheiro())));
			txt_valorAbertura.setText(String.valueOf(caixa.getAbertura()));
			txt_dataAbertura.setValue(caixa.getDataAbertura().toLocalDate());
			txt_horaAbertura.setText(caixa.getHoraAbertura().toString());
			txt_valorAbertura.setDisable(false);
			btn_abrirCaixa.setVisible(false);
			btn_fecharCaixa.setVisible(true);
			txt_valorAbertura.setDisable(false);
		}
	}

	@FXML
	void OnActionAbrir(ActionEvent event) {
		boolean erro = false;
		caixa = new Caixa();
		if (txt_valorAbertura.getText().equals("") == true) {
			erro = true;
			ctm_valorAbertura.show(txt_valorAbertura, Side.RIGHT, 10, 0);
			txt_valorAbertura.setStyle(corErro);
		} else {
			ctm_valorAbertura.hide();
			erro = false;
			FXNotification fxn;
			txt_dataAbertura.setValue(LocalDate.now());
			txt_horaAbertura.setText(LocalDateTime.now().format(horaFormat));
			txt_valorAbertura.setStyle(corNormal);
			caixa.setAbertura(Double.parseDouble(txt_valorAbertura.getText()));
			caixa.setCheque(0.00);
			caixa.setCredito(0.00);
			caixa.setDebito(0.00);
			caixa.setDinheiro(0.00);
			caixa.setStatus("aberto");
			caixa.setDataAbertura(Date.valueOf(txt_dataAbertura.getValue()));
			caixa.setHoraAbertura(Time.valueOf(txt_horaAbertura.getText()));
			btn_abrirCaixa.setVisible(false);
			btn_fecharCaixa.setVisible(true);
			txt_cartaoCredito.setText(String.valueOf(caixa.getCredito()));
			txt_cartaoDebito.setText(String.valueOf(caixa.getDebito()));
			txt_cheque.setText(String.valueOf(caixa.getCheque()));
			txt_dinheiro.setText(String.valueOf(caixa.getDinheiro()));
			txt_valorAtual.setText((String.valueOf(caixa.somaDinheiro())));
			txt_valorAbertura.setText(String.valueOf(caixa.getAbertura()));
			txt_valorAbertura.setDisable(true);
			fxn = new FXNotification(
					"Caixa aberto no dia " + String.valueOf(caixa.getDataAbertura().toLocalDate().format(dataFormat))
							+ "" + " as " + String.valueOf(caixa.getHoraAbertura()),
					FXNotification.NotificationType.INFORMATION);
			fxn.show();
			caixa.setUsuario(LoginController.logado);
			caixaDAO.persist(caixa);
		}

		if (erro == false) {

		}
	}

	@FXML
	void OnActionFechar(ActionEvent event) {
		txt_cartaoCredito.setText("");
		txt_cartaoDebito.setText("");
		txt_cheque.setText("");
		txt_dinheiro.setText("");
		txt_valorAtual.setText("");
		txt_valorAbertura.setText("");
		txt_valorAbertura.setDisable(false);
		txt_dataAbertura.setValue(null);
		txt_horaAbertura.setText("");
		FXNotification fxn;
		caixa.setDataFechamento(Date.valueOf(LocalDate.now()));
		caixa.setHoraFechamento(Time.valueOf(LocalDateTime.now().format(horaFormat)));
		caixa.setStatus("fechado");
		fxn = new FXNotification(
				"Caixa fechado no dia " + String.valueOf(caixa.getDataFechamento().toLocalDate().format(dataFormat))
						+ "" + " as "
						+ String.valueOf(caixa.getHoraFechamento() + "\n Com o valor total de:" + caixa.somaDinheiro()),
				FXNotification.NotificationType.INFORMATION);
		fxn.show();
		caixaDAO.merge(caixa);
		btn_fecharCaixa.setVisible(false);
		btn_abrirCaixa.setVisible(true);

	}

}