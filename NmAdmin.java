package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NmAdmin extends Application {
	// File path to store the next ID
    private static final String NEXT_ID_FILE = "C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\Id admin.txt";
    
    // Static variable to keep track of the next available ID
    private static int nextId;
    
    // Instance variable for the admin's ID
    private int id;

    @Override
    public void start(Stage primaryStage) {
    	
    	Button backButton = new Button("Back");
        
     // Event handler for the back button
    	backButton.setOnAction(e -> {
    	    ConnectPage connectPage = new ConnectPage();
    	    connectPage.start(primaryStage);
    	});
    	
        // Read the next ID from the file or initialize to 1 if file doesn't exist
        nextId = readNextIdFromFile();
        
        id = nextId++; // Assign the next available ID and increment it for the next admin

        Label usernameLabel = new Label("Username: *");
        TextField usernameField = new TextField();
        Label emailLabel = new Label("E_mail: *");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password: *");
        PasswordField passwordField = new PasswordField();
        Label password1Label = new Label("Rewrite Your Password: *");
        PasswordField password1Field = new PasswordField();
        Label phoneLabel = new Label("You Phone Number:");
        TextField phoneField = new TextField();
        Button createButton = new Button("Create An Account");
        Label statusLabel = new Label();

        // Event handler for login button
        createButton.setOnAction(e -> {
            // Vérifier si tous les champs sont remplis
            if (usernameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                    passwordField.getText().isEmpty() || password1Field.getText().isEmpty()) {
                statusLabel.setText("Please fill out all mandatory fields");
            } else {
                // verifier que les deux mots de passe sont les memes
                if (!(passwordField.getText()).equals(password1Field.getText())) {
                    statusLabel.setText("Password do not match");
                    System.out.println("passwords do not match");
                } else {
                	String username = usernameField.getText();
                    String email = emailField.getText();
                    String password = passwordField.getText();
                    if (!UsernameChecker.isUsernameAvailable(username)) {
                        statusLabel.setText("Invalid username.");
                    } else {
                        // Create a string representing the user data
                        String userData = id + "," + username + "," + password + ',' + email;

                        // Write the user data to a text file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\admin.txt", true))) {
                            writer.write(userData);
                            writer.newLine(); // Add a new line for the next user data
                            statusLabel.setText("Your data saved successfully!");
                        } catch (IOException ex) {
                            statusLabel.setText("Error saving user data: " + ex.getMessage());
                        }
                    }
                }
                // Write the next ID to the file
                writeNextIdToFile(nextId);
                
                // Redirect to AmAdmin
                AmAdmin amadmin = new AmAdmin();
                amadmin.start(primaryStage);
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
        		backButton,
                usernameLabel, usernameField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                password1Label, password1Field,
                phoneLabel, phoneField,
                createButton, statusLabel
                
        );

        // Scene
        Scene scene = new Scene(layout, 500, 500);

        // Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Authentication");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Method to read the next ID from the file
    private int readNextIdFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NEXT_ID_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        // If file doesn't exist or cannot be read, return 1
        return 1;
    }

    // Method to write the next ID to the file
    private void writeNextIdToFile(int nextId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NEXT_ID_FILE))) {
            writer.write(String.valueOf(nextId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
}

