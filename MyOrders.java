package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyOrders extends Application {
	
	@Override
    public void start(Stage primaryStage) {
		 String currentUsername = currentcustomerUsername.getCurrentUser();
        // Create root layout
        AnchorPane root = new AnchorPane();
        root.setPrefSize(750, 400);
        
        Button backButton = new Button("Back");
    	backButton.setLayoutX(20);
        backButton.setLayoutY(20);

    	// Event handler for the back button
    	backButton.setOnAction(e -> {
    	    CustInterface custinterface = new CustInterface();
    	    custinterface.start(primaryStage);
    	});

        // Create table view
        TableView<Product> productTableView = new TableView<>();
        productTableView.setLayoutX(10);
        productTableView.setLayoutY(90);
        productTableView.setPrefSize(730, 200);

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

        TableColumn<Product, String> couleurColumn = new TableColumn<>("Couleur");
        couleurColumn.setCellValueFactory(cellData -> cellData.getValue().couleurProperty());

        TableColumn<Product, Integer> quantiteColumn = new TableColumn<>("Quantité");
        quantiteColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());

        TableColumn<Product, Double> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(cellData -> cellData.getValue().prixProperty().asObject());

        // Add columns to table view
        productTableView.getColumns().add(idAdminColumn);
        productTableView.getColumns().add(nomAdminColumn);
        productTableView.getColumns().add(idProduitColumn);
        productTableView.getColumns().add(nomProduitColumn);
        productTableView.getColumns().add(genreColumn);
        productTableView.getColumns().add(tailleColumn);
        productTableView.getColumns().add(couleurColumn);
        productTableView.getColumns().add(quantiteColumn);
        productTableView.getColumns().add(prixColumn);
        
        
     // Create Label for title
        Label titleLabel = new Label("ALL MY ORDERS");
        titleLabel.setLayoutX(282);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold",23));
        

        // Wrap the TableView in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(productTableView);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(90);
        scrollPane.setPrefSize(730,200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scroll bar


        // Add children to root layout
        root.getChildren().addAll(productTableView, titleLabel,scrollPane,backButton);

        // Create scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("All My Orders");
        primaryStage.show();
   
        // Populate the productTableView with products associated with the current customer
        ObservableList<Product> products = getProductsForCurrentCustomer(currentUsername);
        productTableView.setItems(products);
     }

	
	private ObservableList<Product> getProductsForCurrentCustomer(String currentCustomer) {
        ObservableList<Product> products = FXCollections.observableArrayList();       
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equals(currentCustomer)) {
                    int adminId = Integer.parseInt(data[1]);
                    int productId = Integer.parseInt(data[2]);
                    Product product = getProductDetails(adminId, productId);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    private Product getProductDetails(int adminId, int productId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && Integer.parseInt(data[0]) == adminId && Integer.parseInt(data[2]) == productId) {
                    // Create a Product object with the retrieved details
                    return new Product(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5]), data[7], Integer.parseInt(data[6]), Double.parseDouble(data[8]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null; // Product not found
    }

    public static void main(String[] args) {
        launch(args);
    }
        
}
