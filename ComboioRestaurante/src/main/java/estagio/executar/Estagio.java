/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.executar;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Pereira
 */
public class Estagio extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/estagio/view/LoginFXML.fxml"));
    	
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Restaurante Comboio");
    	stage.getIcons().add(new Image(getClass().getResourceAsStream("/estagio/view/resources/iconPosto.png")));
    	stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
