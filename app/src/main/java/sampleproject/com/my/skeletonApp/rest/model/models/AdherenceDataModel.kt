package co.uk.healthera.healthera.domain.model


data class AdherenceDataModel(
    val id: String,

    val remedyId: String,

    val alarmTime: String = "",

    val alarmDate: String = "",

    val actionTime: String = "",

    val action: String? = null,

    val doseQuantity: Int = 0,

    val remedy: RemedyDataModel? = null
)