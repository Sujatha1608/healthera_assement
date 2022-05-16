package sampleproject.com.my.skeletonApp.rest.model.models


data class RemedyDataModel(
    val id: String? = null,
    val dateCreated: Long,
    val isOnGoing: Boolean = false,
    val name: String? = null,
    val dateStart: Long,
    val dateEnd: Long,
    val desc: String? = null,
    val dateCreatedStr: String? = null,
    val dateStartStr: String? = null,
    val alarmDate: String? = null
)
