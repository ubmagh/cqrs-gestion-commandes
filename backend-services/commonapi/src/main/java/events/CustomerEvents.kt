package events



data class CustomerCreatedEvent(
        override val id : String,
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
):BaseEvent<String>(id)



data class CustomerUpdatedEvent(
        override val id : String,
        val nom : String,
        val email : String,
        val tel : String,
        val adresse : String,
):BaseEvent<String>(id)

data class CustomerDeletedEvent(
        override val id : String,
):BaseEvent<String>(id)




