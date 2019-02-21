package estagio.ui.notifications;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.control.TextArea;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class FXNotificationController implements Initializable {

	@FXML
	private HBox rootPane;
	@FXML
	private TextArea textAreaNotifications;
    @FXML
    private HBox principal;
	private Timeline timeline;
    private String corCheck = "-fx-border-color:#0cb388";
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void actCloseNotification(ActionEvent event) {
		FXNotificationFactory.getInstance().closeNotification(rootPane);
	}

	public Pane getNotificationPane() {
		return rootPane;
	}

	public void setMessage(String message) {
		textAreaNotifications.setText(message);

	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;

	}

	@FXML
	public void actMouseEntered(MouseEvent event) {
		
		if(timeline != null) {
			timeline.pause();
		}
	}

	@FXML
	public void actMouseExited(MouseEvent event) {
		if(timeline != null) {
			timeline.jumpTo(timeline.getTotalDuration().subtract(Duration.millis(2000)));
			timeline.play();
		}
	}

	public void alterarCor() {
		principal.setStyle(corCheck);
		
	}

}
