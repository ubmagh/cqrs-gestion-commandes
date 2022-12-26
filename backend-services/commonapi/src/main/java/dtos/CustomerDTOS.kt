package dtos


data class CreateCustomerDTO (
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
)

data class UpdateCustomerDTO (
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
)

