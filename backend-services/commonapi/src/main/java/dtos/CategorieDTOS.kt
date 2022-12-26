package dtos


data class CreateCategorieDTO (
        val nom : String,
        val description : String,
)

data class UpdateCategorieDTO (
        val nom : String,
        val description : String,
)
