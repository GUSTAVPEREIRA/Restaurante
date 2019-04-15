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

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CategoriaDAO;
import estagio.dao.CompraDAO;
import estagio.dao.ContasPagarDAO;
import estagio.dao.FornecedorDAO;
import estagio.dao.ItensCompraDAO;
import estagio.dao.ParcelaPagarDAO;
import estagio.dao.ProdutoDAO;
import estagio.model.Categoria;
import estagio.model.Compra;
import estagio.model.ContasPagar;
import estagio.model.Fornecedor;
import estagio.model.ItensCompra;
import estagio.model.ParcelaPagar;
import estagio.model.Produto;
import estagio.ui.notifications.FXNotification;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class CompraController implements Initializable {

	@FXML
	private StackPane sp_compra;

	@FXML
	private VBox vb_compra;

	@FXML
	private TableView<ItensCompra> tb_produtos;

	@FXML
	private TableColumn<ItensCompra, String> tc_codigo;

	@FXML
	private TableColumn<ItensCompra, String> tc_produto;

	@FXML
	private TableColumn<ItensCompra, String> tc_quantidade;

	@FXML
	private TableColumn<ItensCompra, String> tc_valorDeCompra;

	@FXML
	private Label lbl_descricao;

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
	private Label lbl_dias;

	@FXML
	private Tooltip ttp_lblDias;

	@FXML
	private JFXTextField txt_dias;

	@FXML
	private ContextMenu ctm_dias;

	@FXML
	private MenuItem mi_dias;

	@FXML
	private Label lbl_valorTotal;

	@FXML
	private JFXTextField txt_valorTotal;

	@FXML
	private Label lbl_dataLancamento;

	@FXML
	private Tooltip ttp_lblDataLancamento;

	@FXML
	private JFXDatePicker dp_dataLancamento;

	@FXML
	private ContextMenu ctm_dataLancamento;

	@FXML
	private MenuItem mi_dataLancamento;

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
	private ContextMenu ctm_dataVencimento;

	@FXML
	private MenuItem mi_dataVencimento;

	@FXML
	private JFXButton btn_Adicionar;
	@FXML
	private JFXButton btn_Novo;

	@FXML
	private Tooltip ttp_btnAdicionar;

	@FXML
	private JFXButton btn_Alterar;

	@FXML
	private Tooltip ttp_btnAlterar;

	@FXML
	private JFXButton btn_Remover;

	@FXML
	private Tooltip ttp_btnRemover;

	@FXML
	private JFXButton btn_Cancelar;

	@FXML
	private Tooltip ttp_btnCancelar;

	@FXML
	private JFXButton btn_Gravar;

	@FXML
	private Tooltip ttp_btnGravar;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private Tooltip ttp_btnSair;

	@FXML
	private VBox vb_telaProduto;

	@FXML
	private Label lbl_cbbCategoria;

	@FXML
	private JFXComboBox<Categoria> cbb_categoria;

	@FXML
	private Label lbl_cbbProduto;

	@FXML
	private JFXComboBox<Produto> cbb_Produto;

	@FXML
	private Label lbl_quantAtual;

	@FXML
	private JFXTextField txt_quantAtual;

	@FXML
	private Label lbl_adicionar;

	@FXML
	private JFXTextField txt_adicionarQuantidade;

	@FXML
	private Label lbl_valorUnitario;

	@FXML
	private JFXTextField txt_valorUnitario;

	@FXML
	private JFXButton btn_adicionar;

	@FXML
	private JFXButton btn_Voltar;

	@FXML
	private Tooltip ttp_btnSair1;

	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
	Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	ButtonType btnSim = new ButtonType("Sim");
	ButtonType btnNao = new ButtonType("Não");
	CategoriaDAO categoriaDAO;
	Categoria categoria;
	Produto produto;
	ProdutoDAO produtoDAO;
	Fornecedor fornecedor;
	FornecedorDAO fornecedorDAO;
	List<Categoria> listCategoria = new ArrayList<Categoria>();
	List<Produto> listProduto = new ArrayList<Produto>();
	List<Fornecedor> listFornecedor = new ArrayList<Fornecedor>();
	JFXAutoCompletePopup<Categoria> autoCompletePopupCategoria = new JFXAutoCompletePopup<Categoria>();
	JFXAutoCompletePopup<Produto> autoCompletePopupProduto = new JFXAutoCompletePopup<Produto>();
	Compra compra;
	CompraDAO compraDAO;
	ItensCompra itensCompra = new ItensCompra();
	private ObservableList<Integer> obsl_parcelas;
	private ObservableList<Categoria> obslCategoria;
	private ObservableList<Produto> obslProduto;
	private ObservableList<ItensCompra> obslItensCompra;
	double valorTotal;

	// private ObservableList<Fornecedor> obslFornecedor;
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
	void OnActionAdicionar(ActionEvent event) {
		itensCompra = new ItensCompra();
		Boolean erro = false;
		if (!txt_adicionarQuantidade.getText().equals("")) {
			int quant = Integer.parseInt(txt_adicionarQuantidade.getText());
			if (quant >= 1 && quant <= 999) {
				itensCompra.setQuantidade(quant);
			} else {
				erro = false;

			}
		} else {
			erro = true;
		}
		itensCompra.setProduto(produto);
		itensCompra.setValor(produto.getPreco_compra());
		if (erro != true) {

			for (int i = 0; i < compra.getListaItensCompra().size() && erro != true; i++) {

				if (compra.getListaItensCompra().get(i).getProduto().getNome().equals(produto.getNome())) {
					compra.getListaItensCompra().remove(i);
					erro = true;
				}
			}

			compra.addItemCompra(itensCompra);
			valorTotal = 0.00;
			for (int i = 0; i < compra.getListaItensCompra().size(); i++) {
				valorTotal = valorTotal + (compra.getListaItensCompra().get(i).getQuantidade()
						* compra.getListaItensCompra().get(i).getProduto().getPreco_compra());
			}
			txt_valorTotal.setText(nf.format(valorTotal));
			carregaTela(compra);
			btn_Gravar.setDisable(false);
			btn_Cancelar.setDisable(false);
			btn_Alterar.setDisable(true);
			btn_Remover.setDisable(true);

		}

	}

	public void carregaTela(Compra compra) {

		tb_produtos.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Produto.id"));
		tc_produto.setCellValueFactory(new PropertyValueFactory<>("Produto"));
		tc_quantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		tc_valorDeCompra.setCellValueFactory(new PropertyValueFactory<>("Produto"));

		tc_valorDeCompra.setCellValueFactory((data) -> {
			Double temp = data.getValue().getProduto().getPreco_compra();
			temp = temp * data.getValue().getQuantidade();
			txt_valorTotal.setText(nf.format(valorTotal));

			return new SimpleStringProperty(nf.format(temp));
		});

		tc_codigo.setCellValueFactory((data) -> {
			String id = data.getValue().getProduto().getId().toString();
			return new SimpleStringProperty(id);
		});

		if (!compra.getListaItensCompra().isEmpty()) {
			obslItensCompra = FXCollections.observableArrayList(compra.getListaItensCompra());
			tb_produtos.setItems(obslItensCompra);
		}

	}

	@FXML
	void OnActionAlterar(ActionEvent event) {

		if (tb_produtos.getSelectionModel().getSelectedItem() != null) {
			vb_compra.setDisable(true);
			this.setItensCompra(tb_produtos.getSelectionModel().getSelectedItem());
			vb_telaProduto.setVisible(true);
			cbb_categoria.getSelectionModel().select(itensCompra.getProduto().getCategoria());
			listProduto = produtoDAO.listarPorCategoria(cbb_categoria.getSelectionModel().getSelectedItem().getNome());
			obslProduto = FXCollections.observableArrayList(listProduto);
			cbb_Produto.setItems(obslProduto);
			cbb_Produto.getSelectionModel().select(itensCompra.getProduto());
			txt_quantAtual.setText(String.valueOf(itensCompra.getProduto().getEstoque()));
			txt_adicionarQuantidade.setText(String.valueOf(itensCompra.getQuantidade()));
			txt_valorUnitario.setText(nf.format(itensCompra.getProduto().getPreco_compra()));
		}
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		tb_produtos.getItems().clear();
		valorTotal = 0;
		txt_valorTotal.setText(nf.format(valorTotal));
		listProduto = new ArrayList<Produto>();
		compra = new Compra();
		desativaTela();

	}

	@FXML
	void OnActionGravar(ActionEvent event) {
		ContasPagar contasPagar = new ContasPagar();
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
			// ctm_valorTotal.show(txt_valorTotal, Side.TOP, 10, 0);
			txt_valorTotal.setStyle(corErro);
		} else {
			txt_valorTotal.setStyle(corNormal);
			// ctm_valorTotal.hide();
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

		contasPagar.setAbertura(Date.valueOf(dp_abertura.getValue()));
		if (dp_vencimento.getValue() == null
				|| comparaData(contasPagar.getAbertura(), Date.valueOf(dp_vencimento.getValue())) == false) {
			erro = true;
			dp_vencimento.setStyle(corErro);
		} else {
			dp_vencimento.setStyle(corNormal);
			contasPagar.setVencimento(Date.valueOf(dp_vencimento.getValue()));
		}
		int dias;
		if (txt_dias.getText().replace(" ", "").length() > 0) {
			dias = Integer.parseInt(txt_dias.getText());
		} else
			dias = 30;

		contasPagar.setStatus("ABERTO");
		if (erro == false) {
			List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
			Double auxValor = contasPagar.getValorTotal() / contasPagar.getNumParcelas();

			for (int i = 0; i < contasPagar.getNumParcelas(); i++) {
				Date auxAbertura = contasPagar.getAbertura();
				Date auxVencimento = contasPagar.getVencimento();

				auxAbertura = adicionarDias(auxAbertura, i * dias);
				auxVencimento = adicionarDias(auxVencimento, i * dias);

				ParcelaPagar parcelaPagar = new ParcelaPagar();
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

			compra.setData(contasPagar.getAbertura());
			compra.setValorTotal(valorTotal);
			compraDAO.save(compra);

			ItensCompraDAO itensCompraDAO = new ItensCompraDAO();
			for (ItensCompra ic : compra.getListaItensCompra()) {
				ic.setCompra(compra);
				produto = produtoDAO.findById(ic.getProduto().getId());
				produto.setEstoque(produto.getEstoque() + ic.getQuantidade());
				produtoDAO.save(produto);
				itensCompraDAO.save(ic);
			}
			ContasPagarDAO contasPagarDAO = new ContasPagarDAO();
			contasPagarDAO.save(contasPagar);
			ParcelaPagarDAO parcelaPagarDAO = new ParcelaPagarDAO();
			for (ParcelaPagar pp : parcelasPagar) {
				parcelaPagarDAO.save(pp);
			}
			compra = new Compra();
			carregaTela(compra);
			desativaTela();
			FXNotification fxn;
			fxn = new FXNotification("Compra realizada no valor total de: " + nf.format(valorTotal),
					FXNotification.NotificationType.INFORMATION);
			fxn.show();

		} else {
			FXNotification fxn;
			fxn = new FXNotification("Por favor, corrija os erros.", FXNotification.NotificationType.ERROR);
			fxn.show();
		}
	}

	public Date adicionarDias(Date data, int soma) {
		LocalDate localData = data.toLocalDate();
		localData = localData.plusDays(soma);
		return Date.valueOf(localData);
	}

	public boolean comparaData(Date lancamento, Date vencimento) {
		return vencimento.after(lancamento);
	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		vb_telaProduto.setVisible(true);
		vb_compra.setDisable(true);
	}

	@FXML
	void OnActionRemover(ActionEvent event) {
		Boolean erro = false;
		for (int i = 0; i < compra.getListaItensCompra().size() && erro != true; i++) {

			if (compra.getListaItensCompra().get(i).getProduto().getNome().equals(itensCompra.getProduto().getNome())) {
				compra.getListaItensCompra().remove(i);
				erro = true;
			}
		}
		valorTotal = 0.00;
		for (int i = 0; i < compra.getListaItensCompra().size(); i++) {
			valorTotal = valorTotal + (compra.getListaItensCompra().get(i).getQuantidade()
					* compra.getListaItensCompra().get(i).getProduto().getPreco_compra());
		}
		txt_valorTotal.setText(nf.format(valorTotal));
		btn_Alterar.setDisable(true);
		btn_Remover.setDisable(true);
		if (compra.getListaItensCompra().size() == 0) {
			btn_Cancelar.setDisable(true);
		}
		carregaTela(compra);
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_compra.setVisible(false);
	}

	public void desativaTelaProduto() {
		cbb_categoria.getSelectionModel().select(0);
		cbb_Produto.getSelectionModel().clearSelection();
		vb_telaProduto.setVisible(false);
		vb_compra.setDisable(false);
		txt_quantAtual.setText("");
		txt_adicionarQuantidade.setText("");
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		vb_telaProduto.setVisible(false);
		desativaTelaProduto();

	}

	@FXML
	void OnMouseClickedProduto(MouseEvent event) {
		if (tb_produtos.getSelectionModel().getSelectedItem() != null) {
			this.setItensCompra(tb_produtos.getSelectionModel().getSelectedItem());

		}
	}

	public void setItensCompra(ItensCompra itensCompra) {
		this.itensCompra = itensCompra;
		btn_Remover.setDisable(false);
		btn_Alterar.setDisable(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dp_abertura.setValue(LocalDate.now());
		valorTotal = 0.00;
		txt_valorTotal.setText(nf.format(valorTotal));
		compra = new Compra();
		compraDAO = new CompraDAO();
		produtoDAO = new ProdutoDAO();
		categoriaDAO = new CategoriaDAO();
		// Combo box Parcelas
		obsl_parcelas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbb_parcela.setItems(obsl_parcelas);
		cbb_parcela.getSelectionModel().select(0);
		// Combo box categoria;
		listCategoria = categoriaDAO.listar("");
		obslCategoria = FXCollections.observableArrayList(listCategoria);
		cbb_categoria.setItems(obslCategoria);
		// Combo box produto
		listProduto = produtoDAO.listar("");
		obslProduto = FXCollections.observableArrayList(listProduto);
		cbb_Produto.setItems(obslProduto);

		InitComboBoxCategoria();
		InitComboBoxProduto();

		txt_descricao.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_dias.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 15));
		txt_quantAtual.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 15));
		txt_valorTotal.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_valorUnitario.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_adicionarQuantidade.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 4));

	}

	public void desativaTela() {
		// Context Menus
		ctm_cbParcela.hide();
		ctm_dataLancamento.hide();
		ctm_dataVencimento.hide();
		ctm_descricao.hide();
		ctm_dias.hide();
		// Style
		cb_avista.setStyle(corNormal);
		cb_aprazo.setStyle(corNormal);
		cbb_parcela.setStyle(corNormal);
		txt_dias.setStyle(corNormal);
		txt_valorTotal.setStyle(corNormal);
		dp_dataLancamento.setStyle(corNormal);
		dp_vencimento.setStyle(corNormal);
		cbb_categoria.setStyle(corNormal);
		cbb_Produto.setStyle(corNormal);
		// Check Box
		cb_avista.setSelected(false);
		cb_aprazo.setSelected(false);
		// Combo box
		cbb_parcela.setDisable(true);
		cbb_Produto.setDisable(true);
		cbb_categoria.getSelectionModel().select(0);
		// TextoBox
		txt_dias.setText("");
		txt_valorTotal.setText("");
		txt_descricao.setText("");
		txt_valorUnitario.setText("");
		txt_quantAtual.setText("");
		// Data picker
		dp_abertura.setValue(LocalDate.now());
		dp_dataLancamento.setValue(null);
		dp_vencimento.setValue(null);
		// Buttons
		btn_Alterar.setDisable(true);
		btn_Remover.setDisable(true);
		btn_Cancelar.setDisable(true);
		btn_Gravar.setDisable(true);

	}

	@FXML
	void OnMouseselectionCategoria(ActionEvent event) {

		produtoDAO = new ProdutoDAO();
		if (cbb_categoria.getSelectionModel().getSelectedIndex() != -1) {
			listProduto = produtoDAO.listarPorCategoria(cbb_categoria.getSelectionModel().getSelectedItem().getNome());
			obslProduto = FXCollections.observableArrayList(listProduto);
			cbb_Produto.setItems(obslProduto);
			InitComboBoxProduto();
			txt_quantAtual.setText("");
			txt_valorUnitario.setText("");
			cbb_Produto.setDisable(false);
			btn_adicionar.setDisable(true);
			// InitComboBoxCid();
		}
	}

	@FXML
	void OnMouseselectionProduto(ActionEvent event) {

		if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1) {
			produto = cbb_Produto.getSelectionModel().getSelectedItem();
			txt_quantAtual.setText(String.valueOf(produto.getEstoque()));
			txt_valorUnitario.setText(nf.format(produto.getPreco_compra()));
			btn_adicionar.setDisable(false);
			// InitComboBoxCid();
		}
	}

	private void InitComboBoxCategoria() {
		autoCompletePopupCategoria.getSuggestions().addAll(cbb_categoria.getItems());

		autoCompletePopupCategoria.setSelectionHandler(eventt -> {
			cbb_categoria.setValue(eventt.getObject());
			cbb_categoria.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupCategoria.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_categoria.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupCategoria.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupCategoria.setHideOnEscape(false);
			autoCompletePopupCategoria.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupCategoria.getFilteredSuggestions().isEmpty() || cbb_categoria.showingProperty().get()
					|| cbb_categoria.getEditor().isFocused() == false) {
				autoCompletePopupCategoria.hide();
			} else {
				autoCompletePopupCategoria.show(editor);
			}
		});
		cbb_categoria.setConverter(new StringConverter<Categoria>() {

			@Override
			public String toString(Categoria provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Categoria fromString(String string) {
				try {
					int index = cbb_categoria.getSelectionModel().getSelectedIndex();
					return cbb_categoria.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	private void InitComboBoxProduto() {
		autoCompletePopupProduto.getSuggestions().clear();
		autoCompletePopupProduto.getSuggestions().addAll(cbb_Produto.getItems());

		autoCompletePopupProduto.setSelectionHandler(eventt -> {
			cbb_Produto.setValue(eventt.getObject());
			cbb_Produto.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupProduto.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_Produto.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupProduto.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupProduto.setHideOnEscape(false);
			autoCompletePopupProduto.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupProduto.getFilteredSuggestions().isEmpty() || cbb_Produto.showingProperty().get()
					|| cbb_Produto.getEditor().isFocused() == false) {
				autoCompletePopupProduto.hide();
			} else {
				autoCompletePopupProduto.show(editor);
			}
		});
		cbb_Produto.setConverter(new StringConverter<Produto>() {

			@Override
			public String toString(Produto provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public Produto fromString(String string) {
				try {
					int index = cbb_Produto.getSelectionModel().getSelectedIndex();
					return cbb_Produto.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}

	public void ativaTela() {

	}
}
