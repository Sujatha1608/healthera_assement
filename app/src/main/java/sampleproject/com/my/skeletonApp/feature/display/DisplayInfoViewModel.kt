package sampleproject.com.my.skeletonApp.feature.display

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.rxjava3.kotlin.subscribeBy
import sampleproject.com.my.skeletonApp.core.event.SingleLiveEvent
import sampleproject.com.my.skeletonApp.rest.model.models.RemedyDataModel
import sampleproject.com.my.skeletonApp.rest.model.usecase.AdherencesUseCase
import sampleproject.com.my.skeletonApp.rest.model.usecase.RemediesUseCase
import javax.inject.Inject


class DisplayInfoViewModel @Inject constructor(private val adherencesUseCase: AdherencesUseCase, private val remediesUseCase: RemediesUseCase): ViewModel() {



    lateinit var callBack: ViewModelCallBack
    val errorEvent = MutableLiveData<String>()
    val loadingDialogEvent = SingleLiveEvent<Boolean>()

    var dataResultInfo = MutableLiveData<List<RemedyDataModel>>()
    val list = mutableListOf<RemedyDataModel>()
    lateinit var model: RemedyDataModel

    init {
        getAdherence()
        getRemedies()
    }
    interface ViewModelCallBack {
        fun updateRecyclerView(update: Boolean)

    }
    fun resultDetails():MutableLiveData<List<RemedyDataModel>>{
        return dataResultInfo
    }
    private fun getAdherence() {
        loadingDialogEvent.postValue(true)
        adherencesUseCase.execute()
            .subscribeBy(
                onSuccess = {
                    Timber.d { "api $it" }
                    loadingDialogEvent.postValue(false)


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
                    list.clear()
                    for (i in it.data!!) {
                        model = RemedyDataModel( id = i.remedyId!!, name = i.medicineName!!)
                        list.add(model)


                    }
                    resultDetails().value=list

                },
                onError = { e ->
                    errorEvent.postValue(e.message.toString())
                    loadingDialogEvent.postValue(false)

                }
            )
    }
    fun onLeftClick(){

    }
    fun onRightClick(){

    }

}