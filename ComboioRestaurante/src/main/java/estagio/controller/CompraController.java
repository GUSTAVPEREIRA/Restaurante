package estagio.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
import estagio.dao.EstoqueDAO;
import estagio.dao.FornecedorDAO;
import estagio.dao.ProdutoDAO;
import estagio.model.Categoria;
import estagio.model.Cidade;
import estagio.model.Estado;
import estagio.model.Estoque;
import estagio.model.Fornecedor;
import estagio.model.ItensCompra;
import estagio.model.Produto;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private Spinner<Integer> txt_adicionarQuantidade;

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
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
	String corNormal = "-fx-border-color:white";
	Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	ButtonType btnSim = new ButtonType("Sim");
	ButtonType btnNao = new ButtonType("Não");
	CategoriaDAO categoriaDAO;
	Categoria categoria;
	Produto produto;
	ProdutoDAO produtoDAO;
	Fornecedor fornecedor;
	FornecedorDAO fornecedorDAO;
	Estoque estoque;
	EstoqueDAO estoqueDAO;
	List<Categoria> listCategoria = new ArrayList<Categoria>();
	List<Produto> listProduto = new ArrayList<Produto>();
	List<Fornecedor> listFornecedor = new ArrayList<Fornecedor>();
	JFXAutoCompletePopup<Categoria> autoCompletePopupCategoria = new JFXAutoCompletePopup<Categoria>();
	JFXAutoCompletePopup<Produto> autoCompletePopupProduto = new JFXAutoCompletePopup<Produto>();

	private ObservableList<Integer> obsl_parcelas;
	private ObservableList<Categoria> obslCategoria;
	private ObservableList<Produto> obslProduto;

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

	}

	@FXML
	void OnActionAlterar(ActionEvent event) {

	}

	@FXML
	void OnActionCancelar(ActionEvent event) {

	}

	@FXML
	void OnActionGravar(ActionEvent event) {

	}

	@FXML
	void OnActionNovo(ActionEvent event) {
		vb_telaProduto.setVisible(true);
		vb_compra.setDisable(true);
	}

	@FXML
	void OnActionRemover(ActionEvent event) {

	}

	@FXML
	void OnActionSair(ActionEvent event) {

	}

	public void desativaTelaProduto() {
		cbb_categoria.getSelectionModel().select(0);
		cbb_Produto.getSelectionModel().clearSelection();
		vb_telaProduto.setVisible(false);
		vb_compra.setDisable(false);
		txt_quantAtual.setText("");
		txt_adicionarQuantidade = new Spinner<Integer>(1, 999, 1);
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		vb_telaProduto.setVisible(false);
		desativaTelaProduto();

	}

	@FXML
	void OnMouseClickedProduto(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		produtoDAO = new ProdutoDAO();
		categoriaDAO = new CategoriaDAO();
		// Combo box Parcelas
		obsl_parcelas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbb_parcela.setItems(obsl_parcelas);
		cbb_parcela.getSelectionModel().select(-1);
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
		SpinnerValueFactory<Integer> gradeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1,
				1);
		txt_adicionarQuantidade.setValueFactory(gradeValueFactory);
		EventHandler<KeyEvent> enterKeyEventHandler;

		enterKeyEventHandler = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				// handle users "enter key event"
				if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

					try {
						// yes, using exception for control is a bad solution ;-)
						Integer.parseInt(txt_adicionarQuantidade.getEditor().textProperty().get());
					} catch (NumberFormatException e) {
						// show message to user: "only numbers allowed"
						// reset editor to INITAL_VALUE
						txt_adicionarQuantidade.getEditor().textProperty().set("1");
					}
				}
			}
		};
		txt_adicionarQuantidade.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);

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
		dp_dataLancamento.setValue(null);
		dp_vencimento.setValue(null);

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

		estoqueDAO = new EstoqueDAO();
		if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1) {
			produto = cbb_Produto.getSelectionModel().getSelectedItem();
			estoque = estoqueDAO.findById(produto.getId());
			txt_quantAtual.setText(String.valueOf(estoque.getQuantidade()));
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
					|| cbb_categoria.getEditor().isFocused()==false) {
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
					|| cbb_Produto.getEditor().isFocused()==false) {
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
