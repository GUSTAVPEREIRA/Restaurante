package estagio.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ContasPagarController implements Initializable{

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
	private JFXComboBox<?> cbb_fornecedores;

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
	private JFXComboBox<?> cbb_parcela;

	@FXML
	private ContextMenu ctm_cbParcela;

	@FXML
	private MenuItem mi_cbParcela;

	@FXML
	private Label lbl_valorTotal;

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
	private TableView<?> tb_lancarContas;

	@FXML
	private TableColumn<?, ?> tc_aParcela;

	@FXML
	private TableColumn<?, ?> tc_aLancamento;

	@FXML
	private TableColumn<?, ?> tc_aVencimento;

	@FXML
	private TableColumn<?, ?> tc_aValor;

	@FXML
	private JFXButton btn_Novo;

	@FXML
	private Tooltip ttp_btnNovo;

	@FXML
	private JFXButton btn_Editar;

	@FXML
	private Tooltip ttp_btnNovo1;

	@FXML
	private JFXButton btn_Excluir;

	@FXML
	private Tooltip ttp_btnExcluir;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnExcluir1;

	@FXML
	private Label lbl_status;

	@FXML
	private Tooltip ttp_lblStatus;

	@FXML
	private JFXComboBox<?> cbb_status;

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
	private TableView<?> tb_baixarContas;

	@FXML
	private TableColumn<?, ?> tc_codigo;

	@FXML
	private TableColumn<?, ?> tc_descricao;

	@FXML
	private TableColumn<?, ?> tc_abertura;

	@FXML
	private TableColumn<?, ?> tc_vencimento;

	@FXML
	private TableColumn<?, ?> tc_parcela;

	@FXML
	private TableColumn<?, ?> tc_status;

	@FXML
	private TableColumn<?, ?> tc_valor;

	@FXML
	private Label lbl_total;

	@FXML
	private JFXTextField txt_nome;

	@FXML
	private ContextMenu ctm_nome;

	@FXML
	private MenuItem mi_nome;

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
	private JFXButton btn_Excluir1;

	@FXML
	private Tooltip ttp_btnExcluir2;

	@FXML
	private JFXButton btn_Cancelar1;

	@FXML
	private Tooltip ttp_btnCancelar;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	void OnActionCancelar(ActionEvent event) {

	}

	@FXML
	void OnActionEditar(ActionEvent event) {

	}

	@FXML
	void OnActionExcluir(ActionEvent event) {

	}

	@FXML
	void OnActionGerar(ActionEvent event) {

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
		// TODO Auto-generated method stub
		
	}

}
