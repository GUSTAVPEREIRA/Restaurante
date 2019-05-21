package estagio.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.Banco;
import estagio.dao.Conexao;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
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

public class RelatorioMovimentoEstoqueController implements Initializable {

	@FXML
	private VBox vboxRelatorio;

	@FXML
	private HBox hboxJasperMaldito;

	@FXML
	private Label lbl_abertura;

	@FXML
	private Tooltip ttp_lblClienteT1;

	@FXML
	private JFXTextField txt_quantidade;

	@FXML
	private ContextMenu ctm_porcentagem;

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
	@FXML
	private int quantidade;

	public void gerar() {
		quantidade = 100;
		Boolean erro = false;
		if (txt_quantidade.getText().equals("") != true) {
			quantidade = Integer.parseInt(txt_quantidade.getText());
		}
		if (quantidade > 0) {
			txt_quantidade.setStyle(corNormal);
		} else {
			txt_quantidade.setStyle(corErro);
			erro = true;
		}

		if (erro != true) {
			exibeRelatorio(quantidade);
		} else {

			new FXNotification("Dados inválidos, corrija os campos contornados em vermelho.",
					FXNotification.NotificationType.ERROR).show();
		}
	}

	@FXML
	void OnActionGerar(ActionEvent event) {
		gerar();
	}

	public void exibeRelatorio(int quantidade) {

		new FXNotification("Gerando relatório, aguarde alguns segundos.", FXNotification.NotificationType.INFORMATION)
				.show();

		Task<JRViewer> task = new Task<JRViewer>() {
			@Override
			public JRViewer call() {
				try {
					Banco.getCon();
					Connection con = Conexao.abre();
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("qt", quantidade);

					InputStream jasper1 = getClass()
							.getResourceAsStream("/estagio/relatorios/RelatorioEstoqueReports.jasper");
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

	@FXML
	void OnActionVoltarOnHell(ActionEvent event) {
		vboxRelatorio.setVisible(false);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txt_quantidade.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 4));

	}

}
