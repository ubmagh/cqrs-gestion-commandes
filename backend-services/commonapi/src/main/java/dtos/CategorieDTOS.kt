package dtos



data class CreateCategorieDTO (
        val nom : String,
        val description : String,
)

data class UpdateCategorieDTO (
        val nom : String,
        val description : String,
)

data class ShortCategorieDTO (
        val id : String,
        val nom : String,
        val description : String,
)

data class LongCategorieDTO (
        val id:String,
        val nom : String,
        val description : String,
        val produits : List<ShortProduitDTO>
)