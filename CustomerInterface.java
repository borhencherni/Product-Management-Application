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

public class CustomerInterface extends Application {
	
	private AmAdmin amAdmin; // Ajoutez une référence à AmAdmin

	@Override
    public void start(Stage primaryStage) {
        

        // Create title label
        Label titleLabel = new Label("Products available now");
        titleLabel.setLayoutX(215);
        titleLabel.setLayoutY(14);
        titleLabel.setFont(new Font("System Bold", 23));

     // Create table view
        TableView<Product> productTableView = new TableView<>();
        productTableView.setLayoutX(11);
        productTableView.setLayoutY(100);
        productTableView.setPrefSize(722, 200);

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
        
        
     // Read data from products.txt and populate the table
        ObservableList<Product> products = readProductsFromFile("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\products.txt");
        productTableView.setItems(products);

        
        
        // Create buttons
        
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setOnAction(e -> {
            // Open the Select Page Admin when the button is clicked
            openSelectPageAdmin(primaryStage);
        });
        
        

        // Wrap the TableView in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(productTableView);
        scrollPane.setLayoutX(11);
        scrollPane.setLayoutY(100);
        scrollPane.setPrefSize(722, 200);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable horizontal scroll bar

     // Create root layout
        AnchorPane root = new AnchorPane();
        root.setPrefSize(750, 400);
        
        // Add components to root layout
        root.getChildren().addAll(backButton,titleLabel, scrollPane);

        // Create scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("All Products");
        primaryStage.show();
    }
	
	private ObservableList<Product> readProductsFromFile(String filePath) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productData = line.split(",");
                if (productData.length == 9) {
                    int idAdmin = Integer.parseInt(productData[0]);
                    String nomAdmin = productData[1];
                    int idProduit = Integer.parseInt(productData[2]);
                    String nomProduit = productData[3];
                    String genre = productData[4];
                    int taille = Integer.parseInt(productData[5]);
                    String couleur = productData[7];
                    int quantite = Integer.parseInt(productData[6]);
                    double prix = Double.parseDouble(productData[8]);
                    products.add(new Product(idAdmin, nomAdmin, idProduit, nomProduit, genre, taille, couleur, quantite, prix));
                }
            }
        } catch (IOException e) {
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