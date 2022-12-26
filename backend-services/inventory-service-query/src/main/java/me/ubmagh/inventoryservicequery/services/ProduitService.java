package me.ubmagh.inventoryservicequery.services;

import events.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.inventoryservicequery.entities.Categorie;
import me.ubmagh.inventoryservicequery.entities.Produit;
import me.ubmagh.inventoryservicequery.repositories.CategorieRepository;
import me.ubmagh.inventoryservicequery.repositories.ProduitRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import queries.GetAllProduitsQuery;
import queries.GetProduitByIdQuery;
import queries.GetProduitsByCategorieIdQuery;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProduitService {

    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    @QueryHandler
    public List<Produit> getAllProduitsList(GetAllProduitsQuery query){
        return produitRepository.findAll();
    }

    @QueryHandler
    public Produit getProduitById(GetProduitByIdQuery query){
        return produitRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Produit> getProduitByCategorieId(GetProduitsByCategorieIdQuery query){
        return produitRepository.findByCategorie_Id(query.getId()).get();
    }


    @EventHandler
    public void on(ProduitCreatedEvent event){
        Produit produit = new Produit(
                event.getId(),
                event.getNom(),
                event.getPrix(),
                event.getQuantite(),
                event.getEtat(),
                null
        );
        produitRepository.save(produit);
        log.info(" 👉👉 Produit créé ");
    }

    @EventHandler
    public void on(ProduitUpdatedEvent event){
        Produit produit = produitRepository.findById(event.getId()).get();
        produit.setEtat(event.getEtat());
        produit.setNom(event.getNom());
        produit.setPrix( event.getPrix());
        produit.setQuantite(event.getQuantite());
        produitRepository.save(produit);
        log.info(" 👉👉 Produit mis-à-jour ");
    }


    @EventHandler
    public void on(ProduitDeletedEvent event){
        produitRepository.deleteById(event.getId());
        log.info(" 👉👉 Produit supprimé ");
    }

    @EventHandler
    public void on(ProduitEtatChangedEvent event){
        Produit produit = produitRepository.findById(event.getId()).get();
        produit.setEtat( event.getEtat() );
        produitRepository.save(produit);
        log.info(" 👉👉 etat de produit changé   ");
    }


    @EventHandler
    public void on(ProduitCategorieChangedEvent event){
        Produit produit = produitRepository.findById(event.getId()).get();
        Categorie categorie = categorieRepository.findById(event.getCategorieId()).get();
        produit.setCategorie( categorie );
        produitRepository.save(produit);
        log.info(" 👉👉 Catégorie de produit changé   ");
    }

}
