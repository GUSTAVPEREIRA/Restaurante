package estagio.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class ManualController implements Initializable{

    @FXML
    private StackPane sp_manual;

    @FXML
    private VBox vb_manual;

    @FXML
    private WebView webViewContainer;

	public void reset() {
		// TODO Auto-generated method stub

	}


	public StackPane getRootPane() {
		return sp_manual;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		URL url = getClass().getResource("/estagio/manual/HelpGuideMain.html");
		webViewContainer.getEngine().load(url.toExternalForm());
		webViewContainer.getEngine().setOnError((event) -> {
			System.out.println(event.getMessage());
		});
		
	}

}
