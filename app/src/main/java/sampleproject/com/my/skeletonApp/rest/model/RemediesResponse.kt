package sampleproject.com.my.skeletonApp.rest.model


import com.squareup.moshi.Json

data class RemediesResponse(
    @Json( name ="data")
    val data: List<RemedyData>? = null
)

data class Medicine(
    @Json( name ="discontinued_date")
    val discontinuedDate: String? = null,

    @Json( name ="prescription_charges")
    val prescriptionCharges: Int = 0,

    @Json( name ="medicine_id")
    val medicineId: String? = null,

    @Json( name ="generic_name")
    val genericName: String? = null,

    @Json( name ="nhs_price")
    val nhsPrice: Int = 0,

    @Json( name ="course_quantity")
    val courseQuantity: Int = 0,

    @Json( name ="ampp_name")
    val amppName: String? = null,

    @Json( name ="amp_id")
    val ampId: String? = null,

    @Json( name ="medicine_name")
    val medicineName: String? = null,

    @Json( name ="pip_code")
    val pipCode: Any? = null,

    @Json( name ="controlled")
    val controlled: Boolean = false,

    @Json( name ="ampp_id")
    val amppId: String? = null,

    @Json( name ="name")
    val name: String? = null,

    @Json( name ="_id")
    val id: String? = null,

    @Json( name ="vmpp_id")
    val vmppId: String? = null,

    @Json( name ="nhs_price_date")
    val nhsPriceDate: String? = null,

    @Json( name ="gtin")
    val gtin: List<String?>? = null,

    @Json( name ="start_date")
    val startDate: String? = null
)

data class ScheduleItem(
    @Json( name ="dose_max")
    val doseMax: Int = 0,

    @Json( name ="dose_min")
    val doseMin: Int = 0,

    @Json( name ="day_iterator")
    val dayIterator: Int = 0,

    @Json( name ="day_offset")
    val dayOffset: Any? = null,

    @Json( name ="alarm_window")
    val alarmWindow: Int = 0
)

data class RemedyData(
    @Json( name ="end_date")
    val endDate: Long = 0,

    @Json( name ="medicine_type")
    val medicineType: String? = null,

    @Json( name ="allow_edit")
    val allowEdit: Boolean = false,

    @Json( name ="medicine_id")
    val medicineId: String? = null,

    @Json( name ="date_created")
    val dateCreated: Long = 0,

    @Json( name ="repeat_cycle")
    val repeatCycle: Int = 0,

    @Json( name ="medicine")
    val medicine: Medicine? = null,

    @Json( name ="can_request")
    val canRequest: Boolean = false,

    @Json( name ="medicine_name")
    val medicineName: String? = null,

    @Json( name ="reorder_timestamp")
    val reorderTimestamp: Long = 0,

    @Json( name ="remedy_id")
    val remedyId: String? = null,

    @Json( name ="schedule")
    val schedule: List<Any?>? = null,

    @Json( name ="is_ongoing")
    val isOngoing: Boolean = false,

    @Json( name ="date_modified")
    val dateModified: Long = 0,

    @Json( name ="patient_id")
    val patientId: String? = null,

    @Json( name ="instruction")
    val instruction: String? = null,

    @Json( name ="special_instruction")
    val specialInstruction: Any? = null,

    @Json( name ="price")
    val price: Double? = null,

    @Json( name ="_id")
    val id: String? = null,

    @Json( name ="is_current")
    val isCurrent: Boolean = false,

    @Json( name ="start_date")
    val startDate: Long = 0
)
