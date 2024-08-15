package application;

import javafx.beans.property.*;

public class Product {
	private final IntegerProperty idAdmin;
    private final StringProperty nomAdmin;
    private final IntegerProperty idProduit;
    private final StringProperty nomProduit;
    private final StringProperty genre;
    private final IntegerProperty taille;
    private final StringProperty couleur;
    private final IntegerProperty quantite;
    private final DoubleProperty prix;

    public Product(int idAdmin, String nomAdmin, int idProduit, String nomProduit, String genre, int taille, String couleur, int quantite, double prix) {
        this.idAdmin = new SimpleIntegerProperty(idAdmin);
        this.nomAdmin = new SimpleStringProperty(nomAdmin);
        this.idProduit = new SimpleIntegerProperty(idProduit);
        this.nomProduit = new SimpleStringProperty(nomProduit);
        this.genre = new SimpleStringProperty(genre);
        this.taille = new SimpleIntegerProperty(taille);
        this.couleur = new SimpleStringProperty(couleur);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.prix = new SimpleDoubleProperty(prix);
    }

    public int getIdAdmin() {
        return idAdmin.get();
    }

    public IntegerProperty idAdminProperty() {
        return idAdmin;
    }

    public String getNomAdmin() {
        return nomAdmin.get();
    }

    public StringProperty nomAdminProperty() {
        return nomAdmin;
    }

    public int getIdProduit() {
        return idProduit.get();
    }

    public IntegerProperty idProduitProperty() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit.get();
    }

    public StringProperty nomProduitProperty() {
        return nomProduit;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public int getTaille() {
        return taille.get();
    }

    public IntegerProperty tailleProperty() {
        return taille;
    }

    public String getCouleur() {
        return couleur.get();
    }

    public StringProperty couleurProperty() {
        return couleur;
    }

    public int getQuantite() {
        return quantite.get();
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public double getPrix() {
        return prix.get();
    }

    public DoubleProperty prixProperty() {
        return prix;
    }
 // toString() method
    @Override
    public String toString() {
        return "Product{" +
                "idAdmin=" + idAdmin +
                ", nomAdmin=" + nomAdmin +
                ", idProduit=" + idProduit +
                ", nomProduit=" + nomProduit +
                ", genre=" + genre +
                ", taille=" + taille +
                ", couleur=" + couleur +
                ", quantite=" + quantite +
                ", prix=" + prix +
                '}';
    }

}
