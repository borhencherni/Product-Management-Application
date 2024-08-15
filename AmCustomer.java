package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AmCustomer extends Application {

    @Override
    public void start(Stage primaryStage) {
    	Button backButton = new Button("Back");
    	backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        
     // Event handler for the back button
    	backButton.setOnAction(e -> {
    	    ConnectPage connectPage = new ConnectPage();
    	    connectPage.start(primaryStage);
    	});


        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300);
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);
        Button loginButton = new Button("Login");
        Label statusLabel = new Label();

        // Event handler for login button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
            	CustInterface customerinterface=new CustInterface();
        		customerinterface.start(primaryStage);
        		currentcustomerUsername.setCurrentUser(username);
                statusLabel.setText("Login successful! Role: ");
            } else {
                statusLabel.setText("Invalid username or password.");
            }
		System.out.println("login button clicked!");
        });


        // Layout
           Pane pane = new Pane();
           VBox layout = new VBox(20);
           layout.setPadding(new Insets(20));
           layout.getChildren().addAll(
                   usernameLabel, usernameField,
                   passwordLabel, passwordField,
                   loginButton, statusLabel
           );

           // Set the position of the layout within the Pane
           layout.setLayoutX(20);
           layout.setLayoutY(60);

           // Add the backButton to the Pane directly
           pane.getChildren().addAll(layout, backButton);
           Scene scene = new Scene(pane, 400, 400);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Authentication");
        primaryStage.show();
    }

    // Method to authenticate a user
    private boolean authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 3 && userData[0].trim().equals(username) && userData[1].trim().equals(password)) {
                    return true; // Username and password match
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; // No match found
    }
    public static void main(String[] args) {
        launch(args);
    }

}
