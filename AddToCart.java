package application;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddToCart extends Application {
	@Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(700, 400);
        
        Button backButton = new Button("Back");
    	backButton.setLayoutX(20);
        backButton.setLayoutY(20);

    	// Event handler for the back button
    	backButton.setOnAction(e -> {
    	    CustInterface custinterface = new CustInterface();
    	    custinterface.start(primaryStage);
    	});

        Label titleLabel = new Label("PLEASE CHOOSE THE PRODUCT YOU WANT");
        titleLabel.setLayoutX(163);
        titleLabel.setLayoutY(23);
        titleLabel.setFont(new Font("System Bold", 18));

        TextField productIDField = new TextField();
        productIDField.setLayoutX(250);
        productIDField.setLayoutY(206);
        productIDField.setPrefSize(198, 33);

        TextField adminIDField = new TextField();
        adminIDField.setLayoutX(14);
        adminIDField.setLayoutY(206);
        adminIDField.setPrefSize(198, 34);

        TextField quantityField = new TextField();
        quantityField.setLayoutX(486);
        quantityField.setLayoutY(206);
        quantityField.setPrefSize(198, 34);
        
        TextField usernameField = new TextField();
        usernameField.setLayoutX(311);
        usernameField.setLayoutY(84);
        usernameField.setPrefSize(198, 33);
        
        DialogPane dialogPane = new DialogPane();
        dialogPane.setLayoutX(135);
        dialogPane.setLayoutY(264);
        dialogPane.setPrefSize(454, 54);

        Button addButton = new Button("Add");
        addButton.setLayoutX(271);
        addButton.setLayoutY(330);
        addButton.setPrefSize(154, 42);
        addButton.setFont(new Font("System Bold", 18));
        addButton.setOnAction(e -> {
            // When the "Add" button is clicked, write the product details to the file
            String username = usernameField.getText();
            int adminId = Integer.parseInt(adminIDField.getText());
            int productId = Integer.parseInt(productIDField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            writeProductToFile(username, adminId, productId, quantity);
            
         // Clear all the text fields
            usernameField.clear();
            adminIDField.clear();
            productIDField.clear();
            quantityField.clear();
            
            // Set the text of the dialog pane to indicate successful product addition
            dialogPane.setContentText("Product added successfully");
        });

        Label quantityLabel = new Label("Quantité:");
        quantityLabel.setLayoutX(501);
        quantityLabel.setLayoutY(167);
        quantityLabel.setFont(new Font("System Bold", 15));

        Label yourUsernameLabel = new Label("You Username:");
        yourUsernameLabel.setLayoutX(184);
        yourUsernameLabel.setLayoutY(89);
        yourUsernameLabel.setFont(new Font("System Bold", 15));
        
        Label adminIDLabel = new Label("admin_id:");
        adminIDLabel.setLayoutX(29);
        adminIDLabel.setLayoutY(167);
        adminIDLabel.setFont(new Font("System Bold", 15));

        Label productIDLabel = new Label("product_id:");
        productIDLabel.setLayoutX(269);
        productIDLabel.setLayoutY(167);
        productIDLabel.setFont(new Font("System Bold", 15));
        
        root.getChildren().addAll(
                dialogPane,titleLabel, productIDField, adminIDField, quantityField, addButton,
                quantityLabel, yourUsernameLabel, usernameField, adminIDLabel, productIDLabel
                ,backButton
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add To Cart");
        primaryStage.show();
    }
	
	private void writeProductToFile(String username, int adminId, int productId, int quantity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\\\Users\\\\borhen\\\\Desktop\\\\TP JAVA\\\\Projecttest\\\\src\\\\application\\\\cart.txt", true))) {
            // Append product details to the file
            writer.write(username + "," + adminId + "," + productId + "," + quantity);
            writer.newLine(); // Add newline for next entry
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
