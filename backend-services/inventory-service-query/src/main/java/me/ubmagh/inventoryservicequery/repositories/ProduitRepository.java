package me.ubmagh.inventoryservicequery.repositories;

import jdk.nashorn.internal.runtime.options.Option;
import me.ubmagh.inventoryservicequery.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, String> {

    Optional<List<Produit>> findByCategorie_Id(String categorieId);

}
