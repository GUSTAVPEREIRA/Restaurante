package estagio.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import estagio.ui.notifications.FXNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BackupController {

	@FXML
	private StackPane sp_backup;

	@FXML
	private VBox vb_backup;

	@FXML
	private JFXButton btn_Backup;

	@FXML
	private Tooltip ttp_btnNovo;

	@FXML
	private JFXButton btn_restore;

	@FXML
	private Tooltip ttp_btnRestore;

	@FXML
	private JFXTextField txt_backup;

	@FXML
	private JFXTextField txt_restore;

	@FXML
	private JFXButton btn_Sair;

	@FXML
	private JFXButton btn_selecioneRestore;

	@FXML
	private JFXButton btn_selecioneBackup;

	@FXML
	private Tooltip ttp_btnSair;

	File fileBackup = null;
	File fileRestore = null;

	@FXML
	void OnActionBackup(ActionEvent event) throws IOException, InterruptedException {

		if (fileBackup != null) {
			fazBackup(fileBackup.getAbsolutePath());
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Selecione o caminho para realizar o backup",
					FXNotification.NotificationType.WARNING);
			fxn.show();
		}

	}

	@FXML
	void OnActionBackupEnter(KeyEvent event) throws IOException, InterruptedException {
		if (fileBackup != null) {
			fazBackup(fileBackup.getAbsolutePath());
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Selecione o caminho para realizar o backup",
					FXNotification.NotificationType.WARNING);
			fxn.show();
		}
	}

	@FXML
	void OnActionRestore(ActionEvent event) throws IOException, InterruptedException {
		if (fileRestore != null) {
			restaura(fileRestore.getAbsolutePath());
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Selecione o caminho para realizar o restaurar",
					FXNotification.NotificationType.WARNING);
			fxn.show();
		}
	}

	@FXML
	void OnActionRestoreEnter(KeyEvent event) throws IOException, InterruptedException {
		if (fileRestore != null) {
			restaura(fileRestore.getAbsolutePath());
		} else {
			FXNotification fxn;
			fxn = new FXNotification("Selecione o caminho para realizar o restaurar",
					FXNotification.NotificationType.WARNING);
			fxn.show();
		}
	}

	@FXML
	void OnActionSair(ActionEvent event) {
		sp_backup.setVisible(false);
	}

	@FXML
	void OnActionSairEnter(KeyEvent event) {
		sp_backup.setVisible(false);
	}

	@FXML
	void OnActionSelecioneRestore(ActionEvent event) {
		selecioneRestore();
	}

	@FXML
	void OnActionSelecioneRestoreEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			selecioneRestore();
		}
	}

	@FXML
	void OnActionSelecioneBackup(ActionEvent event) {
		selecioneBackup();
	}

	@FXML
	void OnActionSelecioneBackupEnter(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			selecioneBackup();
		}
	}

	public void selecioneRestore() {
		FileChooser f = new FileChooser();
		f.setTitle("Selecione o local para restaurar");
		f.setSelectedExtensionFilter(new ExtensionFilter("Text Files", "*.backup"));
		fileRestore = f.showOpenDialog(new Stage());
		if (fileRestore != null) {
			txt_restore.setText(fileRestore.getAbsolutePath());
		}
	}

	public void selecioneBackup() {
		DirectoryChooser f = new DirectoryChooser();
		f.setTitle("Selecione o local do backup");
		fileBackup = f.showDialog(new Stage());
		if (fileBackup != null) {
			txt_backup.setText(fileBackup.getAbsolutePath());
		}
	}

	public static void restaura(String filepath) throws IOException, InterruptedException {

		ProcessBuilder pb = new ProcessBuilder("pg_restore", "--dbname=restaurante", "--host=localhost", "--port=5432",
				"--username=postgres", filepath);

		pb.environment().put("PGPASSWORD", "postgres123");

		try {

			ProcessBuilder pb0 = new ProcessBuilder("psql", "--host=localhost", "--port=5432", "--username=postgres",
					"--command=select pg_terminate_backend(pid) from pg_stat_activity where datname='restaurante';");
			pb0.environment().put("PGPASSWORD", "postgres123");
			ProcessBuilder pb1 = new ProcessBuilder("psql", "--host=localhost", "--port=5432", "--username=postgres",
					"--command=DROP DATABASE IF EXISTS restaurante");
			pb1.environment().put("PGPASSWORD", "postgres123");
			ProcessBuilder pb2 = new ProcessBuilder("psql", "--host=localhost", "--port=5432", "--username=postgres",
					"--command=CREATE DATABASE restaurante");
			pb2.environment().put("PGPASSWORD", "postgres123");

			final Process process0 = pb0.start();

			final BufferedReader r0 = new BufferedReader(new InputStreamReader(process0.getErrorStream()));
			String line = r0.readLine();
			while (line != null) {
				System.err.println(line);
				line = r0.readLine();
			}
			r0.close();

			process0.waitFor();
			process0.destroy();

			final Process process1 = pb1.start();

			final BufferedReader r1 = new BufferedReader(new InputStreamReader(process1.getErrorStream()));
			line = r1.readLine();
			while (line != null) {
				System.err.println(line);
				line = r1.readLine();
			}
			r1.close();

			process1.waitFor();
			process1.destroy();

			final Process process2 = pb2.start();

			final BufferedReader r2 = new BufferedReader(new InputStreamReader(process2.getErrorStream()));
			line = r2.readLine();
			while (line != null) {
				System.err.println(line);
				line = r2.readLine();
			}
			r2.close();

			process2.waitFor();
			process2.destroy();

			final Process process = pb.start();

			final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			line = r.readLine();
			while (line != null) {
				System.err.println(line);
				line = r.readLine();
			}
			r.close();

			process.waitFor();
			process.destroy();
			FXNotification fxn;
			fxn = new FXNotification("Restore concluído. Por favor feche o programa e abra novamente.",
					FXNotification.NotificationType.INFORMATION);
			fxn.show();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public static void fazBackup(String filepath) throws IOException, InterruptedException {

		DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH-mm-ss");
		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String hora = LocalDateTime.now().format(horaFormat);
		String data = LocalDate.now().format(dataFormat);

		filepath += "/backup_" + data + "_" + hora + ".backup";

		ProcessBuilder pb = new ProcessBuilder("pg_dump", "--host=localhost", "--port=5432", "--dbname=restaurante",
				"--username=postgres", "--format=custom", "--file=" + filepath);
		pb.environment().put("PGPASSWORD", "postgres123");

		try {
			final Process process = pb.start();

			final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String line = r.readLine();
			while (line != null) {
				System.err.println(line);
				line = r.readLine();
			}
			r.close();

			process.waitFor();
			process.destroy();
			FXNotification fxn;
			fxn = new FXNotification("Backup concluído.",
					FXNotification.NotificationType.INFORMATION);
			fxn.show();

		} catch (IOException e) {
			FXNotification fxn;
			fxn = new FXNotification(e.getMessage()+"",
					FXNotification.NotificationType.ERROR);
			fxn.show();
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
