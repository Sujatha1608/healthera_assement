package sampleproject.com.my.skeletonApp.feature.display

import androidx.lifecycle.MutableLiveData
import sampleproject.com.my.skeletonApp.AppPreference
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import io.reactivex.rxjava3.kotlin.subscribeBy
import sampleproject.com.my.skeletonApp.core.event.SingleLiveEvent
import sampleproject.com.my.skeletonApp.rest.model.usecase.AdherencesUseCase
import sampleproject.com.my.skeletonApp.rest.model.usecase.RemediesUseCase
import javax.inject.Inject


class DisplayInfoViewModel @Inject constructor(private val adherencesUseCase: AdherencesUseCase, private val remediesUseCase: RemediesUseCase): ViewModel() {



    lateinit var callBack: ViewModelCallBack
    val errorEvent = MutableLiveData<String>()
    val loadingDialogEvent = SingleLiveEvent<Boolean>()

    var dataResultInfo: MutableList<DataResultResponse> = mutableListOf()
    val list = mutableListOf<DataResultResponse>()

    init {
        getAdherence()
        getRemedies()
    }
    interface ViewModelCallBack {
        fun updateRecyclerView(update: Boolean)

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

                },
                onError = { e ->
                    errorEvent.postValue(e.message.toString())
                    loadingDialogEvent.postValue(false)

                }
            )
    }
}