package estagio.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import estagio.dao.Banco;
import estagio.dao.Conexao;
import estagio.dao.UsuarioDAO;
import estagio.model.Usuario;
import estagio.ui.notifications.FXNotification;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class RelatorioCaixaController implements Initializable {

	@FXML
	private VBox vboxRelatorio;

	@FXML
	private Label lbl_usuario;

	@FXML
	private Tooltip ttp_lblStatus;

	@FXML
	private JFXComboBox<Usuario> cbb_usuario;

	@FXML
	private Tooltip ttp_status;

	@FXML
	private ContextMenu ctm_status;

	@FXML
	private MenuItem mi_status;

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
	private HBox hboxJasperMaldito;

	@FXML
	private JFXButton btn_VoltarOnHell;

	@FXML
	private Tooltip ttp_btnVoltar1;
	String corErro = "-fx-border-color:red;";
	String corNormal = "-fx-border-color:white";
	SwingNode sn = new SwingNode();
	private Usuario usuario;
	private ObservableList<Usuario> obslUsuario;
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private List<Usuario> listaUsuario;
	UsuarioDAO usuarioDAO;
	JFXAutoCompletePopup<Usuario> autoCompletePopupUsu = new JFXAutoCompletePopup<Usuario>();

	@FXML
	void OnActionGerar(ActionEvent event) {

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
		if (cbb_usuario.getSelectionModel().getSelectedItem() == null) {
			erro = true;
			cbb_usuario.setStyle(corErro);
		} else {
			usuario = cbb_usuario.getSelectionModel().getSelectedItem();
			cbb_usuario.setStyle(corNormal);
		}
		if (erro != true) {
			exibeRelatorio(usuario, DataAberturaT, DataVencimentoT);
		} else {

			new FXNotification("Dados inválidos, corrija os campos contornados em vermelho.",
					FXNotification.NotificationType.ERROR).show();
		}

	}

	public void exibeRelatorio(Usuario usu, Date ini, Date fim) {

		new FXNotification("Gerando relatório, aguarde alguns segundos.", FXNotification.NotificationType.INFORMATION)
				.show();

		Task<JRViewer> task = new Task<JRViewer>() {
			@Override
			public JRViewer call() {
				try {
					Banco.getCon();
					Connection con = Conexao.abre();
					HashMap<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("id", usu.getId());
					parameters.put("dataini", ini);
					parameters.put("datafim", fim);

					InputStream jasper1 = getClass()
							.getResourceAsStream("/estagio/relatorios/RelatorioCaixaReports.jasper");
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
	void OnMouseSelectionUsuario(ActionEvent event) {

	}

	private void InitComboBoxUsu() {
		autoCompletePopupUsu.getSuggestions().addAll(cbb_usuario.getItems());

		autoCompletePopupUsu.setSelectionHandler(eventt -> {
			cbb_usuario.setValue(eventt.getObject());
			cbb_usuario.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupUsu.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_usuario.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupUsu.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupUsu.setHideOnEscape(false);
			autoCompletePopupUsu.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupUsu.getFilteredSuggestions().isEmpty() || cbb_usuario.showingProperty().get()
					|| cbb_usuario.getEditor().isFocused() == false) {
				autoCompletePopupUsu.hide();
			} else {
				cbb_usuario.hide();
				autoCompletePopupUsu.show(editor);

			}
		});
		cbb_usuario.setConverter(new StringConverter<Usuario>() {

			@Override
			public String toString(Usuario provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Usuario fromString(String string) {
				try {
					int index = cbb_usuario.getSelectionModel().getSelectedIndex();
					return cbb_usuario.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usuarioDAO = new UsuarioDAO();
		listaUsuario = usuarioDAO.listar("");
		obslUsuario = FXCollections.observableArrayList(listaUsuario);
		cbb_usuario.setItems(obslUsuario);
		InitComboBoxUsu();
	}

}
