package dtos

import enums.EtatProduit


data class CreateProduitDTO (
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
)

data class UpdateProduitDTO (
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
)


data class ShortProduitDTO (
        val id: String,
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
)

data class LongProduitDTO (
        val id: String,
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
        val categorie :ShortCategorieDTO
)

data class ChangeEtatDTO (
        val id: String,
        val etat : EtatProduit,
        )

data class ChangeCategorieProduitDTO (
        val id: String,
        val categorieId : String,
)

