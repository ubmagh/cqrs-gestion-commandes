package events



data class CategorieCreatedEvent(
        override val id : String,
        val nom : String,
        val description : String,
):BaseEvent<String>(id)


data class CategorieUpdatedEvent(
        override val id : String,
        val nom : String,
        val description : String,
):BaseEvent<String>(id)

data class CategorieDeletedEvent(
        override val id : String,
):BaseEvent<String>(id)




