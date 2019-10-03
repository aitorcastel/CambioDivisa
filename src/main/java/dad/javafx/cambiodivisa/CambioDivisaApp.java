package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {

	private TextField divisaOriginalText, divisaCambioText;
	private ComboBox<String> divisaOriginalCombo, divisaCambioCombo;
	private Button cambiarButton;


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub


		// ------------------------------

		divisaOriginalText = new TextField();
		divisaOriginalText.setPromptText("0");
		divisaOriginalText.setMaxWidth(80);

		divisaCambioText = new TextField();
		divisaCambioText.setMaxWidth(80);
		divisaCambioText.setDisable(true);
		divisaCambioText.setStyle("-fx-opacity: 1.0;");
		divisaCambioText.setPromptText("0");

		divisaOriginalCombo = new ComboBox<String>();
		divisaOriginalCombo.getItems().addAll("Euro", "Dolar", "Yen", "Libra");
		divisaOriginalCombo.setPromptText("Euro");
		divisaOriginalCombo.getSelectionModel().selectFirst();
		
		divisaCambioCombo = new ComboBox<String>();
		divisaCambioCombo.getItems().addAll("Euro", "Dolar", "Yen", "Libra");
		divisaCambioCombo.setPromptText("Euro");
		divisaCambioCombo.getSelectionModel().selectFirst();

		cambiarButton = new Button("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e -> oncambiarButtonAction(e));

		// ------------------------------

		HBox originalBox = new HBox(5, divisaOriginalText, divisaOriginalCombo);
		originalBox.setAlignment(Pos.CENTER);

		HBox cambioBox = new HBox(5, divisaCambioText, divisaCambioCombo);
		cambioBox.setAlignment(Pos.CENTER);

		// ------------------------------

		VBox root = new VBox(5, originalBox, cambioBox, cambiarButton);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Object oncambiarButtonAction(ActionEvent e) {

		try {

			/*
			 * Divisa euro = new Divisa("Euro", 1.0); 
			 * Divisa libra = new Divisa("Libra",0.8873); 
			 * Divisa dolar = new Divisa("Dolar", 1.2007); 
			 * Divisa yen = new Divisa("Yen", 133.59);
			 * 
			 * Divisa origen = yen; 
			 * Divisa destino = dolar;
			 * 
			 * Double cantidad = 2000.0;
			 * 
			 * System.out.println(destino.fromEuro(origen.toEuro(cantidad))); // convierte 2000 yenes en dólares
			 */
			int x = 0, y = 0;
			Divisa euro = new Divisa("Euro", 1.0);
			Divisa libra = new Divisa("Libra", 0.8873);
			Divisa dolar = new Divisa("Dolar", 1.2007);
			Divisa yen = new Divisa("Yen", 133.59);

			Divisa[] tipoDivisaArray = { euro, libra, dolar, yen };

			while (tipoDivisaArray[x].getNombre()
					.equalsIgnoreCase(divisaOriginalCombo.getSelectionModel().getSelectedItem()) != true)
				x++;
			while (tipoDivisaArray[y].getNombre()
					.equalsIgnoreCase(divisaCambioCombo.getSelectionModel().getSelectedItem()) != true)
				y++;

			Double cantidad = Double.parseDouble(divisaOriginalText.getText());

			divisaCambioText
					.setPromptText(String.valueOf(tipoDivisaArray[y].fromEuro(tipoDivisaArray[x].toEuro(cantidad))));

		} catch (Exception x) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("ERROR");
			alert.setContentText("Campo vacío / Valor no admito");
			alert.showAndWait();

		}

		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
