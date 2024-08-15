package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class AddProductView extends Application {
	
	private AmAdmin amAdmin; // Ajoutez une référence à AmAdmin

    // Ajoutez un constructeur pour recevoir l'instance d'AmAdmin
    public AddProductView(AmAdmin amAdmin) {
        this.amAdmin = amAdmin;
    }
	
    @Override
    public void start(Stage primaryStage) {
        // Create root layout
        AnchorPane root = new AnchorPane();
        root.setPrefSize(700, 500);
        
            
     // Create labels
        Label titleLabel = new Label("ADD YOUR NEW PRODUCT");
        titleLabel.setLayoutX(209);
        titleLabel.setLayoutY(8);
        titleLabel.setFont(new Font("System Bold",21));

        Label nameLabel = new Label("Name:");
        nameLabel.setLayoutX(36);
        nameLabel.setLayoutY(129);
        nameLabel.setFont(new Font("System Bold",15));

        Label genreLabel = new Label("Genre:");
        genreLabel.setLayoutX(36);
        genreLabel.setLayoutY(203);
        genreLabel.setFont(new Font("System Bold",15));

        Label tailleLabel = new Label("Taille:");
        tailleLabel.setLayoutX(36);
        tailleLabel.setLayoutY(281);
        tailleLabel.setFont(new Font("System Bold",15));

        Label couleurLabel = new Label("Couleur:");
        couleurLabel.setLayoutX(370);
        couleurLabel.setLayoutY(281);
        couleurLabel.setFont(new Font("System Bold",15));
        
        Label prixLabel = new Label("Prix:");
        prixLabel.setLayoutX(370);
        prixLabel.setLayoutY(203);
        prixLabel.setFont(new Font("System Bold",15));

        Label quantiteLabel = new Label("Quantité:");
        quantiteLabel.setLayoutX(370);
        quantiteLabel.setLayoutY(128);
        quantiteLabel.setFont(new Font("System Bold",15));

        Label productIdLabel = new Label("product_id:");
        productIdLabel.setLayoutX(209);
        productIdLabel.setLayoutY(68);
        productIdLabel.setFont(new Font("System Bold",15));

        // Create text fields
        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(135);
        nameTextField.setLayoutY(123);
        nameTextField.setPrefSize(198, 33);

        ComboBox<String> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll("Men", "Women");
        genreComboBox.setLayoutX(135);
        genreComboBox.setLayoutY(197);
        genreComboBox.setPrefSize(198, 34);

        TextField tailleTextField = new TextField();
        tailleTextField.setLayoutX(135);
        tailleTextField.setLayoutY(275);
        tailleTextField.setPrefSize(198, 34);

        TextField couleurTextField = new TextField();
        couleurTextField.setLayoutX(461);
        couleurTextField.setLayoutY(122);
        couleurTextField.setPrefSize(198, 34);

        TextField prixTextField = new TextField();
        prixTextField.setLayoutX(461);
        prixTextField.setLayoutY(197);
        prixTextField.setPrefSize(198, 34);

        TextField quantiteTextField = new TextField();
        quantiteTextField.setLayoutX(461);
        quantiteTextField.setLayoutY(275);
        quantiteTextField.setPrefSize(198, 34);

        TextField productIdTextField = new TextField();
        productIdTextField.setLayoutX(301);
        productIdTextField.setLayoutY(63);
        productIdTextField.setPrefSize(198, 33);
        
        DialogPane dialogPane = new DialogPane();
        dialogPane.setLayoutX(273);
        dialogPane.setLayoutY(400);
        dialogPane.setPrefSize(200, 54);

        // Create button
        
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setOnAction(e -> {
            // Open the Select Page Admin when the button is clicked
            openSelectPageAdmin(primaryStage);
        });
        
        Button addButton = new Button("Add");
        addButton.setLayoutX(273);
        addButton.setLayoutY(343);
        addButton.setPrefSize(154, 42);
        addButton.setFont(Font.font("System Bold Italic", 18));
        
     // Event handler for the add button
        addButton.setOnAction(e -> {
        	// Check if all fields are filled
            if (nameTextField.getText().isEmpty() || genreComboBox.getValue() == null ||
                tailleTextField.getText().isEmpty() || couleurTextField.getText().isEmpty() ||
                prixTextField.getText().isEmpty() || quantiteTextField.getText().isEmpty() ||
                productIdTextField.getText().isEmpty()) {
                // If any field is empty, display an error message
                dialogPane.setContentText("Please fill out all fields.");
            } else {
            String name = nameTextField.getText();
            String genre = genreComboBox.getValue();
            String taille = tailleTextField.getText();
            String couleur = couleurTextField.getText();
            String prix = prixTextField.getText();
            String quantite = quantiteTextField .getText();
            String productid = productIdTextField.getText();

         // Utilisez l'instance existante de AmAdmin
            String adminId = amAdmin.getAdminId();
            String adminUsername = amAdmin.getAdminUsername();
     
                   
            // Store product information along with admin details in a new file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\borhen\\Desktop\\TP JAVA\\Projecttest\\src\\application\\products.txt", true))) {
                String productData = adminId + "," + adminUsername +"," + productid + "," + name + "," + genre + "," + taille + "," + couleur + "," + quantite + "," + prix;
                writer.write(productData);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Clear fields after adding product
            nameTextField.clear();
            genreComboBox.getSelectionModel().clearSelection();
            tailleTextField.clear();
            couleurTextField.clear();
            prixTextField.clear();
            quantiteTextField.clear();
            productIdTextField.clear();
            
         // Set the text of the dialog pane to indicate successful product addition
            dialogPane.setContentText("Product added successfully");
            }});


        // Add components to root layout
        root.getChildren().addAll(
                backButton,titleLabel, nameLabel, genreLabel, tailleLabel, couleurLabel, prixLabel, quantiteLabel, productIdLabel,
                nameTextField, genreComboBox, tailleTextField, couleurTextField, prixTextField, quantiteTextField,
                productIdTextField, addButton
                ,dialogPane
        );

        // Create scene and set it on the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Product");
        primaryStage.show();
    }
    private void openSelectPageAdmin(Stage primaryStage) {
        SelectPageAdmin selectPageAdmin = new SelectPageAdmin(amAdmin);
        selectPageAdmin.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}



