package dtos.produit;

import enums.EtatProduit;
import lombok.Data;

@Data
public class ShortProduitDTO {

    private String id;
    private String nom;
    private Double prix;
    private int quantite;
    private EtatProduit etat;
}
