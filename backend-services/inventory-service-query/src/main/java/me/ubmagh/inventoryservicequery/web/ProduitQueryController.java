package me.ubmagh.inventoryservicequery.web;

import dtos.produit.LongProduitDTO;
import dtos.produit.ShortProduitDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.inventoryservicequery.entities.Produit;
import me.ubmagh.inventoryservicequery.mappers.ProduitMapper;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import queries.GetAllProduitsQuery;
import queries.GetProduitByIdQuery;
import queries.GetProduitsByCategorieIdQuery;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class ProduitQueryController {

    private QueryGateway queryGateway;
    private ProduitMapper mapper;

    @GetMapping("/produits")
    public List<ShortProduitDTO> getAllProduits(){
        List<Produit> response = queryGateway.query(new GetAllProduitsQuery(), ResponseTypes.multipleInstancesOf(Produit.class)).join();
        List<ShortProduitDTO> dtos = response.stream().map(c->{
            log.info("+++++==========> id:  "+c.getId());
            return mapper.toShortProduit(c);
        }).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/produits/{id}")
    public LongProduitDTO getProduitById(@PathVariable String id){
        Produit response = queryGateway.query(new GetProduitByIdQuery(id), ResponseTypes.instanceOf(Produit.class)).join();
        return mapper.toLongProduit(response);
    }

    @GetMapping("/produitsByCategorie/{id}")
    public List<ShortProduitDTO> getProduitsByCategorieId(@PathVariable String id){
        List<Produit> response = queryGateway.query(new GetProduitsByCategorieIdQuery(id), ResponseTypes.multipleInstancesOf(Produit.class)).join();
        List<ShortProduitDTO> dtos = response.stream().map(c-> mapper.toShortProduit(c)).collect(Collectors.toList());
        return dtos;
    }

}
