package co.uk.healthera.healthera.domain.model


data class RemedyDataModel(
    val id: String,

    val dateCreated: String = "",

    val isOnGoing: Boolean = false,

    val name: String = "",

    val dateStart: String = "",

    val dateEnd: String = "",

    val desc: String? = null
)
