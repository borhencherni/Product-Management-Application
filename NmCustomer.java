package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class NmCustomer extends Application {
	@Override
    public void start(Stage primaryStage) {
		Button backButton = new Button("Back");
        
	     // Event handler for the back button
	    	backButton.setOnAction(e -> {
	    	    ConnectPage connectPage = new ConnectPage();
	    	    connectPage.start(primaryStage);
	    	});
       
    
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
                    passwordField.getText().isEmpty() || password1Field.getText().isEmpty() ) {
                statusLabel.setText("Please fill out all mandatory fields");
            }  
            
            else {
	        	String username = usernameField.getText();
	        	String email = emailField.getText();
	        	String password = passwordField.getText();
	        	//String phone = phoneField.getText();
	        	
	        	// verifier que les deux mots de passe sont les meme
	        	
	        	if (!password.equals(password1Field.getText())) {
	                statusLabel.setText("Password do not match");
	            } 
	        	else { 
	        		if(!UsernameCustChecker.isUsernameAvailable(username)) {
	        			 statusLabel.setText("Invalid username.");
		                }
		        		
		        	else {
		        	// Create a string representing the user data
		        	String userData = username + "," + password +',' + email ;
		 
		        	// Write the user data to a text file
		        	try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\customer.txt", true))) {
		        	    writer.write(userData);
		        	    writer.newLine(); // Add a new line for the next user data
		        	    writer.close();
		        	    statusLabel.setText("Your data saved successfully!");
		        	} catch (IOException ex) {
		        	    statusLabel.setText("Error saving user data: " + ex.getMessage());
		        	}
	        	}}}
        	
        // Redirect to AmAdmin
        AmCustomer amcustomer = new AmCustomer();
        amcustomer.start(primaryStage);     
        System.out.println("create button clicked!");
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
        		backButton,
                usernameLabel, usernameField,
                emailLabel,emailField,
                passwordLabel, passwordField,
                password1Label,password1Field,
                phoneLabel,phoneField,
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

}
