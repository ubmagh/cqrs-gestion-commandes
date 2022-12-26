package me.ubmagh.inventoryservicequery.repositories;

import me.ubmagh.inventoryservicequery.entities.Categorie;
import me.ubmagh.inventoryservicequery.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, String> {

    public Optional<Categorie> findByNomEquals(String nom);

}
