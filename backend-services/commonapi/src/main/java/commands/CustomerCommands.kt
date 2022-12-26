package commands

data class CreateCustomerCommand(
        override val id : String,
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
):BaseCommand<String>(id)


data class UpdateCustomerCommand(
        override val id : String,
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
):BaseCommand<String>(id)



data class DeleteCustomerCommand(
        override val id : String,
):BaseCommand<String>(id)
