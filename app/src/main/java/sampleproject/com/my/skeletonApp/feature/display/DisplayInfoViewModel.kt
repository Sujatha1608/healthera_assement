package sampleproject.com.my.skeletonApp.feature.display

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.rxjava3.kotlin.subscribeBy
import sampleproject.com.my.skeletonApp.core.event.SingleLiveEvent
import sampleproject.com.my.skeletonApp.rest.model.models.RemedyDataModel
import sampleproject.com.my.skeletonApp.rest.model.usecase.AdherencesUseCase
import sampleproject.com.my.skeletonApp.rest.model.usecase.RemediesUseCase
import sampleproject.com.my.skeletonApp.utilities.ObservableString
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DisplayInfoViewModel @Inject constructor(private val adherencesUseCase: AdherencesUseCase, private val remediesUseCase: RemediesUseCase): ViewModel() {


    lateinit var callBack: ViewModelCallBack
    val errorEvent = MutableLiveData<String>()
    val loadingDialogEvent = SingleLiveEvent<Boolean>()

    var dataResultInfo = MutableLiveData<List<RemedyDataModel>>()
    var adherenceListLiveData = MutableLiveData<MutableSet<String?>>()
    val datesList: MutableSet<String?> = HashSet()
    val displayListData: MutableMap<String, MutableList<RemedyDataModel>> = HashMap()
    val remediesList: MutableList<RemedyDataModel> = ArrayList()
    var displayDate = ObservableString("")

    init {
        getAdherence()
        getRemedies()
    }

    interface ViewModelCallBack {
        fun updateRecyclerView(update: Boolean)
        fun onLeftClick()
        fun onRightClick()
        fun closeClick()

    }

    fun resultDetails(): MutableLiveData<List<RemedyDataModel>> {
        return dataResultInfo
    }

    fun adherenceDetails(): MutableLiveData<MutableSet<String?>> {
        return adherenceListLiveData
    }

    private fun getAdherence() {
        loadingDialogEvent.postValue(true)
        adherencesUseCase.execute()
            .subscribeBy(
                onSuccess = {
                    Timber.d { "api $it" }
                    loadingDialogEvent.postValue(false)
                    for (i in it.data!!) {
                        datesList.add(i.patientId)
                    }
                    adherenceListLiveData.value = datesList
                },
                onError = { e ->
                    errorEvent.postValue(e.message.toString())
                    loadingDialogEvent.postValue(false)

                }
            )
    }
    private fun getRemedies() {
        loadingDialogEvent.postValue(true)
        remediesUseCase.execute()
            .subscribeBy(
                onSuccess = {
                    Timber.d { "api $it" }
                    loadingDialogEvent.postValue(false)
                    remediesList.clear()
                    for (i in it.data!!) {
                        val model = RemedyDataModel(
                            id = i.patientId,
                            i.dateCreated,
                            false,
                            i.medicineName,
                            i.startDate,
                            i.endDate,
                            getDate(i.dateCreated),
                            getDate(i.startDate),
                            getDate(i.endDate),
                            getTime(i.dateCreated)
                        )
                        remediesList.add(model)
                    }
                    resultDetails().value = remediesList
                },
                onError = { e ->
                    errorEvent.postValue(e.message.toString())
                    loadingDialogEvent.postValue(false)

                }
            )
    }

    private fun getDate(epoc: Long): String {
        return try {
            val sdf = SimpleDateFormat("dd MMMM yyyy")
            val netDate = Date(epoc * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
            ""
        }
    }

    private fun getTime(epoc: Long): String {
        return try {
            val sdf = SimpleDateFormat("hh:mm a")
            val netDate = Date(epoc * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
            ""
        }
    }
    fun onPreviousClick(){
        callBack.onLeftClick()
    }
    fun onNextClick(){
        callBack.onRightClick()
    }
    fun onCloseClick(){
        callBack.closeClick()
    }

}