package commands

import enums.EtatProduit

data class CreateCategorieCommand(
        override val id : String,

        val nom : String,
        val description : String,
):BaseCommand<String>(id)


data class UpdateCategorieCommand(
        override val id : String,

        val nom : String,
        val description : String,
):BaseCommand<String>(id)



data class DeleteCategorieCommand(
        override val id : String,
):BaseCommand<String>(id)
