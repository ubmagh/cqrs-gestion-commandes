package dtos.produit;

import dtos.categorie.ShortCategorieDTO;
import enums.EtatProduit;
import lombok.Data;

@Data
public class LongProduitDTO {

    private String id;
    private String nom;
    private Double prix;
    private int quantite;
    private EtatProduit etat;
    private ShortCategorieDTO categorie;

}
