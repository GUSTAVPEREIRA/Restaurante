/**
 * 
 */
package estagio.ui.notifications;

import java.io.IOException;
import java.net.URL;

import javax.management.RuntimeErrorException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * @author Gabriel L. P. Abreu
 *
 */
public class FXNotification {

	static final String INFORMATION_STYLESHEET = "/estagio/ui/notifications/FXNotificationStylesheet_Information.css";
	static final String WARNING_STYLESHEET = "/estagio/ui/notifications/FXNotificationStylesheet_Warning.css";
	static final String ERROR_STYLESHEET = "/estagio/ui/notifications/FXNotificationStylesheet_Error.css";

	private HBox notificationPane;
	private FXNotificationController notificationController;
	private Timeline timeline;

	public FXNotification(String message) {
		buildNotification();
		buildTimeline(Duration.millis(7000));
		notificationController.setMessage(message);
	}

	public FXNotification(String message, NotificationType notificationType) {
		this(message);
		buildNotificationType(notificationType);
	}

	public FXNotification(String message, Duration duration) {
		buildNotification();
		buildTimeline(duration);
		notificationController.setMessage(message);
	}

	public FXNotification(String message, Duration duration, NotificationType notificationType) {
		this(message, duration);
		buildNotificationType(notificationType);
	}

	public void show() {
		FXNotificationFactory.getInstance().showNewNotification(notificationPane);
	}

	public void closeNotification() {
		FXNotificationFactory.getInstance().closeNotification(notificationPane);
	}

	private void buildNotification() {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(this.getClass().getResource("/estagio/ui/notifications/FXMLFXNotification.fxml"));

		HBox newNotification;
		try {
			newNotification = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeErrorException(null, "Failed to build notification");
		}

		notificationPane = newNotification;
		notificationController = fxmlLoader.<FXNotificationController>getController();
	}

	private void buildNotificationType(NotificationType notificationType) {
		if (notificationController == null)
			throw new UnsupportedOperationException(
					"buildNotification() must be used to instantiate notificationController");

		URL url = null;

		switch (notificationType) {
		case INFORMATION:
			url = this.getClass().getResource(INFORMATION_STYLESHEET);
			break;
		case WARNING:
			url = this.getClass().getResource(WARNING_STYLESHEET);
			break;
		case ERROR:
			url = this.getClass().getResource(ERROR_STYLESHEET);
			break;

		default:
			break;
		}

		notificationController.setRootStyleSheet(url);
	}

	private void buildTimeline(Duration duration) {
		timeline = new Timeline(new KeyFrame(duration, ae -> closeNotification()));

		timeline.play();

		notificationController.setTimeline(timeline);
	}

	public enum NotificationType {
		WARNING, INFORMATION, ERROR;
	}

}
