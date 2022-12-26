package me.ubmagh.inventoryservicequery.services;

import events.CategorieCreatedEvent;
import events.CategorieDeletedEvent;
import events.CategorieUpdatedEvent;
import events.ProduitCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.inventoryservicequery.entities.Categorie;
import me.ubmagh.inventoryservicequery.entities.Produit;
import me.ubmagh.inventoryservicequery.repositories.CategorieRepository;
import me.ubmagh.inventoryservicequery.repositories.ProduitRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import queries.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CategorieService {

    private CategorieRepository categorieRepository;


    @QueryHandler
    public List<Categorie> getAllCategoriesList(GetAllCategoriesQuery query){
        return categorieRepository.findAll();
    }

    @QueryHandler
    public Categorie getCategorieById(GetCategorieByIdQuery query){
        return categorieRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public Categorie getCategorieByNom(GetCategorieByNomQuery query){
        return categorieRepository.findByNomEquals(query.getNom()).get();
    }

    @EventHandler
    public void on(CategorieCreatedEvent event){
        Categorie categorie = new Categorie(
                event.getId(),
                event.getNom(),
                event.getDescription(),
                null
        );
        categorieRepository.save(categorie);
        log.info(" 👉👉 Categorie créée ");
    }


    @EventHandler
    public void on(CategorieUpdatedEvent event){
        Categorie categorie = categorieRepository.findById(event.getId()).get();
        categorie.setNom( event.getNom());
        categorie.setDescription( event.getDescription() );
        categorieRepository.save(categorie);
        log.info(" 👉👉 Categorie mise--à-jour ");
    }


    @EventHandler
    public void on(CategorieDeletedEvent event){
        categorieRepository.deleteById(event.getId());
        log.info(" 👉👉 Categorie supprimée ");
    }


}
