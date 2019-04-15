/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import java.io.File;
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
/**
 *
 * @author Pereira
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CaixaDAO;
import estagio.dao.CategoriaDAO;
import estagio.dao.ClienteDAO;
import estagio.dao.ItensVendaDAO;
import estagio.dao.ProdutoDAO;
import estagio.dao.TipoVendaDAO;
import estagio.dao.VendaDAO;
import estagio.model.Caixa;
import estagio.model.Categoria;
import estagio.model.Cliente;
import estagio.model.ItensVenda;
import estagio.model.Produto;
import estagio.model.TipoVenda;
import estagio.model.Venda;
import estagio.ui.notifications.FXNotification;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class VendaController implements Initializable {

	@FXML
	private StackPane sp_venda;

	@FXML
	private HBox hb_Venda;

	@FXML
	private VBox vb_iniciarVenda;

	@FXML
	private Label lbl_cbbTipoVenda;

	@FXML
	private JFXComboBox<TipoVenda> cbb_tipoVenda;

	@FXML
	private Label lbl_dataLancamento;

	@FXML
	private Tooltip ttp_lblDataLancamento;

	@FXML
	private ContextMenu ctm_dias;

	@FXML
	private MenuItem mi_dias;

	@FXML
	private JFXButton btn_Iniciar;

	@FXML
	private Tooltip ttp_btnAlterar;

	@FXML
	private JFXButton btn_RemoverVenda;

	@FXML
	private Tooltip ttp_btnRemover;

	@FXML
	private VBox vb_venda;

	@FXML
	private FlowPane fp_mapaVendas;

	@FXML
	private VBox vb_mapaVenda;

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
	private Tooltip ttp_btnAdicionar;

	@FXML
	private JFXButton btn_Remover;

	@FXML
	private TableView<ItensVenda> tb_produtos;

	@FXML
	private TableColumn<ItensVenda, String> tc_codigo;

	@FXML
	private TableColumn<ItensVenda, String> tc_produto;

	@FXML
	private TableColumn<ItensVenda, String> tc_quantidade;

	@FXML
	private TableColumn<ItensVenda, String> tc_valorDeCompra;

	@FXML
	private Label lbl_TipoCondicao;

	@FXML
	private Tooltip ttp_lblTipoCondicao;

	@FXML
	private Label lbl_total;

	@FXML
	private JFXTextField txt_total;

	@FXML
	private ContextMenu ctm_total;

	@FXML
	private MenuItem mi_total;

	@FXML
	private Label lbl_comanda;

	@FXML
	private JFXTextField txt_comanda;

	@FXML
	private Label lbl_BaixarConta1;

	@FXML
	private Tooltip ttp_lblBaixarConta1;

	@FXML
	private ContextMenu ctm_lblBaixarConta1;

	@FXML
	private MenuItem mi_lblBaixarConta1;

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
	private JFXCheckBox cb_bCredito;

	@FXML
	private JFXCheckBox cb_bDebito;

	@FXML
	private JFXCheckBox cb_bDinheiro;

	@FXML
	private JFXCheckBox cb_bCheque;

	@FXML
	private Label lbl_cbbCliente;

	@FXML
	private JFXComboBox<Cliente> cbb_cliente;

	@FXML
	private JFXButton btn_fecharVenda;

	@FXML
	private Tooltip ttp_btnFecharVenda;

	@FXML
	private JFXButton btn_Voltar;

	@FXML
	private Tooltip ttp_btnSair11;

	Venda venda;
	ItensVenda itensVenda;
	Categoria categoria;
	Produto produto;
	TipoVenda tipoVenda;
	Cliente cliente;
	VendaDAO vendaDAO;
	CategoriaDAO categoriaDAO;
	ProdutoDAO produtoDAO;
	TipoVendaDAO tipoVendaDAO;
	ClienteDAO clienteDAO;
	ItensVendaDAO itensVendaDAO;
	List<Categoria> listCategoria = new ArrayList<Categoria>();
	List<Produto> listProduto = new ArrayList<Produto>();
	List<Cliente> listCliente = new ArrayList<Cliente>();
	List<TipoVenda> listTipoVenda = new ArrayList<TipoVenda>();
	List<Venda> listVenda = new ArrayList<Venda>();
	List<JFXButton> listbutton = new ArrayList<JFXButton>();
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String corErro = "-fx-border-color: red;";
	String corNormal = "-fx-border-color:white";
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
	Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	ButtonType btnSim = new ButtonType("Sim");
	ButtonType btnNao = new ButtonType("Não");
	JFXAutoCompletePopup<Categoria> autoCompletePopupCategoria = new JFXAutoCompletePopup<Categoria>();
	JFXAutoCompletePopup<Produto> autoCompletePopupProduto = new JFXAutoCompletePopup<Produto>();
	JFXAutoCompletePopup<Cliente> autoCompletePopupCliente = new JFXAutoCompletePopup<Cliente>();
	JFXAutoCompletePopup<TipoVenda> autoCompletePopupTipoVenda = new JFXAutoCompletePopup<TipoVenda>();
	private ObservableList<Integer> obsl_parcelas;
	private ObservableList<Categoria> obslCategoria;
	private ObservableList<Produto> obslProduto;
	private ObservableList<ItensVenda> obslItensVenda;
	private ObservableList<TipoVenda> obslTipoVenda;
	Double valorTotal;
	public void iniciaDAO() {

		categoriaDAO = new CategoriaDAO();
		produtoDAO = new ProdutoDAO();
		tipoVendaDAO = new TipoVendaDAO();
		clienteDAO = new ClienteDAO();
		itensVendaDAO = new ItensVendaDAO();
		vendaDAO = new VendaDAO();
	}

	public void iniciaModel() {

		categoria = new Categoria();
		produto = new Produto();
		tipoVenda = new TipoVenda();
		cliente = new Cliente();
		itensVenda = new ItensVenda();
		venda = new Venda();
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
		itensVenda = new ItensVenda();
		Boolean erro = false;
		if (!txt_adicionarQuantidade.getText().equals("")) {
			int quant = Integer.parseInt(txt_adicionarQuantidade.getText());
			if (quant >= 1 && quant <= 999) {
				itensVenda.setQuantidade(quant);
			} else {
				erro = false;

			}
		} else {
			erro = true;
		}
		itensVenda.setProduto(produto);
		itensVenda.setValor(produto.getPreco_compra());
		if (erro != true) {

			for (int i = 0;i < venda.getListaItensVenda().size() && erro != true; i++) {

				if (venda.getListaItensVenda().get(i).getProduto().getNome().equals(produto.getNome())) {
					venda.getListaItensVenda().remove(i);
					erro = true;
				}
			}

			venda.addItemVenda(itensVenda);
			valorTotal = 0.00;
			for (int i = 0; i < venda.getListaItensVenda().size(); i++) {
				valorTotal = valorTotal + (venda.getListaItensVenda().get(i).getQuantidade()
						* venda.getListaItensVenda().get(i).getProduto().getPreco_compra());
			}
			txt_total.setText(nf.format(valorTotal));
			carregaTela(venda);
			btn_Remover.setDisable(true);

		}
	}

	@FXML
	void OnActionCheque(ActionEvent event) {

	}

	@FXML
	void OnActionCredito(ActionEvent event) {

	}

	@FXML
	void OnActionDebito(ActionEvent event) {

	}

	@FXML
	void OnActionDinheiro(ActionEvent event) {

	}

	@FXML
	void OnActionFecharVenda(ActionEvent event) {

	}

	@FXML
	void OnActionIniciar(ActionEvent event) {
		// "/resources/logoPosto.png"

		venda = new Venda();
		if (cbb_tipoVenda.getSelectionModel().getSelectedIndex() != -1 && txt_comanda.getText().equals("") != true) {
			venda.setData(Date.valueOf((LocalDate.now())));
			venda.setTipoVenda(cbb_tipoVenda.getSelectionModel().getSelectedItem());
			venda.setId(null);
			venda.setValorTotal(0.00);
			venda.setComanda(Integer.parseInt(txt_comanda.getText()));
			Caixa caixa = new Caixa();
			CaixaDAO caixaDAO = new CaixaDAO();
			caixa = caixaDAO.listaCaixasVenda(LoginController.logado);
			venda.setCaixa(caixa);
			venda.setStatus("ABERTO");
			if (venda.getCaixa() == null) {
				FXNotification fxn;
				fxn = new FXNotification("Caixa não está aberto.", FXNotification.NotificationType.WARNING);
				fxn.show();
			} else {
				vendaDAO.save(venda);
				CarregaVenda();
			}
		}
	}

	public void desativaTela() {

	}

	public void CarregaVenda() {

		fp_mapaVendas.getChildren().clear();
		listVenda.clear();
		listVenda = new ArrayList<Venda>();
		listVenda = vendaDAO.listaVenda();
		for (Venda vv : listVenda) {
			AdicionarVenda(vv);
		}
	}
	
	public void verVenda(String comanda)
	{
		vb_mapaVenda.setVisible(true);
		venda = vendaDAO.FindToComanda(Integer.parseInt(comanda));
		venda.setListaItensVenda(new ArrayList<ItensVenda>());
	}

	public void AdicionarVenda(Venda adicionarVenda) {
		File imageFile = new File(
				"/home/gustavo/Developer/Projeto_Estagio2/ComboioRestaurante/src/main/java/estagio/view/resources/logoPosto2.png");
		Image image = new Image(imageFile.toURI().toString());
		JFXButton buttonVenda = new JFXButton(String.valueOf(adicionarVenda.getComanda()), new ImageView(image));
		buttonVenda.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                verVenda(buttonVenda.getText());
	            }
	        });
		buttonVenda.setContentDisplay(ContentDisplay.TOP);
		buttonVenda.setPrefWidth(98);
		buttonVenda.setPrefHeight(51);
		buttonVenda.setStyle("-fx-background-image: url(/resources/logoPosto.png);");
		listbutton.add(buttonVenda);
		fp_mapaVendas.getChildren().add(buttonVenda);
	}

	@FXML
	void OnActionRemover(ActionEvent event) {
		Boolean erro = false;
		for (int i = 0; i < venda.getListaItensVenda().size() && erro != true; i++) {

			if (venda.getListaItensVenda().get(i).getProduto().getNome().equals(itensVenda.getProduto().getNome())) {
				venda.getListaItensVenda().remove(i);
				erro = true;
			}
		}
		valorTotal = 0.00;
		for (int i = 0; i < venda.getListaItensVenda().size(); i++) {
			valorTotal = valorTotal + (venda.getListaItensVenda().get(i).getQuantidade()
					* venda.getListaItensVenda().get(i).getProduto().getPreco_compra());
		}
		txt_total.setText(nf.format(valorTotal));
		btn_Remover.setDisable(true);
		carregaTela(venda);
	}

	@FXML
	void OnActionRemoverVenda(ActionEvent event) {

	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		vb_mapaVenda.setVisible(false);
		desativaTelaVenda();
	}
	
	public void desativaTelaVenda() {
		cbb_categoria.setSelectionModel(null);
		cbb_Produto.setDisable(true);
		txt_quantAtual.setText("");
		txt_adicionarQuantidade.setText("");
		txt_valorUnitario.setText("");
		
	}

	public void carregaTela(Venda venda) {

		tb_produtos.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Produto.id"));
		tc_produto.setCellValueFactory(new PropertyValueFactory<>("Produto"));
		tc_quantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		tc_valorDeCompra.setCellValueFactory(new PropertyValueFactory<>("Produto"));

		tc_valorDeCompra.setCellValueFactory((data) -> {
			Double temp = data.getValue().getProduto().getPreco_compra();
			temp = temp * data.getValue().getQuantidade();
			txt_total.setText(nf.format(valorTotal));

			return new SimpleStringProperty(nf.format(temp));
		});

		tc_codigo.setCellValueFactory((data) -> {
			String id = data.getValue().getProduto().getId().toString();
			return new SimpleStringProperty(id);
		});

		if (!venda.getListaItensVenda().isEmpty()) {
			obslItensVenda = FXCollections.observableArrayList(venda.getListaItensVenda());
			tb_produtos.setItems(obslItensVenda);
		}

	}
	
	
	@FXML
	void OnMouseClickedProduto(MouseEvent event) {
		if (tb_produtos.getSelectionModel().getSelectedItem() != null) {
			this.setItensVenda(tb_produtos.getSelectionModel().getSelectedItem());

		}
	}

	public void setItensVenda(ItensVenda itensVenda) {
		this.itensVenda = itensVenda;
		btn_Remover.setDisable(false);
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
	void OnMouseselectionCliente(ActionEvent event) {

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

	@FXML
	void OnMouseselectionTipoVenda(ActionEvent event) {
		if (cbb_tipoVenda.getSelectionModel().getSelectedIndex() != -1) {
			btn_Iniciar.setDisable(false);
			// InitComboBoxCid();
		} else {
			btn_Iniciar.setDisable(true);
		}
	}

	public void inicializaComboBox() {
		// Combo box parcelas;
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

		// Combo box TipoVenda
		listTipoVenda = tipoVendaDAO.listar("");
		obslTipoVenda = FXCollections.observableArrayList(listTipoVenda);
		cbb_tipoVenda.setItems(obslTipoVenda);
	}

	public void regex() {
		txt_comanda.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 5));
		txt_adicionarQuantidade.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 5));

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iniciaDAO();
		iniciaModel();
		inicializaComboBox();
		desativaTela();
		InitComboBoxTipoVenda();
		InitComboBoxCategoria();
		InitComboBoxProduto();
		CarregaVenda();
		regex();
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

	private void InitComboBoxTipoVenda() {
		autoCompletePopupTipoVenda.getSuggestions().clear();
		autoCompletePopupTipoVenda.getSuggestions().addAll(cbb_tipoVenda.getItems());

		autoCompletePopupTipoVenda.setSelectionHandler(eventt -> {
			cbb_tipoVenda.setValue(eventt.getObject());
			cbb_tipoVenda.getSelectionModel().select(eventt.getObject());
		});
		autoCompletePopupTipoVenda.setStyle(
				"-fx-control-inner-background:WHITE;" + "-fx-accent: #00A279;" + "" + "-fx-font:14px 'Arial'");
		TextField editor = cbb_tipoVenda.getEditor();
		editor.textProperty().addListener(observable -> {
			// The filter method uses the Predicate to /filter the Suggestions defined above
			// I choose to use the contains method while ignoring cases
			autoCompletePopupTipoVenda.filter(item -> item.getNome().contains(editor.getText().toUpperCase()));
			autoCompletePopupTipoVenda.setHideOnEscape(false);
			autoCompletePopupTipoVenda.setAutoFix(false);
			// Hide the autocomplete popup if the filtered suggestions is empty or when the
			// box's original popup is open
			if (autoCompletePopupTipoVenda.getFilteredSuggestions().isEmpty() || cbb_tipoVenda.showingProperty().get()
					|| cbb_tipoVenda.getEditor().isFocused() == false) {
				autoCompletePopupTipoVenda.hide();
			} else {
				autoCompletePopupTipoVenda.show(editor);
			}
		});
		cbb_tipoVenda.setConverter(new StringConverter<TipoVenda>() {

			@Override
			public String toString(TipoVenda provinceState) {
				if (provinceState == null)
					return "";
				return provinceState.toString();
			}

			@Override
			public TipoVenda fromString(String string) {
				try {
					int index = cbb_tipoVenda.getSelectionModel().getSelectedIndex();
					return cbb_tipoVenda.getItems().get(index);
				} catch (Exception e) {
					return null;
				}

			}
		});
	}
}
