/**
 * 
 */
package estagio.ui.notifications;

import java.io.IOException;

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
	
	
	private HBox notificationPane;
	private FXNotificationController notificationController;
	private Timeline timeline;

	public FXNotification(String message) {
		buildNotification();
		buildTimeline(Duration.millis(4000));
		notificationController.setMessage(message);
	}
	
	public FXNotification(String message, NotificationType notificationType) {
		this(message);
		notificationController.alterarCor();
		
	}

	public FXNotification(String message, Duration duration) {
		buildNotification();
		buildTimeline(duration);
		notificationController.setMessage(message);
	}

	public FXNotification(String message, Duration duration, NotificationType notificationType) {
		buildNotification();
		buildTimeline(duration);
		notificationController.setMessage(message);
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

	private void buildTimeline(Duration duration) {
		timeline = new Timeline(new KeyFrame(duration, ae -> closeNotification()));

		timeline.play();

		notificationController.setTimeline(timeline);
	}

	public enum NotificationType{
		WARNING,
		INFORMATION,
		ERROR;
	}
	
}
