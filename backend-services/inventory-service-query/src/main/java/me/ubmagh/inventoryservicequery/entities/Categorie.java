package me.ubmagh.inventoryservicequery.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categorie {

    @Id
    private String id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits = new ArrayList<>();

}
