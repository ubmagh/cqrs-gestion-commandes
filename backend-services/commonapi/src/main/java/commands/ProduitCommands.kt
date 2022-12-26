package commands

import enums.EtatProduit

data class CreateProduitCommand(
        override val id : String,

        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
):BaseCommand<String>(id)

data class UpdateProduitCommand(
        override val id : String,

        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
):BaseCommand<String>(id)

data class DeleteProduitCommand(
        override val id : String,
):BaseCommand<String>(id)


data class ChangeEtatProduitCommand(
        override val id : String,
        val etat : EtatProduit,
):BaseCommand<String>(id)

data class ChangeCategorieProduitCommand(
        override val id : String,
        val categorieId : String,
):BaseCommand<String>(id)