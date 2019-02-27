package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class CaixaController {

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

	@FXML
	void OnActionAbrir(ActionEvent event) {

	}

	@FXML
	void OnActionFechar(ActionEvent event) {

	}

}