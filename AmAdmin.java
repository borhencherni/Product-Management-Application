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
	
public class AmAdmin extends Application {
    
    private User currentUser;

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
    
        Label usernameLabel = new Label("Username:*");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(300);
        Label passwordLabel = new Label("Password:*");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);
        Button loginButton = new Button("Login");
        Label statusLabel = new Label();
     
       
        // Event handler for login button
        loginButton.setOnAction(e -> {
        	if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ) {
                statusLabel.setText("Please fill out all mandatory fields");
            }else {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                statusLabel.setText("Login successful! Role: ");
                String adminId = iduser(username); // Obtient l'ID de l'administrateur
                currentUser = new User(username, password,adminId);
                SelectPageAdmin selectpageadmin =new SelectPageAdmin(this);
                selectpageadmin.start(primaryStage);
                System.out.println("login button clicked!");

            } else {
                statusLabel.setText("Invalid username or password.");
            }
            }
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
    	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\admin.txt"))) {
    	        String line;
    	        while ((line = reader.readLine()) != null) {
    	            String[] userData = line.split(",");
    	            if (userData.length >= 3 && userData[1].equals(username) && userData[2].equals(password)) {
    	                return true; // Username and password match
    	            }
    	        }
    	    } catch (IOException ex) {
    	        ex.printStackTrace();
    	    }
    	    return false; // No match found
    	}
    
    
    // Méthode pour rechercher l'ID de l'administrateur à partir de son nom d'utilisateur
    private String iduser(String username) {
        String adminId = ""; // Initialise l'ID de l'administrateur à une chaîne vide
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\admin.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminData = line.split(",");
                if (adminData.length >= 4 && adminData[1].equals(username)) {
                    adminId = adminData[0]; // ID de l'administrateur trouvé
                    break; // Arrête la recherche une fois que l'administrateur est trouvé
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return adminId; // Retourne l'ID de l'administrateur
    }
    
    
    // Getter method to retrieve the admin ID
    public String getAdminId() {
        return currentUser != null ? currentUser.getid() : null;
    }

    
    // Getter method to retrieve the admin username
    public String getAdminUsername() {
        return currentUser != null ? currentUser.getUsername() : null;
    }
   
    
    public static void main(String[] args) {
        launch(args);
    }
}
