package estagio.controller;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.model.ContasPagar;
import estagio.model.Fornecedor;
import estagio.model.ParcelaPagar;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ContasPagarController implements Initializable {

	@FXML
	private StackPane sp_contasPagar;

	@FXML
	private Label lbl_TipoConta;

	@FXML
	private Tooltip ttp_lblTipoConta;

	@FXML
	private JFXCheckBox cb_fornecedor;

	@FXML
	private JFXCheckBox cb_outros;

	@FXML
	private Label lbl_listaFornecedores;

	@FXML
	private Tooltip ttp_cbbListaFornecedores;

	@FXML
	private JFXComboBox<Fornecedor> cbb_fornecedores;

	@FXML
	private ContextMenu ctm_cbFornecedores;

	@FXML
	private MenuItem mi_cbFornecedores;

	@FXML
	private Label lbl_descricao;

	@FXML
	private Tooltip ttp_lblDescricao;

	@FXML
	private JFXTextField txt_descricao;

	@FXML
	private ContextMenu ctm_descricao;

	@FXML
	private MenuItem mi_descricao;

	@FXML
	private Label lbl_TipoCondicao;

	@FXML
	private Tooltip ttp_lblTipoCondicao;

	@FXML
	private JFXCheckBox cb_avista;

	@FXML
	private JFXCheckBox cb_aprazo;

	@FXML
	private Label lbl_parcelas;

	@FXML
	private Tooltip ttp_cbbParcelas;

	@FXML
	private JFXComboBox<Integer> cbb_parcela;

	@FXML
	private ContextMenu ctm_cbParcela;

	@FXML
	private MenuItem mi_cbParcela;

	@FXML
	private Label lbl_valorTotal;

	@FXML
	private Tooltip ttp_lblValorTotal;

	@FXML
	private JFXTextField txt_valorTotal;

	@FXML
	private ContextMenu ctm_valorTotal;

	@FXML
	private MenuItem mi_valorTotal;

	@FXML
	private Label lbl_dataLancamento;

	@FXML
	private Tooltip ttp_lblDataLancamento;

	@FXML
	private JFXDatePicker dp_dataLancamento;

	@FXML
	private Label lbl_dataVencimento;

	@FXML
	private Tooltip ttp_lblDataVencimento;

	@FXML
	private JFXDatePicker dp_dataVencimento;

	@FXML
	private JFXButton btn_Gerar;

	@FXML
	private Tooltip ttp_btnGerar;

	@FXML
	private TableView<ParcelaPagar> tb_lancarContas;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_aLancamento;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_aVencimento;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_aValor;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private Tooltip ttp_btnNovo;

	@FXML
	private JFXButton btn_GravarCP;

	@FXML
	private Tooltip ttp_btnGravarCP;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnCancelar;

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
	private Label lbl_abertura;

	@FXML
	private Tooltip ttp_lblCliente1;

	@FXML
	private JFXDatePicker dp_abertura;

	@FXML
	private Label lbl_vencimento;

	@FXML
	private Tooltip ttp_lblVencimento;

	@FXML
	private JFXDatePicker dp_vencimento;

	@FXML
	private TableView<ParcelaPagar> tb_baixarContas;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_codigo;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_descricao;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_abertura;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_vencimento;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_parcela;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_status;

	@FXML
	private TableColumn<ParcelaPagar, String> tc_valor;

	@FXML
	private Label lbl_total;

	@FXML
	private JFXTextField txt_total;

	@FXML
	private ContextMenu ctm_total;

	@FXML
	private MenuItem mi_total;

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
	private JFXButton btn_Cancelar1;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	private ObservableList<Integer> obsl_parcelas;
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	ContasPagar contasPagar = new ContasPagar();
	ParcelaPagar parcelaPagar = new ParcelaPagar();
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@FXML
	void OnActionCancelar(ActionEvent event) {

	}

	@FXML
	void OnActionGravarCP(ActionEvent event) {

	}

	@FXML
	void OnActionExcluir(ActionEvent event) {

	}

	
	public void desativaTela()
	{
		dp_dataLancamento.setValue(LocalDate.now());
	}
	@FXML
	void OnActionGerar(ActionEvent event) {
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
				|| Double.parseDouble(txt_valorTotal.getText()) < 0) {
			erro = true;
			ctm_valorTotal.show(txt_valorTotal, Side.TOP, 10, 0);
			txt_valorTotal.setStyle(corErro);
		} else {
			txt_valorTotal.setStyle(corNormal);
			ctm_valorTotal.hide();
			contasPagar.setValorTotal(Double.parseDouble(txt_valorTotal.getText()));
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
			}
			else
			{
				condicao = "A VISTA";
			}
			contasPagar.setCondicaoPgto(condicao);
			contasPagar.setNumParcelas(cbb_parcela.getSelectionModel().getSelectedItem());
		}

		if (cb_fornecedor.isSelected() == false && cb_outros.isSelected() == false) {
			erro = true;
			lbl_TipoConta.setStyle(corErro);
			
		}
		else
		{
			if (cb_fornecedor.isSelected() == true && cbb_fornecedores.getSelectionModel().getSelectedItem() == null) {
				erro = true;
				lbl_TipoConta.setStyle(corErro);
				cbb_fornecedores.setStyle(corErro);
			}
			else
			{
				lbl_TipoConta.setStyle(corNormal);
				
				if (cb_fornecedor.isSelected() == true) {
					cbb_fornecedores.setStyle(corNormal);
					contasPagar.setFornecedor(cbb_fornecedores.getSelectionModel().getSelectedItem());
				}
			}
		}
		
		contasPagar.setAbertura(Date.valueOf(dp_dataLancamento.getValue()));
		if (dp_dataVencimento.getValue() == null || comparaData(contasPagar.getAbertura(),Date.valueOf(dp_dataVencimento.getValue())) == false) {
			erro = true;
			dp_dataVencimento.setStyle(corErro);
		}
		else
		{
			dp_dataVencimento.setStyle(corNormal);
			contasPagar.setVencimento(Date.valueOf(dp_vencimento.getValue())); 
		}
		if (erro == true) {

		}

	}

	@FXML
	void OnActionGravar(ActionEvent event) {

	}

	@FXML
	void OnActionNovo(ActionEvent event) {

	}

	@FXML
	void OnActionSair(ActionEvent event) {

	}

	@FXML
	void OnMouseClickedFornecedor(MouseEvent event) {

	}

	@FXML
	void OnMouseClickedParcela(MouseEvent event) {

	}

	@FXML
	void OnMouseSelectionUf(ActionEvent event) {

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		desativaTela();
		obsl_parcelas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbb_parcela.setItems(obsl_parcelas);
		cbb_parcela.getSelectionModel().select(0);
		txt_valorTotal.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorParcial.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_descricao.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_total.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));

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
	void OnActionAPrazo(ActionEvent event) {
		if (cb_aprazo.isSelected() == true) {
			cbb_parcela.setDisable(false);

		} else
			cbb_parcela.setDisable(true);

		if (cb_avista.isSelected() == true) {
			cb_avista.setSelected(false);
		}
	}

	public void carregaTela() {
		tb_lancarContas.getItems().clear();
		tc_aVencimento.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
		tc_aLancamento.setCellValueFactory(new PropertyValueFactory<>("Abertura"));
		tc_aValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
		tc_valor.setCellValueFactory((data) -> {
			Double temp = data.getValue().getValor();
			NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
			return new SimpleStringProperty(nf.format(temp));
		});
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
	void OnActionFornecedor(ActionEvent event) {
		if (cb_fornecedor.isSelected() == true) {
			cb_outros.setSelected(false);
			cbb_fornecedores.setDisable(false);

		} else {
			cbb_fornecedores.setDisable(true);
		}
		if (cb_outros.isSelected() == true) {
			cb_outros.setSelected(false);
			cbb_fornecedores.setDisable(false);
		}
	}

	public boolean comparaData(Date lancamento, Date vencimento) {
		return vencimento.after(lancamento);
	}

}
