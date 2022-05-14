package sampleproject.com.my.skeletonApp.rest.model


import com.squareup.moshi.Json

data class AdherencesResponse(
	@Json( name ="data")
	val data: List<AdherenceData>? = null
)

data class AdherenceData(
	@Json( name ="remedy_id")
	val remedyId: String? = null,

	@Json( name ="note")
	val note: String? = null,

	@Json( name ="dose_discrete")
	val doseDiscrete: String? = null,

	@Json( name ="adherence_id")
	val adherenceId: String? = null,

	@Json( name ="action_time")
	val actionTime: Long? = null,

	@Json( name ="dose_quantity")
	val doseQuantity: Int = 0,

	@Json( name ="patient_id")
	val patientId: String? = null,

	@Json( name ="alarm_time")
	val alarmTime: Long = 0,

	@Json( name ="action")
	val action: String? = null,

	@Json( name ="_id")
	val id: String? = null
)
