/**
 * 
 */
package estagio.ui.notifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Gabriel L. P. Abreu
 *
 */
public class FXNotificationFactory {

	private static FXNotificationFactory _instance;
	
	@FXML
	private static StackPane _rootPane;
	@FXML
	StackPane notificationStack;
	
	public FXNotificationFactory() {
		
		notificationStack = new StackPane();
		notificationStack.setPickOnBounds(false);
		_rootPane.getChildren().add(notificationStack);
		
		
	}

	public void closeNotification(Pane notification) {
		notificationStack.getChildren().remove(notification);
	}

	public void showNewNotification(Pane notification) {

		notificationStack.getChildren().add(notification);
		StackPane.setAlignment(notification, Pos.BOTTOM_CENTER);
	}

	
	public synchronized static FXNotificationFactory getInstance() {

		return _instance;

	}

	public synchronized static void initialize(StackPane stackPane) {
	
		_rootPane = stackPane;
		
		if (_instance == null) {
			synchronized (FXNotificationFactory.class) {
				_instance = new FXNotificationFactory();
			}
		}
	}
}
