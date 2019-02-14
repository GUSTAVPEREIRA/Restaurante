/**
 * 
 */
package estagio.view.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Gabriel L. P. Abreu
 *
 */
public class UISetup {

	private static UISetup instance;

	/**
	 * 
	 */
	private UISetup() {
		// TODO Auto-generated constructor stub
	}

	public static UISetup getInstance() {

		if (instance == null)
			instance = new UISetup();

		return instance;
	}

	public Scene createSceneWithParentMinimalSize(Parent parent) {

		if (parent instanceof javafx.scene.layout.Pane) {
			double minWidth = ((javafx.scene.layout.Pane) parent).getMinWidth();
			double minHeight = ((javafx.scene.layout.Pane) parent).getMinHeight();
			return new Scene(parent, minWidth, minHeight);

		}

		return new Scene(parent, 640, 480);
	}

//	public void setStageIcon(Stage stage, String path) {
//		// Set Stage Icon
//		{
//			InputStream IconInputStream = DworDesktopClient.class.getResourceAsStream(path);
//			Image icon = new Image(IconInputStream);
//			stage.getIcons().add(icon);
//		}
//
//	}

	public FXMLLoader changeScene(Stage stage, Class<?> objectclass, String fxmlPath) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(objectclass.getResource(fxmlPath));

		Parent parent = fxmlLoader.load();
		Scene scene = createSceneWithParentMinimalSize(parent);
		stage.setScene(scene);

		return fxmlLoader;
	}

//	public FXMLLoader changeSceneFromDWORMainStage(String fxmlPath) throws IOException {
//		return changeSceneFromDWORMain(DworDesktopClient.getStage(), fxmlPath);
//
//	}
//	
//	public FXMLLoader changeSceneFromDWORMain(Stage stage, String fxmlPath) throws IOException {
//		return changeScene(stage, DworDesktopClient.class, fxmlPath);
//	}
	
	public FXMLLoader initNewScene(Stage stage, Class<?> objectclass, String fxmlPath) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(objectclass.getResource(fxmlPath));
		Parent parent = fxmlLoader.load();
		Scene scene = createSceneWithParentMinimalSize(parent);
		stage.setScene(scene);
		return fxmlLoader;
	}

	public FXMLLoader launchNewScene(Class<?> objectclass, String fxmlPath) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = initNewScene(stage, objectclass, fxmlPath);
		stage.show();
		return fxmlLoader;
	}
	
	public FXMLLoader launchNewSceneAndWait(Class<?> objectclass, String fxmlPath) throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = initNewScene(stage, objectclass, fxmlPath);
                stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		return fxmlLoader;
	}
}
