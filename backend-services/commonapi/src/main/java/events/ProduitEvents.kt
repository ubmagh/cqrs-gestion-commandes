package events

import enums.EtatProduit


data class ProduitCreatedEvent(
        override val id : String,
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
):BaseEvent<String>(id)



data class ProduitUpdatedEvent(
        override val id : String,
        val nom : String,
        val prix : Double,
        val quantite : Int,
        val etat : EtatProduit,
):BaseEvent<String>(id)

data class ProduitDeletedEvent(
        override val id : String,
):BaseEvent<String>(id)

data class ProduitEtatChangedEvent(
        override val id : String,
        val etat : EtatProduit,
):BaseEvent<String>(id)


data class ProduitCategorieChangedEvent(
        override val id : String,
        val categorieId : String,
):BaseEvent<String>(id)
