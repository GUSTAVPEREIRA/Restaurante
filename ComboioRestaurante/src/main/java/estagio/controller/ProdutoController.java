/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.FornecedorDAO;
import estagio.dao.ProdutoDAO;
import estagio.model.Fornecedor;
import estagio.model.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import estagio.dao.CategoriaDAO;
import estagio.model.Categoria;
import estagio.view.util.TextFieldFormatterHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class ProdutoController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXButton btn_Novo;
    @FXML
    private JFXButton btn_Gravar;
    @FXML
    private JFXButton btn_Excluir;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private JFXButton btn_Sair;
    @FXML
    private JFXComboBox<Fornecedor> cbb_forId;
    @FXML
    private Label lbl_codigo;
    @FXML
    private Tooltip ttp_lblCodigo;
    @FXML
    private Label lbl_nome;
    @FXML
    private Tooltip ttp_lblNome;
    @FXML
    private Label lbl_fornecedor;
    @FXML
    private Tooltip ttp_lblFornecedor;
    @FXML
    private Label lbl_preco;
    @FXML
    private Tooltip ttp_lblPreco;
    @FXML
    private JFXTextField txt_preco;
    @FXML
    private Label lbl_preco_compra;
    @FXML
    private Tooltip ttp_lblPrecoC;
    @FXML
    private Label lbl_categoria;
    @FXML
    private Tooltip ttp_lblCategoria;
    @FXML
    private JFXComboBox<Categoria> cbb_categoria;
    @FXML
    private JFXTextField txt_preco_compra;
    @FXML
    private ContextMenu ctm_Nome;
    @FXML
    private ContextMenu ctm_Fornecedor;
    @FXML
    private ContextMenu ctm_Preco;
    @FXML
    private ContextMenu ctm_PrecoCompra;
    @FXML
    private ContextMenu ctm_Categoria;
    @FXML
    private AnchorPane ap_busca;
    @FXML
    private JFXButton btn_filtro;
    @FXML
    private JFXTextField txt_filtro;
    @FXML
    private TableColumn<Produto, String> tc_codigo;
    @FXML
    private TableColumn<Produto, String> tc_nome;
    @FXML
    private TableColumn<Produto, String> tc_fornecedor;
    @FXML
    private TableColumn<Produto, String> tc_preco;
    @FXML
    private TableColumn<Produto, String> tc_preco_compra;
    @FXML
    private TableView<Produto> tb_produto;
    @FXML
    private JFXButton btn_voltar;
    
    private String corErro = "-fx-border-color:red;";
    private String corNormal = "-fx-border-color:white";        
    private Produto produto;
    private ProdutoDAO produtoDAO;
    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDAO;
    private Categoria categoria;
    private CategoriaDAO categoriaDAO;
    private List<Fornecedor> listaFornecedor;
    private ObservableList<Fornecedor> obslFornecedor;
    private List<Categoria> listaCategoria;
    private ObservableList<Categoria> obslCategoria;
    private ObservableList<Produto> obslProduto;
    private List<Produto> listProduto;
    private TextFieldFormatterHelper tffh;
    @FXML
    private AnchorPane ap_produto;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Override
    public void initialize(URL url, ResourceBundle rb) {
       listProduto = new ArrayList();
       produto = new Produto();      
       fornecedor = new Fornecedor();
       categoria = new Categoria();
       resetaDAO();
       listaFornecedor = fornecedorDAO.listar("");
       obslFornecedor = FXCollections.observableArrayList(listaFornecedor);
       cbb_forId.setItems(obslFornecedor);       
       cbb_forId.getSelectionModel().select(-1);
       listaCategoria = categoriaDAO.listar("");
       obslCategoria = FXCollections.observableArrayList(listaCategoria);
       cbb_categoria.setItems(obslCategoria);      
       cbb_categoria.getSelectionModel().select(-1);
       tffh = new TextFieldFormatterHelper();
       txt_nome.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
       txt_preco.setTextFormatter(tffh.getTextFieldDoubleFormatter(15, 2));
       txt_preco_compra.setTextFormatter(tffh.getTextFieldDoubleFormatter(15, 2));
       txt_filtro.setTextFormatter(tffh.getUpperCaseTextFieldFormatter());
       desativaTela();
       
    }    

    public void desativaTela()
    {
       ap_busca.setVisible(false);
       categoria = new Categoria();
       fornecedor = new Fornecedor();
       resetaDAO();
       txt_nome.setText("");     
       txt_codigo.setText("0");
       txt_preco.setText("");
       txt_preco_compra.setText("");       
       ctm_Nome.hide();
       ctm_Categoria.hide();
       ctm_Fornecedor.hide();
       ctm_PrecoCompra.hide();
       ctm_Preco.hide();
       txt_nome.setStyle(corNormal);
       txt_preco.setStyle(corNormal);
       txt_preco_compra.setStyle(corNormal);
       cbb_categoria.setStyle(corNormal);
       cbb_forId.setStyle(corNormal);
       btn_Excluir.setDisable(true);
       btn_Cancelar.setDisable(true);
       cbb_categoria.getSelectionModel().select(null);
       cbb_forId.getSelectionModel().select(null);
       listProduto.clear();
    }
    
    public void ativaTela()
    {
       btn_Excluir.setDisable(false);
       btn_Cancelar.setDisable(false);
    }
    
    @FXML
    private void OnActionBuscar(ActionEvent event) {
        ap_busca.setVisible(true);
        carregaTela(txt_filtro.getText());
    }

    @FXML
    private void OnActionNovo(ActionEvent event) {
        desativaTela();
    }   
    
    @FXML
    private void OnActionGravar(ActionEvent event) {
        Boolean erro=false;
        if (txt_nome.getText().equals("") == true) {
            erro = true;
            txt_nome.setStyle(corErro);
            ctm_Nome.show(txt_nome,Side.RIGHT,10,0);
            
        }
        else
        {
            produto.setNome(txt_nome.getText());
            txt_nome.setStyle(corNormal);
            ctm_Nome.hide();
            
        }
        
        if (txt_preco.getText().equals("") == true) {
            erro = true;
            txt_preco.setStyle(corErro);
            ctm_Preco.show(txt_preco,Side.RIGHT,10,0);
            
        }
        else
        {
            txt_preco.setText(txt_preco.getText().replace(",", "."));
            produto.setPreco(Double.parseDouble(txt_preco.getText()));
            txt_preco.setStyle(corNormal);
            ctm_Preco.hide();
            
        }                
        if (txt_preco_compra.getText().equals("") == true) {
            erro = true;
            txt_preco_compra.setStyle(corErro);
            ctm_PrecoCompra.show(txt_preco_compra,Side.RIGHT,10,0);
        }
        else
        {
            txt_preco_compra.setText(txt_preco_compra.getText().replace(",", "."));
            produto.setPreco_compra(Double.parseDouble(txt_preco_compra.getText()));
            txt_preco_compra.setStyle(corNormal);
            ctm_PrecoCompra.hide();                                    
        }
                     
        if (txt_codigo.getText().equals("") == true) {
            erro = true;
        }
        else
        {
            produto.setId(Long.parseLong(txt_codigo.getText()));
        }
        
        if (cbb_forId.getSelectionModel().getSelectedItem() == null) {
            erro = true;
            cbb_forId.setStyle(corErro);
            ctm_Fornecedor.show(cbb_forId,Side.RIGHT,10,0);
        }
        else
        {
            fornecedor = cbb_forId.getSelectionModel().getSelectedItem();
            produto.setFornecedor(fornecedor);
            cbb_forId.setStyle(corNormal);
            ctm_Fornecedor.hide();
        }
        if (cbb_categoria.getSelectionModel().getSelectedItem() == null) {
            erro = true;
            cbb_categoria.setStyle(corErro);
            ctm_Categoria.show(cbb_categoria,Side.RIGHT,10,0);
        }
        else
        {
            categoria = cbb_categoria.getSelectionModel().getSelectedItem();
            produto.setCategoria(categoria);
            cbb_categoria.setStyle(corNormal);
            ctm_Categoria.hide();
        }
        
        if (erro != true) 
        {
            Alert dialogoExe = new Alert(Alert.AlertType.INFORMATION);
            dialogoExe.setTitle("Gravar");     
            if (produto.getId()== 0) { 
            //O hibernate interpreta que será uma nova inserção somente se a variável
            //estiver nula quando for do tipo long
                produto.setId(null);
                produtoDAO.inserir(produto);
                dialogoExe.setHeaderText("Novo produto: "+produto.getNome()+" inserido.");
            }
            else
            {
                produtoDAO.alterar(produto);
                dialogoExe.setHeaderText("Novo produto: "+produto.getNome()+" alterado.");
            }
            dialogoExe.showAndWait();
            desativaTela();
        }          
    }

    @FXML
    private void OnActionExcluir(ActionEvent event) {
        produtoDAO = new ProdutoDAO();
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        javafx.scene.control.ButtonType btnSim = new javafx.scene.control.ButtonType("Sim");
        javafx.scene.control.ButtonType btnNao = new javafx.scene.control.ButtonType("Não");
        dialogoExe.setTitle("");
        dialogoExe.setHeaderText("Você deseja realmente excluir "+produto.getNome()+"?");
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent(b -> 
        {
            if (b == btnSim)
            {
                if (txt_codigo.getText().equals("0") != true && !txt_codigo.getText().isEmpty()) 
                {
                  produto.setId(Long.parseLong(txt_codigo.getText()));
                  Boolean deletado = produtoDAO.Deletar(produto);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  
                  if (deletado == true) 
                  {                        
                    alert.setContentText("Produto "+produto.getNome()+" Excluido");
                    alert.show();             
                    desativaTela();
                  }
                  else
                  {
                    alert.setContentText("Produto "+produto.getNome()+" não pode ser excluido, verifique se há vendas com está produto.");
                    alert.show();       
                  }
                }     
            }
        });        
    }

    @FXML
    private void OnActionCancelar(ActionEvent event) {
        desativaTela();
    }

    @FXML
    private void OnActionSair(ActionEvent event) {
        ap_produto.setVisible(false);
    }
    
    public void carregaTela(String palavra)
    {
        listProduto.clear();
        tb_produto.getItems().clear();
        tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tc_fornecedor.setCellValueFactory(new PropertyValueFactory<>("Fornecedor"));
        tc_preco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        tc_preco_compra.setCellValueFactory(new PropertyValueFactory<>("Preco_compra"));
        listProduto = produtoDAO.listar(palavra.toUpperCase());
        if (!listProduto.isEmpty()) 
        {
            obslProduto = FXCollections.observableArrayList(listProduto);
            tb_produto.setItems(obslProduto);        
        }
    }


    @FXML
    private void OnActionFiltro(ActionEvent event) 
    {
        if (txt_filtro.getText() != null) 
        {
          carregaTela(txt_filtro.getText()); 
        }
    }
    
    @FXML
    private void Limitetxt_filtro(KeyEvent event) {
        if (txt_filtro.getText().length() == 100) {
            event.consume();
        }
    }

    @FXML
    private void OnMouseClickedProduto(MouseEvent event) {
       if (tb_produto.getSelectionModel().getSelectedItem() != null) 
       {
           this.setProduto(tb_produto.getSelectionModel().getSelectedItem());
       }
    }
    
    public void resetaDAO()
    {
        fornecedorDAO = new FornecedorDAO();
        categoriaDAO = new CategoriaDAO();
        produtoDAO = new ProdutoDAO();
    }

    public void setProduto(Produto produto)
    {    
        resetaDAO();
        this.produto = produto;
        txt_nome.setText(produto.getNome());
        txt_codigo.setText(""+produto.getId());
        txt_preco.setText(Double.toString(produto.getPreco()).replace(".", ","));
        txt_preco_compra.setText(Double.toString(produto.getPreco_compra()).replace(".", ","));
        cbb_forId.getSelectionModel().select(1); 
        cbb_forId.getSelectionModel().select(produto.getFornecedor());    
        cbb_categoria.getSelectionModel().select(1);  
        cbb_categoria.getSelectionModel().select(produto.getCategoria());    
        limpaBuscas();
        ativaTela();
    }
    
    public void limpaBuscas()
    {
        listProduto.clear();
        tb_produto.getItems().clear();
        ap_busca.setVisible(false);
        txt_filtro.setText("");
    }
    
    @FXML
    private void OnActionVoltar(ActionEvent event) {
        limpaBuscas();
        
    }

    @FXML
    private void OnKeyPressedEnter(KeyEvent event) {
            txt_filtro.setOnKeyPressed((KeyEvent event1) -> {
                if (event1.getCode() == KeyCode.ENTER) {
                    carregaTela(txt_filtro.getText());
                }
            });
    }
    
}
