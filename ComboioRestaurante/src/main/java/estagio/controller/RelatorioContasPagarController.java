package estagio.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import estagio.dao.Banco;
import estagio.dao.Conexao;
import estagio.ui.notifications.FXNotification;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class RelatorioContasPagarController {
	@FXML
	private VBox vboxRelatorio;

	@FXML
	private HBox hboxJasperMaldito;

	@FXML
	private Label lbl_abertura;

	@FXML
	private Tooltip ttp_lblClienteT1;

	@FXML
	private JFXDatePicker dp_aberturaIni;

	@FXML
	private Label lbl_aberturaFinal;

	@FXML
	private Tooltip ttp_lblVencimentoT1;

	@FXML
	private JFXDatePicker dp_aberturaFim;

	@FXML
	private JFXButton btn_Gerar;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private JFXButton btn_VoltarOnHell;

	@FXML
	private Tooltip ttp_btnVoltar1;
	String corErro = "-fx-border-color:red;";
	String corNormal = "-fx-border-color:white";
	SwingNode sn = new SwingNode();
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@FXML
	void OnActionGerar(ActionEvent event) {
		gerar();
	}

	public void gerar() {
		Boolean erro = false;
		String dataAbertura = "", dataVencimento = "";
		Date DataVencimentoT = null, DataAberturaT = null;
		if (dp_aberturaIni.getValue() != null) {
			dataAbertura = dp_aberturaIni.getValue().toString();
			DataAberturaT = Date.valueOf(dataAbertura);
			dp_aberturaIni.setStyle(corNormal);

		} else {
			dp_aberturaIni.setStyle(corErro);
			erro = true;
		}

		if (dp_aberturaFim.getValue() != null) {
			dataVencimento = dp_aberturaFim.getValue().toString();
			DataVencimentoT = Date.valueOf(dataVencimento);
			dp_aberturaFim.setStyle(corNormal);
		} else {
			dp_aberturaFim.setStyle(corErro);
			erro = true;
		}
		if (erro != true) {
			exibeRelatorio(DataAberturaT, DataVencimentoT);
		} else {

			new FXNotification("Dados inválidos, corrija os campos contornados em vermelho.",
					FXNotification.NotificationType.ERROR).show();
		}
	}

	@FXML
	void OnEnterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			gerar();
		}
	}

	@FXML
	void OnEnterPressedBack(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			vboxRelatorio.setVisible(false);
		}
	}
	@FXML
	void OnActionVoltarOnHell(ActionEvent event) {
		vboxRelatorio.setVisible(false);
	}

	public void exibeRelatorio(Date ini, Date fim) {

		new FXNotification("Gerando relatório, aguarde alguns segundos.", FXNotification.NotificationType.INFORMATION)
				.show();

		Task<JRViewer> task = new Task<JRViewer>() {
			@Override
			public JRViewer call() {
				try {
					Banco.getCon();
					Connection con = Conexao.abre();
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("dataini", ini);
					parameters.put("datafim", fim);

					InputStream jasper1 = getClass()
							.getResourceAsStream("/estagio/relatorios/RelatorioContasPagarReports.jasper");
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
					hboxJasperMaldito.getChildren().remove(sn);
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

}
