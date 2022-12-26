package me.ubmagh.inventoryservicequery.mappers;

import dtos.produit.LongProduitDTO;
import dtos.produit.ShortProduitDTO;
import me.ubmagh.inventoryservicequery.entities.Produit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    public ShortProduitDTO toShortProduit( Produit produit);
    public LongProduitDTO toLongProduit( Produit produit);


}
