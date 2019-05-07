/**
 * 
 */
package estagio.ui.notifications;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Gabriel L. P. Abreu
 *
 */
public class FXNotificationFactory {

	private static FXNotificationFactory _instance;
	private static StackPane _rootPane;

	StackPane notificationStack;

	public FXNotificationFactory() {

		notificationStack = new StackPane();
		notificationStack.setPickOnBounds(false);
		_rootPane.getChildren().add(notificationStack);

		//notificationStack.setOnMouseClicked((mouseEvent) -> {
			//notificationStack.getChildren().clear();
	//	});

	}

	private void ensureNotificationStackIsOnTop() {

		if (_rootPane.getChildren().indexOf(notificationStack) != (_rootPane.getChildren().size() - 1)) {
			_rootPane.getChildren().remove(notificationStack);
			_rootPane.getChildren().add(notificationStack);
		}

	};

	public void closeNotification(Pane notification) {
		notificationStack.getChildren().remove(notification);
	}

	public void showNewNotification(Pane notification) {
		ensureNotificationStackIsOnTop();
		notificationStack.getChildren().add(notification);
		StackPane.setAlignment(notification, Pos.TOP_LEFT);
		
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
