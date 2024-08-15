package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConnectPage extends Application {
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect ");
        
        //text and his font size
        Text headerText = new Text("JUST FEW STEPS BEFORE START ");
        headerText.setLayoutX(30);
        headerText.setLayoutY(100);
        headerText.setFont(new Font("System Bold",23));        
        // Create buttons
        Button NewButton = new Button("New member");
        NewButton.setPrefSize(200, 50);
        //NewButton.setAlignment(Pos.CENTER);
        NewButton.setLayoutX(100);
        NewButton.setLayoutY(150);

        
        Button AncientButton = new Button("Already have an account");
        AncientButton.setPrefSize(200, 50);
        //AncientButton.setAlignment(Pos.CENTER);
        AncientButton.setLayoutX(100);
        AncientButton.setLayoutY(220);
        
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
 
        // Set button actions
        NewButton.setOnAction(e -> {
        	if (AdminButtonState.isAdminButtonClicked()) {
            	NmAdmin login=new NmAdmin();
            	login.start(primaryStage);
        	
            // Action for new button (open new page or print message)
            System.out.println("New member button clicked!");
        	}
        	else{
        		if(CustomerButtonState.isCustomerButtonClicked()) {
        			NmCustomer login=new NmCustomer();
                	login.start(primaryStage);
            	
                // Action for new button (open new page or print message)
                System.out.println("New member button clicked!");
        		}}});
        	        
        AncientButton.setOnAction(e -> {
        	if (AdminButtonState.isAdminButtonClicked()) {
        	AmAdmin login=new AmAdmin();
        	login.start(primaryStage);
            // Action for ancient button (open customer page or print message)
            System.out.println("Already button clicked!");
        	}
        	else{
        		if(CustomerButtonState.isCustomerButtonClicked()) {
        			AmCustomer login=new AmCustomer();
                	login.start(primaryStage);
            	
                // Action for new button (open new page or print message)
                System.out.println("New member button clicked!");
        		}}});
        
        backButton.setOnAction(e ->{
        	openWelcomePage(primaryStage);
        	
        });

       
     // Create root layout
        AnchorPane root = new AnchorPane();
        root.setPrefSize(400, 400);
        root.getChildren().addAll(headerText,NewButton, AncientButton,backButton);


        // Set scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	private void openWelcomePage(Stage primaryStage) {
        WelcomePage welcomepage = new WelcomePage();
        welcomepage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
