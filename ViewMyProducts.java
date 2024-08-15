package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewMyProducts extends Application {
	
	private AmAdmin amAdmin; // Ajoutez une référence à AmAdmin

    // Ajoutez un constructeur pour recevoir l'instance d'AmAdmin
    public ViewMyProducts(AmAdmin amAdmin) {
        this.amAdmin = amAdmin;
    }
    
	@Override
    public void start(Stage primaryStage) {
		
		String adminId = amAdmin.getAdminId();
        String adminUsername = amAdmin.getAdminUsername();
        
        AnchorPane root = new AnchorPane();
        root.setPrefSize(700, 400);

        Label titleLabel = new Label("MY AVAILABLE PRODUCTS");
        titleLabel.setLayoutX(200);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold",23));
        
     
        
        // Create table view
        TableView<Product> productTableView = new TableView<>();
        productTableView.setLayoutX(67);
        productTableView.setLayoutY(68);
        productTableView.setPrefSize(566, 218);

           // Create table columns
        TableColumn<Product, Integer> idAdminColumn = new TableColumn<>("ID Admin");
        idAdminColumn.setCellValueFactory(cellData -> cellData.getValue().idAdminProperty().asObject());

        TableColumn<Product, String> nomAdminColumn = new TableColumn<>("Nom Admin");
        nomAdminColumn.setCellValueFactory(cellData -> cellData.getValue().nomAdminProperty());

        TableColumn<Product, Integer> idProduitColumn = new TableColumn<>("ID Produit");
        idProduitColumn.setCellValueFactory(cellData -> cellData.getValue().idProduitProperty().asObject());

        TableColumn<Product, String> nomProduitColumn = new TableColumn<>("Nom Produit");
         nomProduitColumn.setCellValueFactory(cellData -> cellData.getValue().nomProduitProperty());

           TableColumn<Product, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

           TableColumn<Product, Integer> tailleColumn = new TableColumn<>("Taille");
           tailleColumn.setCellValueFactory(cellData -> cellData.getValue().tailleProperty().asObject());

           TableColumn<Product, Integer> quantiteColumn = new TableColumn<>("Quantité");
           quantiteColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());

           TableColumn<Product, String> couleurColumn = new TableColumn<>("Couleur");
           couleurColumn.setCellValueFactory(cellData -> cellData.getValue().couleurProperty());

           TableColumn<Product, Double> prixColumn = new TableColumn<>("Prix");
           prixColumn.setCellValueFactory(cellData -> cellData.getValue().prixProperty().asObject());

           // Add columns to table view
           productTableView.getColumns().add(idAdminColumn);
           productTableView.getColumns().add(nomAdminColumn);
           productTableView.getColumns().add(idProduitColumn);
           productTableView.getColumns().add(nomProduitColumn);
           productTableView.getColumns().add(genreColumn);
           productTableView.getColumns().add(tailleColumn);
           productTableView.getColumns().add(quantiteColumn);
           productTableView.getColumns().add(couleurColumn);
           productTableView.getColumns().add(prixColumn);
           
        // Read products from file and filter by admin ID and name
           String filename = "C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\products.txt";
           ObservableList<Product> products = getProductsFromFile(filename, Integer.parseInt(adminId) , adminUsername);
           System.out.println(products);
           productTableView.setItems(products);
           
           Button backButton = new Button("Back");
           backButton.setLayoutX(20);
           backButton.setLayoutY(20);
           backButton.setOnAction(e -> {
               // Open the Select Page Admin when the button is clicked
               openSelectPageAdmin(primaryStage);
           });


        Button deleteButton = new Button("DELETE");
        deleteButton.setLayoutX(442);
        deleteButton.setLayoutY(322);
        deleteButton.setPrefSize(84, 38);
        deleteButton.setFont(new Font("System Bold",16));

       
        TextField productIdField = new TextField();
        productIdField.setLayoutX(200);
        productIdField.setLayoutY(300);
        productIdField.setPrefSize(198, 33);

        Label productIdLabel = new Label("Product id:");
        productIdLabel.setLayoutX(67);
        productIdLabel.setLayoutY(305);
        productIdLabel.setFont(new Font("System Bold",16));

        TextField nameField = new TextField();
        nameField.setLayoutX(200);
        nameField.setLayoutY(349);
        nameField.setPrefSize(198, 33);

        Label nameLabel = new Label("Name:");
        nameLabel.setLayoutX(67);
        nameLabel.setLayoutY(354);
        nameLabel.setFont(new Font("System Bold",16));
        
        // Wrap the TableView in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(productTableView);
        scrollPane.setLayoutX(67);
        scrollPane.setLayoutY(68);
        scrollPane.setPrefSize(566, 218);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scroll bar

        root.getChildren().addAll(titleLabel, productTableView ,scrollPane, deleteButton, productIdField, productIdLabel, nameField, nameLabel,
        backButton);
        deleteButton.setOnAction(e -> {
            // Retrieve the ID and name of the product to remove
            int productIdToRemove = Integer.parseInt(productIdField.getText());
            String productNameToRemove = nameField.getText();

            // Find the product in the ObservableList
            Product productToRemove = null;
            for (Product product : productTableView.getItems()) {
                if (product.getIdProduit() == productIdToRemove && product.getNomProduit().equals(productNameToRemove)) {
                    productToRemove = product;
                    break;
                }
            }

            if (productToRemove != null) {
                // Remove the selected product from the ObservableList
                productTableView.getItems().remove(productToRemove);
                // Update the products.txt file (optional)
                updateProductsFile(filename, productToRemove);
            } else {
                System.out.println("Product not found.");
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Available Products Form");
        primaryStage.show();
    }
	
	// Method to update the products.txt file
	/*private void updateProductsFile(String filename, Product productToRemove) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        StringBuilder content = new StringBuilder();
	        while ((line = reader.readLine()) != null) {
	            // Check if the line corresponds to the product to remove
	            String[] parts = line.split(",");
	            int idProduit = Integer.parseInt(parts[2]);
	            String nomProduit = parts[3];
	            if (idProduit != productToRemove.getIdProduit() || !nomProduit.equals(productToRemove.getNomProduit())) {
	                content.append(line).append("\n");
	            }
	        }
	        // Write the updated content back to the file
	        Files.write(Paths.get(filename), content.toString().getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/
	
	private void updateProductsFile(String filename, Product productToRemove) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        StringBuilder content = new StringBuilder();
	        while ((line = reader.readLine()) != null) {
	            // Check if the line corresponds to the product to remove
	            String[] parts = line.split(",");
	            if (parts.length >= 4) { // Check if the array has at least 4 elements
	                int idProduit = Integer.parseInt(parts[2]); // Ensure index 2 exists
	                String nomProduit = parts[3]; // Ensure index 3 exists
	                if (idProduit != productToRemove.getIdProduit() || !nomProduit.equals(productToRemove.getNomProduit())) {
	                    content.append(line).append("\n");
	                }
	            }
	        }
	        // Write the updated content back to the file
	        Files.write(Paths.get(filename), content.toString().getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	// Method to read products from file and filter by admin ID and name
    private ObservableList<Product> getProductsFromFile(String filename, int adminId, String adminName) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	 // Parse line and create Product object
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    int idAdmin = Integer.parseInt(parts[0]);
                    String nomAdmin = parts[1];
                    int idProduit = 0;
                    if (!parts[2].isEmpty()) {
                        idProduit = Integer.parseInt(parts[2]);
                    }
                    String nomProduit = parts[3];
                    String genre = parts[4];
                    int taille = 0;
                    if (!parts[5].isEmpty()) {
                        taille = Integer.parseInt(parts[5]);
                    }
                    String couleur = parts[7];
                    int quantite = 0;
                    if (!parts[6].isEmpty()) {
                        quantite = Integer.parseInt(parts[6]);
                    }
                    double prix = Double.parseDouble(parts[8]);
                // Add product to list if admin ID and name match
                if (idAdmin == adminId && nomAdmin.equals(adminName)) {
                    products.add(new Product(idAdmin, nomAdmin, idProduit, nomProduit, genre, taille, couleur, quantite, prix));
                }
            }
            }} catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    private void openSelectPageAdmin(Stage primaryStage) {
        SelectPageAdmin selectPageAdmin = new SelectPageAdmin(amAdmin);
        selectPageAdmin.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
