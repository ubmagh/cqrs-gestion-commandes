package me.ubmagh.inventoryservicequery.mappers;

import dtos.LongCategorieDTO;
import dtos.ShortCategorieDTO;
import me.ubmagh.inventoryservicequery.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    public ShortCategorieDTO toShortCategorie(Categorie categorie);
    public LongCategorieDTO toLongCategorie(Categorie categorie);


}
