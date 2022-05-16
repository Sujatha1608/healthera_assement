package sampleproject.com.my.skeletonApp.rest.model.models


data class RemedyDataModel(
    val id: String,

    val dateCreated: String = "",

    val isOnGoing: Boolean = false,

    val name: String = "",

    val dateStart: String = "",

    val dateEnd: String = "",

    val desc: String? = null
)
