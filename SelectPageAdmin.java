package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SelectPageAdmin extends Application {
	
	public Stage primaryStage;
    public AnchorPane root;
    private AmAdmin amAdmin; // Ajoutez une référence à AmAdmin
    
 // Ajoutez un constructeur pour recevoir l'instance d'AmAdmin
    public SelectPageAdmin(AmAdmin amAdmin) {
        this.amAdmin = amAdmin;
    }
    
	@Override
	
	
		public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        root = new AnchorPane();
	        root.setPrefSize(700, 400);
	        showAdminInterface();}
	     

	    private void showAdminInterface() {
	        root.getChildren().clear();
        // Create label
        Label titleLabel = new Label("PLEASE CHOOSE ONE OPTION");
        titleLabel.setLayoutX(168);
        titleLabel.setLayoutY(38);
        titleLabel.setFont(new Font("System Bold",24));

        // Create buttons
        Button addProductButton = new Button("Add a product");
        addProductButton.setLayoutX(261);
        addProductButton.setLayoutY(180);
        addProductButton.setPrefSize(178, 40);
        addProductButton.setFont(new Font("System Bold",18));
        addProductButton.setOnAction(e -> openAddProductView());

        Button viewAllProductsButton = new Button("View all products");
        viewAllProductsButton.setLayoutX(261);
        viewAllProductsButton.setLayoutY(245);
        viewAllProductsButton.setPrefSize(178, 40);
        viewAllProductsButton.setFont(new Font("System Bold",18));
        viewAllProductsButton.setOnAction(e -> openCustomerInterface());

        Button viewMyProductsButton = new Button("View my products");
        viewMyProductsButton.setLayoutX(261);
        viewMyProductsButton.setLayoutY(110);
        viewMyProductsButton.setPrefSize(178, 40);
        viewMyProductsButton.setFont(new Font("System Bold",18));
        viewMyProductsButton.setOnAction(e -> openViewMyProducts());
        
        // Create buttons
        Button logoutButton= new Button("logout");
        logoutButton.setLayoutX(670);
        logoutButton.setLayoutY(20);
        logoutButton.setOnAction(e -> {
            // Open the Select Page Admin when the button is clicked
            openAmadmin(primaryStage);
        });

        // Add components to root layout
        root.getChildren().addAll(titleLabel, addProductButton, viewAllProductsButton, viewMyProductsButton,logoutButton);

        // Create scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Interface");
        primaryStage.show();
    }
	    
	    private void openAmadmin(Stage primaryStage) {
		    primaryStage.close(); // Close the current stage
		    AmAdmin amadmin = new AmAdmin();
		    amadmin.start(primaryStage); // Start the AmCustomer interface in the same stage
		}
	
	private void openAddProductView() {
        AddProductView addProductView = new AddProductView(amAdmin);
        addProductView.start(primaryStage);
    }

    private void openCustomerInterface() {
        CustomerInterface customerInterface = new CustomerInterface();
        customerInterface.start(primaryStage);
    }
    
    // Method to open the view for admin to see their products
    private void openViewMyProducts() {
        ViewMyProducts viewMyProducts = new ViewMyProducts(amAdmin);
        viewMyProducts.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}   
    



