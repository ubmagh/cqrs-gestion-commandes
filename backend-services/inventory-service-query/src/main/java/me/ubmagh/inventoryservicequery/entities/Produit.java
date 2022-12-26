package me.ubmagh.inventoryservicequery.entities;

import enums.EtatProduit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit {

    @Id
    private String id;

    private String nom;
    private Double prix;
    private int quantite;
    @Enumerated(EnumType.STRING)
    private EtatProduit etat;

    @ManyToOne()
    private Categorie categorie;

}
