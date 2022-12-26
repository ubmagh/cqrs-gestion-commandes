package me.ubmagh.inventoryservicequery.mappers;

import dtos.categorie.ShortCategorieDTO;
import me.ubmagh.inventoryservicequery.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    ShortCategorieDTO toShortCategorie(Categorie categorie);


}
