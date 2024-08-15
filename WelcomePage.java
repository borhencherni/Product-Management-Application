package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class WelcomePage extends Application {

    @Override 
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome");
        
        //text1 and his font size
        Text headerText = new Text("You are Welcome ");
        headerText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
        
        //text2 and his font size
        Text headerText2= new Text("Join us as :");
        headerText2.setStyle("-fx-font-size: 30px; -fx-fill: black;");

        // Create buttons
        Button adminButton = new Button("Admin");
        Button customerButton = new Button("Customer");
        // Set button actions
        adminButton.setOnAction(e -> {
        	AdminButtonState.setAdminButtonClicked(true);
            // Switch to the ConnectPage
            ConnectPage connectPage = new ConnectPage();
            connectPage.start(primaryStage);
            System.out.println("Admin button clicked!");
        });

        customerButton.setOnAction(e -> {
        	CustomerButtonState.setCustomerButtonClicked(true);
            // Switch to the ConnectPage
            ConnectPage connectPage = new ConnectPage();
            connectPage.start(primaryStage);
            System.out.println("Customer button clicked!");
        }); 

        // Create layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(headerText,headerText2,adminButton, customerButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        adminButton.setPrefSize(100, 50); // Largeur : 100 pixels, Hauteur : 50 pixels
        customerButton.setPrefSize(100, 50); // Largeur : 120 pixels, Hauteur : 40 pixels


        // Set scene
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
