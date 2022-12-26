package me.ubmagh.inventoryservicequery.web;

import dtos.categorie.ShortCategorieDTO;
import lombok.AllArgsConstructor;
import me.ubmagh.inventoryservicequery.mappers.CategorieMapper;
import me.ubmagh.inventoryservicequery.entities.Categorie;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import queries.GetAllCategoriesQuery;
import queries.GetCategorieByIdQuery;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CategorieQueryController {

    private QueryGateway queryGateway;
    private CategorieMapper mapper;

    @GetMapping("/categories")
    public List<ShortCategorieDTO> getAllCategories(){
        List<Categorie> response = queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(Categorie.class)).join();
        List<ShortCategorieDTO> dtos = response.stream().map( c -> mapper.toShortCategorie(c)).collect(Collectors.toList());
        return dtos;
    }


    @GetMapping("/categories/{id}")
    public ShortCategorieDTO getCategorieById(@PathVariable String id){
        Categorie response = queryGateway.query(new GetCategorieByIdQuery(id), ResponseTypes.instanceOf(Categorie.class)).join();
        return mapper.toShortCategorie(response);
    }

}
