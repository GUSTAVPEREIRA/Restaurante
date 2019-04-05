package estagio.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import estagio.dao.CategoriaDAO;
import estagio.dao.MovimentoEstoqueDAO;
import estagio.dao.ProdutoDAO;
import estagio.model.Categoria;
import estagio.model.MovimentoEstoque;
import estagio.model.Produto;
import estagio.view.util.TextFieldFormatterHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class AtualizarEstoqueController implements Initializable {

	@FXML
	private StackPane sp_compra;

	@FXML
	private VBox vb_compra;

	@FXML
	private TableView<MovimentoEstoque> tb_produtos;

	@FXML
	private TableColumn<MovimentoEstoque, String> tc_codigo;

	@FXML
	private TableColumn<MovimentoEstoque, String> tc_produto;

	@FXML
	private TableColumn<MovimentoEstoque, String> tc_quantidade;

	@FXML
	private TableColumn<MovimentoEstoque, String> tc_valorMovimentacao;

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
	private JFXButton btn_retirar;

	@FXML
	private Tooltip ttp_btnAdicionar1;

	@FXML
	private JFXButton btn_Voltar;

	@FXML
	private Tooltip ttp_btnSair1;

	@FXML
	private Label lbl_motivo;

	@FXML
	private JFXTextField txt_motivo;

	Produto produto;
	ProdutoDAO produtoDAO;
	Categoria categoria;
	CategoriaDAO categoriaDAO;
	MovimentoEstoque movimentoEstoque;
	MovimentoEstoqueDAO movimentoEstoqueDAO;
	List<MovimentoEstoque> listaMovimento = new ArrayList<MovimentoEstoque>();
	private ObservableList<Categoria> obslCategoria;
	private ObservableList<Produto> obslProduto;
	private ObservableList<MovimentoEstoque> obslItensMovimento;
	JFXAutoCompletePopup<Categoria> autoCompletePopupCategoria = new JFXAutoCompletePopup<Categoria>();
	JFXAutoCompletePopup<Produto> autoCompletePopupProduto = new JFXAutoCompletePopup<Produto>();
	List<Categoria> listCategoria = new ArrayList<Categoria>();
	List<Produto> listProduto = new ArrayList<Produto>();
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	String corNormal = "-fx-border-color:white";
	String corErro = "-fx-border-color: red;";

	
	public void adiciona(String tipo)
	{
		movimentoEstoque = new MovimentoEstoque();
		Boolean erro = false;
		if (!txt_adicionarQuantidade.getText().equals("")) {
			int quant = Integer.parseInt(txt_adicionarQuantidade.getText());
			if (quant >= 1 && quant <= 999) {
				movimentoEstoque.setQuantidade(quant);
			} else {
				erro = false;

			}
		} 
		if (txt_motivo.getText().equals("")) 
		{
			erro = true;
		}
		else
			movimentoEstoque.setMotivo(txt_motivo.getText());;
		movimentoEstoque.setProduto(produto);
		if (erro != true) {

			for (int i = 0; i < listaMovimento.size() && erro != true; i++) {

				if (listaMovimento.get(i).getProduto().getNome().equals(produto.getNome())) {
					listaMovimento.remove(i);
					erro = true;
				}
			}

			movimentoEstoque.setTipo(tipo);
			listaMovimento.add(movimentoEstoque);
			carregaTela();
			btn_Gravar.setDisable(false);
			btn_Cancelar.setDisable(false);
			btn_Alterar.setDisable(true);
			btn_Remover.setDisable(true);

		}
	}
	
	@FXML
	void OnActionAdicionar(ActionEvent event) {
		
		adiciona("Entrada");

	}

	public void carregaTela() {

		tb_produtos.getItems().clear();
		tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Produto.id"));
		tc_produto.setCellValueFactory(new PropertyValueFactory<>("Produto"));
		tc_quantidade.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		tc_valorMovimentacao.setCellValueFactory(new PropertyValueFactory<>("Tipo"));

		tc_codigo.setCellValueFactory((data) -> {
			String id = data.getValue().getProduto().getId().toString();
			return new SimpleStringProperty(id);
		});

		if (!listaMovimento.isEmpty()) {
			obslItensMovimento = FXCollections.observableArrayList(listaMovimento);
			tb_produtos.setItems(obslItensMovimento);
		}
		tb_produtos.setRowFactory((row) -> {

			return new TableRow<MovimentoEstoque>() {
				@Override
				public void updateItem(MovimentoEstoque item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setStyle("-fx-background-color: none;");
					} else if (item.getTipo().equals("Entrada") == true) {
						setStyle("-fx-background-color: #00A279;");
					} else {
						setStyle("-fx-background-color: #FF211B;");
					}
				}
			};
		});

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

	@FXML
	void OnActionAlterar(ActionEvent event) {
		if (tb_produtos.getSelectionModel().getSelectedItem() != null) {
			vb_compra.setDisable(true);
			this.setMovimentoEstoque(tb_produtos.getSelectionModel().getSelectedItem());
			vb_telaProduto.setVisible(true);
			cbb_categoria.getSelectionModel().select(movimentoEstoque.getProduto().getCategoria());
			listProduto = produtoDAO.listarPorCategoria(cbb_categoria.getSelectionModel().getSelectedItem().getNome());
			obslProduto = FXCollections.observableArrayList(listProduto);
			cbb_Produto.setItems(obslProduto);
			cbb_Produto.getSelectionModel().select(movimentoEstoque.getProduto());
			txt_quantAtual.setText(String.valueOf(movimentoEstoque.getProduto().getEstoque()));
			txt_adicionarQuantidade.setText(String.valueOf(movimentoEstoque.getQuantidade()));
			txt_valorUnitario.setText(nf.format(movimentoEstoque.getProduto().getPreco_compra()));
		}
	}

	@FXML
	void OnActionCancelar(ActionEvent event) {
		tb_produtos.getItems().clear();

		listProduto = new ArrayList<Produto>();
		movimentoEstoque = new MovimentoEstoque();
		desativaTela();
	}

	public void desativaTela() {

		cbb_categoria.setStyle(corNormal);
		cbb_Produto.setStyle(corNormal);
		cbb_Produto.setDisable(true);
		cbb_categoria.getSelectionModel().select(0);
		// TextoBox
		txt_valorUnitario.setText("");
		txt_quantAtual.setText("");
		// Buttons
		btn_Alterar.setDisable(true);
		btn_Remover.setDisable(true);
		btn_Cancelar.setDisable(true);
		btn_Gravar.setDisable(true);

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
		movimentoEstoque.setTipo("Saida");
		adiciona("Saída");
	}

	@FXML
	void OnActionRetirar(ActionEvent event) {
		adiciona("Saída");
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_compra.setVisible(false);
	}

	@FXML
	void OnActionVoltar(ActionEvent event) {
		vb_telaProduto.setVisible(false);
		desativaTelaProduto();
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
	void OnMouseClickedProduto(MouseEvent event) {
		if (tb_produtos.getSelectionModel().getSelectedItem() != null) {
			this.setMovimentoEstoque(tb_produtos.getSelectionModel().getSelectedItem());

		}
	}

	public void setMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
		this.movimentoEstoque = movimentoEstoque;
		btn_Remover.setDisable(false);
		btn_Alterar.setDisable(false);
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
			btn_retirar.setDisable(true);
		}
	}

	@FXML
	void OnMouseselectionProduto(ActionEvent event) {
		if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1) {
			produto = cbb_Produto.getSelectionModel().getSelectedItem();
			txt_quantAtual.setText(String.valueOf(produto.getEstoque()));
			txt_valorUnitario.setText(nf.format(produto.getPreco_compra()));
			btn_adicionar.setDisable(false);
			btn_retirar.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		movimentoEstoque = new MovimentoEstoque();
		movimentoEstoqueDAO = new MovimentoEstoqueDAO();
		produtoDAO = new ProdutoDAO();
		categoriaDAO = new CategoriaDAO();
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
		txt_motivo.setTextFormatter(
				TextFieldFormatterHelper.getTextFieldToUpperFormatter("[a-zA-Z 0-9\\u00C0-\\u00FF]+", 100));
		txt_quantAtual.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 15));
		txt_valorUnitario.setTextFormatter(TextFieldFormatterHelper.getTextFieldDoubleFormatter(15, 2));
		txt_adicionarQuantidade.setTextFormatter(TextFieldFormatterHelper.getTextFieldToUpperFormatter("[0-9]+", 4));

	}

}
