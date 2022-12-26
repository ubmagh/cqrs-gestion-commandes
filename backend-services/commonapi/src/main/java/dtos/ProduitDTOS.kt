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


data class ChangeEtatDTO (
        val id: String,
        val etat : EtatProduit,
        )

data class ChangeCategorieProduitDTO (
        val id: String,
        val categorieId : String,
)

