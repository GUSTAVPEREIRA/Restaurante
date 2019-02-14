/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author Pereira
 */
public class CadastroController {
//xml bugadao

    @FXML
    private AnchorPane ap_cadastros;
    @FXML
    private JFXButton Categoria;
    @FXML
    private JFXButton Cliente;
    @FXML
    private JFXButton Cidade;
    @FXML
    private JFXButton Estado;
    @FXML
    private JFXButton Empresa;
    @FXML
    private JFXButton Produto;
    @FXML
    private JFXButton Usuario;
    @FXML
    private VBox VB_Menu;
    @FXML
    private JFXButton Fornecedor;
    @FXML
    private JFXDrawer draw_menu;
    @FXML
    private JFXButton Sair;
    private Node node;
    @FXML
    private AnchorPane ap_menu;
    public void inicialization()
    {

 
    }

    @FXML
    private void OnActionCategoria(ActionEvent event) throws IOException {
        
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/CategoriaFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionCliente(ActionEvent event) throws IOException {
        
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/ClienteFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionCidade(ActionEvent event) throws IOException {
    
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/CidadeFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionEstado(ActionEvent event) throws IOException {
        
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/EstadoFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionEmpresa(ActionEvent event) throws IOException {
    
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/EmpresaFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionProduto(ActionEvent event) throws IOException {
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/ProdutoFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionUsuario(ActionEvent event) throws IOException {
        
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/UsuarioFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionFornecedor(ActionEvent event) throws IOException {
        
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/FornecedorFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionSair(ActionEvent event) throws IOException{
     
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
        ap_menu.getChildren().setAll(node);        
    }
    
    
    
}
